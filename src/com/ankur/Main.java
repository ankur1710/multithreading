package com.ankur;

public class Main {

    public static void main(String[] args) {
	 Thread thread = new Thread(new Runnable() {
         @Override
         public void run() {
             System.out.println(Thread.currentThread().getName());
         }
     });
	 thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
         @Override
         public void uncaughtException(Thread t, Throwable e) {
             System.out.println("a critical Error happened.");
         }
     });
        System.out.println(Thread.currentThread().getName());
	 thread.start();
        System.out.println(Thread.currentThread().getName());
    }
}
