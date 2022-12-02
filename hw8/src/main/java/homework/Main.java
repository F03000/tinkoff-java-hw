package homework;

import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


/**
 * В этой домашней работе вам необходимо найти в большом txt файле с книгами данные об авторах и названиях произведений.
 * Сделать это необходимо двумя способами: обычным последовательным и реактивным, с использованием библиотеки reactor.core
 * При этом нужно собрать статистику какой из подходов более эффективен по расходу ресурсов.
 * Советую обратить внимание на доклад:
 * <p><a href="https://www.youtube.com/watch?v=tjp8pTOyiWg">Максим Гореликов — Дизайн реактивной системы на Spring 5/Reactor</a>
 * <p>Данные о задействованных ресурсах можно собрать к примеру с помощью {@link Runtime}.</p>
 * <p>Для RAM: {@link Runtime#totalMemory()} и {@link Runtime#freeMemory()}</p>
 */
public class Main {
    private static final String AUTHOR_STRING_PREFIX = "Author: ";
    private static final String TITLE_STRING_PREFIX = "Title: ";
    private static final int FIRST_SYMBOL_AFTER_AUTHOR_INDEX = 8;
    private static final int FIRST_SYMBOL_AFTER_TITLE_INDEX = 7;

    public static void main(String[] args) {
        Path path = getFilePath();
        Main main = new Main();
//        main.reactiveVersion(path);
        main.listVersion(path);
    }

    private static Path getFilePath() {
        String classpath = System.getProperty("java.class.path");
        String[] classpathEntries = classpath.split(File.pathSeparator);
        String resourcesPath = classpathEntries[1];
        return Paths.get(resourcesPath, "books.txt");
    }

    private void reactiveVersion(Path path) {
        long startTime = System.nanoTime();

        Flux<String> stringFlux = Flux.using(
                () -> Files.lines(path),
                Flux::fromStream,
                Stream::close
        );

        List<String> valuableStrings = stringFlux.filter(this::isStingValuable).collectList().block();
        Map<String, List<String>> authorToTitles = new HashMap<>();

        if (valuableStrings == null) {
            System.out.println("No valuable strings found");
            return;
        }

        String currentTitle = null;
        for (String str : valuableStrings) {
            currentTitle = fillMapWithData(authorToTitles, currentTitle, str);
        }
        printResults(startTime, authorToTitles);
    }

    private void printResults(long startTime, Map<String, List<String>> authorToTitles) {
        for (Map.Entry<String, List<String>> entry : authorToTitles.entrySet()) {
            entry.getValue().forEach(title -> System.out.println(entry.getKey() + " : " + title));
        }

        Runtime currentRuntime = Runtime.getRuntime();
        System.out.println("Memory used: " + (currentRuntime.totalMemory() - currentRuntime.freeMemory()) / 1024 + "KB");

        long finishTime = System.nanoTime();
        System.out.println(TimeUnit.NANOSECONDS.toMillis(finishTime - startTime));
    }

    private void putDataToMap(Map<String, List<String>> authorToTitles, String currentTitle, String currentAuthor) {
        if (authorToTitles.containsKey(currentAuthor)) {
            authorToTitles.get(currentAuthor).add(currentTitle);
        } else {
            authorToTitles.put(currentAuthor, new ArrayList<>(Collections.singleton(currentTitle)));
        }
    }

    private String fillMapWithData(Map<String, List<String>> authorToTitles, String currentTitle, String str) {
        String currentAuthor;
        if (str.startsWith(TITLE_STRING_PREFIX)) {
            currentTitle = str.substring(FIRST_SYMBOL_AFTER_TITLE_INDEX);
        } else if (str.startsWith(AUTHOR_STRING_PREFIX)) {
            currentAuthor = str.substring(FIRST_SYMBOL_AFTER_AUTHOR_INDEX);
            putDataToMap(authorToTitles, currentTitle, currentAuthor);
        }
        return currentTitle;
    }


    private boolean isStingValuable(String s) {
        return s.startsWith(TITLE_STRING_PREFIX) || s.startsWith(AUTHOR_STRING_PREFIX);
    }

    private void listVersion(Path path) {
        long startTime = System.nanoTime();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toString()))) {
            String currentLine;
            Map<String, List<String>> authorToTitles = new HashMap<>();

            String currentTitle = null;
            while ((currentLine = bufferedReader.readLine()) != null) {
                currentTitle = fillMapWithData(authorToTitles, currentTitle, currentLine);
            }

            printResults(startTime, authorToTitles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}