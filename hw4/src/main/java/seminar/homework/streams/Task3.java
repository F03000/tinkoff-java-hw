package seminar.homework.streams;


import seminar.User;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.IntStream;

public class Task3 {
    public void printNameStats(List<User> users) {
        IntSummaryStatistics summaryStatistics = getNameLengthStream(users).summaryStatistics();
        if (summaryStatistics.getCount() > 0) {
            System.out.println("MAX: " + summaryStatistics.getMax());
            System.out.println("MAX: " + summaryStatistics.getCount());
        }
    }

    private IntStream getNameLengthStream(List<User> users) {
        return users.stream()
                .mapToInt(user -> user.getName().length());
    }
}
