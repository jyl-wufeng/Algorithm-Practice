package com.linked.list;

import com.damin.User;

import java.util.Comparator;

public class TwoWayLinkedListDemo {
    public static void main(String[] args) {
        TwoWayLinkedList<User> twoWayLinkedList = new TwoWayLinkedList<>(20);
        twoWayLinkedList.add(new User(12, "12号"));
        twoWayLinkedList.add(new User(1, "1号"));
        twoWayLinkedList.add(new User(4, "4号"));
        twoWayLinkedList.add(new User(2, "2号"));
        twoWayLinkedList.add(new User(7, "7号"));
        twoWayLinkedList.add(new User(4, "4号"));
        twoWayLinkedList.add(new User(5, "5号"));
        twoWayLinkedList.remove(new User(2, "2号"));
        twoWayLinkedList.remove(new User(4, "4号"));
        twoWayLinkedList.insert(2, new User(11, "11号"));
        twoWayLinkedList.add(new User(4, "4号"));
        twoWayLinkedList.add(new User(3, "3号"));
        twoWayLinkedList.intercept();
        twoWayLinkedList.bubbleSort(Comparator.comparingInt(o -> o.age));
        //twoWayLinkedList.QuickSort(Comparator.comparingInt(o -> o.age));
        System.out.println("---------------------------");
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
        while (temp.next != null && temp.next != tail) {
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

    public void intercept() {
        NodeT<T> temp = top;
        while (temp.next != null) {
            temp = temp.next;
            System.out.println(temp.data.toString());
        }
    }

    /**
     * 冒泡排序
     *
     * @param c 比较器
     */
    public void bubbleSort(Comparator<? super T> c) {
        boolean isOrder = false;
        int end = this.maxSize;
        while (!isOrder && end > 0) {
            int start = 0;
            NodeT<T> temp = top;
            //是否发生交换
            isOrder = true;
            while (temp.next != null && start < end) {
                temp = temp.next;
                start++;
                //如果下一个元素比当前小，交换位置
                if (temp.next != null && c.compare(temp.data, temp.next.data) > 0) {
                    T tempData=temp.data;
                    temp.data=temp.next.data;
                    temp.next.data=tempData;
                    isOrder = false;
                }
            }
            end--;
        }
    }

    /**
     * 快速排序
     * @param c 比较器
     */
    public void QuickSort(Comparator<? super T> c){
        QuickSortImplements(this.top.next,this.tail.prev,c);
    }

    private void QuickSortImplements(NodeT<T> start,NodeT<T> end,Comparator<? super T> c){
        NodeT<T> left=start;
        NodeT<T> right=end;
        NodeT<T> middle=start;
        while (start!=end){
            //从后往前
            while (start!=end&&c.compare(middle.data,end.data)<0){
                end=end.prev;
            }
            if(c.compare(middle.data,end.data)>0){
                T temp=middle.data;
                middle.data=end.data;
                end.data=temp;
                middle=end;
            }
            //从前往后
            while (start!=end&&c.compare(middle.data,start.data)>0){
                start=start.next;
            }
            if(c.compare(middle.data,start.data)<0){
                T temp=middle.data;
                middle.data=start.data;
                start.data=temp;
                middle=start;
            }
            if(start==end||start==end.next){
                break;
            }
        }
        if(left!=middle) {
            System.out.println(left.data.toString()+"-111--"+middle.data.toString());
            QuickSortImplements(left, middle, c);
        }
        if(middle.next!=right&&middle.next!=null) {
            System.out.println(middle.next.data.toString()+"---"+right.data.toString());
            QuickSortImplements(middle.next, right, c);
        }
    }
}

class NodeT<T>{
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

