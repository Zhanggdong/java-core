package com.greworld.datatructure.tree.binarynode;

import com.greworld.datatructure.queue.LinkedQueue;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author 风骚的GRE
 * @Description 利用层次遍历原理构造完全二叉树
 * 明白了层次遍历算法后，我们可以利用层次遍历算法来构建一棵完全二叉树，为什么是完全二叉树而不是二叉树呢？
 * 显然层次遍历不能确定唯一的一棵二叉树，看个简单的例子，层次遍历顺序为AB时，有如下两种情况：
 *       A   A
 *     /      \
 *    B        B
 *  那为什么层次遍历就可以确定完全二叉树呢？这是因为完全二叉树的特性所决定的，一棵具有n个结点的完全二叉树，
 *  对于序号为i(0≤i＜n)的结点，则有如下规则
 *  ①若i=0，则i为根结点，无父母结点；若i>0，则i的父母结点序号为⌊i−12⌋(向下取整)。
 *  ②若2i+1＜n,则i的左孩子结点序号为2i+1，否则i无左孩子。
 *  ③若2i+2＞n,则i的右孩子结点序号为2i+2，否则i无右孩子。
 *  因此很容易知道第0个结点就是完全二叉树，而左孩子结点序号为2i+1，否则没有左孩子，
 *  右结点序号为2i+2，否则没有右孩子，这样的编号恰好符合层次遍历的访问顺序，因此
 *  层次遍历确实可以确定一棵完全二叉树，如下图：

 * @date 2018/2/26.
 */
public class CompleteBinaryTree<T extends Comparable> extends BinarySearchTree <T> {
    /**
     * 构建空完全二叉树
     */
    public CompleteBinaryTree()
    {
        super();
    }
    /**
     * 以层序遍历构造完全二叉树
     * @param levelOrderArray
     */
    public CompleteBinaryTree(T[] levelOrderArray)
    {
        this.root = create(levelOrderArray, 0);
    }
    /**
     * 层次遍历构造完全二叉树
     * @param levelOrderArray
     * @param i
     * @return
     */
    public BinaryNode<T> create(T[] levelOrderArray ,int i){
        if(levelOrderArray ==null){
            throw new RuntimeException("the param 'array' of create method can\'t be null !");
        }
        BinaryNode<T> p = null;
        if (i<levelOrderArray.length){//递归结束条件
            p=new BinaryNode<>(levelOrderArray[i],null,null);
            p.left=create(levelOrderArray,2*i+1);  //根据完全二叉树的性质 2*i+1 为左孩子结点
            p.right=create(levelOrderArray,2*i+2); //2*i+2 为右孩子结点
        }
        return p;
    }
    /**
     * 搜索二叉树的包含操作和移除操作不适合层次遍历构造的完全二叉树
     * 根据层次遍历构建的二叉树必须用层次遍历来判断(仅适用层次遍历构建的完全二叉树)
     * @param data
     * @return
     */
    @Override
    public boolean contains(T data) {
        /**
         * 存放需要遍历的结点,左结点一定优先右节点遍历
         */
        LinkedQueue<BinaryNode<T>> queue=new LinkedQueue<>();
        StringBuffer sb=new StringBuffer();
        BinaryNode<T> p=this.root;

        while (p!=null){
            //判断是否存在data
            if(data.compareTo(p.data)==0){
                return true;
            }
            //先按层次遍历结点,左结点一定在右结点之前访问
            if(p.left!=null){
                //孩子结点入队
                queue.add(p.left);
            }

            if (p.right!=null){
                queue.add(p.right);
            }
            //访问下一个结点
            p=queue.poll();
        }
        return false;
    }

    /**
     * 搜索二叉树的包含操作和移除操作不适合层次遍历构造的完全二叉树
     * @param data
     * @return
     */
    @Override
    public void remove(T data) {
        //do nothing 取消删除操作
    }
    /**
     * 完全二叉树只通过层次遍历来构建,取消insert操作
     * @param data
     */
    @Override
    public void insert(T data) {
        //do nothing //取消insert操作
    }
    /**
     * 测试
     * @param args
     */
    public static void main(String args[])
    {

        String[] levelorderArray = {"A","B","C","D","E","F"};
        CompleteBinaryTree<String> cbtree = new CompleteBinaryTree<>(levelorderArray);
        System.out.println("先根遍历:"+cbtree.preOrder());
        System.out.println("非递归先根遍历:"+cbtree.preOrderTraverse());
        System.out.println("中根遍历:"+cbtree.inOrder());
        System.out.println("非递归中根遍历:"+cbtree.inOrderTraverse());
        System.out.println("后根遍历:"+cbtree.postOrder());
        System.out.println("非递归后根遍历:"+cbtree.postOrderTraverse());
        System.out.println("查找最大结点(根据搜索二叉树):"+cbtree.findMax());
        System.out.println("查找最小结点(根据搜索二叉树):"+cbtree.findMin());
        System.out.println("判断二叉树中是否存在E:"+cbtree.contains("E"));

    }
}
