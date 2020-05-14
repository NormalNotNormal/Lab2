package Lab_one;

public class AccountManager {
    private Individual[] clients;
    private int size;//число физических лиц в массиве

    public AccountManager() {
        clients = new Individual[size];
        size = 0;
    }

    public AccountManager(int size) {
        this.clients = new Individual[clients.length];
        for (int i = 0; i < this.clients.length; i++) {
            this.clients[i] = clients[i];
        }
        size = checkPlacedIndividuals(clients);
    }

    private void extend() {//метод расширения массива
        Individual[] newIndividuals = new Individual[clients.length * 2];
        for (int i = 0; i < clients.length; i++) {
            newIndividuals[i] = clients[i];
        }
        clients = newIndividuals;//сравнение значений первого и второго массива
    }

    private int checkPlacedIndividuals(Individual[] clients) {//метод проверки занятых мест в массиве
        int count = 0;//переменная проверки каждого элеменита в массиве
        for (Individual individual : clients) {
            if (individual != null) count++;//если ячейка занятая,то счетчик дальше
        }
        return count;//возвращает данные в программу
    }

    public boolean add(Individual client) {
        if (!checkFreeSpaces()) {
            extend();
            return false;
        } else {
            lockAdd(client);
            size++;
            return true;
        }
    }

    public boolean add(Individual client, int link) {//добавляем ссылку в заданное место в массиве
        if (clients[link] == null) {//добавить ссылку на экземпляр
            clients[link] = client;
            size++;
            return true;
        } else {
            return false;
        }
    }

    public Individual get(int number) {
        return clients[number];
    }

    public Individual set(Individual client, int number) {
        Individual ForReturn = clients[number];//новый массив который будет возвращать ссылку по заданному номеру ячейки
        // новый класс с именем .. имеет в массиве номер ячейки равное number
        clients[number] = client;//значение ячейки массива приравниваем переменной которая возвращает ссылку которую заменили
        if (client == null && ForReturn != null) size--;// номер  0 и значение ячейки не равно 0
        if (client != null && ForReturn == null) size++;
        return ForReturn;
    }

    public Individual delete(int number) {
        Individual GoAway = clients[number];
        clients[number] = null;
        size--;
        return GoAway;
    }


    public int getSize() {
        return size;
    }

    public Individual[] getClients() {
        return clients;
    }


    public Individual[] getSortedByBalanceIndividuals(){
        trim();
        Individual[] inds = new Individual[size];
        System.arraycopy(clients,0,inds,0,size);
        for (int i = 0; i < inds.length; i++) {
            for (int j = 0; j < inds.length - 1; j++) {
                if (inds[j].getTotalBalance() > inds[j + 1].getTotalBalance()) {//массивы имеют строчные данные,присваем значение баланса из класса Individual,чтобы значение масива было целочисленным
                    Individual tmp = inds[j];//тип данных это ссылка на класс
                    inds[j] = inds[j + 1];
                    inds[j + 1] = tmp;
                }
            }
        }
        return inds;
    }

    public DebitAccount getAccount(String stateNumber) {
        for (Individual client : clients) {
            if (client.get(stateNumber) != null) {
                return client.get(stateNumber);
            }
        }
        return null;
    }

    public DebitAccount delete(String Number) {
        DebitAccount ForReturn = getAccount(Number);
        for (int i = 0; i < clients.length; i++) {
            clients[i].delete(Number);
        }
        return ForReturn;

    }
    private boolean checkFreeSpaces(){
        return !(size == clients.length);
    }

    private void lockAdd(Individual client){
        for(int i = 0; i< clients.length; i++){
            if(clients[i] == null){
                clients[i] = client;
            }
        }
    }
    private void trim(){
        for(int i = 0; i< clients.length; i++){
            for(int j = 0; j< clients.length-1; j++){
                if(clients[j]==null && clients[j+1]!=null){
                    Individual buf = clients[j];
                    clients[j] = clients[j+1];
                    clients[j+1] = buf;
                }
            }
        }
    }

}


