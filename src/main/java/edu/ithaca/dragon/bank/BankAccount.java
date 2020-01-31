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
        } else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
        if(isAmountValid(startingBalance)){
            this.balance = startingBalance;
        } else {
            throw new IllegalArgumentException("The starting balance" + startingBalance + "is invalid, cannot create account");
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
        if (!isAmountValid(amount)){
            throw new IllegalArgumentException("Invalid amount, cannot withdraw.");
        }
        if (amount > getBalance()) {
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
        if (amount<0){
            return false;
        }
        String amountStr = Double.toString(amount);
        String[] amountList = amountStr.split("\\.", 2);
        if (amountList[1].length()>2) {
            return false;
        }
        return true;
    }

    /**
     * @throws IllegalArgumentException if deposit is negative or has more than 2 numbers after the decimal
     * @post Increase the balance by amount if amount is non-negative
     */

    public void deposit(double amount) {
        if (!isAmountValid(amount)){
            throw new IllegalArgumentException("Invalid amount, cannot deposit.");
        }
        balance += amount;


    }
    /**
     * @throws IllegalArgumentException if transfer amount is negative or has more than 2 numbers after the decimal
     * @post Increase the balance of anouther account by amount if amount is non-negative and decreases it from another account
     */


    public void transfer(double amount, BankAccount accountName) {
        this.withdraw(amount);
        accountName.deposit(amount);
    }

}
