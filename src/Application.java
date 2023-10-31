import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import Controller.*;

public class Application {
    private static int server_port = 8000;
    protected static HttpServer server;

    public static void main(String[] args) {
        
        if (args.length != 0) {
            server_port = Integer.parseInt(args[0]);
        }

        try {

            server = HttpServer.create(new InetSocketAddress(server_port), 2);
            new Router(server);
            
            server.setExecutor(null); 
            server.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}
