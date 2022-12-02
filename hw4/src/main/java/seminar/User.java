package seminar;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class User {
    private Long id;
    private String name;
    private int age;
    private Set<Role> roles = new HashSet<>();

    private Car car;

    public User(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User() {
        this.id = 0L;
        this.name = "name";
        this.age = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public static class Car {
        String name;

        public String getName() {
            return name;
        }
    }

    public Optional<Car> getCar() {
        return Optional.of(this.car);
    }
}
