package com.linked.list;

import com.damin.User;

/**
 * 单向有序链表
 */
public class OneWayLinkedListDemo {
    public static void main(String[] args) {
        OneWayLinkedList<User> oneWayLinkedList = new OneWayLinkedList<User>() {
            @Override
            int Comparator(User data, User nextData) {
                return data.age - nextData.age;
            }
        };
        oneWayLinkedList.add(new User(1, "1号"));
        oneWayLinkedList.add(new User(4, "4号"));
        oneWayLinkedList.add(new User(2, "2号"));
        oneWayLinkedList.add(new User(7, "7号"));
        oneWayLinkedList.add(new User(4, "4号"));
        oneWayLinkedList.add(new User(5, "5号"));
        oneWayLinkedList.remove(new User(2, "2号"));
        oneWayLinkedList.remove(new User(4, "4号"));
        oneWayLinkedList.intercept();
    }
}

abstract class OneWayLinkedList<T> {
    Node<T> top = new Node<T>(null, null);

    public void add(T data) {
        Node<T> temp = top;
        //如果到最后一个或者遇到比当前插入对象大的元素，停止后移
        while (temp.nextData != null && (temp.data == null || this.Comparator(temp.nextData.data, data) <= 0)) {
            temp = temp.nextData;
        }
        //插入节点指向后一个节点，前一个节点指向插入的节点
        temp.nextData = new Node<T>(data, temp.nextData);
    }

    public void remove(T t) {
        Node<T> temp = top;
        boolean find = true;
        while (temp.nextData != null) {
            //下一个节点是要删除的节点退出
            if (temp.nextData.data.equals(t)) {
                find = false;
                break;
            }
            temp = temp.nextData;
        }
        if (find) {
            throw new RuntimeException("未找到该元素");
        }
        //当前节点指向下一个节点的下一个节点
        temp.nextData = temp.nextData.nextData;
    }

    public void intercept() {
        Node<T> temp = top;
        while (temp.nextData != null) {
            temp = temp.nextData;
            System.out.println(temp.data.toString());
        }
    }

    abstract int Comparator(T data, T nextData);
}

class Node<T> {
    T data;
    Node<T> nextData;

    public Node(T data, Node<T> nextData) {
        this.data = data;
        this.nextData = nextData;
    }
}


