package com.stack;

import java.lang.reflect.Array;

public class StackDemo {
    public static void main(String[] args) {
        Stack<Integer> stack=new Stack<>(10);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pull());
        System.out.println(stack.pull());

    }

}

class Stack<T> {
    public int maxSize;
    public Node<T> top;
    public int current;
    public Stack(int maxSize) {
        this.maxSize = maxSize;
        this.top = new Node<T>(null);
        this.current=0;
    }

    public void push(T data){
        if(this.current==maxSize){
            throw new RuntimeException("队列已满");
        }
        top.next=new Node<T>(data);
        top.next.prev=top;
        top=top.next;
        current++;
    }
    public T pull(){
        if(top.data==null){
            throw new RuntimeException("队列为空");
        }
        T temp=top.data;
        top=top.prev;
        top.next=null;
        current--;
        return temp;
    }
}

class Node<T> {

    Node<T> next;
    Node<T> prev;
    T data;

    public Node(T data){
        this.data=data;
    }

}