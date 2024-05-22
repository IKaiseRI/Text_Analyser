package md.stefanini_task;

import md.stefanini_task.exceptions.InvalidInputArgumentException;
import md.stefanini_task.utils.FileUtils;
import md.stefanini_task.utils.OutputUtils;
import md.stefanini_task.utils.SentenceUtils;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static md.stefanini_task.validation.Validation.isValidPath;
import static md.stefanini_task.validation.Validation.isValidPhraseSize;
import static md.stefanini_task.validation.Validation.isValidTopValue;

public class OutputRunner {

    public static void run(String[] args) {
        Map<String, String> stringArguments = getStringArguments(args);

        Path filePath = Path.of(stringArguments.getOrDefault("-file", null));
        String stringTop = stringArguments.getOrDefault("-top", null);
        String stringPhraseSize = stringArguments.getOrDefault("-phraseSize", null);

        try {
            isValidPath(filePath);

            String contentFromPath = FileUtils.getContentFromPath(filePath);
            List<String> sentences = SentenceUtils.getSentences(contentFromPath);
            int phraseSize = isValidPhraseSize(stringPhraseSize, SentenceUtils.getLengthOfSentenceWithMostWords(contentFromPath));

            Map<String, Integer> phrases = SentenceUtils.findPhrases(sentences, phraseSize);

            int top = isValidTopValue(stringTop, phrases.entrySet().size());

            Map<String, Integer> topEntriesByValue = SentenceUtils.getTopEntriesByValue(phrases, top);
            int maxPhraseLength = SentenceUtils.getMaxPhraseLength(topEntriesByValue);
            int phraseColumnWidth = Math.max(maxPhraseLength, 10);

            OutputUtils.printPrimaryTable(SentenceUtils.getWords(contentFromPath), sentences);
            OutputUtils.printPhrasesTable(topEntriesByValue, phraseColumnWidth);

        } catch (InvalidInputArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, String> getStringArguments(String[] args) {
        return Arrays.stream(args)
                .filter(arg -> arg.startsWith("-"))
                .collect(Collectors.toMap(
                        arg -> arg.substring(0, arg.indexOf('=')),
                        arg -> arg.substring(arg.indexOf('=') + 1)
                ));
    }
}
