package org.task;

import org.task.exceptions.InvalidInputArgumentException;

import java.nio.file.Files;
import java.nio.file.Path;

public class Validation {

    public static void isValidPath(Path path) throws InvalidInputArgumentException {
        if (path == null) {
            throw new InvalidInputArgumentException("Path argument (-file) is missing");
        }
        if (!Files.exists(path) || !Files.isRegularFile(path) || !Files.isExecutable(path)) {
            throw new InvalidInputArgumentException("Invalid path or file doesn't exist");
        }
    }

    public static void isValidTopValue(Integer topValue, Integer phraseCount) throws InvalidInputArgumentException {
        if (topValue == null) {
            throw new InvalidInputArgumentException("Top argument (-top) is missing");
        }
        if (topValue <= 0 || topValue > phraseCount) {
            throw new InvalidInputArgumentException("Invalid top value or value exceeds number of phrases");
        }
    }

    public static void isValidPhraseSize(Integer phraseSize, Integer maxPossibleSize) throws InvalidInputArgumentException {
        if (phraseSize == null) {
            throw new InvalidInputArgumentException("PhraseSize argument (-phraseSize) is missing");
        }
        if (phraseSize <= 0 || phraseSize > maxPossibleSize) {
            throw new InvalidInputArgumentException("Invalid phraseSize or phraseSize exceeds length of each sentence");
        }
    }
}
