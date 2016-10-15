package cz.codecamp.logger;

import cz.codecamp.logger.loggers.StdoutLogger;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoggerTester {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); // pozor na velikost pismen
    private static int minLogLevel = 4;
    private static final Map<String, LogLevelEnum> LEVEL_MAP;

    static {
        Map<String, LogLevelEnum> levelMap = new HashMap<>();
        levelMap.put("d", LogLevelEnum.DEBUG);
        levelMap.put("i", LogLevelEnum.INFO);
        levelMap.put("w", LogLevelEnum.WARNING);
        levelMap.put("e", LogLevelEnum.ERROR);
        LEVEL_MAP = Collections.unmodifiableMap(levelMap);
    }

    public static void main(String args) {

        LoggerInterface logger = new StdoutLogger();

        for (Scanner scanner = new Scanner(System.in); ; ) {
            System.out.print("> ");

            if (!scanner.hasNextLine()) {
                break;
            }

            String line = scanner.nextLine();
            String[] parts = line.split("\\s+", 2);

            if (parts.length < 2) {
                System.err.println("not enough arguments");
                continue;
            }

            LogLevelEnum level = LEVEL_MAP.get(parts[0].substring(0, 1).toLowerCase());
            if (level == null) {
                System.err.println("unknown level [" + parts[0] + "]");
                continue;
            }

            // Zalogovani pouze zprav od zvoleneho levelu (final int minLogLevel)
            // level 1: log vseho
            // level 2: log vseho bez DEBUG; loguje se INFO, WARNING, ERROR
            // level 3: log vseho bez DEBUG, INFO; loguje se WARNING, ERROR
            // level 4: log vseho bez DEBUG, INFO, WARNING; loguje se ERROR
            logger.log(level, parts[1], minLogLevel);
        }
    }

}
