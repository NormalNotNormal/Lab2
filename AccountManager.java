package Lab_one;

public class AccountManager {
    private Individual[] individuals;
    private int size;//число физических лиц в массиве

    public AccountManager() {
        individuals = new Individual[size];
        size = 0;
    }

    public AccountManager(int size) {
        this.individuals = new Individual[individuals.length];
        for (int i = 0; i < this.individuals.length; i++) {
            this.individuals[i] = individuals[i];
        }
        size = checkPlacedIndividuals(individuals);
    }

    private void extend() {//метод расширения массива
        Individual[] newIndividuals = new Individual[individuals.length * 2];
        for (int i = 0; i < individuals.length; i++) {
            newIndividuals[i] = individuals[i];
        }
        individuals = newIndividuals;//сравнение значений первого и второго массива
    }

    private int checkPlacedIndividuals(Individual[] individuals) {//метод проверки занятых мест в массиве
        int count = 0;//переменная проверки каждого элеменита в массиве
        for (Individual individual : individuals) {
            if (individual != null) count++;//если ячейка занятая,то счетчик дальше
        }
        return count;//возвращает данные в программу
    }

    public boolean add(Individual individual) {
        if (!checkFreeSpaces()) {
            extend();
            return false;
        } else {
            lockAdd(individual);
            size++;
            return true;
        }
    }

    public boolean add(Individual individual, int link) {//добавляем ссылку в заданное место в массиве
        if (individuals[link] == null) {//добавить ссылку на экземпляр
            individuals[link] = individual;
            size++;
            return true;
        } else {
            return false;
        }
    }

    public Individual get(int number) {
        return individuals[number];
    }

    public Individual set(Individual individual, int number) {
        Individual ForReturn = individuals[number];//новый массив который будет возвращать ссылку по заданному номеру ячейки
        // новый класс с именем .. имеет в массиве номер ячейки равное number
        individuals[number] = individual;//значение ячейки массива приравниваем переменной которая возвращает ссылку которую заменили
        if (individual == null && ForReturn != null) size--;// номер  0 и значение ячейки не равно 0
        if (individual != null && ForReturn == null) size++;
        return ForReturn;
    }

    public Individual delete(int number) {
        Individual GoAway = individuals[number];
        individuals[number] = null;
        size--;
        return GoAway;
    }


    public int getSize() {
        return size;
    }

    public Individual[] getIndividuals() {
        return individuals;
    }


    public Individual[] getSortedByBalanceIndividuals(){
        trim();
        Individual[] inds = new Individual[size];
        System.arraycopy(individuals,0,inds,0,size);
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
        for (Individual individual : individuals) {
            if (individual.get(stateNumber) != null) {
                return individual.get(stateNumber);
            }
        }
        return null;
    }

    public DebitAccount delete(String Number) {
        DebitAccount ForReturn = getAccount(Number);
        for (int i = 0; i < individuals.length; i++) {
            individuals[i].delete(Number);
        }
        return ForReturn;

    }
    private boolean checkFreeSpaces(){
        return !(size == individuals.length);
    }

    private void lockAdd(Individual individual){
        for(int i = 0; i< individuals.length; i++){
            if(individuals[i] == null){
                individuals[i] = individual;
            }
        }
    }
    private void trim(){
        for(int i = 0; i< individuals.length; i++){
            for(int j = 0; j< individuals.length-1; j++){
                if(individuals[j]==null && individuals[j+1]!=null){
                    Individual buf = individuals[j];
                    individuals[j] = individuals[j+1];
                    individuals[j+1] = buf;
                }
            }
        }
    }

}


