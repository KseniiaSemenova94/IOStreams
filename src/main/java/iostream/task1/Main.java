package iostream.task1;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Main {
    final static String[] KEYWORDS = {"byte", "short", "int", "long", "char", "float", "double", "boolean", "if",
            "else", "switch", "case", "default", "while", "do", "break", "continue", "for", "try", "catch", "finally",
            "throw", "throws", "private", "public", "protected", "import", "package", "class", "interface", "extends",
            "implements", "static", "final", "void", "abstract", "native", "new", "return", "this", "super",
            "synchronized", "volatile", "const", "goto", "instanceof", "enum", "assert", "transient", "strictfp"};

    public static void main(String[] args) {
        String fileContent = "";

        try (FileInputStream fileInputStream = new FileInputStream("src/main/java/iostream/files/JavaCode.txt");
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
            byte[] contents = new byte[1024];
            int bytesRead;

            while ((bytesRead = bufferedInputStream.read(contents)) != -1) {
                fileContent = fileContent.concat(new String(contents, 0, bytesRead));

            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading file " + ioe);
        }

        fileContent = fileContent.replaceAll("\".*?\"", ""); // Удаляю возможное содержимое строк
        // Далее отделяю ключевые слова от сопутсвующих символов, чтобы они не мешали последующему сравнению
        Pattern pattern = Pattern.compile("\\s*(\\s|;|:|\\.|\\(|\\)|\\{|\\}|\\]|\\[)\\s*");
        String[] words = pattern.split(fileContent);

        Map<String, String> keywordsMap = new HashMap<>();

        for (String word : KEYWORDS) {
            keywordsMap.put(word, word);
        }

        Map<String, Integer> result = new HashMap<>();

        for (String word : words) {
            if (keywordsMap.containsKey(word)) {
                if (result.containsKey(word)) {
                    result.put(word, result.get(word) + 1);
                } else {
                    result.put(word, 1);
                }
            }
        }

        try (FileOutputStream fileOutputStream =
                     new FileOutputStream("src/main/java/iostream/files/ResultTask1.txt", false);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            byte[] buffer = result.toString().getBytes();
            bufferedOutputStream.write(buffer, 0, buffer.length);
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e);
        } catch (IOException ioe) {
            System.out.println("Exception while writing file " + ioe);
        }
    }
}
