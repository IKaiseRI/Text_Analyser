package org.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.task.OutputUtils.printPhrasesTable;
import static org.task.OutputUtils.printPrimaryTable;

public class OutputRunner {

    public static void run(Path path, Integer top, Integer phraseSize) {
        String contentFromPath = getContentFromPath(path);
        List<String> words = getWords(contentFromPath);
        List<String> sentences = getSentences(contentFromPath);

        printPrimaryTable(words, sentences);

        Map<String, Integer> phrases = findPhrases(sentences, phraseSize);
        Map<String, Integer> topEntriesByValue = getTopEntriesByValue(phrases, top);
        int maxPhraseLength = topEntriesByValue.keySet().stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);

        int phraseColumnWidth = Math.max(maxPhraseLength, 10);

        printPhrasesTable(topEntriesByValue, phraseColumnWidth);
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

    public static Map<String, Integer> findPhrases(List<String> sentences, int phraseSize) {
        Map<String, Integer> phraseCountMap = new HashMap<>();

        for (String sentence : sentences) {
            List<String> words = getWords(sentence);

            IntStream.range(0, words.size() - phraseSize + 1)
                    .mapToObj(i -> String.join(" ", words.subList(i, i + phraseSize)))
                    .forEach(phrase -> phraseCountMap.merge(phrase, 1, Integer::sum));
        }

        return phraseCountMap;
    }

    public static Map<String, Integer> getTopEntriesByValue(Map<String, Integer> map, int n) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(n)
                .collect(
                        Collectors.toMap(
                                        Map.Entry::getKey,
                                        Map.Entry::getValue,
                                        (existing, replacement) -> replacement,
                                        LinkedHashMap::new
                        )
                );
    }
}
