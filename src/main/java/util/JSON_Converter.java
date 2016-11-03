/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import entity.Event;
import entity.Samarit;
import entity.User;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import net.minidev.json.JSONArray;

/**
 *
 * @author danie
 */
public class JSON_Converter {

    private static Gson  gson = new GsonBuilder().setPrettyPrinting().create();
    private DateUtils du = new DateUtils();

    public static Samarit parseSamarit(String jsonString){
        Samarit s = null;
        s=gson.fromJson(jsonString, Samarit.class);
        
        return s;
    }
    public static String jsonFromSamarit(Samarit s){
        return gson.toJson(s);
    }

    public JsonObject parseEvent(Event event) {
        JsonObject jEvent = new JsonObject();
        jEvent.addProperty("id", event.getId());
        if (event.getEventName() != null) {
            jEvent.addProperty("title", event.getEventName());
        }
        if (event.getDateStart() != null) {
            Date start = event.getDateStart();
            LocalDateTime localStart = DateUtils.asLocalDateTime(start);
            jEvent.addProperty("start", localStart.toString());
        }
        if (event.getDateEnd() != null) {
            Date end = event.getDateEnd();
            LocalDateTime localEnd = DateUtils.asLocalDateTime(end);
            jEvent.addProperty("end", localEnd.toString());
        }
        jEvent.addProperty("allDay", event.isAllDay());

        return jEvent;
    }

    public String parseEvents(List<Event> events) {
        JSONArray jevents = new JSONArray();
        if (events != null) {
            for (Event event : events) {
                jevents.add(parseEvent(event));
            }

        }
        return gson.toJson(jevents);
    }
}
