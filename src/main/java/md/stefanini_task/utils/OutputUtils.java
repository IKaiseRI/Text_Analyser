package md.stefanini_task.utils;

import java.util.List;
import java.util.Map;

public class OutputUtils {

    public static void printPrimaryTable(List<String> words, List<String> sentences) {
        System.out.println("+------------------------+-------+");
        System.out.printf("| %-22s | %-5d |\n", "Number of words:", words.size());
        System.out.printf("| %-22s | %-5d |\n", "Number of sentences:", sentences.size());
        System.out.println("+------------------------+-------+");
    }

    public static void printPhrasesTable(Map<String, Integer> topEntriesByValue, int phraseColumnWidth) {
        String horizontalLine = "+" + "-".repeat(phraseColumnWidth + 2) + "+" + "-------+";

        System.out.println(horizontalLine);
        System.out.printf("| %-"+ phraseColumnWidth +"s | %-5s |\n", "Phrases", "Count");
        System.out.println(horizontalLine);
        for (Map.Entry<String, Integer> entry : topEntriesByValue.entrySet()) {
            System.out.printf("| %-"+ phraseColumnWidth +"s | %-5d |\n", entry.getKey(), entry.getValue());
        }
        System.out.println(horizontalLine);
    }
}
