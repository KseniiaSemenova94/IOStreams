package iostream.task2;

import iostream.Util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static iostream.Util.KEYWORDS;

public class Main {
    private final static String INPUT_FILE_PATH = "src/main/java/iostream/files/JavaCode.txt";
    private final static String OUTPUT_FILE_PATH = "src/main/java/iostream/files/ResultTask2.txt";

    public static void main(String[] args) {
        String fileContent = "";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(INPUT_FILE_PATH))) {
            fileContent = CharacterStreamTask.readJavaCodeFromFile(bufferedReader, fileContent);
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e);
            return;
        } catch (IOException ioe) {
            System.out.println("Exception while reading file " + ioe);
            return;
        }

        String[] words = Util.formatFileContent(fileContent);

        Map<String, String> keywordsMap = new HashMap<>();

        for (String word : KEYWORDS) {
            keywordsMap.put(word, word);
        }

        Map<String, Integer> result = Util.getSameWordsAmount(words, keywordsMap);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(OUTPUT_FILE_PATH))) {
            bufferedWriter.write(result.toString());
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e);
        } catch (IOException ioe) {
            System.out.println("Exception while writing file " + ioe);
        }
    }
}
