package task1;

import java.util.List;

public class Task1 {
    public List<Integer> square56(List<Integer> inputList) {
        return inputList
                .stream()
                .map(x -> x * x + 10)
                .filter(x -> (x % 10 != 5) && (x % 10 != 6))
                .toList();
    }
}
