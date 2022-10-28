package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.Headers;

import java.io.*;
import java.net.HttpURLConnection;

import java.io.IOException;
import java.nio.file.Files;

public class FileDefaultHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {
            if (exchange.getRequestMethod().toUpperCase().equals("GET")) {
                String urlPath = exchange.getRequestURI().toString();
                if (urlPath == null || urlPath.equals("/")) {
                    urlPath = "/index.html";
                }

                String filePath = "web" + urlPath;
                File file = new File(filePath);

                if (file.exists()) {
                    exchange.sendResponseHeaders(200, 0L);
                    OutputStream respBody = exchange.getResponseBody();
                    Files.copy(file.toPath(), respBody);
                    respBody.close();
                }
                else {
                    exchange.sendResponseHeaders(404, 0L);
                    OutputStream respBody = exchange.getResponseBody();
                    File notFound = new File("web/HTML/404.html");
                    Files.copy(notFound.toPath(), respBody);
                    exchange.getResponseBody().close();
                }
            }
            else {
                exchange.sendResponseHeaders(405, 0L);
                exchange.getResponseBody().close();
            }
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();

            // Display/log the stack trace
            e.printStackTrace();
        }
    }
}
