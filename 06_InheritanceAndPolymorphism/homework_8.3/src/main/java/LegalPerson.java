public class LegalPerson extends Client {
    private final double COMMISSION = 0.01d;

    public void take(double amountToTake) {
        if (amountToTake * (1 + COMMISSION) <= amount) {
            amount -= amountToTake * (1 + COMMISSION);
        }
    }
}
