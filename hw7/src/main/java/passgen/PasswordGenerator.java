package passgen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

public class PasswordGenerator {
    private final Generator generator;
    private final PasswordGeneratorSettings settings;
    private boolean usedNoun;
    private final Random rand;
    private final List<Integer> possibleSizes;
    private final int minSize;

    public PasswordGenerator(PasswordGeneratorSettings settings) {
        generator = new Generator();
        this.settings = settings;
        usedNoun = false;
        rand = new Random();

        Set<Integer> possibleSizesSet = generator.getPossibleAdjectiveSizes();
        possibleSizesSet.retainAll(generator.getPossibleNounSizes());
        possibleSizes = possibleSizesSet.stream().toList();

        Optional<Integer> optionalMinSize = possibleSizes.stream().min(Comparator.naturalOrder());
        if (optionalMinSize.isEmpty()) {
            throw new IllegalStateException("passgen.PasswordGenerator is not provided with any words");
        }
        minSize = optionalMinSize.get();

    }

    public String generatePassword() {
        if (settings.getPasswordLength() < minSize) {
            return null;
        }
        usedNoun = false;
        List<String> resultList = new ArrayList<>();
        int lengthLeft = settings.getPasswordLength();
        List<Supplier<String>> generateMethods = new ArrayList<>();

        if (settings.isWithDigits()) {
            generateMethods.add(() -> String.valueOf(generator.generateDigit()));
        }
        if (settings.isWithSpecialCharacters()) {
            generateMethods.add(() -> String.valueOf(generator.generateSpecialCharacter()));
        }
        generateMethods.add(this::generateRandomWord);

        while (lengthLeft > 0) {
            int randomGeneratorIndex = rand.nextInt(generateMethods.size());
            String generatedString = generateMethods.get(randomGeneratorIndex).get();
            if (lengthLeft - generatedString.length() < minSize) {
                generatedString = wordGenerator(lengthLeft);
            }
            resultList.add(generatedString);
            lengthLeft -= generatedString.length();
        }
        Collections.reverse(resultList);
        return String.join("", resultList);
    }

    private String generateRandomWord() {
        int randomSizeIndex = rand.nextInt(possibleSizes.size());
        return wordGenerator(possibleSizes.get(randomSizeIndex));
    }

    private String wordGenerator(int size) {
        if (!usedNoun) {
            usedNoun = true;
            return generator.generateNoun(size);
        } else {
            return generator.generateAdjective(size);
        }
    }
}
