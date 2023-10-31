package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class Router implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        
        if (path.equals("/")) {

            exchange.sendResponseHeaders(200, "home".length());
            OutputStream os = exchange.getResponseBody();
            os.write("home".toString().getBytes());
            os.close();

        } else if (path.equals("/login")) {
            System.out.println("Solicitando a pagina Login...");
            
            File file = new File("src\\View\\HTMLs\\login.html");
            
            exchange.sendResponseHeaders(200, file.length());

            OutputStream output = exchange.getResponseBody();
            FileInputStream fs = new FileInputStream(file);
            final byte[] buffer = new byte[0x10000];
            int count = 0;
            while ((count = fs.read(buffer)) >= 0) {
                output.write(buffer, 0, count);
            }
            output.flush();
            output.close();
            fs.close();

        }

    }


}
