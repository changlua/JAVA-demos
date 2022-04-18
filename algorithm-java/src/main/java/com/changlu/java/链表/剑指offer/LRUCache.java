package com.changlu.java.链表.剑指offer;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LRUCache
 * @Author ChangLu
 * @Date 4/21/2022 5:38 PM
 * @Description 最近最少使用LRU【中等】
 * 核心思路：①设置指定的容量。②hash存储缓存键值对。③链表来存储一个读取的顺序。（最新读的、修改的、创建的会放在head；新增容量满的话就会移除最后节点也就是最近最少读取的）
 * leetcode：https://leetcode-cn.com/problems/OrIXps/
 *
 * 执行效率：
 *  执行用时：42 ms, 在所有 Java 提交中击败了83.38%的用户
 *  内存消耗：107.9 MB, 在所有 Java 提交中击败了94.18%的用户
 */
class LRUCache {

    public static void main(String[] args) {
        final LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        System.out.println(lruCache.get(1));
        lruCache.put(3,3);
        System.out.println(lruCache.get(2));
        lruCache.put(4,4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }

    class DLinkedNodde{
        int key;
        int value;
        DLinkedNodde prev;
        DLinkedNodde next;
        public DLinkedNodde(){}
        public DLinkedNodde(int _key, int _value){
            key = _key;
            value = _value;
        }
    }

    //缓存：临时存放key与value
    private Map<Integer, DLinkedNodde> cache = new HashMap<>();

    private int size;
    private int capacity;
    private DLinkedNodde head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkedNodde();
        tail = new DLinkedNodde();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        //从缓存中获取
        DLinkedNodde node = cache.get(key);
        if (node == null) {
            return -1;
        }
        //若是key存在，那么就将其移到头部
        moveToHead(node);
        //返回值
        return node.value;
    }

    public void put(int key, int value) {
        //尝试从缓存中获取
        DLinkedNodde node = cache.get(key);
        if (node == null) {
            DLinkedNodde newNode = new DLinkedNodde(key, value);
            //若是此时容量大于一个预计值
            if ((cache.size() + 1) > capacity) {
                //移除链表最后一个节点
                DLinkedNodde removeNode = removeTail();
                //删除哈希表中的节点
                cache.remove(removeNode.key);
            }
            //添加到新的哈希表以及链表头部
            cache.put(key, newNode);
            addToHead(newNode);
        }else {
            //读取到了，修改值将其添加到顶部
            node.value = value;
            removeNode(node);
            addToHead(node);
        }
    }

    /**
     * 移除最后一个节点
     * @return
     */
    private DLinkedNodde removeTail() {
        DLinkedNodde prev = tail.prev;
        removeNode(prev);
        return prev;
    }

    /**
     * 将节点移到头部
     * @param node 节点
     */
    private void moveToHead(DLinkedNodde node) {
        removeNode(node);//将该节点的位置移除
        addToHead(node);
    }

    /**
     * 将节点添加到头部
     * @param node
     */
    private void addToHead(DLinkedNodde node) {
        //由于head、tail作为虚拟节点
        //绑定node的前缀、后缀
        node.prev = head;
        node.next = head.next;
        node.next.prev = node;//修改了的节点prev也要改
        //绑定head的后缀
        head.next = node;
    }

    /**
     * 将当前节点位置删除
     * @param node
     */
    private void removeNode(DLinkedNodde node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
