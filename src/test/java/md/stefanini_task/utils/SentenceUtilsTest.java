package md.stefanini_task.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static md.stefanini_task.utils.SentenceUtils.findPhrases;
import static md.stefanini_task.utils.SentenceUtils.getLengthOfSentenceWithMostWords;
import static md.stefanini_task.utils.SentenceUtils.getMaxPhraseLength;
import static md.stefanini_task.utils.SentenceUtils.getSentences;
import static md.stefanini_task.utils.SentenceUtils.getTopEntriesByValue;
import static md.stefanini_task.utils.SentenceUtils.getWords;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SentenceUtilsTest {

    @Test
    @DisplayName("checkGetTopEntriesByValue")
    void givenMapWhenGetTopEntriesByValueThenReturnMapWithTopValues() {
        Map<String, Integer> testMap = new HashMap<>();
        testMap.put("C", 3);
        testMap.put("A", 1);
        testMap.put("B", 2);

        int n = 2;

        Map<String, Integer> expectedResult = new LinkedHashMap<>();
        expectedResult.put("C", 3);
        expectedResult.put("B", 2);

        Map<String, Integer> result = getTopEntriesByValue(testMap, n);

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("checkGetWords")
    void givenContentWhenGetWordsThenReturnListOfWords() {
        String content = "This is a test sentence";
        List<String> result = getWordsTest(content);

        List<String> actualResult = getWords(content);

        assertAll(
                () -> assertThat(actualResult.size()).isEqualTo(result.size()),
                () -> assertThat(actualResult.get(0)).isEqualTo(result.get(0))
        );
    }

    @Test
    @DisplayName("checkGetSentences")
    void givenContentWhenGetSentencesThenReturnListOfSentences() {
        String content = "This is sentence one. This is sentence two! And this is sentence three?";
        List<String> result = getSentencesTest(content);

        List<String> actualResult = getSentences(content);

        assertAll(
                () -> assertThat(actualResult.size()).isEqualTo(result.size()),
                () -> assertThat(actualResult.get(0)).isEqualTo(result.get(0))
        );
    }

    @Test
    @DisplayName("checkGetLengthOfSentenceWithMostWords")
    void givenContentWhenGetLengthOfSentenceWithMostWordsThenReturnValue() {
        String content = "This is sentence one. This is sentence two! And this is sentence three?";
        List<String> sentencesTest = getSentencesTest(content);

        int value = sentencesTest.stream()
                .mapToInt(sentence -> getWordsTest(sentence).size())
                .max()
                .orElse(0);

        Integer actualValue = getLengthOfSentenceWithMostWords(content);

        assertThat(actualValue).isEqualTo(value);
    }

    @Test
    @DisplayName("checkFindPhrases")
    void givenSentencesWHenFindPhrasesThenReturnMap() {
        List<String> sentences1 = getSentencesTest("This is a test. This test is simple.");
        Map<String, Integer> expectedResult = new HashMap<>();
        expectedResult.put("This is", 1);
        expectedResult.put("is a", 1);
        expectedResult.put("a test", 1);
        expectedResult.put("This test", 1);
        expectedResult.put("test is", 1);
        expectedResult.put("is simple", 1);

        Map<String, Integer> actualResult = findPhrases(sentences1, 2);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("checkGetMaxPhraseLength")
    void givenMapWhenGetMaxPhraseLengthThenReturnMaxPhraseLength() {
        Map<String, Integer> map = new HashMap<>();
        map.put("short", 1);
        map.put("medium length", 2);
        map.put("the longest phrase here", 3);

        Integer maxPhraseLength = getMaxPhraseLength(map);

        assertThat(maxPhraseLength).isEqualTo(23);

    }
    private List<String> getWordsTest(String sentence) {
        return Arrays.asList(sentence.split("\\s+"));
    }

    private List<String> getSentencesTest(String content) {
        return Arrays.stream(content.split("[.!?]"))
                .map(String::trim)
                .toList();
    }
}