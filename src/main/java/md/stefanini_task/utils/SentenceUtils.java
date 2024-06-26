package md.stefanini_task.utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SentenceUtils {

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

    public static List<String> getWords(String content) {
        return Arrays.stream(content.split("\\s+")).toList();
    }

    public static List<String> getSentences(String content) {
        return Arrays.stream(content.split("[.!?]"))
                .map(String::trim)
                .filter(sentence -> !sentence.isEmpty())
                .collect(Collectors.toList());
    }

    public static Integer getLengthOfSentenceWithMostWords(String content) {
        List<String> sentences = getSentences(content);

        return sentences.stream()
                .mapToInt(sentence -> getWords(sentence).size())
                .max()
                .orElse(0);
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

    public static Integer getMaxPhraseLength(Map<String, Integer> map) {
        return map.keySet().stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }
}
