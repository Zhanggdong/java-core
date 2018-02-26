package com.greworld.datatructure.tree.base.define.binarynode;

import com.greworld.datatructure.tree.base.define.Tree;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author 风骚的GRE
 * @Description 查找二叉树
 * @see Tree 树接口定义
 * @see  BinaryNode 树结构定义
 * @date 2018/2/22.
 */
public class BinarySearchTree<T extends Comparable> implements Tree<T>{

    protected BinaryNode<T> root;// 根节点

    public BinarySearchTree() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    /********************************二叉树的大小(size)计算的设计与实现（递归）******************************
     很显然树也是一个递归结构，而且我们发现，要从第一层开始计算，求出整棵树的结点数，只要先求出第2层的结点数
     （左子树和右子树），然后加上父结点1就是整棵树的结点数了，是不是跟汉诺塔求解很相似呢？只不过这里是反向求
     解罢了，因此我们的算法程序可以这样设计：
     */
    /**
     * 计算大小
     * @return
     */
    @Override
    public int size() {
        return size(root);
    }
    /**
     * 递归实现：定义根节点root后，再用subtree实现递归
     * @param subtree
     * @return
     */
    private int size(BinaryNode<T> subtree) {
        if (subtree==null) {
            return 0;
        }else {
            //对比汉诺塔:H(n)=H(n-1) + 1 + H(n-1)
            return size(subtree.left)+1+size(subtree.right);
        }
    }
    /*************************二叉查找树的深度(height)设计与实现（递归）******************************
     根据前面的术语，可知树的深度即为最大层的结点所在层次，而大小就是树的结点数，关于深度，我们只需要从根结点开始寻找，
     然后计算出左子树的深度和右子树的深度，接着比较左子树与右子树的深度，最后返回深度大的即可。深度求解过程图示以及代
     码实现如下：
     *******************************************************************************************************************/
    /**
     * 计算深度
     * @return
     */
    @Override
    public int height() {
        return height(root);
    }
    /**
     * 递归实现
     * @param subtree
     * @return
     */
    private int height(BinaryNode<T> subtree) {
        if (subtree==null){
            return 0;
        }else {
            int l = height(subtree.left);
            int r = height(subtree.right);
            return (l>r)?(l+1):(r+1);
        }
    }

    /*********************************递归与非递归的先根次序遍历算法的实现**************************************
     先根次序遍历算法，其访问规则是先遍历根结点，再遍历左子树，最后遍历右子树。
     如下图先根遍历的次序为ABEFC
     */

