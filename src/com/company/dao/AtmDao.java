package com.company.dao;

import com.company.entity.Atm;
import com.company.exception.NotEnoughMoneyException;

import java.io.IOException;

public class AtmDao implements IAtmDao {

    private Atm thisAtm;
    private String path = "atmStatus.txt";

    public AtmDao() throws IOException {
        thisAtm = new Atm(ReadWrite.getAtmBalance(path));
    }

    @Override
    public boolean enoughMoneyHere(double toGiveOut){
        return toGiveOut < thisAtm.getAtmBalance();
    }

    @Override
    public boolean giveOut(double toGiveOut) throws NotEnoughMoneyException, IOException {
        if (!thisAtm.giveOutMoney(toGiveOut))
            throw new NotEnoughMoneyException();

        ReadWrite.overwriteTheAtmBalance(path, thisAtm.getAtmBalance());
        return true;
    }

    @Override
    public void topUp(double toTopUp) throws IOException {
        thisAtm.topUpBalance(toTopUp);
        ReadWrite.overwriteTheAtmBalance(path, thisAtm.getAtmBalance());
    }

    @Override
    public double getBalance() {
        return thisAtm.getAtmBalance();
    }

}
