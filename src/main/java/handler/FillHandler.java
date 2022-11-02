package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.Headers;
import request.FillRequest;
import result.FillResult;
import service.FillService;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import java.io.IOException;

public class FillHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {
            if (exchange.getRequestMethod().toUpperCase().equals("POST")) {
                String url = exchange.getRequestURI().toString();
                String[] args = url.split("/");
                String username = args[2];

                //Checking to see if generations is specified
                FillRequest request;
                if (args.length > 3) {
                    int gens = Integer.parseInt(args[3]);
                    request = new FillRequest(username, gens);
                }
                else {
                    request = new FillRequest(username);
                }

                FillService service = new FillService();
                FillResult result = service.fill(request);

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
