package cz.codecamp.logger.loggers;

import java.io.*;

/**
 * Created by honzapua on 9.10.2016.
 */

public class FileLogger extends PrintStreamLogger {

    public FileLogger(String fileName) throws FileNotFoundException {
        super(new PrintStream( //
                new FileOutputStream(
                        new File(fileName), // jmeno souboru
                        true // vzdycky pridava kdyz existuje, neprepise ho
                )
        ));
    }


}


