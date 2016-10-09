package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.*;

/**
 * Created by honzapua on 9.10.2016.
 */

public class FileLogger implements LoggerInterface, Closeable {
    private final PrintStream fileStream; // deklarace z hintu


    public FileLogger() throws FileNotFoundException {
        fileStream = new PrintStream( //
                new FileOutputStream(
                        new File("application.log"), // jmeno souboru
                        true // vzdycky pridava kdyz existuje, neprepise ho
                )
        );
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        fileStream.printf("[%s] %s%n", level.name(), message); // [string] [string konecradky]
    }

    @Override
    public void close() throws IOException {
        fileStream.close();
    }
}


