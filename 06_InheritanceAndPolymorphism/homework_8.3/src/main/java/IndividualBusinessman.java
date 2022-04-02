public class IndividualBusinessman extends Client {

    private final double COMMISSION_SMALL = 0.01d;
    private final double COMMISSION_BIG = 0.005d;

    public void put(double amountToPut) {
        if (amountToPut > 0) {
            if (amountToPut < 1000) {
                amount += amountToPut * (1 - COMMISSION_SMALL);
            }
            else {
                amount += amountToPut * (1 - COMMISSION_BIG);
            }
        }
    }
}
