package com.ankur.hackerpolice;

public class Vault {
    private int password;

    public Vault(int password){
        this.password = password;
    }

    //to check password entered
    public boolean isCorrect(int guess) {
        try {
            Thread.sleep(10); //to slow the attacker
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return guess == this.password;
    }
}
