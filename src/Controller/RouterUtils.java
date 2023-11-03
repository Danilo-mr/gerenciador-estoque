package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;

public class RouterUtils {

    static void sendHomeHTML(HttpExchange exchange) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void sendLoginHTML(HttpExchange exchange) throws IOException {
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

    static void sendDashboardHTML(HttpExchange exchange) throws IOException {
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
    }

    static void sendMessageLoginPage(HttpExchange exchange, String message) {
        try {
            String htmlContent = new String(Files.readAllBytes(Paths.get("src\\View\\HTMLs\\login.html")));
            htmlContent = htmlContent.replace("</h1>", "</h1><br><h4>"+message+"</h4>");
            exchange.sendResponseHeaders(200, htmlContent.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(htmlContent.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void sendMessageSignUpPage(HttpExchange exchange, String message) {
        try {
            String htmlContent = new String(Files.readAllBytes(Paths.get("src\\View\\HTMLs\\cadastro.html")));
            htmlContent = htmlContent.replace("</h1>", "</h1><br><h4>"+message+"</h4>");
            exchange.sendResponseHeaders(200, htmlContent.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(htmlContent.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void sendCadastroHTML(HttpExchange exchange) throws IOException {
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

    static Map<String, String> getParameters(HttpExchange exchange) throws IOException {
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
}
