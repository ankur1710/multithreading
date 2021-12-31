package com.ankur.threadshareresource;

public class InventoryCounterMain {

    public static void main(String[] args) throws InterruptedException {

        InventoryCounters inventoryCounters = new InventoryCounters();
        IncreaseCounterThread increaseCounterThread = new IncreaseCounterThread(inventoryCounters);
        DecreaseCounterThread decreaseCounterThread = new DecreaseCounterThread(inventoryCounters);

        //when we run both the threads at the same time , the value of the count will be inconsistent.
        increaseCounterThread.start();
        decreaseCounterThread.start();

        decreaseCounterThread.join();
        increaseCounterThread.join();

        System.out.println("get the Count "+ inventoryCounters.getCount());
    }


}
