package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.Headers;
import request.RegisterRequest;
import result.RegisterResult;
import service.RegisterService;

import java.io.*;
import java.net.HttpURLConnection;

import java.io.IOException;

public class RegisterHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (exchange.getRequestMethod().toUpperCase().equals("POST")) {
                InputStream reqBody = exchange.getRequestBody();
                String reqData = HandlerHelper.readString(reqBody);

                RegisterRequest request = (RegisterRequest) HandlerHelper.decode(reqData, RegisterRequest.class);
                RegisterService service = new RegisterService();
                RegisterResult result = service.register(request);
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
