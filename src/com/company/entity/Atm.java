package com.company.entity;

public class Atm {

    private double atmBalance;

    public Atm(double atmBalance){
        this.atmBalance = atmBalance;
    }

    public double getAtmBalance(){
        return atmBalance;
    }

    public void topUpBalance(double toTopUp) {
        atmBalance += toTopUp;
    }

    public boolean giveOutMoney(double toGiveOut) {
        if (toGiveOut > atmBalance)
            return false;

        atmBalance -= toGiveOut;
        return true;
    }

}
