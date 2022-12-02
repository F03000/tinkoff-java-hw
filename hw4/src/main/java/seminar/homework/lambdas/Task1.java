package seminar.homework.lambdas;

import seminar.Permission;
import seminar.User;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class Task1 {
    private final Set<User> users = new HashSet<>();

    public void removeUsersWithPermission(Permission permission) {
        users.removeIf(removeUserPredicate(permission));
    }

    private Predicate<User> removeUserPredicate(Permission permission) {
        return user -> user.getRoles().stream()
                .anyMatch(role -> role.getPermissions().contains(permission));
    }
}