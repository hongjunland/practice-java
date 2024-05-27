package designpattern.behavior.statepattern;

import designpattern.behavior.statepattern.context.BankAccount;

public class StatePatternMain {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        account.deposit(100);
        account.withdraw(50);
        account.withdraw(70);
        account.deposit(40);
        account.withdraw(30);
    }
}
