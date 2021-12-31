package com.ankur.multiexecutor;

import java.util.List;

public class MultiExecutor {

    private final List<Runnable> tasks;

    public MultiExecutor(List<Runnable> tasks){
        this.tasks = tasks;
    }

    //Execute all the tasks in different threads.
    public void executeAll(){
        for (Runnable task: tasks){
            Thread thread = new Thread(task);
            thread.start();
        }
    }


}
