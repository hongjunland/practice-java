## State Pattern
"State Pattern"은 객체의 상태에 따라 객체의 행동을 변경할 수있도록 하는 디자인 패턴입니다.
객체는 내부 상테에 따라 다르게 행동하며, 상태 변경은 객체 내부에서 캡슐화됩니다. 이를 통해 상태 전환을 관리하고 코드의 복잡성을 줄일 수 있습니다.

## 구성 요소
* State Interface: 상태별 행동을 정의
* ConcreteState classes: State Interface의 구현체
* Context class: 현재 상태를 관리하고 상태별 행동을 위임(delegate)합니다.

## 장점
* 유연성 증가: 상태 전환 로직이 상태 객체 내에 캡슐화되어 있기 때문에, 상태별 행동과 전환 로직을 쉽게 관리할 수 있다.
* 가독성 향상: 상태별 로직이 각 상태 클래스에 분리되어 있어 코드의 가독성과 유지보수성 향상

## 적합한 사용 사례
* Editor: 텍스트의 상태(편집모드, 읽기 전용모드 등)에 따라 다르게 행동
* 비디오 플레이어: 재생, 일시 정지, 정지 등의 상태에 따라 다른 행동을 수행할 때
* 게임 캐릭터 상태: 걷기, 뛰기등에 따라 다른 행동을 수행하도록 할 때
* ATM: 카드 삽입, PIN 입력상태, 거래 처리 상태 등에 따라 다르게 행동할 때
* 세션 관리: 로그인, 로그아웃, 타임아웃 등의 상태에 따라 다르게 동작해야 할 때
* DBMS의 트랜잭션 관리:시작, 진행중, 커밋, 롤백등의 상태에 따라 다른 작업을 수행해야 할 때 


## 예시 코드

* AccountState: State Interface
    ```java
    public interface AccountState {
        void deposit(BankAccount account, double amount);
        void withdraw(BankAccount account, double amount);
    }
    ```
  
* NormalState: Concrete State class
    ```java
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
    ```
  
* OverDraftState: Concrete State class
    ```java
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
    ```
  
* FrozenState: Concrete State class
    ```java
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
    ```

* BankAccount: State Context
    ```java
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
    ```

* StatePatternMain: Main Class
    ```java
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
    ```

* Output
    ```shell
    입금: 100.0, 잔액: 100.0
    출금: 50.0, 잔액: 50.0
    출금: 70.0, 잔액: -20.0
    예금이 부족하므로 초과 인출상태로 전환합니다.
    입금: 40.0, 잔액: 20.0
    일반 상태로 전환합니다.
    출금: 30.0, 잔액: -10.0
    예금이 부족하므로 초과 인출상태로 전환합니다.
    ```