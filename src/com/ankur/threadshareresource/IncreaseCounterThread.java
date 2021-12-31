package com.ankur.threadshareresource;

public class IncreaseCounterThread extends Thread{
    private InventoryCounters inventoryCounters;

    public IncreaseCounterThread(InventoryCounters inventoryCounters) {
       this.inventoryCounters=inventoryCounters;
    }

    @Override
    public void run() {
        for(int i =0;i<1000;i++){
            inventoryCounters.increaseCounter();
        }
    }
}
