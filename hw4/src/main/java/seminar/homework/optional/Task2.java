package seminar.homework.optional;

import seminar.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Task2 {
    private static final String ADMIN_ROLE = "admin";

    public User findAnyAdmin() {
        Optional<List<User>> users = findUsersByRole(ADMIN_ROLE);
        return users
                .filter(Predicate.not(List::isEmpty))
                .map(userList -> userList.get(0))
                .orElseThrow(() -> new IllegalStateException("No admins found"));
    }

    private Optional<List<User>> findUsersByRole(String role) {
        //real search in DB
        return Optional.empty();
    }


}
