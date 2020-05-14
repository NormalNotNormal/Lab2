package Lab_one;

public class Individual implements Client {
    private DebitAccount[] accounts;//массив аккаунтов
    private int size;
    private String Name;


    public Individual() {
        accounts = new DebitAccount[16];//инициирующий массив из 16 элементов
        size = 0;
    }


    public Individual(int size) {
        accounts = new DebitAccount[size];
        size = 0;
    }


    public Individual(DebitAccount[] accounts) {
        this.accounts = new DebitAccount[accounts.length];
        for (int i = 0; i < this.accounts.length; i++) {
            this.accounts[i] = accounts[i];
        }
        size = checkPlacedAccount(accounts);
    }


    private void extend() {
        DebitAccount[] newAccount = new DebitAccount[accounts.length * 2];
        for (int i = 0; i < accounts.length; i++) {
            newAccount[i] = accounts[i];
        }
        accounts = newAccount;
    }


    private int checkPlacedAccount(DebitAccount[] accounts) {
        int count = 0;
        for (DebitAccount account : accounts) {//1 ссылочный тип данных(на предыдущий класс) 2 значение ячеек 3 название массива
            if (account != null)
                count++;//второе условие если значение строки пустое то переходим к следующей ячейке
        }
        return count;
    }

    public boolean add(DebitAccount account) {
        if (!checkFreeSpaces()) {//проверяем есть ли свободное место то
            extend();//расширяем? и возвраащем как ложное до тех пор пока все ячейки не станут заполненными
            return false;
        } else {
            lockAdd(account);//закрепляем значение за ячейкой
            size++;
            return true;
        }
    }


    public boolean add(DebitAccount account, int number) {//в ней участвует название значений предыдущего класса и номер ячейки
        if (accounts[number]==null) {//ячейка массива пустая
            accounts[number] = account;
            size++;
            return true;
        } else {
            return false;
        }
    }

    //возвращающий ссылку на экземпляр класса Account по его номеру в массиве


    public DebitAccount get(int number) {
        return accounts[number];//number номер счета класса
    }


    //возвращающий ссылку на экземпляр класса Account, по номеру счета. В качестве
    //параметра принимает строку – номер счета.


    public DebitAccount get(String Number) {//state неизменное.после lockadd зафиксировали значение в ячеййках и каждой ячсейке мы даем
        //строчное имя содержащее наименование физического лица(аккаунт)
        for (int i = 0; i < accounts.length; i++) {
            if (!(accounts[i] ==null) && accounts[i].equals(Number))

                return accounts[i];

        }
        return null;
    }


    //− определяющий, есть ли у физ. лица счет с заданным номером.
    // В качестве параметра принимает строку – номер счета. Возвращает логическое значение


    public boolean hasAccount(String Number) {
        for (int i = 0; i < accounts.length; i++) {
            if (!(accounts[i]==null) && accounts[i].equals(Number))
                return true;
        }
        return false;
    }


//изменяющий ссылку на экземпляр класса Account по его номеру в массиве. Принимает в
//качестве параметров номер и ссылку на экземпляр класса Account. Возвращает ссылку, которую заменили

    public DebitAccount set(DebitAccount account, int index) {//index номер ссылки по которой ищется ссылка класса в массиве
        DebitAccount ForReturn = accounts[index];//новый класс с именем .. имеет в массиве номер ячейки равное index
        accounts[index] = account;//имя ячейки =
        if (account == null && ForReturn != null) size--;// номер  0 и значение ячейки не равно 0
        if (account != null && ForReturn == null) size++;
        return ForReturn;
    }


    //удаляющий счет из массива по его номеру в массиве (принимает номер в массиве в качестве
    //параметра). Возвращает удаленную из массива ссылку на экземпляр класса Account


    public DebitAccount delete(int number) {
        DebitAccount ForReturn = accounts[number];
        accounts[number] = null;
        size--;
        return ForReturn;
    }


    public DebitAccount delete(String number) {
        DebitAccount ForReturn = get(number);
        if(ForReturn==null) return null;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getNumber().equals(number)) ;
            accounts[i] = null;
            return ForReturn;
        }
        return null;
    }

    public int getSize() {//size количество реальных элементов в массиве
        return size;
    }

    //возвращающий массив счетов (значений null в массиве быть не должно, его размер должен
    //быть равен числу элементов в исходном массиве).


    public DebitAccount[] getAccounts() {
        DebitAccount[] ForReturn = new DebitAccount[size];
        trim();//потрясти все реальные элементы столкнуть в левый конец массива а все null вправо
        for (int i = 0; i < ForReturn.length; i++) {
            ForReturn[i] = accounts[i];
        }

        return ForReturn;

    }

    public DebitAccount[] getSortedAccountsByBalance() {
        DebitAccount[] Sort = new DebitAccount[size];//новый массив с именем sort и значением size
        trim();
        for (int i = Sort.length - 1; i > 0; i--) {//создаем тело сортировки пузырем
            for (int j = 0; j < i; j++) {
                if (Sort[j].getBalance() > Sort[j + 1].getBalance()) {//массивы имеют строчные данные,присваем значение баланса из класса Account,чтобы значение масива было цисленным
                    DebitAccount tmp = Sort[j];//тип данных это ссылка на класс
                    Sort[j] = Sort[j + 1];
                    Sort[j + 1] = tmp;
                }
            }
        }
        return Sort;
    }


    public double getTotalBalance(){
        float totalBalance = 0f;
        for (DebitAccount account:accounts) {
            if (account != null)
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    public String getName(){return Name;}

    public void   setName(String Name){this.Name = Name;}

    private void  lockAdd(DebitAccount account) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i]==null) {
                accounts[i] = account;
                return;
            }
        }
    }

    private boolean checkFreeSpaces() {
        return !(size == accounts.length);
    }

    public void trim() {
        for (int i = 0; i < accounts.length; i++) {
            for (int j = 0; j < accounts.length - 1; j++) {
                if (accounts[j] == null && accounts[j + 1] != null) {
                    DebitAccount sort = accounts[j];
                    accounts[j] = accounts[j + 1];
                    accounts[j + 1] = sort;

                }


            }
        }
    }
}