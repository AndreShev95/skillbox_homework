public class Main {
    public static final double COMMISSION = 0.01d;
    public static void main(String[] args) {
        CardAccount bankAccount = new CardAccount();
        bankAccount.put(4.0);
        System.out.println(bankAccount.getAmount());
        bankAccount.take(1.0, COMMISSION);
        System.out.println((bankAccount.getAmount()));

        CardAccount bankAccount1 = new CardAccount();
        System.out.println(bankAccount1.getAmount());
        bankAccount.send(bankAccount1, 1.0);
        System.out.println(bankAccount.getAmount());
        System.out.println(bankAccount1.getAmount());

        bankAccount1.send(bankAccount, 0.5);
        System.out.println(bankAccount.getAmount());
        System.out.println(bankAccount1.getAmount());

    }
}
