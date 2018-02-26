package com.greworld.datatructure.tree.base.define.binarynode;

import java.io.Serializable;

/**
 * @author 风骚的GRE
 * @Description 二叉树节点
 * @date 2018/2/22.
 */
public class BinaryNode<T extends Comparable> implements Serializable{

    private static final long serialVersionUID = 1131775139665381788L;

    public BinaryNode<T> left;//左节点

    public BinaryNode<T> right;//又节点

    public T data;// 数据元素

    public BinaryNode(T data ,BinaryNode left, BinaryNode right) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public BinaryNode(T data) {
        this.data = data;
    }

    /**
     * 判断是否为叶子节点
     * @return
     */
    public boolean isLeaf(){
        return this.left == null&&this.right==null;
    }

    /**
     * 根据先根和中根遍历算法构造二叉树
     * @param preList 先根遍历次序数组
     * @param inList  中根遍历次序数组
     * @param preStart
     * @param preEnd
     * @param inStart
     * @param inEnd
     * @return root 最终返回的根结点
     */
    public  BinaryNode<T>  createBinarySearchTreeByPreIn(T[] preList , T[] inList,int preStart ,int preEnd ,int inStart ,int inEnd){
        /**
         * 了解了完全二叉树的构造，现在我们回过头来看看二叉树又该如何构造呢？显然从完全二叉树的分析中发现，无论是先根遍历
         * 或者是中根遍历还是后根遍历，都无法唯一确定一棵树，都将面临之前的问题，遍历顺序为AB的树都可能有两种情况。因此已
         * 知二叉树的一种遍历顺序，不能确定唯一一棵二叉树。这是因为后根和先根次序反映的都是父母与孩子结点间的关系而没有反
         * 映兄弟间的关系，而中根次序反映的则是兄弟结点间的关系。既然这样，我们能不能考虑结合两种遍历顺序来构造一个二叉树
         * 呢？答案是肯定的，确实可以通过先根遍历和中根遍历次序或者后根和中根遍历次序唯一确定一棵二叉树，而先根和后根遍历
         * 反应的都是父母与孩子结点的关系，自然也就无法确定一棵唯一二叉树了，如给出先根顺序AB和后根顺序BA，可以确定A是根
         * 结点，但B呢，是左孩子还是右孩子呢？无法确定，下面我们案例来分析上面两种情况。
         *
         * 先根与中根次序构建二叉树及其代码实现:
         *  先根次序preList  [A]  [B]   [D]  [G]  [C]  [E]  [F]  [H]  (3)(4)(5)(6)
         *                   root |-- 左子树---|  |-- 右子树-------|
         *  中根次序inList   [D]  [G]   [B]  [A]  [E]  [C]  [H]  [F]
         *                   |-- 左子树---| root  |-- 右子树-------|
         *   (1)由先根次序和中根次序分解：
         *   [1]|先根次序   [B]      [D]     [G]    [2] 中根次序  [D]      [G]   [B]
         *              root --左子树-|                           |--左子树-|   root
         *   (2) 先处理A的左子树，由[1]可知，DG的根节点为B，再由[2]可知DG为B的左子树节点
         *   [1] 先根次序   [D]     [G]    [2]|中根次序  [D]      [G]
         *              root                              root   |--右子树-|
         *   (3)再把DG抽取看做左子树，由[1]可知，D是G的根节点，由[2]可知，G是D的右子树
         *   依次类推
         *
         */
        //preList[preStart]必须根结点数据,创建根结点root
        BinaryNode<T> p=new BinaryNode<>(preList[preStart]);
        //如果没有其他元素,就说明结点已构建完成
        if (preStart == preEnd && inStart == inEnd) {
            return p;
        }
        //找出中根次序的根结点下标root
        int root=0;

        for (root = inStart; root < inEnd; root++) {
            //如果中根次序中的元素值与先根次序的根结点相当,则该下标index即为inList中的根结点下标
            if (preList[preStart].compareTo(inList[root])==0){
                break;
            }
        }

        //获取左子树的长度
        int leftLength=root-inStart;
        //获取右子树的长度
        int rightLength=inEnd-root;

        //递归构建左子树
        if(leftLength>0){
            //左子树的先根序列：preList[1] , ... , preList[i]
            //左子树的中根序列：inList[0] , ... , inList[i-1]
            p.left=createBinarySearchTreeByPreIn(preList,inList,preStart+1,preStart+leftLength,inStart,root-1);
        }

        //构建右子树
        if (rightLength>0){
            //右子树的先根序列：preList[i+1] , ... , preList[n-1]
            //右子树的中根序列：inList[i+1] , ... , inList[n-1]
            p.right=createBinarySearchTreeByPreIn(preList,inList,preStart+leftLength+1,preEnd,root+1,inEnd);
        }

        return p;
    }

    /**
     *Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
     * 后根/中根遍历构建二叉树
     * @param postList 后根遍历序列
     * @param inList 中根遍历序列
     * @param postStart
     * @param postEnd
     * @param inStart
     * @param inEnd
     * @return 根结点
     */
    public BinaryNode<T> createBinarySearchTreeByPostIn(T[] postList,T[] inList,int postStart,int postEnd,int inStart,int inEnd){
        //构建根结点
        BinaryNode<T> p=new BinaryNode<>(postList[postEnd]);

        if(postStart==postEnd && inStart==inEnd){
            return p;
        }

        //查找中根序列的根结点下标root
        int root=0;

        for (root=inStart;root<inEnd;root++){
            //查找到
            if (postList[postEnd].compareTo(inList[root])==0){
                break;
            }
        }

        //左子树的长度
        int leftLenght=root-inStart;
        //右子树的长度
        int rightLenght=inEnd-root;

        //递归构建左子树
        if(leftLenght>0){
            //postStart+leftLenght-1:后根左子树的结束下标
            p.left=createBinarySearchTreeByPostIn(postList,inList,postStart,postStart+leftLenght-1,inStart,root-1);
        }
        //递归构建右子树
        if(rightLenght>0){
            p.right=createBinarySearchTreeByPostIn(postList,inList,postStart+leftLenght,postEnd-1,root+1,inEnd);
        }
        return p;
    }

}
