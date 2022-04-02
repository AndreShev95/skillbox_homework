public class CardAccount extends BankAccount {
    protected final double COMMISSION = 0.01d;

    public boolean take(double amountToTake) {
        if (amountToTake * (1 + COMMISSION) <= amount){
            amount -= amountToTake * (1 + COMMISSION);
            return true;
        }
        else return false;
    }

    public boolean take(double amountToTake, double COMMISSION) {
        if (amountToTake * (1 + COMMISSION) <= amount){
            amount -= amountToTake * (1 + COMMISSION);
            return true;
        }
        else return false;
    }
}
