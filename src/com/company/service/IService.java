package com.company.service;

import com.company.exception.NotEnoughMoneyException;

import java.io.IOException;
import java.text.ParseException;

public interface IService {

    boolean enoughMoneyHere(double toGiveOut);
    boolean giveOut(double toGiveOut) throws NotEnoughMoneyException, IOException;
    void topUp(double toTopUp) throws IOException;
    double getBalance();

    boolean getAccess(String cardNumber, int cardPassword) throws NullPointerException;
    boolean isThereSuchCard(String cardNumber);
    boolean withdrawalOfFunds(String cardNumber, double amount) throws NullPointerException, IOException;
    boolean isTheCardBlocked(String cardNumber) throws NullPointerException, ParseException, IOException;

    double getCardBalance(String cardNumber) throws NullPointerException;

    void topUpTheBalance(String cardNumber, double amount) throws NullPointerException, IOException;
    void cardBlocking(String cardNumber) throws NullPointerException, IOException;

}
