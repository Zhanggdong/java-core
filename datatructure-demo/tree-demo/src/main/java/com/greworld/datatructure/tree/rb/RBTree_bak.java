package com.greworld.datatructure.tree.rb;

import com.greworld.datatructure.tree.Tree;
import com.greworld.datatructure.tree.binarynode.BinaryNode;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/3/1.
 */
public class RBTree_bak<T extends Comparable> implements Tree<T> {

    private RBTNode<T> root;// 根节点
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class RBTNode<T extends Comparable>{
        private T data;            //数据元素
        private RBTNode<T> left;    // 左孩子
        private RBTNode<T> right;    // 右孩子
        private RBTNode<T> parent;    // 父结点
        private boolean color;      //color of parent link
        private int size;           //subtree count

        public RBTNode(T data){
            this(data,RED,1,null,null,null);
        }

        public RBTNode(T data, boolean color, int size) {
            this(data,color,size,null,null,null);
        }

        public RBTNode(T data, boolean color, int size, RBTNode<T> parent, RBTNode<T> left, RBTNode<T> right) {
            this.data = data;
            this.color = color;
            this.size = size;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public T getKey(){
            return data;
        }
        public String toString() {
            return ""+data+(this.color==RED?"(R)":"B");
        }
    }
    private boolean isRed(RBTNode x) {
        if(x == null) {
            return false;
        }
        return x.color == RED;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(RBTNode<T> p) {
        if(p == null) {
            return 0;
        }
        return p.size;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public String preOrder() {
        return null;
    }

    @Override
    public String inOrder() {
        return null;
    }

    @Override
    public String postOrder() {
        return null;
    }

    @Override
    public String levelOrder() {
        return null;
    }

    @Override
    public void insert(T data) {

    }

    @Override
    public void remove(T data) {

    }

    @Override
    public T findMax() {
        return null;
    }

    @Override
    public T findMin() {
        return null;
    }

    @Override
    public BinaryNode findNode(T data) {
        return null;
    }

    @Override
    public boolean contains(T data) throws Exception {
        return false;
    }

    @Override
    public void clear() {
        root = null;
    }
}
