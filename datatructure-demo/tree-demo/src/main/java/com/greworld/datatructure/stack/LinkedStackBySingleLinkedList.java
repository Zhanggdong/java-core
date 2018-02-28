package com.greworld.datatructure.stack;

import com.greworld.datatructure.linkedlist.singleLinked.SingleILinkedList;

import java.io.Serializable;

/**
 * @author 风骚的GRE
 * @Description 链式栈的实现(利用单链表即可)
 * @date 2018/2/28.
 */
public class LinkedStackBySingleLinkedList<T> implements Stack<T> ,Serializable {

    private static final long serialVersionUID = 3409158027110650450L;


    private SingleILinkedList<T> linkedList;

    public LinkedStackBySingleLinkedList(){
        linkedList=new SingleILinkedList<>();
    }


    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    /**
     * 栈顶插入(单链表尾部)
     * @param data
     */
    @Override
    public void push(T data) {
        linkedList.add(data);
    }

    /**
     * 获取元素,不删除
     * @return
     */
    @Override
    public T peek() {
        if(isEmpty())
            throw new EmptyStackException("Stack empty");
        return linkedList.get(0);
    }

    /**
     * 栈顶移除
     * @return
     */
    @Override
    public T pop() {
        if(isEmpty())
            throw new EmptyStackException("Stack empty");
        return linkedList.remove(0);
    }
}
