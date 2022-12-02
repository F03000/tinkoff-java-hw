package passgen;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PasswordGeneratorSettings passwordGeneratorSettings = new PasswordGeneratorSettings();

        System.out.println("Welcome to UniqueMemorizablePasswordGenerator");
        System.out.println("Do you want to have digits in password? (y if yes):");
        if (scanner.nextLine().toLowerCase(Locale.ROOT).equals("y")) {
            passwordGeneratorSettings.setWithDigits(true);
        }

        System.out.println("Do you want to have special characters in password? (y if yes):");
        if (scanner.nextLine().toLowerCase(Locale.ROOT).equals("y")) {
            passwordGeneratorSettings.setWithSpecialCharacters(true);
        }

        while (true) {
            System.out.println("Please enter your password size:");
            try {
                int size = scanner.nextInt();
                passwordGeneratorSettings.setPasswordLength(size);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input. Try again.");
            } finally {
                scanner.nextLine();
            }
        }
        do {
            PasswordGenerator passwordGenerator = new PasswordGenerator(passwordGeneratorSettings);
            System.out.println("Your password is:");
            String pass = passwordGenerator.generatePassword();
            if (pass == null) {
                throw new IllegalStateException("Impossible to generate password with current configuration");
            }
            System.out.println(pass);
            System.out.println("Type again if you want to generate another password");
        } while (scanner.nextLine().toLowerCase(Locale.ROOT).equals("again"));
        scanner.close();
    }
}
