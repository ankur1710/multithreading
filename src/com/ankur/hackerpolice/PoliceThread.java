package com.ankur.hackerpolice;

public class PoliceThread extends Thread{

    @Override
    public void run(){
        for(int i=10;i>=0;i--){
            try {
                Thread.sleep(1000);
                System.out.println(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("game over Hackers ");
        System.exit(0);
    }
}
