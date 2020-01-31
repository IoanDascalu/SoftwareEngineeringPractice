package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance) {
        if (isEmailValid(email)) {
            this.email = email;
            this.balance = startingBalance;
        } else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    /**
     * @throws IllegalArgumentException if withdraw is negative
     * @throws IllegalArgumentException if withdraw is larger than amount contained
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw(double amount) {
        String amountStr = Double.toString(amount);
        amountStr = amountStr.substring(amountStr.indexOf('.') + 1);
        if (amount < 0 || amountStr.length() > 2) { //if amount is negative or the amount has more than two decimal places
            throw new IllegalArgumentException("Invalid withdraw amount, cannot withdraw");
        } else if (amount > getBalance()) {
            throw new IllegalArgumentException("Invalid amount, cannot withdraw more than current balance");
        }
        balance -= amount;

    }


    public static boolean isEmailValid(String email) {
        //there's probably a better way to do it than this large if-else block but I couldn't think of a way
        if (email.indexOf('@') == -1) {
            return false;
        }
        String prefixString = email.substring(0, email.indexOf('@'));
        String domainString = email.substring(email.indexOf('@'));
        if (prefixString.length() == 0) { //if nothing in prefix
            return false;
        } else if (prefixString.indexOf('.') != -1) { //if prefix has a .
            return false;
        } else if (domainString.equals("@")) { //if nothing in domain
            return false;
        } else if (domainString.indexOf('.') == -1) { //if no dot
            return false;
        } else if (domainString.contains("..")) { //if two dots in a row
            return false;
        }
        String domainString2 = email.substring(email.indexOf('.') + 1);
        if (domainString.indexOf('.') == domainString.indexOf('@') + 1) { //if nothing before dot
            return false;
        } else if (domainString.indexOf('.') == domainString.length() - 1) { //if dot is last char, nothing after it
            return false;
        } else if (domainString2.length() < 2) { //if the domain name after the dot is smaller than two chars
            return false;
        }
        String specialStr = "#$%^&*/~`"; //checking for all special characters in the email
        for (int i = 0; i < specialStr.length(); i++) {
            for (int j = 0; j < email.length(); j++) {
                if (email.charAt(j) == specialStr.charAt(i)) {
                    return false;
                }
            }
        }

        return true;

    }

    /**
     * @param amount the current amount inside the bank account
     * returns true if the amount is not negative and has 2 decimal points or less.
     */

    public static boolean isAmountValid(double amount) {
        return false;
    }
}
