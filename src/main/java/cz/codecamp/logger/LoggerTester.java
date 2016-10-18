package cz.codecamp.logger;

import cz.codecamp.logger.loggers.*;

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

    public static void main(String[] args) throws FileNotFoundException {
        /*LoggerInterface fileStream = new FileLogger();
        LoggerInterface standartOut = new StdoutLogger();
        List<LoggerInterface> list = new ArrayList<LoggerInterface>();
        list.add(fileStream);
        list.add(standartOut);
        //LoggerInterface logger = new ImperativeMultiLogger(list);
        LoggerInterface logger = new FunctionalMultiLogger(list);*/
        //FileRotationLogger frl = new FileRotationLogger(0,0,1);
        FileLogger fileLogger = new FileLogger();
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

            fileLogger.log(level, parts[1]);
        }
    }

}
