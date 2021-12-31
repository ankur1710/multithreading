package com.ankur.threadstop;

import java.io.IOException;

public class InterruptExample {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new BlockingTask());
        System.out.println(Thread.currentThread().getName());
//        thread.setDaemon(true);
        thread1.start();
//        thread.interrupt();
        System.out.println(Thread.currentThread().getName());

    }

    private static class BlockingTask implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(100000); // this denotes a long processing thread
            } catch (InterruptedException e) {
                System.out.println("Exiting Blocking thread.");
            }
        }
    }

}
