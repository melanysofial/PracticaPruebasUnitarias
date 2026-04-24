package com.example.wallet;

public class SmartWallet {
    private double balance;
    private String userType;
    private boolean active;

    public SmartWallet(String userType) {
        this.userType = userType;
        this.balance = 0;
        this.active = true;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }

        double cashback = 0;
        if (amount > 100) {
            cashback = amount * 0.01;
        }

        double newBalance = balance + amount + cashback;

        //Regla de usuario Standard: Si el saldo excede 5000 se rechaza
        if ("Standard".equals(userType) && newBalance > 5000) {
            return false;
        }

        balance = newBalance;
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }

        if (amount > balance) {
            return false;
        }

        balance -= amount;

        //Si la cuenta queda en 0 se desactiva
        if (balance == 0) {
            active = false;
        }

        return true;
    }

    //Para pruebas unitarias
    public double getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }
}