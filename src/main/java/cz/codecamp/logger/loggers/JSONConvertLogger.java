package cz.codecamp.logger.loggers;

import com.google.gson.Gson;
import cz.codecamp.logger.FormatedMessage;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class JSONConvertLogger {

    private Gson gson = new Gson();

    String formatToJson(LogLevelEnum lvl, long ts, String msg){
        FormatedMessage formatedMessage = new FormatedMessage(lvl,ts,msg);
        String str = gson.toJson(formatedMessage);
        return str;
    }
}
