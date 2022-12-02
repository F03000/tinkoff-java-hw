package seminar;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {
    private final List<Transaction> transactions = new ArrayList<>();

    /**
     * дез использования стримов
     */
    void classicTransaction() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();

        for (Transaction transaction : transactions) {
            Currency currency = transaction.getCurrency();
            List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
            if (transactionsForCurrency == null) {
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }
    }

    void streamTransaction() {
        Map<Currency, List<Transaction>> transactionsByCurr = transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency));
    }

    /**
     * стрим состоит из трёх видов операций: создания, промежуточных, терминальных
     */
    void exampleStream() {
        //Создание стрима
        List<String> names = transactions.stream()
                //Промежуточные операции
                .filter(d -> d.getNumber() > 300)
                .map(Transaction::getCurrency)
                .map(Currency::getName)
                .limit(3)
                //Терминальная операция
                .collect(Collectors.toList());
    }

    /**
     * методы создания стримов
     */
    void createStream() throws IOException {
        //Пустого
        Stream<Object> stream0 = Stream.empty();

        //Перечислением элементов
        Stream<String> stream1 = Stream.of("gently", "down", "the", "stream");
        //Из массива
        Stream<String> stream2 = Arrays.stream("gently down the stream".split(" "));
        //Из коллекции
        List<String> strings = Arrays.asList("sup1", "sup2", "sup3");
        Stream<String> stream3 = strings.stream();

        //Из API
        Path path = Paths.get(".");
        Stream<Path> stream4 = Files.list(path);
    }
    
    void concatStream() {
        Stream<Object> s1 = Stream.of("gently", "down", "the", "stream");
        Stream<Object> s2 = Arrays.stream("gently down the stream".split(" "));
        Stream<Object> s = Stream.concat(s1, s2);
    }

    void generateStream() {
        //С помощью генератора
        Stream<Double> randoms = Stream.generate(Math::random);
        //ХОТЯ ЛУЧШЕ
        DoubleStream doubles = ThreadLocalRandom.current().doubles();
    }

    void iterateStream() {
        //Итеративно
        Stream<Integer> integers = Stream.iterate(0, x -> x + 1);
        //ХОТЯ ЛУЧШЕ
        IntStream range = IntStream.range(0, 1000);
    }

    void mapStream() {
//        <R> Stream<R> map(Function<? super T, ? extends R> mapper);
//        IntStream mapToInt(ToIntFunction<? super T> mapper);
//        LongStream mapToLong(ToLongFunction<? super T> mapper);
//        DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper);
    }


    /**
     * для всех файлов в директории получить поток слов в каждом из них
     */
    void flatMapStream() throws IOException {
        Path path = Paths.get(".");
        Pattern separator = Pattern.compile("\\s");
        try(Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            //НЕ ТО, что нам надо!
            //Stream<Stream<String>> streamStream = lines.map(separator::splitAsStream);

            //ТО, что нам надо!
            Stream<String> words = lines.flatMap(separator::splitAsStream);
            words.forEach(System.out::println);
        }

        /*А также:
          flatMapToDouble
          flatMapToInt
          flatMapToLong
          */
    }

    /**
     * терминальные операции - коллекторы
     */
    void collectors() {
//        //java.util.stream.Collectors
//        Collector<T, ?, List<T>> toList()
//        Collector<T, ?, Set<T>> toSet()
//        Collector<T, ?, C extends Collection<T>>
//        toCollection(Supplier<C> collectionFactory)

        //Пример применения
        Stream<String> myStream = Stream.of("gently", "down", "the", "stream");
        List<String> list = myStream.collect(Collectors.toList());


//        Collector<T, ?, Map<K,U>> toMap(
//                Function<? super T, ? extends K> keyMapper,
//                Function<? super T, ? extends U> valueMapper)
//
//        //Пример применения
        Stream<String> people = Stream.of("gently", "down", "the", "stream");
        Map<Integer, String> lenToStrings = people.collect(Collectors.toMap(String::length, p -> p));
    }

    void distinctStream() {
        Stream.of(1, 5, 8, 7, 8, 5, 9, 9)
                .distinct()
                .forEach(System.out::println);
    }

    void sortedStream() {
        Stream.of(1, 5, 8, 7, 8, 5, 9, 9)
                .sorted()
                .forEach(System.out::println);
    }

    void terminalOperations() {
        //Предъяви первый элемент
//        Optional<T> findFirst();
        //Предъяви любой элемент
//        Optional<T> findAny();

//        //Проверь, удовлетворяет ли условию...
//        //...какой-то
//        boolean anyMatch(Predicate<? super T> predicate);
//        //...все
//        boolean allMatch(Predicate<? super T> predicate);
//        //...никакой
//        boolean noneMatch(Predicate<? super T> predicate);

    }


    private class Currency {
        private String name;

        public String getName() {
            return name;
        }
    }

    private class Transaction {
        private Integer number;
        private Currency currency;

        public Currency getCurrency() {
            return currency;
        }

        public Integer getNumber() {
            return number;
        }
    }
}
