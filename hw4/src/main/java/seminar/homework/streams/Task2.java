package seminar.homework.streams;

import seminar.User;

import java.util.*;

public class Task2 {
    private final Set<User> users = new HashSet<>();

    public int getTotalAge() {
        return users.stream()
                .mapToInt(User::getAge)
                .sum();
    }

    public int countEmployees(Map<String, List<User>> departments) {
        return departments.values().stream()
                .mapToInt(List::size)
                .sum();
    }
}
