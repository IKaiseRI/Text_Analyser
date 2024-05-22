package md.stefanini_task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OutputRunnerTest {

    @Test
    @DisplayName("checkRun")
    void givenValidArgumentsWhenRunThenReceiveResult() {
        String[] args = {"-file=src/test/resources/test.txt", "-top=3", "-phraseSize=2"};
        try {
            OutputRunner.run(args);
        } catch (RuntimeException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("checkRunException")
    void givenMissingArgumentWHenRunThenTExpectException() {
        String[] args = {"-top=3", "-phraseSize=2"};
        assertThrows(RuntimeException.class, () -> OutputRunner.run(args));
    }
}