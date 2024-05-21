package org.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OutputRunner {

    public static void run(Path path) {
        String contentFromPath = getContentFromPath(path);
        List<String> words = getWords(contentFromPath);
        List<String> sentences = getSentences(contentFromPath);

        System.out.println("Number of words: " + words.size());
        System.out.println("Number of sentences: " + sentences.size());
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

    public static List<String> getSentences(String content) {
        return Arrays.stream(content.split("\\.!?"))
                .map(String::trim)
                .filter(sentence -> !sentence.isEmpty())
                .collect(Collectors.toList());
    }
}
