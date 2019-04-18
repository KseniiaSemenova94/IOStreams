package iostream.task1;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ByteStreamTask {

    public static String readJavaCodeFromFile(String fileAddress) {
        String fileContent = "";
        try (FileInputStream fileInputStream = new FileInputStream(fileAddress);
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
        return fileContent;
    }

    public static String[] formatFileContent(String content) {
        String result = content.replaceAll("\".*?\"", ""); // Удаляю возможное содержимое строк
        // Далее отделяю ключевые слова от сопутсвующих символов, чтобы они не мешали последующему сравнению
        Pattern pattern = Pattern.compile("\\s*(\\s|;|:|\\.|\\(|\\)|\\{|\\}|\\]|\\[)\\s*");
        return pattern.split(content);
    }

    public static Map<String, Integer> getSameWordsAmount(String[] words, Map<String, String> keywords) {
        Map<String, Integer> result = new HashMap<>();
        for (String word : words) {
            if (keywords.containsKey(word)) {
                if (result.containsKey(word)) {
                    result.put(word, result.get(word) + 1);
                } else {
                    result.put(word, 1);
                }
            }
        }
        return result;
    }

    public static void writeMapToFile(String fileAddress, Map<String, Integer> map) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileAddress, false);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            byte[] buffer = map.toString().getBytes();
            bufferedOutputStream.write(buffer, 0, buffer.length);
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e);
        } catch (IOException ioe) {
            System.out.println("Exception while writing file " + ioe);
        }
    }
}
