package com.sise.android.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sise.android.pojo.Message;

import java.util.List;

/**
 * @author draven
 */
public class ReturnJsonMessage {
    public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    public static String returnMessage(Object object) {
        if (object != null) {
            if (object instanceof Integer) {
                if ((int)object > 0) {
                    return gson.toJson(new Message("200", object));
                }
            }
            if (object instanceof List) {
                if (((List) object).size() != 0) {
                    return new Gson().toJson(new Message("200", object));
                } else {
                    return gson.toJson(new Message("500", "Error"));
                }
            }
            return gson.toJson(new Message("200", object));
        }
        return gson.toJson(new Message("500", "Error"));
    }
    public static String DIYMessage(Message message) {
        return gson.toJson(message);
    }
}
