package org.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class OutputRunner {

    public static void run(Path path) {
        System.out.println(getContentFromPath(path));
    }

    public static String getContentFromPath(Path path) {
        String content;
        try {
            content = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return content;
    }
}
