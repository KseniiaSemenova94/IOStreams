package iostream.task1;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    private void createFileWithContent(String content) {
        try (FileOutputStream fileOutputStream =
                     new FileOutputStream("src/main/java/iostream/files/JavaCode.txt", false);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            byte[] buffer = content.getBytes();
            bufferedOutputStream.write(buffer, 0, buffer.length);
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e);
        } catch (IOException ioe) {
            System.out.println("Exception while writing file " + ioe);
        }
    }

    private String getResultFromFile() {
        String fileContent = "";
        try (FileInputStream fileInputStream =
                     new FileInputStream("src/main/java/iostream/files/ResultTask1.txt");
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

    @Test
    void simpleClassTest() {
        String testCode = "public static void main(String[] args) {\n\r try{} catch {}}";
        createFileWithContent(testCode);
        Main.main(null);
        String result = getResultFromFile();
        assertTrue(result.contains("public=1"));
        assertTrue(result.contains("static=1"));
        assertTrue(result.contains("void=1"));
        assertFalse(result.contains("String=1"));
        assertTrue(result.contains("try=1"));
        assertTrue(result.contains("catch=1"));
    }

    @Test
    void simpleClassTest2() {
        String testCode = "public static void main(String[] args) { System.out.println(\"I have class Main\"); }";
        createFileWithContent(testCode);
        Main.main(null);
        String result = getResultFromFile();
        assertTrue(result.contains("public=1"));
        assertTrue(result.contains("static=1"));
        assertTrue(result.contains("void=1"));
        assertFalse(result.contains("class=1"));
    }


}
