package com.company.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Card {

    private final String cardNumber;
    private final int cardPassword;
    private double cardBalance;
    private boolean cardState;
    private String cardUnlockDate;

    public Card(String cardNumber, int cardPassword, double cardBalance, boolean cardState){
        this.cardNumber = cardNumber;
        this.cardPassword = cardPassword;
        this.cardBalance = cardBalance;
        this.cardState = cardState;
    }

    public Card(String cardNumber, int cardPassword, double cardBalance, boolean cardState, String cardUnlockDate){
        this.cardNumber = cardNumber;
        this.cardPassword = cardPassword;
        this.cardBalance = cardBalance;
        this.cardState = cardState;
        this.cardUnlockDate = cardUnlockDate;
    }

    private void roundBalance(){
        cardBalance = Math.round(cardBalance * 100.0) / 100.0;
    }

    public String getCardNumber(){
        return cardNumber.replaceAll("-", "");
    }

    public boolean getAccess(String cardNumber, int cardPassword){
        return getCardNumber().equals(cardNumber) & this.cardPassword == cardPassword;
    }

    public double getCardBalance() {
        return cardBalance;
    }

    public void topUpTheBalance(double amount) {
        cardBalance += amount;
        roundBalance();
    }

    public boolean withdrawalOfFunds(double amount) {
        if (cardBalance - amount < 0)
            return false;

        cardBalance -= amount;
        roundBalance();
        return true;
    }

    public boolean getCardState() {
        return cardState;
    }

    public void cardBlocking() {
        cardState = true;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        cardUnlockDate = new SimpleDateFormat("yyyyMMdd_HHmmss").format(calendar.getTime());
    }

    public void cardUnlock(){
        cardState = false;
        cardUnlockDate = null;
    }

    public String getCardUnlockDate(){
        return cardUnlockDate;
    }

    @Override
    public String toString() {
        if (cardState)
            return cardNumber + ' ' + cardPassword + ' ' + cardBalance + ' ' + cardState + ' ' + cardUnlockDate + '\n';

        return cardNumber + ' ' + cardPassword + ' ' + cardBalance + ' ' + cardState + '\n';
    }
}
