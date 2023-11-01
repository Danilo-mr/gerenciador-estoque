package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import Model.AuthService;
import Model.User;
import Model.UserRepository;

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
            if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {

                sendLoginHTML(exchange);

            } else if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {

                Map<String, String> parameters = getParameters(exchange);
                AuthService authService = new AuthService(new UserRepository());
                boolean userAuthenticated;
                userAuthenticated = authService.authenticateUser(parameters.get("email"), parameters.get("senha"));

                if (userAuthenticated) {

                    File file = new File("src\\View\\HTMLs\\dashboard.html");
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

                } else {
                         
                }
            }
        }

        private Map<String, String> getParameters(HttpExchange exchange) throws IOException {
            Map<String, String> parameters = new HashMap<>();
            InputStream inputStream = exchange.getRequestBody();

            byte[] buffer = new byte[10000];
            int length = inputStream.read(buffer);
            String data = new String(buffer, 0, length);

            try {
                data = URLDecoder.decode(data, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            String[] pairs = data.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length > 1) {
                    parameters.put(keyValue[0], keyValue[1]);
                }
            }

            return parameters;
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
