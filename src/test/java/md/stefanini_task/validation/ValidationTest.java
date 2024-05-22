package md.stefanini_task.validation;

import md.stefanini_task.exceptions.InvalidInputArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static md.stefanini_task.validation.Validation.isValidPath;
import static md.stefanini_task.validation.Validation.isValidPhraseSize;
import static md.stefanini_task.validation.Validation.isValidTopValue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidationTest {

    @Test
    @DisplayName("checkPathArgumentMissingException")
    void givenNullPathWHenIsValidPathThenThrowException() {
        String expectedMessage = "Path argument (-file) is missing";

        InvalidInputArgumentException invalidInputArgumentException = assertThrows(InvalidInputArgumentException.class, () -> isValidPath(null));

        assertThat(invalidInputArgumentException.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    @DisplayName("checkInvalidPath")
    void givenInvalidPathWHenISValidPathTHenThrowException() {
        String expectedMessage = "Invalid path or file doesn't exist";
        Path path = Paths.get("This/is/an/invalid/path");

        InvalidInputArgumentException invalidInputArgumentException = assertThrows(InvalidInputArgumentException.class, () -> isValidPath(path));

        assertThat(invalidInputArgumentException.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    @DisplayName("checkTopValueExceedsNumber")
    void givenInvalidValueWhenIsValidTopValueThenThrowException() {
        String expectedMessage = "Invalid top value or value exceeds number of phrases";

        InvalidInputArgumentException invalidInputArgumentException = assertThrows(InvalidInputArgumentException.class,
                () -> isValidTopValue("-1", 3));

        assertThat(invalidInputArgumentException.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    @DisplayName("checkTopValueMissing")
    void givenNonDigitValueWhenIsValidTopValueThenThrowException() {
        String expectedMessage = "Invalid top value or value is missing";

        InvalidInputArgumentException invalidInputArgumentException = assertThrows(InvalidInputArgumentException.class,
                () -> isValidTopValue("notDigit", 3));

        assertThat(invalidInputArgumentException.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    @DisplayName("checkPhraseSizeExceedsMaximum")
    void givenInvalidPhraseSizeWhenIsValidPhraseSizeThenThrowException() {
        String expectedMessage = "Invalid phraseSize or phraseSize exceeds maximum allowed length";

        InvalidInputArgumentException invalidInputArgumentException = assertThrows(InvalidInputArgumentException.class,
                () -> isValidPhraseSize("-1", 3));

        assertThat(invalidInputArgumentException.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    @DisplayName("checkPhraseSizeMissing")
    void givenNonDigitPhraseSizeWHenIsValidPhraseSizeThenThrowException() {
        String expectedMessage = "PhraseSize argument is invalid or missing";

        InvalidInputArgumentException invalidInputArgumentException = assertThrows(InvalidInputArgumentException.class,
                () -> isValidPhraseSize("notDigit", 3));

        assertThat(invalidInputArgumentException.getMessage()).isEqualTo(expectedMessage);
    }
}