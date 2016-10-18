package cz.codecamp.logger;

import cz.codecamp.logger.loggers.FileLogger;
import cz.codecamp.logger.loggers.MultiLogger;
import cz.codecamp.logger.loggers.StdoutLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
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

    public static void main(String[] args) throws FileNotFoundException {

        StdoutLogger stdoutLogger = new StdoutLogger();
        FileLogger fileLogger = new FileLogger(new PrintStream(new FileOutputStream(new File("application_" + LocalDateTime.now().getDayOfMonth() + "_" + LocalDateTime.now().getMonth() + ".log"), true)));

        List<PragmaticLoggerInterface> loggers = new ArrayList<>();
        loggers.add(stdoutLogger);
        loggers.add(fileLogger);
        LoggerInterface multiLog = new MultiLogger(loggers);

        int index = 0;
        for (int i = 0; i < Thread.currentThread().getStackTrace().length; i++) {
            if (Thread.currentThread().getStackTrace()[i].getClassName().equals(LoggerTester.class.getName()))
                index = i;
        }

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

            /*Multilig in JSON format*/
            multiLog.logJson(level, parts[1], Thread.currentThread().getStackTrace()[index].getClassName(), Thread.currentThread().getStackTrace()[index].getLineNumber());

//            /*multilog*/
//            multiLog.log(level, parts[1]);
//            /*filelog in JSON format*/
//            fileLogger.logJson(level, parts[1], Thread.currentThread().getStackTrace()[index].getClassName(), Thread.currentThread().getStackTrace()[index].getLineNumber());
//
//            fileLogger.log(level, parts[1]);
//
//            stdoutLogger.log(level, parts[1], Thread.currentThread().getStackTrace()[index].getClassName(), Thread.currentThread().getStackTrace()[index].getLineNumber());
        }
    }

}
