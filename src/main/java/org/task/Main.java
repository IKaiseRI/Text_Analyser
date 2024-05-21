package org.task;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("src/main/resources", "text.txt");
        OutputRunner.run(path);
    }
}