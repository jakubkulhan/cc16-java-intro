package cz.codecamp.logger.loggers;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dmachace on 16.10.2016.
 */
public class FileHandler {

    private static Properties props = Properties.INSTANCE;
    private static File file;
    private static String fileName;
    private static Logger logger = new Logger();


    public void write (String content) {
        if (new File(props.getDirectory()).isDirectory()) {
            try {
                fileName = getFileName();
                file = new File(fileName);
                if (!file.exists()) {
                    file.createNewFile();
                    logger.system("New log file created (" + file.getName() + ")");

                    if (props.getDeleteOldLogs().equals("Y")) {
                        this.deleteOldFiles(props.getDeleteOldLogsTime());
                    }
                }

                Writer output;
                output = new BufferedWriter(new FileWriter(fileName, true));
                output.append(content);
                output.append("\r\n");
                output.close();
            } catch (IOException e) {
                logger.system("IO Exception - " + e.toString());
            }
        } else {
            logger.system("The directory " + props.getDirectory() + " does not exist");
        }
    }

    public void systemWrite(String content) {
        Writer output;
        try {
            output = new BufferedWriter(new FileWriter(props.getSystemLogFileName(), true));
            output.append(content);
            output.append("\r\n");
            output.close();
        }
        catch(IOException e) {
            System.out.print("sys log not accessible: " + e);
        }
    }

    public String getFileName() {
        DateFormat dateFormat = new SimpleDateFormat(props.getDateSuffixFormat());

        Date date = new Date();
        String directory = props.getDirectory();
        fileName = directory + props.getFileName() + dateFormat.format(date)+ ".log";
        return fileName;
    }



    public void deleteOldFiles(long daysBack) {

        logger.system("Scanning for file then " + props.getDeleteOldLogsTime() + " days to delete them");

        File directory = new File(props.getDirectory());
        if(directory.exists()){

            File[] listFiles = directory.listFiles();
            for(File listFile : listFiles) {
                if (listFile.isFile() && listFile.getName().contains(props.getFileName()))
                {
                    long diff = new Date().getTime() - listFile.lastModified();
                    long cutoff = (daysBack * (24 * 60 * 60 * 1000));
                    if (diff > cutoff) {
                        listFile.delete();

                        logger.system("File " + listFile.getName() + " deleted for being too old");
                    }
                }
            }
        }
        else {

            logger.system("Cannot delete old logs. Directory for files does not exist");

        }
    }

    public String getSysLogFilePath() {
        return props.getSystemLogFileName();
    }
}