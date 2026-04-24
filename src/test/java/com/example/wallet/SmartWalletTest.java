package com.example.wallet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SmartWalletTest {

    private SmartWallet wallet;

    @BeforeEach
    void inicializarBilletera() {
        wallet = new SmartWallet("Standard");
    }

    @Test
    void depositoValidoDebeAumentarSaldo() {
        assertEquals(true, wallet.deposit(50));
        assertEquals(50, wallet.getBalance());
    }

    @Test
    void depositoMayorA100DebeAplicarCashback() {
        assertEquals(true, wallet.deposit(200));
        assertEquals(202, wallet.getBalance());
    }

    @Test
    void retiroValidoDebeDisminuirSaldo() {
        wallet.deposit(90);

        assertEquals(true, wallet.withdraw(40));
        assertEquals(50, wallet.getBalance());
        assertEquals(true, wallet.isActive());
    }

    @Test
    void depositoExactamente100NoAplicaCashback() {
        assertEquals(true, wallet.deposit(100));
        assertEquals(100, wallet.getBalance());
    }

    @Test
    void depositoDebeRespetarLimiteMaximoStandard() {
        for (int i = 0; i < 50; i++) {
            assertEquals(true, wallet.deposit(100));
        }

        assertEquals(5000, wallet.getBalance());
        assertEquals(false, wallet.deposit(1));
        assertEquals(5000, wallet.getBalance());
    }

    @Test
    void depositoNegativoDebeFallar() {
        assertEquals(false, wallet.deposit(-10));
        assertEquals(0, wallet.getBalance());
    }

    @Test
    void retiroNegativoDebeFallar() {
        wallet.deposit(40);

        assertEquals(false, wallet.withdraw(-5));
        assertEquals(40, wallet.getBalance());
    }

    @Test
    void retiroMayorQueSaldoDebeFallar() {
        wallet.deposit(40);

        assertEquals(false, wallet.withdraw(50));
        assertEquals(40, wallet.getBalance());
        assertEquals(true, wallet.isActive());
    }

    @Test
    void retiroExactoDebeInactivarCuenta() {
        wallet.deposit(80);

        assertEquals(true, wallet.withdraw(80));
        assertEquals(0, wallet.getBalance());
        assertEquals(false, wallet.isActive());
    }
}