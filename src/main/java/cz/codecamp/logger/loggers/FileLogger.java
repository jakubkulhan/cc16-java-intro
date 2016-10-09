package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.*;

/**
 * Created by honzapua on 9.10.2016.
 */

public class FileLogger extends PrintStreamLogger {

    public FileLogger() throws FileNotFoundException {
        super(new PrintStream( //
                new FileOutputStream(
                        new File("application.log"), // jmeno souboru
                        true // vzdycky pridava kdyz existuje, neprepise ho
                )
        ));
    }


}


