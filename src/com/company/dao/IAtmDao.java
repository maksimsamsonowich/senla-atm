package com.company.dao;

import com.company.exception.NotEnoughMoneyException;

import java.io.IOException;

public interface IAtmDao {

    boolean enoughMoneyHere(double toGiveOut);
    boolean giveOut(double toGiveOut) throws NotEnoughMoneyException, IOException;
    void topUp(double toTopUp) throws IOException;
    double getBalance();

}
