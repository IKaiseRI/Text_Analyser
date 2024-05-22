package md.stefanini_task.validation;

import md.stefanini_task.exceptions.InvalidInputArgumentException;

import java.nio.file.Files;
import java.nio.file.Path;

public class Validation {

    public static void isValidPath(Path path) throws InvalidInputArgumentException {
        if (path == null) {
            throw new InvalidInputArgumentException("Path argument (-file) is missing");
        }
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            throw new InvalidInputArgumentException("Invalid path or file doesn't exist");
        }
    }

    public static int isValidTopValue(String top, Integer phraseCount) throws InvalidInputArgumentException {
        try {
            int topValue = Integer.parseInt(top);
            if (topValue <= 0 || topValue > phraseCount) {
                throw new InvalidInputArgumentException("Invalid top value or value exceeds number of phrases");
            }
            return topValue;
        } catch (NumberFormatException e) {
            throw new InvalidInputArgumentException("Invalid top value or value is missing");
        }

    }

    public static int isValidPhraseSize(String phrase, int maxPossibleSize) throws InvalidInputArgumentException {
        try {
            int phraseSize = Integer.parseInt(phrase);
            if (phraseSize <= 0 || phraseSize > maxPossibleSize) {
                throw new InvalidInputArgumentException("Invalid phraseSize or phraseSize exceeds maximum allowed length");
            }
            return phraseSize;
        } catch (NumberFormatException e) {
            throw new InvalidInputArgumentException("PhraseSize argument is invalid or missing");
        }
    }
}
