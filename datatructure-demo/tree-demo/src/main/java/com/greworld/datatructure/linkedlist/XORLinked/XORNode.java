package com.greworld.datatructure.linkedlist.XORLinked;

/**
 * @author 风骚的GRE
 * @Description 异或结点
 * @date 2018/2/28.
 */
public class XORNode<T> {
    public T data;
    public XORNode<T> ptrdiff;//异或指针

    public XORNode() {
    }

    public XORNode(T data, XORNode<T> ptrdiff) {
        this.data = data;
        this.ptrdiff = ptrdiff;

    }
}
