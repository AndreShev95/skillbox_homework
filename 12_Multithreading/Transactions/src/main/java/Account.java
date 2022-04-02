public class Account{

    private long money;
    private String accNumber;

    private volatile boolean block;

    public Account(long money, String accNumber) {
        this.money = money;
        this.accNumber = accNumber;
        block = false;
    }

    public Account() {
        money = 0;
        accNumber = "";
        block = false;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock() {
        block = true;
    }

    public void addMoney (long amount){
        money = money + amount;
    }

    public void withdrawMoney (long amount){
        money = money - amount;
    }
}
