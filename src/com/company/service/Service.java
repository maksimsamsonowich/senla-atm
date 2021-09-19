package com.company.service;

import com.company.dao.AtmDao;
import com.company.dao.CardDao;
import com.company.exception.NotEnoughMoneyException;

import java.io.IOException;
import java.text.ParseException;

public class Service implements IService {

    private static CardDao cardDao;
    private static AtmDao atmDao;

    public Service() throws IOException {
        cardDao = new CardDao();
        atmDao = new AtmDao();
    }

    @Override
    public boolean enoughMoneyHere(double toGiveOut) {
        return atmDao.enoughMoneyHere(toGiveOut);
    }

    @Override
    public boolean giveOut(double toGiveOut) throws NotEnoughMoneyException, IOException {
        return atmDao.giveOut(toGiveOut);
    }

    @Override
    public void topUp(double toTopUp) throws IOException {
        atmDao.topUp(toTopUp);
    }

    @Override
    public double getBalance() {
        return atmDao.getBalance();
    }

    @Override
    public boolean getAccess(String cardNumber, int cardPassword) throws NullPointerException {
        return cardDao.getAccess(cardNumber, cardPassword);
    }

    @Override
    public boolean isThereSuchCard(String cardNumber) {
        return cardDao.isThereSuchCard(cardNumber);
    }

    @Override
    public boolean withdrawalOfFunds(String cardNumber, double amount) throws NullPointerException, IOException {
        return cardDao.withdrawalOfFunds(cardNumber, amount);
    }

    @Override
    public boolean isTheCardBlocked(String cardNumber) throws NullPointerException, ParseException, IOException {
        return cardDao.isTheCardBlocked(cardNumber);
    }

    @Override
    public double getCardBalance(String cardNumber) throws NullPointerException {
        return cardDao.getCardBalance(cardNumber);
    }

    @Override
    public void topUpTheBalance(String cardNumber, double amount) throws NullPointerException, IOException {
        cardDao.topUpTheBalance(cardNumber, amount);
    }

    @Override
    public void cardBlocking(String cardNumber) throws NullPointerException, IOException {
        cardDao.cardBlocking(cardNumber);
    }
}
