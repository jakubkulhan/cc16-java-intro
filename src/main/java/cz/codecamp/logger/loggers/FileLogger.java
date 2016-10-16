package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;

import java.io.*;

/**
 * Created by honzapua on 9.10.2016.
 */

public class FileLogger extends PrintStreamLogger {

    public FileLogger(String fileName, FormatterInterface formater, LogLevelEnum threshold) throws FileNotFoundException {
        super(new PrintStream( //
                new FileOutputStream(
                        new File(fileName), // jmeno souboru
                        true // vzdycky pridava kdyz existuje, neprepise ho
                )
        ), formater, threshold
        );
    }


}


