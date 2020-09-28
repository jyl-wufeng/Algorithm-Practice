package com.linked.list;

import com.damin.User;

public class TwoWayLinkedListDemo {
    public static void main(String[] args) {
        TwoWayLinkedList<User> twoWayLinkedList=new TwoWayLinkedList<>(20);
        twoWayLinkedList.add(new User(1, "1号"));
        twoWayLinkedList.add(new User(4, "4号"));
        twoWayLinkedList.add(new User(2, "2号"));
        twoWayLinkedList.add(new User(7, "7号"));
        twoWayLinkedList.add(new User(4, "4号"));
        twoWayLinkedList.add(new User(5, "5号"));
        twoWayLinkedList.remove(new User(2, "2号"));
        twoWayLinkedList.remove(new User(4, "4号"));
        twoWayLinkedList.insert(2,new User(11, "11号"));
        twoWayLinkedList.intercept();
    }

}

class TwoWayLinkedList<T> {
    NodeT<T> top;
    NodeT<T> tail;
    int maxSize;
    int current;

    public TwoWayLinkedList(int maxSize) {
        this.maxSize = maxSize;
        this.current = 0;
        this.top = new NodeT<T>();
        this.tail = new NodeT<T>();
    }

    /**
     * 末尾添加元素
     *
     * @param data 数据
     */
    public void add(T data) {
        if (maxSize == current) {
            throw new RuntimeException("达到最大数据长度！");
        }
        NodeT<T> temp = top;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new NodeT<>(data, temp, null);
        tail.prev = temp.next;
        current++;
    }

    /**
     * 删除元素
     *
     * @param data 数据
     * @return
     */
    public boolean remove(T data) {
        boolean isFind = false;
        NodeT<T> temp = top;
        while (temp.next != null&&temp.next !=tail) {
            temp = temp.next;
            if (temp.data.equals(data)) {
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
                isFind = true;
                current--;
            }
        }
        return isFind;
    }

    /***
     * 插入到指定位置
     * @param position 位置
     * @param data 数据
     */
    public void insert(int position, T data) {
        if (position > current + 1) {
            throw new RuntimeException("指定位置不存在");
        }
        if (position > maxSize) {
            throw new RuntimeException("超过最大长度");
        }
        NodeT<T> temp = top;
        int i = 1;
        while (temp.next != null && i < position) {
            temp = temp.next;
            i++;
        }
        NodeT<T> insertData = new NodeT<>(data, temp, temp.next);
        if (temp.next != null) {
            temp.next.prev = insertData;
        }
        temp.next = insertData;
        current++;
    }

    public void intercept(){
        NodeT<T> temp = top;
        while (temp.next!=null){
            temp=temp.next;
            System.out.println(temp.data.toString());
        }
    }
}

class NodeT<T> {
    T data;
    NodeT<T> prev;
    NodeT<T> next;

    public NodeT(T data, NodeT<T> prev, NodeT<T> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public NodeT() {

    }
}

