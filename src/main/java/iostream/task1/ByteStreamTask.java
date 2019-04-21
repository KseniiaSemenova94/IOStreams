package iostream.task1;

import java.io.*;
import java.util.Map;

public class ByteStreamTask {

    public static String readJavaCodeFromFile(BufferedInputStream bufferedInputStream) throws IOException {
        String fileContent = "";
        byte[] contents = new byte[1024];
        int bytesRead;
        while ((bytesRead = bufferedInputStream.read(contents)) != -1) {
            fileContent = fileContent.concat(new String(contents, 0, bytesRead));
        }
        return fileContent;
    }

    public static void writeMapToFile(BufferedOutputStream bufferedOutputStream, Map<String, Integer> map) throws IOException {
        byte[] buffer = map.toString().getBytes();
        bufferedOutputStream.write(buffer, 0, buffer.length);
    }
}

