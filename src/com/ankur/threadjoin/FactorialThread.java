package com.ankur.threadjoin;

import java.math.BigInteger;

public class FactorialThread extends Thread{

    private Long input;
    private BigInteger output;
    private Boolean isFinished = false;

    @Override
    public void run() {
    this.output = getFactorial(input);
    this.isFinished = true;
    }

    public FactorialThread(Long input){
        this.input=input;
    }

    public BigInteger getFactorial(long n){
        BigInteger temp = BigInteger.ONE;
        for(long i = n;i>0;i--){
            temp = temp.multiply(BigInteger.valueOf(i));
        }
        return temp;
    }

    public Boolean getIsFinished(){
        return isFinished;
    }
    public Long getInput(){
        return input;
    }
    public BigInteger getOutput(){
        return output;
    }


}
