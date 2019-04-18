package iostream.task1;

import java.util.HashMap;
import java.util.Map;

import static iostream.TaskConstants.KEYWORDS;

public class Main {
    private final static String INPUT_FILE_PATH = "src/main/java/iostream/files/JavaCode.txt";
    private final static String OUTPUT_FILE_PATH = "src/main/java/iostream/files/ResultTask1.txt";

    public static void main(String[] args) {
        String fileContent = ByteStreamTask.readJavaCodeFromFile(INPUT_FILE_PATH);

        String[] words = ByteStreamTask.formatFileContent(fileContent);

        Map<String, String> keywordsMap = new HashMap<>();

        for (String word : KEYWORDS) {
            keywordsMap.put(word, word);
        }

        Map<String, Integer> result = ByteStreamTask.getSameWordsAmount(words, keywordsMap);

        ByteStreamTask.writeMapToFile(OUTPUT_FILE_PATH, result);
    }
}
