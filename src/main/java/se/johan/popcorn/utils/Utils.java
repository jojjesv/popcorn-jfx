/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.utils;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author johansvensson
 */
public class Utils {

    public static <T> List<T> map(T[] source, MapCallback<T> callback) {
        return map(Arrays.asList(source), callback);
    }
    
    public static <T> List<T> map(List<T> source, MapCallback<T> callback) {
        ArrayList<T> mapped = new ArrayList<>();

        for (T item : source) {
            mapped.add(item);
        }

        return mapped;
    }

    /**
     * Joins an array with the default separator.
     *
     * @param arr Array to join.
     * @return Joined array.
     */
    public static <T> String join(T[] arr) {
        return join(Arrays.asList(arr));
    }
    
    public static <T> String join(List<T> arr) {
        String separator = ", ";
        StringBuilder builder = new StringBuilder();

        for (int i = 0, n = arr.size() - 1; i <= n; i++) {
            builder.append(arr.get(i).toString());

            if (i < n) {
                builder.append(separator);
            }
        }

        return builder.toString();
    }

    /**
     * Splits and trims a string.
     */
    public static List<String> splitTrim(String str, String separator) {
        return map(str.split(separator), new MapCallback<String>() {
            @Override
            public String map(String original) {
                return original.trim();
            }
        });
    }

    public static Map<String, Object> getMapFromJson(JSONObject obj) {
        Map<String, Object> out = new HashMap<>();

        Iterator<Entry<String, Object>> itr = obj.entrySet().iterator();

        while (itr.hasNext()) {
            Entry<String, Object> next = itr.next();
            out.put(next.getKey(), next.getValue());
        }

        return out;
    }

    /**
     * @return An empty string if the original is null.
     */
    public static String safeString(String original) {
        if (original == null) {
            return "";
        }

        return original;
    }

    public interface MapCallback<T> {

        public T map(T original);
    }
}
