package cz.codecamp.logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.codecamp.logger.formaters.JsonFormatter;
import cz.codecamp.logger.loggers.FileLogger;
import cz.codecamp.logger.loggers.MultiLogger;
import cz.codecamp.logger.loggers.StderrLogger;
import cz.codecamp.logger.loggers.StdoutLogger;


import java.io.FileNotFoundException;
import java.util.*;

public class LoggerTester {

    private static final Map<String, LogLevelEnum> LEVEL_MAP;

    static {
        Map<String, LogLevelEnum> levelMap = new HashMap<>();
        levelMap.put("d", LogLevelEnum.DEBUG);
        levelMap.put("i", LogLevelEnum.INFO);
        levelMap.put("w", LogLevelEnum.WARNING);
        levelMap.put("e", LogLevelEnum.ERROR);
        LEVEL_MAP = Collections.unmodifiableMap(levelMap);
    }

    public static void main(String[] args) {
        LoggerInterface logger = new MultiLogger(new StdoutLogger());
        StdoutLogger logger1 = new StdoutLogger();
        logger1.setFormatter(new JsonFormatter(new ObjectMapper()));
        FileLogger logger2 = null;
        try {
            logger2 = new FileLogger("application.log");
        } catch(FileNotFoundException exception) {
            exception.printStackTrace();
        }
        StderrLogger logger3 = new StderrLogger();
        logger3.setLowestLogLevel(LogLevelEnum.WARNING);

        StdoutLogger logger4 = new StdoutLogger();
        logger4.setLowestLogLevel(LogLevelEnum.DEBUG);

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
            logger.log(level, parts[1]);
            logger1.log(level, parts[1]);
            logger2.log(level, parts[1]);
            logger3.log(level, parts[1]);
            logger4.log(level, parts[1]);
        }
    }

}
