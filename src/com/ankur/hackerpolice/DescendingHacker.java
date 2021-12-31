package com.ankur.hackerpolice;

public class DescendingHacker extends Hackers{
    public static final int MAX_PASSWORD = 9999; // max length of the password.
    public DescendingHacker(Vault vault) {
        super(vault);
    }

    @Override
    public void run(){
        for(int guess = MAX_PASSWORD ; guess >= 0 ;guess-- ){
            if(vault.isCorrect(guess)){
                System.out.println("Guessed the password correctly  "+ guess);
                guessedBy();
                System.exit(0);
            }
        }
    }
}
