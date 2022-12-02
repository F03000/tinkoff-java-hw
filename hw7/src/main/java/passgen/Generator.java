package passgen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Generator {
    private final Map<Integer, List<String>> adjectiveMap;
    private final Map<Integer, List<String>> nounMap;
    private final Random rand;
    private static final String SPECIAL_CHARACTERS = "!-$#@%&*";

    Generator() {
        adjectiveMap = readFile("adj");
        nounMap = readFile("noun");
        rand = new Random();
    }

    public char generateSpecialCharacter() {
        int randomIndex = rand.nextInt(SPECIAL_CHARACTERS.length());
        return SPECIAL_CHARACTERS.charAt(randomIndex);
    }

    public int generateDigit() {
        return rand.nextInt(10);
    }

    public Set<Integer> getPossibleNounSizes() {
        return nounMap.keySet();
    }

    public Set<Integer> getPossibleAdjectiveSizes() {
        return adjectiveMap.keySet();
    }

    public String generateNoun(Integer size) {
        return generateWord(size, nounMap);
    }

    public String generateAdjective(Integer size) {
        return generateWord(size, adjectiveMap);
    }

    private String generateWord(Integer size, Map<Integer, List<String>> wordTypeMap) {
        List<String> wordList = wordTypeMap.get(size);
        if (wordList == null) {
            return null;
        }
        int randomIndex = rand.nextInt(wordList.size());
        return capitalizeString(wordList.get(randomIndex));
    }

    private String capitalizeString(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private Map<Integer, List<String>> readFile(String fileName) {
        String classpath = System.getProperty("java.class.path");
        String[] classpathEntries = classpath.split(File.pathSeparator);
        String resourcesPath = classpathEntries[1];
        Map<Integer, List<String>> lines = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get(resourcesPath, fileName).toString()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                List<String> currentLineList = lines.get(line.length());
                if (currentLineList != null) {
                    currentLineList.add(line);
                } else {
                    lines.put(line.length(), new ArrayList<>(Collections.singleton(line)));
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
