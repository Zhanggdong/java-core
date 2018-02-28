package com.greworld.datatructure.tree;

import com.greworld.datatructure.tree.binarynode.BinaryNode;

/**
 * @author 风骚的GRE
 * @Description 树接口定义:T  表示节点元素类型，该类型必须继承Comparable接口，方便比较
 * @date 2018/2/22.
 */
public interface Tree<T extends Comparable> {
    /**
     * 判空
     * @return
     */
    boolean isEmpty();

    /**
     * 二叉树的节点个数
     * @return
     */
    int size();

    /**
     * 返回二叉树的高度或者深度，即节点的最大层次
     * @return
     */
    int height();

    /**
     * 先序遍历
     * @return
     */
    String preOrder();

    /**
     * 中序遍历
     * @return
     */
    String inOrder();

    /**
     * 后序遍历
     * @return
     */
    String postOrder();

    /**
     * 层次遍历
     * @return
     */
    String levelOrder();

    /**
     * 将data插入
     * @param data
     */
    void insert(T data);

    /**
     * 删除元素
     * @param data
     */
    void remove(T data);

    /**
     * 查找最大值
     * @return
     */
    T findMax();

    /**
     * 查找最小值
     * @return
     */
    T findMin();

    /**
     * 根据值找到节点
     * @param data
     * @return
     */
    BinaryNode findNode(T data);

    /**
     * 是否包含莫而过值
     * @param data
     * @return
     * @throws Exception
     */
    boolean contains(T data) throws Exception;

    /**
     * 清空
     */
    void clear();

}
