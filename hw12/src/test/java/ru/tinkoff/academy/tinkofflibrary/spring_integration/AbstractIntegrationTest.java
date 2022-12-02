package ru.tinkoff.academy.tinkofflibrary.spring_integration;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import ru.tinkoff.academy.tinkofflibrary.Containers;

@SpringBootTest
@ExtendWith(Containers.class)
public class AbstractIntegrationTest {

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", Containers.postgre::getJdbcUrl);
        registry.add("spring.datasource.password", Containers.postgre::getPassword);
        registry.add("spring.datasource.username", Containers.postgre::getUsername);
    }

}
