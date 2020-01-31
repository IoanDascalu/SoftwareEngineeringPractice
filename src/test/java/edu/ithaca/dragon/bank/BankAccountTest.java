package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw(200));
        assertEquals(100, bankAccount.getBalance());
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw(-200));
        assertEquals(100, bankAccount.getBalance());
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw(200.002));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw(-2030));
    }
    @Test
    void depositTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.deposit(100);
        assertEquals(300, bankAccount.getBalance());
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(200.000));
        assertEquals(300, bankAccount.getBalance());
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-200.0));

    }


    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com")); //EP
        assertFalse(BankAccount.isEmailValid(""));       //Border
        assertFalse(BankAccount.isEmailValid( "a@b."));  //Border
        assertFalse(BankAccount.isEmailValid( "Funny")); //Border
        assertFalse(BankAccount.isEmailValid( "a.@b.com")); //Border
        assertTrue(BankAccount.isEmailValid( "a@b-c.com")); //Border
        assertTrue(BankAccount.isEmailValid( "a@b.edu")); //EP
        assertTrue(BankAccount.isEmailValid( "a@b.io")); //Border
        assertFalse(BankAccount.isEmailValid( "a@b.q")); //Border
        assertFalse(BankAccount.isEmailValid("@b.io"));
        assertFalse(BankAccount.isEmailValid("#Blessed@b.io"));
        assertFalse(BankAccount.isEmailValid("Find@b..io"));
        assertTrue(BankAccount.isEmailValid( "a@bottle.com")); //EP
        //Missing border tests for special (invalid) characters like #$%&^*,
            //invalid domains (valid would be like gmail.com, ithaca.edu, etc)
            //more dots in the prefix, no dot in the domain

    }

    @Test
    void isAmountValidTest(){
        assertTrue(BankAccount.isAmountValid(100.00));//EP
        assertTrue(BankAccount.isAmountValid(1020.99));//EP
        assertFalse(BankAccount.isAmountValid(-100.00));//EP
        assertTrue(BankAccount.isAmountValid(100.3));//EP
        assertTrue(BankAccount.isAmountValid(100));//border
        assertTrue(BankAccount.isAmountValid(0));//border
        assertFalse(BankAccount.isAmountValid(100.001));//border
        assertFalse(BankAccount.isAmountValid(100.00000001));//EP

    }


    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 100.001));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -100.01));
    }

}