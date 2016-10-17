package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.*;


/**
 * Created by lenka.salacova on 10/3/2016.
 */
public class FileLogger extends AbstractLogger implements Closeable{

    private PrintStream printStream;
    protected String currentName;
    private FormatterInterface formatterInterface;

    public FileLogger(String fileName, FormatterInterface formatterInterface) {
        currentName = getFileName(fileName);

        createPrintStream(new File(currentName));
        this.formatterInterface = formatterInterface;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        //this.printStream.printf("[%s]: %s\n", level.name(), message);
        this.printStream.println(formatterInterface.format(level, message));
    }

    @Override
    public void close() throws IOException {
        printStream.close();
    }

    protected String getFileName(String fileName){
        return fileName+".log";

    }

    protected void createPrintStream(File file){

        try {
            if (printStream != null)
                printStream.close();
            this.printStream = new PrintStream(new FileOutputStream(file), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}


