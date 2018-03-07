package com.greworld.datatructure.tree.rb;

import com.greworld.datatructure.tree.Tree;
import com.greworld.datatructure.tree.binarynode.BinaryNode;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/3/1.
 */
public class RBTree<T extends Comparable> implements Tree<T> {
    private final RBTreeNode<T> root;

    private AtomicLong size = new AtomicLong(0);
    private volatile boolean overrideMode=true;

    public RBTree(){
        this.root = new RBTreeNode<T>();
    }

    public RBTree(boolean overrideMode){
        this();
        this.overrideMode=overrideMode;
    }


    public boolean isOverrideMode() {
        return overrideMode;
    }

    public void setOverrideMode(boolean overrideMode) {
        this.overrideMode = overrideMode;
    }


    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
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

    }

    /**
     * 左旋
     * @param node
     */
    private void rotateLeft(RBTreeNode<T> node){

    }

    /**
     * 右旋
     * @param node
     */
    private void rotateRight(RBTreeNode<T> node){

    }
}
