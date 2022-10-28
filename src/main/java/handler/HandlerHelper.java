package handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public class HandlerHelper {
    public static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static Object decode(String json, Type typeofT) {
        return GSON.fromJson(json, typeofT);
    }

    public static String encode(Object source){
        return GSON.toJson(source);
    }

    public static String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];

        int len;
        while((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }

        return sb.toString();
    }

    public static void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(sw);
        bw.write(str);
        bw.flush();
    }

    public static String getAuthToken(HttpExchange exchange) {
        Headers reqHeaders = exchange.getRequestHeaders();
        return reqHeaders.containsKey("Authorization") ? reqHeaders.getFirst("Authorization") : null;
    }
}
