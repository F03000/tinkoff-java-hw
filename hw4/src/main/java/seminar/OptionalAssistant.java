package seminar;

import java.util.Optional;

public class OptionalAssistant {
    int THRESHOLD = 10;
    static class Company {
        private User ceo = new User();
        private int numberOfEmployees;

        public int getNumberOfEmployees() {
            return numberOfEmployees;
        }

        public Optional<User> getCeo() {
            return Optional.ofNullable(ceo);
        }

        public void setUsers(User user) {
            this.ceo = user;
        }

        Optional<Object> someMethod() {
            return Optional.empty();
        }
    }

    Optional<Object> calcOther() {
        return Optional.empty();
    }


    /**
     * методы создания Optional
     */
    void createOptional() {
        Object x = new Object();
        Object other = new Object();
        Optional<Object> o = null;
        //Создание
        o = Optional.empty(); //пустой
        o = Optional.of(x); //NPE если x == null
        o = Optional.ofNullable(x); //пустой или с x-ом

        //Расчехление
        o.get();
        o.orElse(other);
        o.orElseGet(()->calcOther());
        o.orElseThrow(()->new IllegalStateException());
    }

    /**
     * обратите внимание на отличие map от flatMap
     */
    void map(User user) {
        Optional<User> optUser = Optional.ofNullable(user);
        Optional<String> name = optUser.map(User::getName);
    }

    void flatMap(Role role) {
        Optional<Company> company = Optional.empty();

        //company.map(Company::getUser) вернёт Optional<Optional<User>>!!

        String carName = company.flatMap(Company::getCeo)
                .flatMap(User::getCar)
                .map(User.Car::getName)
                .orElse("Unknown");
    }

    void filter() {
        Optional<Company> company = Optional.empty();

        String carName = company.filter(p -> p.getNumberOfEmployees() >= THRESHOLD)
                .flatMap(Company::getCeo)
                .flatMap(User::getCar)
                .map(User.Car::getName)
                .orElse("Unknown");
    }

    void getOpt() {
        Optional<Company> opt = Optional.empty();
        if (opt.isPresent())
            opt.get().someMethod();

        Company company = new Company();
        if (company != null)
            company.someMethod();
    }
}
