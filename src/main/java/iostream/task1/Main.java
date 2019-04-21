package iostream.task1;

import iostream.Util;

import java.io.*;
import java.util.*;

import static iostream.Util.KEYWORDS;

public class Main {
    private final static String INPUT_FILE_PATH = "src/main/java/iostream/files/JavaCode.txt";
    private final static String OUTPUT_FILE_PATH = "src/main/java/iostream/files/ResultTask1.txt";

    public static void main(String[] args) {
        String fileContent;

        try (FileInputStream fileInputStream = new FileInputStream(INPUT_FILE_PATH);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
            fileContent = ByteStreamTask.readJavaCodeFromFile(bufferedInputStream);
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e);
            return;
        } catch (IOException ioe) {
            System.out.println("Exception while reading file " + ioe);
            return;
        }

        String[] words = Util.formatFileContent(fileContent);

        Set<String> keywordsSet = new HashSet<>(Arrays.asList(KEYWORDS));

        Map<String, Integer> result = Util.getSameWordsAmount(words, keywordsSet);

        try (FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_FILE_PATH, false);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            ByteStreamTask.writeMapToFile(bufferedOutputStream, result);
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e);
        } catch (IOException ioe) {
            System.out.println("Exception while writing file " + ioe);
        }
    }
}
