package cz.codecamp.logger.formaters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.Message;

import java.text.SimpleDateFormat;

public class JsonFormatter implements FormatterInterface {

    class JsonLog {
        private String lvl;
        private String ts;
        private String msg;

        public JsonLog(String lvl, String ts, String msg){
            this.lvl = lvl;
            this.ts = ts;
            this.msg = msg;
        }

        public String getLvl() {
            return lvl;
        }

        public String getTs() {
            return ts;
        }

        public String getMsg() {
            return msg;
        }
    }

    private ObjectMapper mapper;

    public JsonFormatter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public String format(Message message) {
        String ts = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(message.getTimestamp());
        JsonLog log = new JsonLog(message.getLevel().name(), ts, message.getMessage());
        String jsonString = "";
        try {
             jsonString = mapper.writeValueAsString(log);
        } catch (JsonProcessingException exception) {
            //TODO: use custom exception class
            throw new RuntimeException("JSON string couldn't be created from Log object.");
        }
        return jsonString;
    }
}
