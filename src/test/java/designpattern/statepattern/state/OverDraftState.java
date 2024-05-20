package designpattern.statepattern.state;

import designpattern.statepattern.context.BankAccount;

public class OverDraftState implements AccountState{
    @Override
    public void deposit(BankAccount account, double amount) {
        account.setBalance(account.getBalance() + amount);
        System.out.println("입금: " + amount + ", 잔액: "+ account.getBalance());
        if(account.getBalance() >= 0){
            System.out.println("일반 상태로 전환합니다.");
            account.setAccountState(new NormalState());
        }
    }

    @Override
    public void withdraw(BankAccount account, double amount) {
        System.out.println("초과 인출 상태입니다. ");
        account.setBalance(account.getBalance() - amount);
        System.out.println("출금: "+ amount+ ", 잔액: " + account.getBalance());
    }
}
