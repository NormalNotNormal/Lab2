package Lab_one;

public class DebitAccount implements Account {
    private String Number = "";
    private double Balance = 0;

    public DebitAccount(String Number, double Balance) {
        setNumber(Number);
        setBalance(Balance);
    }

    public String getNumber() { return Number; }

    public void setNumber(String number) { this.Number = number; }//!

    public double getBalance() { return Balance; }

    public void setBalance(double balance) { this.Balance = balance; }

}



