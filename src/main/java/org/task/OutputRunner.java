package org.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class OutputRunner {

    public static void run(Path path) {
        String contentFromPath = getContentFromPath(path);
        List<String> words = getWords(contentFromPath);

        System.out.println("Number of words: " + words.size());
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

    public static List<String> getWords(String content) {
        return Arrays.stream(content.split("\\s+")).toList();
    }
}
