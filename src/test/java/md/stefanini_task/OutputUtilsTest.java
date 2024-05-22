package md.stefanini_task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static md.stefanini_task.utils.OutputUtils.printPhrasesTable;
import static md.stefanini_task.utils.OutputUtils.printPrimaryTable;

class OutputUtilsTest {

    @Test
    @DisplayName("checkPrintPrimaryTable")
    void givenWordsAndSentencesWhenPrintPrimaryTableThenPrint() {
        List<String> words = Arrays.asList("This", "is", "a", "test");
        List<String> sentences = List.of("This is a test sentence.");

        String numberOfWords = "Number of words:";
        String numberOfSentences = "Number of sentences:";

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        printPrimaryTable(words, sentences);
        System.setOut(System.out);

        assertThat(out.toString())
                .contains(numberOfWords)
                .contains(numberOfSentences)
                .contains(String.valueOf(words.size()))
                .contains(String.valueOf(sentences.size()));
    }

    @Test
    @DisplayName("checkPrintPhrasesTable")
    void givenTopEntriesByValueWhenPrintPhrasesTableThenPrint() {
        Map<String, Integer> topEntries = new HashMap<>();
        topEntries.put("This is a phrase", 3);
        topEntries.put("Another phrase here", 2);

        int phraseColumnWidth = 20;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        printPhrasesTable(topEntries, phraseColumnWidth);
        System.setOut(System.out);

        String expectedHeader = "| %-" + phraseColumnWidth + "s | %-5s |";
        String expectedLine = "+" + "-".repeat(phraseColumnWidth + 2) + "+" + "-------+";

        assertThat(out.toString())
                .contains(expectedLine)
                .contains(String.format(expectedHeader, "Phrases", "Count"))
                .contains(String.valueOf(topEntries.get("This is a phrase")))
                .contains(String.valueOf(topEntries.get("Another phrase here")));
    }


}