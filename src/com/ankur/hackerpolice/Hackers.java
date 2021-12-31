package com.ankur.hackerpolice;


//this class will encapsulate all the common features of the hacker Threads.
public abstract class Hackers extends Thread {
    protected Vault vault;

    public Hackers (Vault vault){
        this.vault = vault;
        this.setName(this.getClass().getSimpleName());
        this.setPriority(Thread.MAX_PRIORITY);
    }

    @Override
    public void start(){
        System.out.println("starting thread "+this.getName());
        super.start();
    }

    protected void guessedBy(){
        System.out.println("Guessed by  "+this.getName());
    }


}
