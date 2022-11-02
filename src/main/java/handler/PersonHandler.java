package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import request.MultiPersonRequest;
import request.SinglePersonRequest;
import result.MultiPersonResult;
import result.SinglePersonResult;
import service.MultiPersonService;
import service.SinglePersonService;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class PersonHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        
        try {
            if (exchange.getRequestMethod().toUpperCase().equals("GET")) {
                String url = exchange.getRequestURI().toString();
                String[] args = url.split("/");

                //Checking to see if the request is for a single person or all user persons
                if (args.length > 2) {
                    String personID = args[2];

                    String authToken = HandlerHelper.getAuthToken(exchange);
                    SinglePersonRequest request = new SinglePersonRequest(personID, authToken);
                    SinglePersonService service = new SinglePersonService();
                    SinglePersonResult result = service.person(request);
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
                    String authToken = HandlerHelper.getAuthToken(exchange);
                    MultiPersonRequest request = new MultiPersonRequest(authToken);
                    MultiPersonService service = new MultiPersonService();
                    MultiPersonResult result = service.person(request);

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
