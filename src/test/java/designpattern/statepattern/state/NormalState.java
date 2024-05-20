package designpattern.statepattern.state;

import designpattern.statepattern.context.BankAccount;

public class NormalState implements AccountState{
    @Override
    public void deposit(BankAccount account, double amount) {
        account.setBalance(account.getBalance() + amount);
        System.out.println("입금: "+ amount+ ", 잔액: " + account.getBalance());
    }

    @Override
    public void withdraw(BankAccount account, double amount) {
        account.setBalance(account.getBalance() - amount);
        System.out.println("출금: "+ amount+ ", 잔액: " + account.getBalance());
        if(account.getBalance() < 0){
            System.out.println("예금이 부족하므로 초과 인출상태로 전환합니다.");
            account.setAccountState(new OverDraftState());
        }
    }
}
