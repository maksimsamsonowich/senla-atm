package com.company.exception;

public class NotEnoughMoneyException extends Exception {

    @Override
    public String toString() {
        return "В банкомате недостаточно средств.";
    }
}
