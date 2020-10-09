package com.binary.tree;

import com.damin.User;

import java.util.Comparator;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree<User> binaryTree=new BinaryTree<>( new User(62, "62号"),Comparator.comparingInt(o -> o.age));
        binaryTree.insert(new User(58, "58号"));

        binaryTree.insert(new User(88, "88号"));
        binaryTree.insert(new User(47, "47号"));
        binaryTree.insert(new User(73, "73号"));
        binaryTree.insert(new User(99, "99号"));
        binaryTree.insert(new User(35, "35号"));
        binaryTree.insert(new User(51, "51号"));
        binaryTree.insert(new User(93, "93号"));
        binaryTree.insert(new User(37, "37号"));
        binaryTree.preOrderTraverse(binaryTree.top);
    }
}

class BinaryTree<T>{
    Node<T> top;
    int high;
    int total;
    Comparator<? super T> c;
    public BinaryTree(T topData,Comparator<? super T> c){
        this.top=new Node<>(topData);
        this.high=1;
        this.total=1;
        this.c=c;
    }
    public void insert(T data){
        if(top==null){
            throw new RuntimeException("未初始化头节点");
        }
        insert(data,top);
    }
    private void insert(T data, Node<T> node){
        if(c.compare(data,node.data)>0){
            if(node.riteNode==null){
                node.riteNode=new Node<>(data);
            }else{
                insert(data,node.riteNode) ;
            }
        }else{
            if(node.leftNode==null){
                node.leftNode=new Node<>(data);
            }else{
                insert(data,node.leftNode) ;
            }
        }
    }

    public void remove(T data){

    }

    public void preOrderTraverse(Node<T> node){
     if(node==null){
         return;
     }
        preOrderTraverse(node.leftNode);
        System.out.println(node.data.toString());
        preOrderTraverse(node.riteNode);
    }



}

class Node<T>{
    Node<T> leftNode;
    Node<T> riteNode;
    T data;

    public Node(T data){
        this.data=data;
    }
}