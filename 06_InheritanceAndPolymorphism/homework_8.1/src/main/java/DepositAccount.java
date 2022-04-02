import java.time.LocalDate;

public class DepositAccount extends BankAccount {

    private LocalDate lastIncome;

    public boolean put(double amountToPut) {
        if (amountToPut > 0){
            amount += amountToPut;
            lastIncome = LocalDate.now();
            return true;
        }
        else return false;
    }

    public boolean take(double amountToTake) {
        LocalDate takeNow = LocalDate.now();
        if (amountToTake <= amount && takeNow.isAfter(lastIncome.plusMonths(1))){
            amount -= amountToTake;
            return true;
        }
        return false;
    }
}
