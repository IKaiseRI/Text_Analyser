package org.task;

import org.task.exceptions.InvalidInputArgumentException;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.task.Validation.isValidPath;

public class ArgumentsReader {

    public static void getStringArguments(String[] args) {
        Map<String, String> argsMap = Arrays.stream(args)
                .filter(arg -> arg.startsWith("-"))
                .collect(Collectors.toMap(
                        arg -> arg.substring(0, arg.indexOf('=')),
                        arg -> arg.substring(arg.indexOf('=') + 1)
                ));

        Path filePath = Path.of(argsMap.getOrDefault("-file", null));
        int top = Integer.parseInt(argsMap.getOrDefault("-top", null)); // Set default top value
        int phraseSize = Integer.parseInt(argsMap.getOrDefault("-phraseSize", null));
        try {
            isValidPath(filePath);
        } catch (InvalidInputArgumentException e) {
            throw new RuntimeException(e);
        }

        OutputRunner.run(filePath, top, phraseSize);
    }

}
