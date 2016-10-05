package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;

import java.io.*;

/**
 * Created by dmachace on 3.10.2016.
 */
public class FileLogger implements  cz.codecamp.logger.LoggerInterface {
    @Override
    public void log(LogLevelEnum level, String message) {

        FileOutputStream fop = null;
        File file;
        String content = message;

        try {

            file = new File("newfile_cc16.txt");
            fop = new FileOutputStream(file);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            byte[] contentInBytes = content.getBytes();
            fop.write(contentInBytes);
            fop.flush();
            fop.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
