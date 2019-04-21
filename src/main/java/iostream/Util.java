package iostream;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

abstract public class Util {
    public final static String[] KEYWORDS = {"byte", "short", "int", "long", "char", "float", "double", "boolean", "if",
            "else", "switch", "case", "default", "while", "do", "break", "continue", "for", "try", "catch", "finally",
            "throw", "throws", "private", "public", "protected", "import", "package", "class", "interface", "extends",
            "implements", "static", "final", "void", "abstract", "native", "new", "return", "this", "super",
            "synchronized", "volatile", "const", "goto", "instanceof", "enum", "assert", "transient", "strictfp"};

    public static String[] formatFileContent(String content) {
        String result = content.replaceAll("\".*?\"", ""); // Удаляю возможное содержимое строк
        // Далее отделяю ключевые слова от сопутсвующих символов, чтобы они не мешали последующему сравнению
        Pattern pattern = Pattern.compile("\\s*(\\s|;|:|\\.|\\(|\\)|\\{|\\}|\\]|\\[)\\s*");
        return pattern.split(result);
    }

    public static Map<String, Integer> getSameWordsAmount(String[] words, Set<String> keywords) {
        Map<String, Integer> result = new HashMap<>();
        for (String word : words) {
            if (keywords.contains(word)) {
                if (result.containsKey(word)) {
                    result.put(word, result.get(word) + 1);
                } else {
                    result.put(word, 1);
                }
            }
        }
        return result;
    }
}
