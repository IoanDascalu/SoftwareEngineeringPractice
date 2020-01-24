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
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
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
        assertTrue(BankAccount.isEmailValid( "a@b.q")); //Border
        //Missing border tests for special (invalid) characters like #$%&^*,
            //invalid domains (valid would be like gmail.com, ithaca.edu, etc)
            //more dots in the prefix, no dot in the domain

    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}