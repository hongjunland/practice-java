package designpattern.behavior.statepattern.state;

import designpattern.behavior.statepattern.context.BankAccount;

public interface AccountState {
    void deposit(BankAccount account, double amount);
    void withdraw(BankAccount account, double amount);
}
