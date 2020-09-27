package com.queue;

/**
 * 环形队列练习
 */
public class QueueAnnularDemo {

    public static void main(String[] args) {
        QueueAnnular queueAnnular = new QueueAnnular(11);
        queueAnnular.add(1);
        queueAnnular.add(2);
        queueAnnular.removeTop();
        queueAnnular.removeTop();
        queueAnnular.add(3);
        queueAnnular.removeTop();
        queueAnnular.add(4);
        queueAnnular.add(5);
        queueAnnular.add(6);
        queueAnnular.add(7);
        queueAnnular.removeTop();
        queueAnnular.removeTop();
        queueAnnular.add(8);
        queueAnnular.add(9);
        queueAnnular.add(10);
        queueAnnular.add(11);
        queueAnnular.add(12);
        queueAnnular.add(13);
        queueAnnular.add(14);
        QueueAnnular.intercept(queueAnnular);
    }

}

class QueueAnnular {
    //队列首个元素位置
    public int top = 0;
    //队列最后一个元素位置+1
    public int end = 0;
    //队列长度
    public int maxSize = 10;
    //队列
    public int[] data ;

    public QueueAnnular(int maxSize){
        this.maxSize=maxSize;
        this.data=new int[maxSize];
    }
    public void add(int value) {
        if ((top + maxSize - end) % maxSize == maxSize) {
            throw new RuntimeException("队列已满");
        }
        data[end] = value;
        end = (end + 1) % maxSize;
    }

    public int removeTop() {
        if (top == end) {
            throw new RuntimeException("队列为空");
        }
        int temp = data[top];
        data[top] = 0;
        top = (top + 1) % maxSize;
        return temp;
    }

    public static void intercept(QueueAnnular queueAnnular) {
        int top = queueAnnular.top;
        int maxSize = queueAnnular.maxSize;
        int end = queueAnnular.end;
        do {
            System.out.print(queueAnnular.data[top] + "  ");
            top = (top + 1) % maxSize;
        } while (!(top == end));
    }
}