package com.company;

import com.company.dao.Validator;
import com.company.exception.NotEnoughMoneyException;
import com.company.service.Service;

import java.io.IOException;
import java.text.ParseException;

import java.util.Scanner;

public class Main {

    private static Service service;

    public static void main(String[] args) {
        try {
            service = new Service();

            logInPrint();
        } catch (NullPointerException | IOException | ParseException exception) {
            System.out.println("Что-то пошло не так, обратитесь к сотруднкиу банка.\n(" + exception.getMessage() + ")");
            System.exit(0);
        }
    }

    private static void logInPrint() throws IOException, NullPointerException, ParseException {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("Введите номер карты: ");
            String cardNumber = in.nextLine();

            if (cardNumber.contains("-"))
                cardNumber = cardNumber.replaceAll("-", "");

            if (!Validator.luhnValidation(cardNumber) &
                    !service.isThereSuchCard(cardNumber))
                continue;

            if (service.isTheCardBlocked(cardNumber)){
                System.out.println("Ваша карта заблокированна.");
                System.exit(0);
            }

            byte attempts = 3;

            while (attempts != 0) {
                System.out.println("Введите пин-код: ");
                int cardPassword;

                try {
                    cardPassword = in.nextInt();

                    if (!service.getAccess(cardNumber.replaceAll("-", ""), cardPassword)) {
                        attempts -= 1;
                        System.out.println("Вход не удался, попыток до блокировки карты: " + attempts);
                    } else {
                        mainMenu(cardNumber);
                    }
                } catch (Exception exception) {
                    attempts -= 1;
                    System.out.println("Вход не удался, попыток до блокировки карты: " + attempts);
                    in.next();
                    continue;
                }
            }
            System.out.println("Карта заблокированна.");
            service.cardBlocking(cardNumber);
            System.exit(0);
        }
    }

    private static void mainMenu(String cardNumber) throws IOException, NullPointerException {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("Баланс вашей карты: " + Math.round(service.getCardBalance(cardNumber) * 100.0) / 100.0);
            System.out.println("1. Пополнить счет\n2. Получить наличные\n0. Выход");
            int enter;

            try {
                enter = in.nextInt();
            } catch (Exception exception) {
                System.out.println("Введены некорректные данные.");
                in.next();
                continue;
            }

            switch (enter) {
                case 1 -> {
                    System.out.println("Введите желаемую сумму для пополнения: ");
                    double toTopUp;

                    try {
                        toTopUp = in.nextDouble();
                    } catch (Exception exception) {
                        System.out.println("Введены некорректные данные.");
                        in.next();
                        continue;
                    }

                    if (toTopUp < 1 || toTopUp > 1000000) {
                        System.out.println("Введены некорректные данные.");
                        continue;
                    }

                    service.topUpTheBalance(cardNumber, toTopUp);
                    service.topUp(toTopUp);

                    System.out.println("Баланс успешно пополнен!");
                    break;
                }
                case 2 -> {
                    System.out.println("Баланс вашей карты: " + Math.round(service.getCardBalance(cardNumber) * 100.0) / 100.0);
                    System.out.println("Введите желаемую сумму для снятия: ");
                    double toWithdraw;

                    try {
                        toWithdraw = in.nextDouble();

                        if (toWithdraw < 1){
                            System.out.println("Введены некорректные данные.");
                            continue;
                        }

                        if (!service.enoughMoneyHere(toWithdraw)){
                            System.out.println("Мы не можем выдать вам " + toWithdraw + " руб.\nПредлагаем выдать вам "
                                    + Math.round(service.getBalance() * 100.0) / 100.0 + " руб.");
                            continue;
                        }

                        if (service.withdrawalOfFunds(cardNumber, toWithdraw) & service.giveOut(toWithdraw)) {
                            System.out.println("Средства выведены!");
                        } else
                            System.out.println("Недостаточно средств на счете.");
                        break;
                    }catch (NotEnoughMoneyException exception){
                        System.out.println(exception.toString());
                        continue;
                    } catch (Exception exception) {
                        System.out.println("Введены некорректные данные.");
                        in.next();
                        continue;
                    }
                }
                case 0 -> {
                    System.out.println("Спасибо за пользование нашим банкоматом.\nДо свидания!");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Введены некорректные данные.");
                    continue;
                }
            }
        }
    }

}
