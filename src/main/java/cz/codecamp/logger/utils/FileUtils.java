package cz.codecamp.logger.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by honzapua on 16.10.2016.
 */
public final class FileUtils {

    public static String extractFileName(String fileName) {
        int i = fileName.lastIndexOf('.'); // kdyz nenajde tecku je to jen jmeno souboru bez tecky

        if (i == -1) {
            return fileName;
        }

        return fileName.substring(0,i);

    }

    public static String extractFileExtension(String fileName) {
        int i = fileName.lastIndexOf('.');

        if (i == -1) {
            return null; // nenasel jsem priponu vracim null
        }

       return fileName.substring(i + 1);
    }

    /**
     *
     * @param fileName
     * @param fileExt pripona musi byt vcetne tecky
     * @param timeStamp
     * @return
     */
    public static String createTimeStampedFileName(String fileName, String fileExt, Date timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHH0000");
        String ts = sdf.format(timeStamp);

        return String.format("%s-%s%s", fileName, ts, fileExt);
    }

    /**
     * Utility class. Nesmi se instanciovat
     */
    private FileUtils() {
    }
}
