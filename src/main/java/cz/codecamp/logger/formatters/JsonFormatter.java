package cz.codecamp.logger.formatters;

import com.google.gson.Gson;
import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;

/**
 * Created by micha on 05.10.2016.
 */
public class JsonFormatter implements FormatterInterface {
    @Override
    public String format( LogLevelEnum level, String message ) {
        Gson gson = new Gson();
        Row row = new Row( level.name(), System.currentTimeMillis(), message );
        return gson.toJson( row );
    }

    @Override
    public String format( LogLevelEnum level, String message, String callingClass, int callingLineNumber ) {
        Gson gson = new Gson();
        EnhancedRow row = new EnhancedRow( level.name(), System.currentTimeMillis(), message, callingClass, callingLineNumber );
        return gson.toJson( row );
    }

    private class Row {
        String lvl;
        long ts;
        String msg;

        public Row( String lvl, long ts, String msg ) {
            this.lvl = lvl;
            this.ts = ts;
            this.msg = msg;
        }
    }

    private class EnhancedRow extends Row {
        String cls;
        int line;

        public EnhancedRow( String lvl, long ts, String msg, String cls, int line ) {
            super( lvl, ts, msg );
            this.cls = cls;
            this.line = line;
        }
    }
}
