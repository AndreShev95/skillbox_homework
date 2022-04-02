public class Main {
    public static void main(String[] args) {

        Bank bank = new Bank();

        Account account1 = new Account(10000, "11111111");
        bank.addAccount(account1.getAccNumber(), account1);
        Account account2 = new Account(200000, "22222222");
        bank.addAccount(account2.getAccNumber(), account2);
        Account account3 = new Account(300000, "33333333");
        bank.addAccount(account3.getAccNumber(), account3);
        Account account4 = new Account(400000, "44444444");
        bank.addAccount(account4.getAccNumber(), account4);

        System.out.println("Сумма всех счетов в банке до начала транзакций: " + bank.getSumAllAccounts() + " руб.\n");
        for (int i = 0 ; i < 4 ; i++){
            new Thread(()->{
                bank.transfer(account1.getAccNumber(), account2.getAccNumber(), 10000);
                bank.transfer(account2.getAccNumber(), account3.getAccNumber(), 60000);
                bank.transfer(account3.getAccNumber(), account4.getAccNumber(), 10000);
                bank.transfer(account4.getAccNumber(), account1.getAccNumber(), 10000);
            }).start();
        }
        for (int i = 0 ; i < 4 ; i++) {
            new Thread(() -> {
                bank.transfer(account2.getAccNumber(), account1.getAccNumber(), 10000);
                bank.transfer(account3.getAccNumber(), account2.getAccNumber(), 10000);
                bank.transfer(account4.getAccNumber(), account3.getAccNumber(), 10000);
                bank.transfer(account1.getAccNumber(), account4.getAccNumber(), 10000);
            }).start();
        }
    }
}
