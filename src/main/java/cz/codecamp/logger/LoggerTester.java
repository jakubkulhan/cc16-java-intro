package cz.codecamp.logger;

import cz.codecamp.logger.loggers.FileLogger;
import cz.codecamp.logger.loggers.MultiLogger;
import cz.codecamp.logger.loggers.StdoutLogger;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadFactory;

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

        StdoutLogger logger = new StdoutLogger();
        List<PragmaticLoggerInterface> loggers = new ArrayList<>();
        loggers.add(logger);
        loggers.add(new FileLogger());
        LoggerInterface multiLog = new MultiLogger(loggers);
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

           // logger.log1(level, parts[1], Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
            System.out.println(Thread.currentThread().getStackTrace()[1].getClassName());
           // for(Thread.currentThread().getStackTrace().length)
            for (int i = 0; i < Thread.currentThread().getStackTrace().length; i++){
                System.out.println(i + " " + Thread.currentThread().getStackTrace()[i]);
            }
            multiLog.logJson(level, parts[1]);
            System.out.println(LocalDateTime.now().getHour() + " " + LocalDateTime.now().getMinute() + " " + LocalDateTime.now().getSecond());
        }
    }

}
