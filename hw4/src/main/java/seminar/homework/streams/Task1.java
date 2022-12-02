package seminar.homework.streams;

import seminar.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task1 {

    public Map<String, Integer> getMaxAgeByUserName(List<User> users) {
        return users.stream()
                .collect(Collectors.toMap(User::getName, User::getAge, Math::max));
    }
}
