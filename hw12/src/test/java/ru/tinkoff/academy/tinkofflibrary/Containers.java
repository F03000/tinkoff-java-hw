package ru.tinkoff.academy.tinkofflibrary;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.RemoteDockerImage;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.utility.DockerImageName;

import java.net.URI;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.Future;

public class Containers implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {
    public static final String POSTGRE_USERNAME = "postgres";
    public static final String POSTGRE_DATABASE = "postgres";

    private static final Network network = Network.newNetwork();

    public static Flyway flyway;

    public static PostgreSQLContainer postgre = new PostgreSQLContainer<>("postgres:latest")
            .withNetwork(network)
            .withNetworkAliases("postgre")
            .withDatabaseName(POSTGRE_DATABASE)
            .withUsername(POSTGRE_USERNAME)
            .withExposedPorts(5432)
            .withStartupTimeout(Duration.ofMinutes(10))
            .withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger(PostgreSQLContainer.class)));

    public static final AppContainer appContainer = new AppContainer()
            .withNetwork(network)
            .withNetworkAliases("app")
            .withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger(AppContainer.class)));

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        if (extensionContext.getRoot().getStore(ExtensionContext.Namespace.GLOBAL).get("Containers") == null) {
            network.getId();

            postgre.start();
            migrateSchemaWithPrimaryData();

            appContainer.start();
            extensionContext.getRoot().getStore(ExtensionContext.Namespace.GLOBAL).put("Containers", "started");
        }
    }

    @Override
    public void close() {
        appContainer.stop();
        postgre.stop();
        network.close();
    }

    private static void migrateSchemaWithPrimaryData() {
        flyway = Flyway.configure()
                .locations("classpath:db/migration")
                .dataSource(postgre.getJdbcUrl(), POSTGRE_USERNAME, postgre.getPassword()).load();

        flyway.migrate();
    }

    public static class AppContainer extends GenericContainer<AppContainer> {
        private static final int HTTP_PORT = 8080;

        public AppContainer() {
            super(image());
        }

        @Override
        protected void configure() {
            super.configure();
            withExposedPorts(HTTP_PORT);
            waitingFor(Wait.forHttp("/actuator/health").forPort(HTTP_PORT));
            withEnv("DB_URL", "jdbc:postgresql://postgre:5432/" + POSTGRE_DATABASE);
            withEnv("DB_PASSWORD", postgre.getPassword());
            withEnv("DB_USERNAME", POSTGRE_USERNAME);
        }

        private static Future<String> image() {
            var remoteImage = System.getenv("TEST_IMAGE");
            if (remoteImage != null) {
                return new RemoteDockerImage(DockerImageName.parse(remoteImage));
            } else {
                var path = Paths.get(System.getProperty("user.dir"), "Dockerfile");
                return new ImageFromDockerfile("library-jpa-test", true).withDockerfile(path);
            }
        }

        public URI getURI() {
            return URI.create("http://" + getHost() + ":" + getMappedPort(8080));
        }

    }

}
