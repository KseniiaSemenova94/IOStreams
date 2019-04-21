package iostream.task2;

import java.io.BufferedReader;
import java.io.IOException;

public class CharacterStreamTask {
    public static String readJavaCodeFromFile(BufferedReader bufferedReader, String fileContent) throws IOException {
        String s;
        while((s = bufferedReader.readLine())!= null){
            fileContent = fileContent.concat(s);
        }
        return fileContent;
    }

}
