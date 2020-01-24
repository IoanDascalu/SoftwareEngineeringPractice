package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * @throws IllegalArgumentException if withdraw is negative
     * @throws IllegalArgumentException if withdraw is larger than amount contained
     */
    public void withdraw (double amount)  {
        balance -= amount;

    }


    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1){
            return false;
        }
        String prefixString = email.substring(0,email.indexOf('@'));
        String domainString = email.substring(email.indexOf('@'));
        if (prefixString.equals("@")){ //if nothing in prefix
            return false;
        }
        if (prefixString.indexOf('.') != -1){ //if prefix has a .
            return false;
        }
        else if (domainString.equals("@")){ //if nothing in domain
            return false;
        }
        else if (domainString.indexOf('.') == -1){ //if no dot
            return false;
        }
        else if (domainString.indexOf('.') == domainString.indexOf('@')+1){ //if nothing before dot
            return false;
        }
        else if (domainString.indexOf('.') == domainString.length()-1){ //if dot is last char, nothing after it
            return false;
        }
        else {
            return true;
        }
    }
}
