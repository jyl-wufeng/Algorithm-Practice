package com.binary.tree;

import com.damin.User;

import java.util.Comparator;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree<User> binaryTree = new BinaryTree<>(new User(62, "62号"), Comparator.comparingInt(o -> o.age));
        binaryTree.insert(new User(58, "58号"));

        binaryTree.insert(new User(88, "88号"));
        binaryTree.insert(new User(47, "47号"));
        binaryTree.insert(new User(73, "73号"));
        binaryTree.insert(new User(99, "99号"));
        binaryTree.insert(new User(35, "35号"));
        binaryTree.insert(new User(51, "51号"));
        binaryTree.insert(new User(93, "93号"));
        binaryTree.insert(new User(37, "37号"));
        binaryTree.insert(new User(36, "36号"));
        binaryTree.insert(new User(39, "39号"));
        binaryTree.insert(new User(42, "42号"));
        //binaryTree.preOrderTraverse(binaryTree.top);
        binaryTree.remove(new User(88, "88号"));
        binaryTree.preOrderTraverse(binaryTree.top);
    }
}

class BinaryTree<T> {
    Node<T> top;
    int high;
    int total;
    Comparator<? super T> c;

    public BinaryTree(T topData, Comparator<? super T> c) {
        this.top = new Node<>(topData);
        this.high = 1;
        this.total = 1;
        this.c = c;
    }

    public void insert(T data) {
        if (top == null) {
            throw new RuntimeException("未初始化头节点");
        }
        int i=1;
        insert(data, top,i);
        if(i>total){
            total=i;
        }
    }

    private void insert(T data, Node<T> node,int i) {
        i++;
        if (c.compare(data, node.data) > 0) {
            if (node.riteNode == null) {
                node.riteNode = new Node<>(data);
                node.riteNode.parentNode=node;
            } else {
                insert(data, node.riteNode,i);
            }
        } else {
            if (node.leftNode == null) {
                node.leftNode = new Node<>(data);
                node.leftNode.parentNode=node;
            } else {
                insert(data, node.leftNode,i);
            }
        }
        total++;
    }

    public void remove(T data) {
        Node<T> select = select(top, data);
        if(select==null){
            throw new RuntimeException("未找到！");
        }
        remove(select);
    }
    public void remove(Node<T> node) {
        Node<T> replaceNote=null;
         //右边最小的值
        if(node.riteNode!=null){
            replaceNote=selectLeft(node.riteNode);
        }
        //左边最大
        if(replaceNote==null&&node.leftNode!=null){
            replaceNote=selectRite(node.leftNode);
        }
        //没有交换的值 直接删除
        if(replaceNote==null){
            if(node.parentNode==null){
                node.data=null;
            } else if(c.compare(node.parentNode.data,node.data)>1){
                node.parentNode.leftNode=null;
            }else{
                node.parentNode.riteNode=null;
            }
        }else{
            node.data=replaceNote.data;
            if(replaceNote.parentNode==null){
                replaceNote.data=null;
            } else if(c.compare(replaceNote.parentNode.data,replaceNote.data)>0){
                replaceNote.parentNode.leftNode=null;
            }else{
                replaceNote.parentNode.riteNode=null;
            }
        }
        total--;

    }
    public Node<T> selectLeft(Node<T> node){
        if(node.leftNode==null){
            return node;
        }else {
            return selectLeft(node.leftNode);
        }

    }

    public Node<T> selectRite(Node<T> node){
        if(node.riteNode==null){
            return node;
        }else {
            return selectLeft(node.riteNode);
        }

    }

    //查找
    public Node<T> select(Node<T> node, T data) {
        if(node==null){
            return null;
        }
        if (node.data.equals(data)) {
            return node;
        }
        if (c.compare(node.data, data) > 0) {
            return select(node.leftNode, data);
        }
        if (c.compare(node.data, data) < 0) {
            return select(node.riteNode, data);
        }
        return null;
    }

    public void preOrderTraverse(Node<T> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.data.toString());
        preOrderTraverse(node.leftNode);
        preOrderTraverse(node.riteNode);
    }


}

class Node<T> {
    Node<T> leftNode;
    Node<T> riteNode;
    Node<T> parentNode;
    T data;

    public Node(T data) {
        this.data = data;
    }
}