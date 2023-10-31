package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Router {

    public Router(HttpServer server) {
        server.createContext("/", new HomeRouter());
        server.createContext("/home", new HomeRouter());
        server.createContext("/login", new LoginRouter());
        server.createContext("/cadastro", new CadastroRouter());
    }

    public class HomeRouter implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            sendHomeHTML(exchange);
        }

        private void sendHomeHTML(HttpExchange exchange) throws IOException {
            
            File file = new File("src\\View\\HTMLs\\home.html");

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

    public class LoginRouter implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            sendLoginHTML(exchange);
        }

        private void sendLoginHTML(HttpExchange exchange) throws IOException {
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

    public class CadastroRouter implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            sendCadastroHTML(exchange);
        }

        private void sendCadastroHTML(HttpExchange exchange) throws IOException {
            File file = new File("src\\View\\HTMLs\\cadastro.html");

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
