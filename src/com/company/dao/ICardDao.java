package com.company.dao;

import com.company.entity.Card;

import java.io.IOException;
import java.text.ParseException;

public interface ICardDao {

    private Card getCardEntity(String cardNumber){
        return null;
    }

    boolean getAccess(String cardNumber, int cardPassword) throws NullPointerException;
    boolean isThereSuchCard(String cardNumber);
    boolean withdrawalOfFunds(String cardNumber, double amount) throws NullPointerException, IOException;
    boolean isTheCardBlocked(String cardNumber) throws NullPointerException, ParseException, IOException;

    double getCardBalance(String cardNumber) throws NullPointerException;

    void topUpTheBalance(String cardNumber, double amount) throws NullPointerException, IOException;
    void cardBlocking(String cardNumber) throws NullPointerException, IOException;

}
