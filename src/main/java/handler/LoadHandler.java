package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import request.LoadRequest;
import result.LoadResult;
import service.LoadService;

import java.io.*;
import java.net.HttpURLConnection;

import java.io.IOException;

public class LoadHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {

            if (exchange.getRequestMethod().toUpperCase().equals("POST")) {
                InputStream reqBody = exchange.getRequestBody();
                String reqData = HandlerHelper.readString(reqBody);

                LoadRequest request = (LoadRequest) HandlerHelper.decode(reqData, LoadRequest.class);
                LoadService service = new LoadService();
                LoadResult result = service.load(request);
                String json = HandlerHelper.encode(result);

                if(result.isSuccess()) {
                    exchange.sendResponseHeaders(200, 0L);
                    OutputStream respBody = exchange.getResponseBody();
                    HandlerHelper.writeString(json, respBody);
                    respBody.close();
                }
                else {
                    exchange.sendResponseHeaders(400,0L);
                    OutputStream respBody = exchange.getResponseBody();
                    HandlerHelper.writeString(json, respBody);
                    respBody.close();
                }

            }
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();

            // Display/log the stack trace
            e.printStackTrace();
        }
    }
}