    @Override
    public String preOrder() {
        String sb = preOrder(root);
        if (sb.length()>0){
            //去掉尾部","号
            sb = sb.substring(0,sb.length()-1);
        }
        return sb;
    }
    /**
     * 先根遍历
     * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
     * @param subtree
     * @return
     */
    private String preOrder(BinaryNode<T> subtree) {
        StringBuffer sb = new StringBuffer();
        if (subtree!=null){// 递归结束条件
            //先访问根结点
            sb.append(subtree.data+",");
            // 遍历左子树
            sb.append(preOrder(subtree.left));
            // 遍历又子树
            sb.append(preOrder(subtree.right));
        }
        return sb.toString();
    }
    /**
     * 非递归的先根遍历
     * @return
     */
    public String preOrderTraverse(){
        StringBuffer sb=new StringBuffer();
        //构建用于存放结点的栈
        Stack<BinaryNode<T>> stack=new Stack<>();
        BinaryNode<T> p=this.root;
        if (p!=null||stack.isEmpty()){
            if (p!=null){
                // 访问该节点
                sb.append(p.data+",");
                // 将访问过的节点入栈
                stack.push(p);
                // 继续访问左孩子，直到p为null
                p = p.left;
            }
        }else {//若p=null 栈不为空,则说明已沿左子树访问完一条路径, 从栈中弹出栈顶结点,并访问其右孩子
            p=stack.pop();//获取已访问过的结点记录
            p=p.right;
        }
        //去掉最后一个逗号
        if(sb.length()>0){
            return sb.toString().substring(0,sb.length()-1);
        }else {
            return sb.toString();
        }
    }
    /*********************************递归与非递归的中根次序遍历算法的实现**************************************
     中根次序遍历的算法规则是，先遍历左子树，再遍历根结点，最后遍历右子树。
     过程如下图（同样利用的是递归思维）
     */
    @Override
    public String inOrder() {
        String sb = inOrder(root);
        if (sb.length()>0){
            //去掉尾部","号
            sb = sb.substring(0,sb.length()-1);
        }
        return sb;
    }
    /**
     * 中根遍历
     * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
     * @return
     */
    private String inOrder(BinaryNode<T> subtree) {
        StringBuffer sb = new StringBuffer();
        if (subtree!=null){// 递归结束条件
            //先遍历左子树
            sb.append(inOrder(subtree.left));
            //再遍历根结点
            sb.append(subtree.data+",");
            //最后遍历右子树
            sb.append(inOrder(subtree.right));
        }
        return sb.toString();
    }
    /**
     * 非递归的中根遍历
     * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
     * @return
     */
    public String inOrderTraverse(){
        StringBuffer sb = new StringBuffer();
        //构建用于存放结点的栈
        Stack<BinaryNode<T>> stack = new Stack<>();
        BinaryNode<T> p=this.root;
        if (p!=null||stack.isEmpty()){
            while (p!=null){//把左孩子都入栈,至到左孩子为null
                stack.push(p);
                p=p.left;
            }
            //如果栈不为空,因为前面左孩子已全部入栈
            if (!stack.isEmpty()){
                p=stack.pop();
                //访问p结点
                sb.append(p.data+",");
                //访问p结点的右孩子
                p=p.right;
            }
        }
        //去掉最后一个逗号
        if(sb.length()>0){
            return sb.toString().substring(0,sb.length()-1);
        }else {
            return sb.toString();
        }
    }
    /*********************************递归与非递归的后根次序遍历算法的实现**************************************
     后根次序遍历的算法规则是，先访问左子树，再访问右子树，最后访问根结点，如下图(递归思维)：
     */
    @Override
    public String postOrder() {
        String sb = postOrder(root);
        if (sb.length()>0){
            //去掉尾部","号
            sb = sb.substring(0,sb.length()-1);
        }
        return sb;
    }
    /**
     * 后根遍历
     * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
     * @param subtree
     * @return
     */
    private String postOrder(BinaryNode<T> subtree) {
        StringBuffer sb = new StringBuffer();
        if (subtree!=null) {// 递归结束条件
            //先遍历左子树
            sb.append(postOrder(subtree.left));
            //再遍历右子树
            sb.append(postOrder(subtree.right));
            //最后遍历根结点
            sb.append(subtree.data+",");
        }
        return sb.toString();
    }
    /**
     * 非递归后根遍历
     * @return
     */
    public String postOrderTraverse(){
        StringBuffer sb=new StringBuffer();
        //构建用于存放结点的栈
        Stack<BinaryNode<T>> stack=new Stack<>();
        BinaryNode<T> currentNode =this.root;
        BinaryNode<T> prev=this.root;
        while (currentNode!=null||!stack.isEmpty()){
            //把左子树加入栈中,直到叶子结点为止
            while (currentNode!=null){
                stack.push(currentNode);
                currentNode=currentNode.left;
            }
            //开始访问当前结点父结点的右孩子
            if(!stack.isEmpty()){
                //获取右孩子，先不弹出
                BinaryNode<T> temp=stack.peek().right;
                //先判断是否有右孩子或者右孩子是否已被访问过
                if(temp==null||temp==prev){//没有右孩子||右孩子已被访问过
                    //如果没有右孩子或者右孩子已被访问,则弹出父结点并访问
                    currentNode=stack.pop();
                    //访问
                    sb.append(currentNode.data+",");
                    //记录已访问过的结点
                    prev=currentNode;
                    //置空当前结点
                    currentNode=null;
                }else {
                    //有右孩子,则开始遍历右子树
                    currentNode=temp;
                }
            }
        }
        //去掉最后一个逗号
        if(sb.length()>0){
            return sb.toString().substring(0,sb.length()-1);
        }else {
            return sb.toString();
        }
    }
    /**************************************层次遍历算法的实现************************************
     分析完前面的3种遍历算法，我们最后来分析一下层次遍历，二叉查找树的层次遍历特性就是兄弟优先访问，
     两个兄弟结点的访问顺序是先左后右的。同样它们的后代结点的访问次序也是先左后右，左兄弟的所有孩子
     结点一定优先右兄弟的孩子访问。对于二叉查找树的层次遍历算法，我们需要明确如何解决一下的存在的问题
     （假设p从根结点开始访问）：

     p点如何到达其兄弟结点? B->C
     p点如何到达它同层下一个结点(非兄弟结点)？D->E
     p点如何在访问完当前层的最后一个结点时，进入下一层的第一个结点？C->D

     很显然，我们现在遇到的问题跟前面非递归算法遍历的问题有些类似，也就是二叉链表的本身根本无法满足以上任意一个问题，
     因为从B到C，从D到E，从C到D根本没有桥梁，此时肯定得借助第3方容器来满足需求，那么这个容器该如何选呢？该容器必须告
     诉我们下一个访问结点是谁？层次遍历的规则是兄弟优先，从左往右，因此，在访问时，必须先将当前正在访问的结点P的左右
     孩子依次放入容器，如P=C时，E、H必须已在栈中，而且先进入必须先访问，即先进E再进H，然后先访问E再访问H，显然该容器
     必须满足“先进先出”的原则，那也就是队列了，这里我们选择LinkedQueue队列，层次遍历算法描述如下：
     p点从根结点开始访问，设一个空队列，当前p结点不为空时，重复以下操作：
     ① 访问p结点，将p结点的左右孩子依次入队。
     ② 使p指向一个出队结点，重复①的操作，直到队列为空。
    */
    /**
     * 层次遍历
     * @return
     */
     @Override
    public String levelOrder() {
         /**
          * 存放需要遍历的结点,左结点一定优先右节点遍历
          */
         Queue<BinaryNode<T>> queue=new LinkedBlockingQueue<>();
         StringBuffer sb=new StringBuffer();
         BinaryNode<T> p=this.root;
         while (p!=null){
             //记录经过的结点
             sb.append(p.data);
             //先按层次遍历结点,左结点一定在右结点之前访问
             if (p.left!=null){
                 //孩子结点入队
                 queue.add(p.left);
             }
             if (p.right!=null){
                 queue.add(p.right);
             }
             //访问下一个结点
             p=queue.poll();
         }
         return sb.toString();
    }

