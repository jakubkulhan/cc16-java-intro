package cz.codecamp.logger;

import cz.codecamp.logger.loggers.FileLogger;
import cz.codecamp.logger.loggers.PrintStreamLogger;
import cz.codecamp.logger.loggers.StdoutLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoggerTester {

    private static final Map<String, LogLevelEnum> LEVEL_MAP; // v pameti mi urzuje dvojici klic + hodnota. klic musi byt unikatni (vzdy je prvni)

    static {
        Map<String, LogLevelEnum> levelMap = new HashMap<>();
        levelMap.put("d", LogLevelEnum.DEBUG);
        levelMap.put("i", LogLevelEnum.INFO);
        levelMap.put("w", LogLevelEnum.WARNING);
        levelMap.put("e", LogLevelEnum.ERROR);
        //levelMap.put("d", LogLevelEnum.ERROR); - timhle bych prepsala radek 16
        LEVEL_MAP = Collections.unmodifiableMap(levelMap);
    }

    public static void main(String[] args) {

        //LoggerInterface logger = new StdoutLogger();
        //LoggerInterface logger = new FileLogger();
        LoggerInterface logger = new PrintStreamLogger(System.out);
//        LoggerInterface logger = null;
//        try {
//            logger = new PrintStreamLogger(new PrintStream(new FileOutputStream(new File("application.log"), true)));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        for (Scanner scanner = new Scanner(System.in); ; ) {
            System.out.print("> ");

            if (!scanner.hasNextLine()) {
                break;
            }

            String line = scanner.nextLine();
            String[] parts = line.split("\\s+", 2); //regularni vyraz \\s+ makes one or more times a space, applied just once. vrati retezec na 2 casti, odstranuje oddelovac

            if (parts.length < 2) { //jak dlouhy je pole? 0 nebo 1?
                System.err.println("not enough arguments");
                continue; // pokud splneno, skacu na radek 28
            }

            LogLevelEnum level = LEVEL_MAP.get(parts[0].substring(0, 1).toLowerCase());
            if (level == null) {
                System.err.println("unknown level [" + parts[0] + "]");
                continue; // pokud splneno, skacu na radek 28
            }

            logger.log(level, parts[1]);
        }
    }

}
