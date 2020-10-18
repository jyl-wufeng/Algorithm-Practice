package com.binary.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RbTreeMap<K extends Comparable<K>, V> {
    public static void main(String[] args) {
        RbTreeMap<Integer,Integer> rbTreeMap=new RbTreeMap<>();
        rbTreeMap.put(1,1);
        rbTreeMap.put(2,2);
        rbTreeMap.put(3,3);
        rbTreeMap.put(4,4);
        rbTreeMap.put(5,5);
        rbTreeMap.put(6,6);
        rbTreeMap.put(7,7);
        rbTreeMap.put(8,8);
        rbTreeMap.put(8,8);
        rbTreeMap.put(9,9);
        rbTreeMap.put(10,10);
        rbTreeMap.put(11,11);
        rbTreeMap.put(12,12);
        rbTreeMap.put(13,13);
        rbTreeMap.put(14,14);
        rbTreeMap.put(15,15);
        rbTreeMap.put(16,16);
        rbTreeMap.remove(14);
        System.out.println("");
        System.out.println(rbTreeMap.get(12));
        rbTreeMap.printNode();
    }
    RbNode<K,V> root;
    public static final boolean black = false;
    public static final boolean red = true;
    private void setColour(RbNode<K,V> node, boolean colour) {
        if(node!=null) {
            node.colour = colour;
        }
    }

    /**
     * 右旋  r         O
     *     /         /  \
     *    o     >   v    r
     *   /  \           /
     *  v    w         w
     * @param r
     */
    private void rightRotate(RbNode<K,V> r){
        RbNode<K,V> w=r.leftNode.rightNode;
        RbNode<K,V> o=r.leftNode;
        //o的右节点变成r
        r.leftNode.rightNode=r;
        //o的父节点变成r的父节点
        o.parentNode=r.parentNode;
        if(o.parentNode!=null) {
            if (o.parentNode.rightNode == r) {
                o.parentNode.rightNode = o;
            } else {
                o.parentNode.leftNode = o;
            }
        }else {
        root=o;
    }
        r.parentNode=o;
        //w挂在r的左节点
        r.leftNode=w;
        if(w!=null){
            w.parentNode=r;
        }
    }

    /**
     * 左旋
     * @param r
     */
    private void leftRotate(RbNode<K,V> r){
        RbNode<K,V> w=r.rightNode.leftNode;
        RbNode<K,V> o=r.rightNode;
        //o的右节点变成r
        r.rightNode.leftNode=r;
        //o的父节点变成r的父节点
        o.parentNode=r.parentNode;
        if(o.parentNode!=null) {
        if(o.parentNode.leftNode==r){
            o.parentNode.leftNode=o;
        }else{
            o.parentNode.rightNode=o;
        }}else {
            root=o;
        }
        r.parentNode=o;
        //w挂在r的左节点
        r.rightNode=w;
        if(w!=null){
            w.parentNode=r;
        }
    }
    public V put(K key,V value){
        RbNode<K,V> cdNode=root;
        RbNode<K,V> insertPointNode = null;
        int cp = 0;
        //开始找插入点
;        while (cdNode!=null){
            insertPointNode=cdNode;
            cp=cdNode.key.compareTo(key);
            if(cp<0){
                cdNode=cdNode.rightNode;
            }else if(cp>0){
                cdNode=cdNode.leftNode;
            }else {//找到一样的key直接替换value并结束
                cdNode.value=value;
                return value;
            }
        }
        RbNode<K,V> insertNode=new RbNode<K,V>(insertPointNode,key,value);
        if(insertPointNode==null){//如果根节点为空直接添加
            this.root=insertNode;
        }else if(cp<0){
            insertPointNode.rightNode=insertNode;
        }else {
            insertPointNode.leftNode=insertNode;
        }
        putRegulate(insertNode);
        return value;
    }
    private void putRegulate(RbNode<K,V> r){
        if(r.parentNode==null){//根节点直接变颜色
            setColour(r,black);
            return;
        }
        while (r!=null&&r.parentNode!=null&&r.parentNode.colour==red){
            if (r.parentNode==r.parentNode.parentNode.leftNode){//左倾
                if(r.parentNode.parentNode.rightNode!=null&&r.parentNode.parentNode.rightNode.colour==red){//对于234树分裂情况，直接变颜色 中间节点变红，两边变黑
                    setColour(r.parentNode.parentNode,red);
                    setColour(r.parentNode.parentNode.rightNode,black);
                    setColour(r.parentNode.parentNode.leftNode,black);
                    r=r.parentNode.parentNode;
                }else {
                    if (r == r.parentNode.rightNode) {//是右节点 先调整为插入左节点的情况
                        leftRotate(r.parentNode);
                        r = r.leftNode;
                    }
                    rightRotate(r.parentNode.parentNode);
                    setColour(r.parentNode, black);
                    setColour(r.parentNode.rightNode, red);
                }
            }else {//右倾 操作一样，取反
                if(r.parentNode.parentNode.leftNode!=null&&r.parentNode.parentNode.leftNode.colour==red){
                    setColour(r.parentNode.parentNode,red);
                    setColour(r.parentNode.parentNode.rightNode,black);
                    setColour(r.parentNode.parentNode.leftNode,black);
                    r=r.parentNode.parentNode;
                }else {
                    if (r == r.parentNode.leftNode) {
                        rightRotate(r.parentNode);
                        r = r.rightNode;
                    }
                    leftRotate(r.parentNode.parentNode);
                    setColour(r.parentNode, black);
                    setColour(r.parentNode.leftNode, red);
                }
            }
        }
        setColour(root,black);
    }
    public V remove(K key){
        RbNode<K,V> r=getNode(key);
        if(r==null){
            return null;
        }
        RbNode<K,V> replaceNode=null;
        RbNode<K,V> temp;
        if(r==root){
            this.root=null;
            return r.value;
        }else if(r.rightNode!=null) {//找后继节点
             temp=r.rightNode;
            while (temp!=null){
                replaceNode=temp;
                temp=temp.leftNode;
            }
        }else if(r.leftNode!=null){//找前驱节点
            temp=r.leftNode;
            while (temp!=null){
                replaceNode=temp;
                temp=temp.leftNode;
            }
        }
        if(replaceNode!=null){
            r.value=replaceNode.value;
            r.key=replaceNode.key;
        }else {
            replaceNode=r;
        }
        RbNode<K,V> kn=replaceNode.leftNode==null?replaceNode.rightNode:replaceNode.leftNode;
        if(kn!=null){
            kn.parentNode=replaceNode.parentNode;
        }
        if(replaceNode==replaceNode.parentNode.leftNode){
            replaceNode.parentNode.leftNode=kn;
        }else {
            replaceNode.parentNode.rightNode=kn;
        }
        return replaceNode.value;
    }
    public V get(K key){
        RbNode<K,V> r=getNode(key);
        if(r!=null){
            return r.value;
        }else {
            return null;
        }
    }
    private RbNode<K,V> getNode(K key){
        RbNode<K,V> r=root;
        int cp;
        while (r!=null){
            cp=r.key.compareTo(key);
            if(cp>0){
                r=r.leftNode;
            }else if(cp<0){
                r=r.rightNode;
            }else {
                return r;
            }
        }
        return null;
    }
    public static class RbNode<K extends Comparable<K>, V> {
        private boolean colour;
        private RbNode<K,V> leftNode;
        private RbNode<K,V> rightNode;
        private RbNode<K,V> parentNode;
        private K key;
        private V value;

        public RbNode( RbNode<K,V> parentNode, K key, V value) {
            this.colour = red;
            this.parentNode = parentNode;
            this.key = key;
            this.value = value;
        }
    }




    public  <T extends Comparable<?>> void printNode() {
        int maxLevel = maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<RbNode> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces);

        List<RbNode> newNodes = new ArrayList<RbNode>();
        for (RbNode node : nodes) {
            if (node != null) {
                if(node.colour){
                    System.out.print("\033[31;4m" + node.key + "\033[0m");
                }else {
                    System.out.print(node.key);
                }
                //System.out.print(node.key);
                newNodes.add(node.leftNode);
                newNodes.add(node.rightNode);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).leftNode != null)
                    System.out.print("/");
                else
                    printWhitespaces(1);

               printWhitespaces(i + i - 1);

                if (nodes.get(j).rightNode != null)
                    System.out.print("\\");
                else
                    printWhitespaces(1);

                printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(RbNode node) {
        if (node == null)
            return 0;

        return Math.max(maxLevel(node.leftNode), maxLevel(node.rightNode)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }
}
