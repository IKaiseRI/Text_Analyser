package org.task;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("resources", "text.txt");
        OutputRunner.run(path);
    }
}