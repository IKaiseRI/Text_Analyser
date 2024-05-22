package md.stefanini_task.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static md.stefanini_task.utils.FileUtils.getContentFromPath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileUtilsTest {

    @Test
    @DisplayName("checkGetContentFromPath")
    void givenPathWhenGetContentFromPathThenReturnContent() {
        String expectedContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";

        Path path = Path.of("src/test/resources", "test.txt");
        String actualContent = getContentFromPath(path);

        assertThat(actualContent).isEqualTo(expectedContent);
    }

    @Test
    @DisplayName("checkGetContentFromPathException")
    void givenInvalidPathWhenGetContentFromPathThenThrowException() {
        Path invalidPath = Paths.get("This/Path/Doesnt/Exist.txt");
        assertThrows(RuntimeException.class, () -> getContentFromPath(invalidPath));
    }
}