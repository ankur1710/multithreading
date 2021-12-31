package com.ankur.hackerpolice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HackerPoliceThreadPlay {
    public static final int MAX_PASSWORD = 9999; // max length of the password.

    public static void main(String[] args) {

        int password = new Random().nextInt(MAX_PASSWORD);
        System.out.println(password);
        Vault vault = new Vault(password);


        List<Thread> threadList = new ArrayList<>();
        threadList.add(new AcsendingHacker(vault));
        threadList.add(new DescendingHacker(vault));
        threadList.add(new PoliceThread());

        for (Thread t: threadList){
            t.start();
        }

    }


}
