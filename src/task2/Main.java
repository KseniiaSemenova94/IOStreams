package task2;

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

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/files/JavaCode.txt"))) {
            String s;
            while((s = bufferedReader.readLine())!= null){
                fileContent = fileContent.concat(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading file " + ioe);
        }

        fileContent = fileContent.replaceAll("\".*?\"", "");
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

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/files/ResultTask2.txt"))) {
            bufferedWriter.write(result.toString());
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e);
        } catch (IOException ioe) {
            System.out.println("Exception while writing file " + ioe);
        }
    }
}
