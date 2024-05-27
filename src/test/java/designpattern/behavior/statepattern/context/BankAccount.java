package designpattern.behavior.statepattern.context;

import designpattern.behavior.statepattern.state.AccountState;
import designpattern.behavior.statepattern.state.NormalState;

public class BankAccount {
    private AccountState accountState;
    private double balance;

    public BankAccount(){
        this.accountState = new NormalState();
        this.balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountState(AccountState accountState) {
        this.accountState = accountState;
    }

    public void deposit(double amount){
        accountState.deposit(this, amount);
    }
    public void withdraw(double amount){
        accountState.withdraw(this, amount);
    }
}
