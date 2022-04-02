import junit.framework.TestCase;

public class TransferTest extends TestCase {
    Bank bank;
    Account account1, account2;

    @Override
    protected void setUp() throws Exception {
        bank = new Bank();
        account1 = new Account();
        account1.setAccNumber("11111111");
        account1.setMoney(50000);
        account2 = new Account(100000, "22222222");
        bank.addAccount(account1.getAccNumber(), account1);
        bank.addAccount(account2.getAccNumber(), account2);
        bank.transfer(account1.getAccNumber(), account2.getAccNumber(), 50000);
    }

    public void testTransferSender() {
        long actual = account1.getMoney();
        long expected = 0;
        assertEquals(expected, actual);
    }

    public void testTransferGetter() {
        long actual = account2.getMoney();
        long expected = 150000;
        assertEquals(expected, actual);
    }

    public void testGetBalance() {
        long actual = bank.getBalance(account1.getAccNumber());
        long expected = 0;
        assertEquals(expected, actual);
    }

    public void testGetSumAllAccounts(){
        long actual = bank.getSumAllAccounts();
        long expected = 150000;
        assertEquals(expected, actual);
    }

    public void testBigOperation(){
        bank.transfer(account2.getAccNumber(), account1.getAccNumber(), 60000);
        try {
            Thread.sleep(1100);
            if(account1.isBlock() || account2.isBlock()){
                long actual = account2.getMoney();
                long expected = 150000;
                assertEquals(expected, actual);
            }
            else {
                long actual = account2.getMoney();
                long expected = 90000;
                assertEquals(expected, actual);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
