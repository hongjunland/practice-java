package designpattern.statepattern.state;

import designpattern.statepattern.context.BankAccount;

public interface AccountState {
    void deposit(BankAccount account, double amount);
    void withdraw(BankAccount account, double amount);
}
