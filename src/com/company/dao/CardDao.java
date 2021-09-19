package com.company.dao;

import com.company.entity.Card;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CardDao implements ICardDao {

    private final ArrayList<Card> cardsDatabase;
    private final String dataPath = "database.txt";

    public CardDao() throws IOException {
        cardsDatabase = ReadWrite.loadBase(dataPath);
    }

    private Card getCardEntity(String cardNumber){
        return cardsDatabase.stream()
                .filter(card -> card.getCardNumber().equals(cardNumber))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean getAccess(String cardNumber, int cardPassword) throws NullPointerException {
        return getCardEntity(cardNumber).getAccess(cardNumber, cardPassword);
    }

    @Override
    public double getCardBalance(String cardNumber) throws NullPointerException {
        return getCardEntity(cardNumber).getCardBalance();
    }

    @Override
    public boolean isThereSuchCard(String cardNumber) {
        return getCardEntity(cardNumber) != null;
    }

    @Override
    public void topUpTheBalance(String cardNumber, double amount) throws NullPointerException, IOException {
        getCardEntity(cardNumber).topUpTheBalance(amount);
        ReadWrite.overwriteTheDatabase(dataPath, cardsDatabase);
    }

    @Override
    public boolean withdrawalOfFunds(String cardNumber, double amount) throws NullPointerException, IOException {
        if (getCardEntity(cardNumber).withdrawalOfFunds(amount)) {
            ReadWrite.overwriteTheDatabase(dataPath, cardsDatabase);
            return true;
        }

        return false;
    }

    @Override
    public void cardBlocking(String cardNumber) throws NullPointerException, IOException {
        getCardEntity(cardNumber).cardBlocking();
        ReadWrite.overwriteTheDatabase(dataPath, cardsDatabase);
    }

    @Override
    public boolean isTheCardBlocked(String cardNumber) throws NullPointerException, ParseException, IOException {
        if (getCardEntity(cardNumber).getCardState()){
            if (Calendar.getInstance().getTime().after(new SimpleDateFormat("yyyyMMdd_HHmmss").parse(getCardEntity(cardNumber).getCardUnlockDate()))) {
                getCardEntity(cardNumber).cardUnlock();
                ReadWrite.overwriteTheDatabase(dataPath, cardsDatabase);
                return false;
            }

            return true;
        }

        return false;
    }
}
