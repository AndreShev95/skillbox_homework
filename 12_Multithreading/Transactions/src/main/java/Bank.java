import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {

    private Map<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();
    private int countOperation = 1;

    public Bank() {
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    private void tryTransfer(String fromAccountNum, String toAccountNum, long amount) {
        int fromHash = Integer.parseInt(accounts.get(fromAccountNum).getAccNumber());
        int toHash = Integer.parseInt(accounts.get(toAccountNum).getAccNumber());
        if (fromHash < toHash) {
            synchronized (accounts.get(fromAccountNum)) {
                synchronized (accounts.get(toAccountNum)) {
                    accounts.get(fromAccountNum).withdrawMoney(amount);
                    accounts.get(toAccountNum).addMoney(amount);
                }
            }
        } else {
            synchronized (accounts.get(toAccountNum)) {
                synchronized (accounts.get(fromAccountNum)) {
                    accounts.get(fromAccountNum).withdrawMoney(amount);
                    accounts.get(toAccountNum).addMoney(amount);
                }
            }
        }
        System.out.println("Операция № " + countOperation++ + " прошла успешно.\n" +
                "Баланс отправителя " + fromAccountNum + " - " + getBalance(fromAccountNum) + " руб.\n" +
                "Баланс получателя " + toAccountNum + " - " + getBalance(toAccountNum) + " руб.\n" +
                "Сумма всех счетов в банке после завершения транзакции: " + getSumAllAccounts() + " руб.\n");
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {

        if (accounts.get(fromAccountNum).isBlock()) {
            System.out.println("Операция № " + countOperation++ + " невозможна, счет отправителя "
                    + fromAccountNum + " заблокирован.\n");
        } else if (accounts.get(toAccountNum).isBlock()) {
            System.out.println("Операция № " + countOperation++ + " невозможна, счет получателя "
                    + toAccountNum + " заблокирован.\n");
        } else if (getBalance(fromAccountNum) < amount) {
            System.out.println("Операция № " + countOperation++ + ". Недостаточно средств на счету, баланс счета отправителя "
                    + fromAccountNum + " - " + getBalance(fromAccountNum) + " руб.");
        } else if (amount > 50000) {
            try {
                if (isFraud(fromAccountNum, toAccountNum, amount)) {
                    accounts.get(fromAccountNum).setBlock();
                    accounts.get(toAccountNum).setBlock();
                    System.out.println("Операция № " + countOperation++ +
                            ". Обнаружена подозрительная операция. Заблокированы оба счета: "
                            + fromAccountNum + "," + toAccountNum);
                }
                else {
                    tryTransfer(fromAccountNum, toAccountNum, amount);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            tryTransfer(fromAccountNum, toAccountNum, amount);
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public long getSumAllAccounts() {
        long sum = 0;

        for (Map.Entry<String, Account> entry : accounts.entrySet()) {
            sum = sum + entry.getValue().getMoney();
        }
        return sum;
    }

    public void addAccount(String num, Account account) {
        accounts.put(num, account);
    }
}