    @Override
    public void insert(T data) {
        if (data == null){
            throw new RuntimeException("data can \'Comparable be null !");
        }
        // 插入操作
        root = insert(data,root);
    }

    /**
     *       5
     *      / \
     *     2   8
     *    /   /
     *   1    6
     *   推导：4与5比较 compareResult <0 ，往左插入
     *         碰到2 ，4与2比较compareResult > 0 ，往右插入，没有需要比较的元素，则插入成功
     * @param data
     * @param p
     * @return
     */

    private BinaryNode<T> insert(T data, BinaryNode<T> p) {
        if (p == null){
            p = new BinaryNode<T>(data,null,null);
        }

        int compareResult = data.compareTo(p.data);

        if (compareResult<0){// 左
            p.left = insert(data,p.left);
        }else if (compareResult>0){// 又
            p.right = insert(data,p.right);
        }else {
            // 什么也不做，因为已经存在该节点了
        }
        return p;
    }

    @Override
    public void remove(T data) {
        if (data == null){
            throw new RuntimeException("data can \'Comparable be null !");
        }
        // 删除节点
        root = remove(data,root);
    }

    /**
     * 删除节点的情况比较复杂，主要有以下三种：
     * （1）删除叶子节点（也是没有孩子的节点）
     * （2）删除拥有一个孩子节点（可能是左孩子也肯能是又孩子）
     * （3）删除拥有两个孩子的节点
     * @param data
     * @param p
     * @return
     */
    private BinaryNode<T> remove(T data, BinaryNode<T> p) {
        // 没有找到要删除的元素，递归结束
        if (p==null){
            return p;
        }
        int compareResult = data.compareTo(p.data);
        if (compareResult<0){// 左边删除
            p.left = remove(data,p.left);
        }else if (compareResult>0){// 右边删除
            p.right = remove(data,p.right);
        }else if (p.left!=null&&p.right!=null){
            // 中继替换，找到左子树中最小的元素并替换
            p.data = findMin(p.right).data;
            // 移除用于替换的节点
            p.right = remove(p.data,p.right);
        }else {
            // 拥有一个孩子节点的节点和叶子节点的情况
            p = (p.left!=null)?p.left:p.right;
        }
        return p;
    }

