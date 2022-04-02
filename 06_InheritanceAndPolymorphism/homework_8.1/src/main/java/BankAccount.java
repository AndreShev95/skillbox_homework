public class BankAccount {
  private boolean success = false;
  protected double amount = 0d;
  protected final double COMMISSION = 0.0d;

  public double getAmount() {
    return amount;
  }

  public boolean put(double amountToPut) {
    if (amountToPut > 0){
      amount += amountToPut;
      return true;
    }
    else return false;
  }

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

  public boolean send(BankAccount receiver, double amount){
    if (take(amount)) {
      if (receiver.put(amount))
      {
        success = true;
      }
    }
    return success;
  }

}
