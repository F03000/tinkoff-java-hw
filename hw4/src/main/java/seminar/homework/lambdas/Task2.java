package seminar.homework.lambdas;

import seminar.Role;
import seminar.User;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.function.Consumer;

public class Task2 {
    private final Map<String, Set<User>> usersByRole = new HashMap<>();

    public void addUser(User user) {
        user.getRoles().forEach(addUserIfHasRoleConsumer(user));
    }

    private Consumer<Role> addUserIfHasRoleConsumer(User user) {
        return role -> usersByRole
            .computeIfAbsent(role.getName(), x -> new HashSet<>())
            .add(user);
    }

    public Set<User> getUsersInRole(String role) {
        return usersByRole.getOrDefault(role, Collections.emptySet());
    }

}
