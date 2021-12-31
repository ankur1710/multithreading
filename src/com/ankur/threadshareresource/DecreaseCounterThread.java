package com.ankur.threadshareresource;

public class DecreaseCounterThread extends Thread{
    private InventoryCounters inventoryCounters;

    public DecreaseCounterThread(InventoryCounters inventoryCounters) {
        this.inventoryCounters=inventoryCounters;
    }

    @Override
    public void run() {
        for(int i =0;i<1000;i++){
            inventoryCounters.decreaseCounter();
        }
    }
}
