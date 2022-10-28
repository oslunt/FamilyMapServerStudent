package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import request.MultiEventRequest;
import request.SingleEventRequest;
import result.MultiEventResult;
import result.SingleEventResult;
import service.MultiEventService;
import service.SingleEventService;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class EventHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {
            if (exchange.getRequestMethod().toUpperCase().equals("GET")) {
                String url = exchange.getRequestURI().toString();
                String[] args = url.split("/");

                if (args.length > 2) {
                    String eventID = args[2];

                    String authToken = HandlerHelper.getAuthToken(exchange);
                    SingleEventRequest request = new SingleEventRequest(eventID, authToken);
                    SingleEventService service = new SingleEventService();
                    SingleEventResult result = service.event(request);
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
                    MultiEventRequest request = new MultiEventRequest(authToken);
                    MultiEventService service = new MultiEventService();
                    MultiEventResult result = service.event(request);

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
