package com.ankur.httpbookserch;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class WordCountHandler implements HttpHandler {

    private String book;

    public  WordCountHandler(String book){
        this.book = book;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String query = httpExchange.getRequestURI().getQuery();
        String[] keyValue = query.split("=");
        String action = keyValue[0];
        String inputWord = keyValue[1];

        //the input request - localhost:8080/search?word=test
        if(!action.equals("word")){
            httpExchange.sendResponseHeaders(400,0); // if the word is not found then we return 400
        }else{
            long count = countWords(inputWord);

            //covert the count value into ByteStream to be sent over the network
            byte[] response = Long.toString(count).getBytes(StandardCharsets.UTF_8);
            httpExchange.sendResponseHeaders(200, response.length);
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(response);
            outputStream.close();

        }
    }

    private long countWords(String word) {
        long count = 0;
        int index = 0;

        //here we will check the index of the book where the word matches, if it is negative , then the word is not found

        while(index >=0){
            index = book.indexOf(word,index);
            if(index >=0){
                count++;
                index++;
            }
        }
        System.out.println("count of "+word+" is, "+count);
        return count;
    }
}
