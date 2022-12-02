package seminar;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Comparator;

public class Lambda {
    static class ActionEvent {
        String name = "";
    }

    public interface ActionListener {
        void actionPerformed(ActionEvent event);
    }

    /**
     * что использовали до лямбд
     */
    void createTimer() {
        String text = "...";
        var timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                //'text' var is available here
            }
        });
    }

    void sort() {
        String[] friends = {"Peter", "Paul", "Mary"};

        Arrays.sort(friends,
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.length() - o2.length();
                    }
                });
    }

    void predicat() {
        File[] hiddenFiles = new File(".").listFiles(
                new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        return file.isHidden();
                    }
                }
        );
    }

    /**
     * способы использования лямбд
     */
    void lambdas() {
        //single-line
        Comparator<String> result1 = (String first, String second) ->
                first.length() - second.length();

        //multi-line with int return

        Comparator<String> result2 = (String first, String second) -> {
                    if (first.length() < second.length()) return -1;
                    else if (first.length() > second.length()) return 1;
                    else return 0;
                };

        //no-arg, void return
        Runnable runnable = () -> System.out.println("Hello!");
    }

    /**
     * какие лямбды корректны? правильный ответ 1-3
     */
    void check() {
//        1. () → {}
//
//        2. () → "Raoul"
//
//        3. () → {return "Mario";}
//
//        4. (Integer i) → return "Alan" + i
//
//        5. (String s) → {"Iron Man"}
    }


    /**
     * замыкание
     */
    void repeatMessage(String text, int delay) {
        ActionListener  listener = event -> {
            //переменная text доступна внутри лямбды!
            System.out.println(text);
        };
        new Timer(delay, listener).start();

    }

}
