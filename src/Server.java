import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {
    public static void main(String[] args) throws IOException {
        int serverPort = 8000;

        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 2);
        server.createContext("/login", new LoginHandler());
        server.setExecutor(null);
        server.start();

    }

    static class LoginHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
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

                String email = parameters.get("email");
                String senha = parameters.get("senha");

                conectarBancoDeDados("root", "root");
                
            }
        }

        public void conectarBancoDeDados(String usuario, String senha) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gerenciamento-estoque",
                "root",
                "root");

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
