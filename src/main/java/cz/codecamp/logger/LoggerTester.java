package cz.codecamp.logger;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.formatters.CompleteFormatter;
import cz.codecamp.logger.formatters.DateFormatter;
import cz.codecamp.logger.formatters.NoDateFormatter;
import cz.codecamp.logger.loggers.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

//        LoggerInterface logger = new StdoutLogger();
//
//        LoggerInterface logger = new FileLogger();
//
//        LoggerInterface logger = new PrintStreamLogger(System.out);
//
//        LoggerInterface logger = null;
//        try {
//            logger = new PrintStreamLogger(new PrintStream(new FileOutputStream(new File("application.log"), true)));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        AbstractLogger stdoutLogger = new StdoutLogger(new NoDateFormatter());
        AbstractLogger stdoutLogger1 = new StdoutLogger(new DateFormatter());
        AbstractLogger stdoutLogger2 = new StdoutLogger(new CompleteFormatter());
        //AbstractLogger stdoutLogger3 = new StdoutLogger(new JsonFormatter());
        AbstractLogger stdoutLogger4 = new PrintSteamLoggerLevelComparison(System.out, new NoDateFormatter(), LogLevelEnum.WARNING);


        AbstractLogger[] loggers = {stdoutLogger, stdoutLogger1/*, stdoutLogger3*/, stdoutLogger4};
        AbstractLogger multiLogger = new MultiLogger(loggers);
        AbstractLogger dayLogger = new DayLogger(System.getProperty("user.home") + "/desktop//system_logger", new DateFormatter());


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

            multiLogger.log(level, parts[1]);
            stdoutLogger2.log(level, parts[1]);
            dayLogger.log(level, parts[1]);
            // stdoutLogger.debug(parts[1]);







        }



    }

}
