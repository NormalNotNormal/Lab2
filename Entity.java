package Lab_one;

class Node{
    Node next;//указатель(ссылка) на следующий элемент
    Account value;//все имена юридических лиц(первое тип данных(это и есть сслка на данные интерфейса Аккаунт)value=название переменной

    public <T> Node(T value, Node buffer) {
    }

    public Node() {

    }
}

public class Entity<T> implements Client {

    private Node head;//указатель на превый элемент
    private  Node tail;//указатель на последний элемент
    String Name;//инициализируем имя
    private int size;//инициализируем размер списка

    public Entity(String Name/*,Node head, Node tail*/){
        setName(Name);
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

//создание списка и заполнение его элементов ссылками из массива(второйконструктор)
    public void addFirst(Account value) {
        Node a = new Node();//создаем новый элемент в списке
        a.value = value;//делаем так что бы можно проводить операции с данными

        if (head == null)//условие если список пуст,то указываем ссылки начала и конца на новый элемент
        {
            head = a;
            tail = a;
        } else {
            a.next= head;//или новый элемент ссылается на предыдущий
            head = a;
        }
    }
//добавление в конец списка
    public void addBack(Account value){
        Node a = new Node();
        a.value = value;

        if (tail==null)//если список пуст
        {
            head = a;//делаем возможным использование данных скопированных в переменную value по интерфейсной ссылке Account
            tail = a;//ссылка на конец
        }else{
            tail.next = a;
            tail = a;//иначе предыдущий элемент ссылается на новый конец
        }
    }
/*    //добавляющий узел в заданную позицию в списке
    public void addHere(Account value){
        Node a = new Node();
        for (int i = 0;i<this.Node.length;i++);
    }*/
public Entity(T[] valuesArray){
    for (T value:valuesArray) {
        add(value);
    }
    size = valuesArray.length;
}

//по идее добавляющий в заданную позицию в списке
public void add(T value){
        if(head == null){
            head = new Node(value,null);
        }else{
            Node buffer = head;
            while(buffer.next!=null){
                buffer = buffer.next;
            }
            buffer.next = new Node(value,buffer);
        }
        size++;
    }

//удаляющий по его номеру в списке?
    void delEl(Account value)          //удаление элемента
    {
        if(head == null)        //если список пуст -
            return;             //ничего не делаем

        if (head == tail) {     //если список состоит из одного элемента
            head = null;        //очищаем указатели начала и конца
            tail = null;
            return;             //и выходим
        }

        if (head.value == value) {    //если первый элемент - тот, что нам нужен
            head = head.next;       //переключаем указатель начала на второй элемент
            return;                 //и выходим
        }

        Node t = head;       //иначе начинаем искать
        while (t.next != null) {    //пока следующий элемент существует
            if (t.next.value == value) {  //проверяем следующий элемент
                if(tail == t.next)      //если он последний
                {
                    tail = t;           //то переключаем указатель на последний элемент на текущий
                }
                t.next = t.next.next;   //найденный элемент выкидываем
                return;                 //и выходим
            }
            t = t.next;                //иначе ищем дальше
        }
    }

    public T set(T value, int index){
        if(index<0 || index>=size) throw new IndexOutOfBoundsException("Нельзя менять значения с номерами которых нет в списке");
        Node buffer = head;
        for(int i = 0; i<index;i++){
            buffer = buffer.next;
        }
        T forReturn = (T) buffer.value;
        buffer.value = (Account) value;//buffer.value = value;
        return forReturn;
    }

    @Override
    public boolean add(DebitAccount account) {
        return false;
    }

    @Override
    public boolean add(DebitAccount account, int Number) {
        return false;
    }

    @Override
    public Account get(int Number) {
        return null;
    }

    @Override
    public Account get(String Number) {
        return null;
    }

    @Override
    public boolean hasAccount(String Number) {
        return false;
    }

    @Override
    public DebitAccount set(DebitAccount account, int index) {
        return null;
    }

    @Override
    public DebitAccount delete(int number) {
        return null;
    }

    @Override
    public DebitAccount delete(String Number) {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public DebitAccount[] getAccounts() {
        return new DebitAccount[0];
    }

    @Override
    public DebitAccount[] getSortedAccountsByBalance() {
        return new DebitAccount[0];
    }

    @Override
    public double getTotalBalance() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String Name) {

    }
}
