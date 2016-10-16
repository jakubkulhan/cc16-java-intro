package cz.codecamp.logger;

import cz.codecamp.logger.formatters.LogFormatter;
import cz.codecamp.logger.formatters.LogJSONFormatter;
import cz.codecamp.logger.formatters.LogWithoutTimeFormatter;
import cz.codecamp.logger.loggers.FileLogger;
import cz.codecamp.logger.loggers.StdoutLogger;
import cz.codecamp.logger.loggers.PrintStreamLogger;
import cz.codecamp.logger.loggers.MultiLogger;

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

        LoggerInterface stdoutLogger = new StdoutLogger();
        LoggerInterface fileLogger = new FileLogger();
        //LoggerInterface printStreamLogger = new PrintStreamLogger(System.out);

        List<LoggerInterface> loggers = new ArrayList<LoggerInterface>();

        loggers.add(stdoutLogger);
        loggers.add(fileLogger);
        //loggers.add(printStreamLogger);

        LoggerInterface logger = new MultiLogger(loggers);
        //LoggerInterface logger = new StdoutLogger();

        logger.setLevel(LogLevelEnum.INFO);

        FormatterInterface formatter = new LogFormatter();

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

            logger.log(level, parts[1], formatter);
        }
    }

}
