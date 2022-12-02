package task2;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task2 {
    public Map<Integer, Integer> countNumbers(List<Integer> inputList) {
        return inputList
                .stream()
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                (x) -> 1,
                                Integer::sum))
                .entrySet()
                .stream()
                .filter(x -> x.getValue() != 1)
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue));
    }
}
