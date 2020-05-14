package Lab_one;

public interface Client {
    public boolean add(DebitAccount account);

    public boolean add(DebitAccount account, int Number);

    public Account get(int Number);

    public Account get(String Number);

    public  boolean hasAccount(String Number);

    public DebitAccount set(DebitAccount account, int index);

    public DebitAccount delete(int number);

    public DebitAccount delete(String Number);

    public  int getSize();

    public DebitAccount[] getAccounts();

    public  DebitAccount[] getSortedAccountsByBalance();

    public double getTotalBalance();

    public String getName();

    public void setName(String Name);

}
