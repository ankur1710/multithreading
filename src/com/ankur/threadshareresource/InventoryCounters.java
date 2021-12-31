package com.ankur.threadshareresource;

public class InventoryCounters {
    private int count;
    private final Object lock = new Object();

    public  void increaseCounter(){
        synchronized (this.lock){
            count++;
        }

    }

    public  void decreaseCounter(){
        synchronized (this.lock){
            count--;
        }
    }

    public  int getCount(){
        synchronized (this.lock){
            return count;
        }
    }
}
