package designpattern.behavior.statepattern.state;

import designpattern.behavior.statepattern.context.BankAccount;

public class FrozenState implements AccountState{
    @Override
    public void deposit(BankAccount account, double amount) {
        System.out.println("동결상태이므로 입금이 불가능합니다.");
    }

    @Override
    public void withdraw(BankAccount account, double amount) {
        System.out.println("동결상태이므로 출금이 불가능합니다.");
    }
}
