package cz.codecamp.logger.loggers;


import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class FileLogger implements PragmaticLoggerInterface {

    private PrintStream stream;

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");

    private Timer timer = new Timer();

    public FileLogger(Time time) {
        createStream();
        init(time);
    }

    private void createStream(){
        try {
            if(stream != null){
                stream.close();
            }
            stream = new PrintStream(new FileOutputStream(new File(df.format(new Date()) + ".log")), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void init(Time time){

            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    createStream();
                    init(time);
                }
            };

         timer.schedule(timerTask, time.getTime());

    }

    @Override
    public void log(LogLevelEnum level, String message) {
        stream.printf(formatter.format(level, message));
    }

    public enum Time {

        EVERY5MINUTES(5 * 60 * 1000),
        EVERY24HOURS(24 * 60 * 60 * 1000),
        EVERY10SECONDS(10 * 1000);

        private int time;

        Time(int time){
           this.time = time;
        }

        public int getTime(){
            return time;
        }

    }
}
