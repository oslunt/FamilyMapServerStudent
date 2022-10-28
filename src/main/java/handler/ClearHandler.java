package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.Headers;
import result.ClearResult;
import service.ClearService;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import java.io.IOException;

public class ClearHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {
            if (exchange.getRequestMethod().toUpperCase().equals("POST")) {
                ClearService service = new ClearService();
                ClearResult result = service.clear();
                String json = HandlerHelper.encode(result);

                if(result.isSuccess()) {
                    exchange.sendResponseHeaders(200, 0L);
                    OutputStream respBody = exchange.getResponseBody();
                    HandlerHelper.writeString(json, respBody);
                    respBody.close();
                }
                else {
                    exchange.sendResponseHeaders(400, 0L);
                    OutputStream respBody = exchange.getResponseBody();
                    HandlerHelper.writeString(json, respBody);
                    respBody.close();
                }
            }
            else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();

            // Display/log the stack trace
            e.printStackTrace();
        }
    }
}
