package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TokenController {
    public static String readInToken(String tokenUrl) {
        StringBuffer token = new StringBuffer();

        try {
            FileInputStream inputStream = new FileInputStream(tokenUrl);

            int c;
            while ((c = inputStream.read()) != -1) {
                token.append((char) c);
            }

            inputStream.close();

        } catch (FileNotFoundException e) {
            // TODO Rerun
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Stream failed");
            e.printStackTrace();
        }

        return String.valueOf(token);
    }
}
