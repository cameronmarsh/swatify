package edu.swarthmore.cs.cs71.swatify.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonUtil {
    private static final Gson gson = new Gson();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(Class<T> objectClass, String json) {
        return gson.fromJson(json, objectClass);
    }

    public static <T> List<T> fromJsonArray(Type listType, String json) {
        return gson.fromJson(json, listType);
    }
}
