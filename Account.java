package Lab_one;

public class Account {
    private String Number = "";
    private double Balance = 0;

    public Account(String Number, double Balance) {
        setNumber(Number);
        setBalance(Balance);
    }

    public String getNumber() { return Number; }

    private void setNumber(String number) { this.Number = number; }//!

    public double getBalance() { return Balance; }

    private void setBalance(double balance) { this.Balance = balance; }

}



