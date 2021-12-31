package com.ankur.threadjoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FactorialOfList {
    public static void main(String[] args) throws InterruptedException {
        List<Long> inputNumbers = Arrays.asList(10L,123L,3456L,7891L,12L,12121L);

        List<FactorialThread> listOfThreads = new ArrayList<>(inputNumbers.size());
        for (Long input : inputNumbers){
            listOfThreads.add(new FactorialThread(input));
        }

        for(Thread thread: listOfThreads){
            thread.setDaemon(true);
            thread.start();
        }
        for(Thread thread: listOfThreads){
            thread.join(); ///this will force the main thread to finish all the the threads in the list.. 10ms - time we are willing to wait.
        }

//        Thread.sleep(10);

        for(int i=0;i<inputNumbers.size();i++){
            FactorialThread factorialThread = listOfThreads.get(i);
            if(factorialThread.getIsFinished()){
                System.out.println("Factorial of "+factorialThread.getInput() +" is "+ factorialThread.getOutput());
            }
            else {
                System.out.println("computation is still in progress for "+factorialThread.getInput());
            }
        }

    }
}
