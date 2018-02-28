package com.greworld.datatructure.tree.avl;

/**
 * @author 风骚的GRE
 * @Description 平衡二叉搜索树(AVL树)节点
 * 元素T必须继承{@link Comparable}接口
 * @date 2018/2/28.
 */
public class AVLNode<T extends Comparable> {
    public AVLNode<T> left;// 左节点
    public AVLNode<T> right;// 右节点
    public T data;
    public int height;//当前结点的高度

    public AVLNode(T data) {
        this(null,null,data);
    }

    public AVLNode(AVLNode<T> left, AVLNode<T> right, T data) {
        this(left,right,data,0);
    }

    public AVLNode(AVLNode<T> left, AVLNode<T> right, T data, int height) {
        this.left=left;
        this.right=right;
        this.data=data;
        this.height = height;
    }
}
