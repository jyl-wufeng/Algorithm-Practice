package com.binary.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HuffmanTreeDemo {
    public static void main(String[] args) {
        HuffmanTree huffmanTree=new HuffmanTree(new int[]{5,29,7,8,14,23,3,11});
        huffmanTree.preOrderTraverse(huffmanTree.top);
    }
}

class HuffmanTree{
        HuuffmanNode top;

    public void createHuffmanTree(int[] datas) {
        List<HuuffmanNode> list = new ArrayList<>();
        for (int data : datas) {
            HuuffmanNode node = new HuuffmanNode(data);
            list.add(node);
        }
        while (list.size()>1) {
            Collections.sort(list);
            HuuffmanNode leftNode = list.get(0);
            HuuffmanNode rightNode = list.get(1);
            HuuffmanNode parentNode = new HuuffmanNode(leftNode.value + rightNode.value);
            parentNode.leftNode = leftNode;
            parentNode.rightNode = rightNode;
            list.add(parentNode);
            list.remove(leftNode);
            list.remove(rightNode);
        }
       this.top=list.get(0);
    }
    public  HuffmanTree(int[] datas){
        this.createHuffmanTree(datas);
    }

    public void preOrderTraverse(HuuffmanNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.value);
        preOrderTraverse(node.leftNode);
        preOrderTraverse(node.rightNode);
    }
}

class HuuffmanNode implements Comparable<HuuffmanNode>{
    HuuffmanNode leftNode;
    HuuffmanNode rightNode;
    int value;

    public HuuffmanNode(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(HuuffmanNode o) {
        return -(o.value-this.value);
    }
}