    /**
     * 非递归删除
     * @param data
     * @return
     */
    public T removeUnrecure(T data){
        if (data == null){
            throw new RuntimeException("data can \'Comparable be null !");
        }
        //从跟节点开始查找
        BinaryNode<T> current = this.root;
        // 记录父节点
        BinaryNode<T> parent = this.root;
        // 判断左孩子的flag
        boolean isLeft = true;
        // 找到要删除的节点
        while (data.compareTo(current.data)!=0){
            //更新父节点记录
            parent = current;
            int result = data.compareTo(current.data);
            if (result<0){// 从左子树查找
                isLeft = true;
                current = current.left;
            }else if (result>0){// 从又子树开始查找
                isLeft = false;
                current = current.right;
            }
            // 如果没有找到，返回null
            if (current == null){
                return null;
            }
        }
        // ----------------------到这里说明已找到要删除的节点
        // 要删除的是叶子节点
        if (current.left==null&&current.right==null){
            if (current==this.root){
                this.root = null;
            }else if (isLeft){
                parent.left = null;
            }else {
                parent.right=null;
            }
        }
        //删除带有一个孩子结点的结点,当current的left不为null
        else if (current.right==null){
            if (current==this.root){
                this.root = current.left;
            }else if (isLeft){//current为parent的左孩子
                parent.left = current.left;
            }else {//current为parent的右孩子
                parent.right = current.right;
            }
        }
        //删除带有两个孩子结点的结点
        else{
            //找到当前要删除结点current的右子树中的最小值元素
            BinaryNode<T> successor= findSuccessor(current);
            if (current==this.root){
                this.root = successor;
            }else if (isLeft){
                parent.right = successor;
            }else{
                parent.right = successor;
            }
            // 把当前要删除的结点的左孩子赋值给successor
            successor.left = current.left;
        }
        return current.data;
    }

    private BinaryNode<T> findSuccessor(BinaryNode<T> delNode) {
        BinaryNode<T> successor = delNode;
        BinaryNode<T> successorParent = delNode;
        BinaryNode<T> current = delNode.right;
        //不断查找左结点,直到为空,则successor为最小值结点
        while (current!=null){
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        //如果要删除结点的右孩子与successor不相等,则执行如下操作(如果相等,则说明是删除结点)
        if (delNode.right != successor){
            successorParent.left = successor.right;
            //把中继结点的右孩子指向当前要删除结点的右孩子
            successor.right = delNode.right;
        }
        return successor;
    }

    @Override
    public T findMax() {
        if(isEmpty())
            throw new EmptyTreeException("BinarySearchTree is empty!");
        return findMax(root).data;
    }
    /**
     * 查找最大值结点
     * @param p
     * @return
     */
    private BinaryNode<T> findMax(BinaryNode<T> p) {
        if (p==null) {//结束条件
            return null;
        }else if (p.right==null){//如果没有又结点,那么t就是最小的
            return p;
        }
        return findMin(p.right);
    }

    @Override
    public T findMin() {
        if(isEmpty())
            throw new EmptyTreeException("BinarySearchTree is empty!");
        return findMin(root).data;
    }

    /**
     * 查找最小值结点
     * @param p
     * @return
     */
    private BinaryNode<T> findMin(BinaryNode<T> p) {
        if (p==null) {//结束条件
            return null;
        }else if (p.left==null){//如果没有左结点,那么t就是最小的
            return p;
        }
        return findMin(p.left);
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
}
