package cww0418;
public class Test {    //哈希表
    static class Node {
        public int val;
        public int key;  //里面存放的是
        public Node next;

        public Node(int val, int key) {
            this.val = val;
            this.key = key;
        }
    }

    private Node[] array = new Node[101];       //开散列
    private int size = 0; //哈希表中的个数

    public void put(int key, int val) {  //增操作
        int index = hashFunction(key);
        Node list = array[index];
        for (Node cur = list; cur != null; cur = cur.next) {   //遍历一下这个链表
            if (cur.key == key) {
                cur.val = val;
                return;
            }
        }
        //进行头插
        Node newNode = new Node(key, val);
        newNode.next = list;
        array[index] = newNode;//存放的是新节点的tou
        size++;
        if(size / array.length>LOAD_FACTOR){
            resize();//进行扩容
        }
    }
    private void resize() {
        Node [] newArray =  new Node[array.length * 3];  //扩大了2倍
        for (int i = 0; i <array.length ; i++) {
            for(Node cur = array[i];cur!=null;cur=cur.next){
                int index = cur.key % newArray.length; //找到新的下标
                Node newNode =new Node(cur.val,cur.key);
                newNode.next  =newArray[index];
                newArray[index] =newNode;
            }
        }
        array =  newArray;
    }

    private static final double LOAD_FACTOR = 0.75;//负载因子

    public Integer get(int key) { //查操作
        int index = hashFunction(key);
        Node list = array[index];
        for (Node cur = list; cur != null; cur = cur.next) {
            if(cur.key==key){
                return cur.val;
            }
        }
        return null;
    }
    private int hashFunction ( int key){
        return key % array.length;
    }
}
