package com.changlu.java.O1HashAndString.exer;

/**
 * @ClassName DiyHashMap
 * @Author ChangLu
 * @Date 4/2/2022 10:34 AM
 * @Description 手写数组+链表：最最简单的一个HashMap实现
 */
class Node{
    private Object key;
    private Object value;
    private Node next;

    public Node(Object key,Object value) {
        this.key = key;
        this.value = value;
    }

    public Node(Object key,Object value, Node next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (key != null ? !key.equals(node.key) : node.key != null) return false;
        if (value != null ? !value.equals(node.value) : node.value != null) return false;
        return next != null ? next.equals(node.next) : node.next == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (next != null ? next.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", value=" + value +
                ", next=" + next +
                '}';
    }
}

class MyMap{
    Node[] nodes;

    //构建Map的容量
    public MyMap(int capacity){
        if (capacity < 16) {
            nodes = new Node[capacity];
        }else {
            //计算出capacity最高幂
            nodes = new Node[Integer.highestOneBit(capacity)];
        }
    }

    //插入
    public void put(Object key,Object value){
        //1、计算key的hash值
        int hashKey =  key.hashCode() & (nodes.length - 1);
        //2、获取对应下标Node结点，看是否存在
        Node node = nodes[hashKey];
        //若是不存在直接插入
        if (node == null){
            nodes[hashKey] = new Node(key,value);
            return;
        }
        //若是存在，遍历Node结点(若是有结点，直接覆盖)
        Node cur = node;
        while (cur != null) {
            //判断key是否相同
            if (key.equals(cur.getKey())) {
                cur.setValue(value);
                return;
            }
            cur = cur.getNext();
        }
        //若是没有结点，采用头插法
        nodes[hashKey] = new Node(key,value,node);
    }

    //获取
    public Object get(Object key){
        //1、获取到对应的node结点
        int hashKey =  key.hashCode() & (nodes.length - 1);
        //2、遍历node来获取对应的值
        Node node = nodes[hashKey];
        Node cur = node;
        while (cur != null){
            //判断对应的node结点的key是否相同，若是相同那么直接返回
            if (cur.getKey().equals(key)){
                return cur.getValue();
            }
            cur = cur.getNext();
        }
        return null;
    }

}

public class DiyHashMap {

    public static void main(String[] args) {
        final MyMap myMap = new MyMap(20);
        myMap.put("changlu","123");
        myMap.put("changlu","456");
        System.out.println(myMap.get("changlu"));
        System.out.println(myMap.get("liner"));
    }

}
