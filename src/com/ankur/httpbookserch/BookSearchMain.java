package com.ankur.httpbookserch;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BookSearchMain {

    private static final String INPUT_FILE = "src/com/ankur/httpbookserch/book.txt";
    private static final int NUMBER_OF_THREADS = 1;

    public static void main(String[] args) throws IOException {

        //Read the book from the file and convert it into String
        String book = new String(Files.readAllBytes(Paths.get(INPUT_FILE)));

        //start the HttpServer
        startHttpServer(book);
    }

    private static void startHttpServer(String book) throws IOException {
        //it takes the port to listen 8080 and the size of the queue - 0 [this is because we want the requests to get to the thread pool queue]
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080),0);
        httpServer.createContext("/search", new WordCountHandler(book));

        Executor executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        httpServer.setExecutor(executor);
        httpServer.start();
    }


}
