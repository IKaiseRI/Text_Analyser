package org.task;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ArgumentsReader {

    public static void getStringArguments(String[] args) {
        Map<String, String> argsMap = Arrays.stream(args)
                .filter(arg -> arg.startsWith("-"))
                .collect(Collectors.toMap(
                        arg -> arg.substring(0, arg.indexOf('=')),
                        arg -> arg.substring(arg.indexOf('=') + 1)
                ));

        String filePath = argsMap.getOrDefault("-file", null);
        int top = Integer.parseInt(argsMap.getOrDefault("-top", "10")); // Set default top value
        int phraseSize = Integer.parseInt(argsMap.getOrDefault("-phraseSize", "1")); // Set default phraseSize value

        OutputRunner.run(Path.of(filePath), top, phraseSize);
    }

}
