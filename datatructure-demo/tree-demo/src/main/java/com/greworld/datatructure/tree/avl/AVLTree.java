package com.greworld.datatructure.tree.avl;

import com.greworld.datatructure.tree.binarynode.BinaryNode;
import com.greworld.datatructure.tree.Tree;

/**
 * @author 风骚的GRE
 * @Description 平衡二叉搜索树(AVL树)
 * @date 2018/2/28.
 */
public class AVLTree<T extends Comparable> implements Tree<T> {
    /** The tree root. */
    public AVLNode<T> root;

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public int size() {
        return size(root);
    }

    public int size(AVLNode<T> subtree){
        if(subtree==null){
            return 0;
        }else {
            return size(subtree.left) + 1 + size(subtree.right);
        }


    }

    @Override
    public int height() {
        return height(root);
    }

    /**
     * @param p
     * @return
     */
    public int height(AVLNode<T> p){
        return p == null ? -1 : p.height;
    }

    @Override
    public String preOrder() {
        String sb=preOrder(root);
        if(sb.length()>0){
            //去掉尾部","号
            sb=sb.substring(0,sb.length()-1);
        }
        return sb;
    }

    /**
     * 先根遍历
     * @param subtree
     * @return
     */
    public String preOrder(AVLNode<T> subtree){
        StringBuilder sb =new StringBuilder();
        if (subtree!=null) {
            //先访问根结点
            sb.append(subtree.data).append(",");
            //访问左子树
            sb.append(preOrder(subtree.left));
            //访问右子树
            sb.append(preOrder(subtree.right));
        }
        return sb.toString();
    }

    @Override
    public String inOrder() {
        String sb=inOrder(root);
        if(sb.length()>0){
            //去掉尾部","号
            sb=sb.substring(0,sb.length()-1);
        }
        return sb;
    }

    /**
     * 中根遍历
     * @param subtree
     * @return
     */
    private String inOrder(AVLNode<T> subtree){
        StringBuilder sb =new StringBuilder();
        if (subtree!=null) {
            //访问左子树
            sb.append(inOrder(subtree.left));
            //访问根结点
            sb.append(subtree.data).append(",");
            //访问右子树
            sb.append(inOrder(subtree.right));
        }
        return sb.toString();
    }

    @Override
    public String postOrder() {
        String sb=postOrder(root);
        if(sb.length()>0){
            //去掉尾部","号
            sb=sb.substring(0,sb.length()-1);
        }
        return sb;
    }

    /**
     * 后根遍历
     * @param subtree
     * @return
     */
    private String postOrder(AVLNode<T> subtree){
        StringBuilder sb =new StringBuilder();
        if (subtree!=null){
            //访问左子树
            sb.append(postOrder(subtree.left));
            //访问右子树
            sb.append(postOrder(subtree.right));
            //访问根结点
            sb.append(subtree.data).append(",");
        }
        return sb.toString();
    }

    @Override
    public String levelOrder() {
        /**
         * @see com.greworld.datatructure.tree.binarynode.BinarySearchTree#levelOrder()
         * @return
         */
        return null;
    }

    /**
     * 插入方法
     * @param data
     */
    @Override
    public void insert(T data) {
        if (data==null){
            throw new RuntimeException("data can\'t not be null ");
        }
        this.root=insert(data,root);
    }

    /**
     * 插入方法：递归实现：与BST（二叉查找树）的插入实现原理一样，使用递归算法，根据值大小查找到插入位置，
     * 然后进行插入操作，插入完成后，我们需要进行平衡判断，评估子树是否需要进行平衡修复，需要则利用上述的
     * 旋转的四种情景套入代码即可，最后要记得重新计算插入结点路径上的高度
     * @param data
     * @param p
     * @return
     */
    private AVLNode<T> insert(T data, AVLNode<T> p) {
        //说明已没有孩子结点,可以创建新结点插入了.
        if (p==null){
            p=new AVLNode<T>(data);
        }else if (data.compareTo(p.data)<0){//向左子树寻找插入位置
            p.left=insert(data,p.left);
            //插入后计算子树的高度,等于2则需要重新恢复平衡,由于是左边插入,左子树的高度肯定大于等于右子树的高度
            if (height(p.left)-height(p.right)==2){
                //判断data是插入点的左孩子还是右孩子
                if(data.compareTo(p.left.data)<0){
                    //进行LL旋转
                    p=singleRotateLeft(p);
                }else {
                    //进行左右旋转
                    p=doubleRotateWithLeft(p);
                }
            }
        }else if (data.compareTo(p.data)>0) {//向右子树寻找插入位置
            p.right=insert(data,p.right);
            //插入后计算子树的高度,等于2则需要重新恢复平衡,由于是右边插入,右左子树的高度肯定大于等于左子树的高度
            if (height(p.right)-height(p.left)==2){
                //判断data是插入点的左孩子还是右孩子
                if (data.compareTo(p.right.data)<0){
                    //进行右左旋转
                    p=doubleRotateWithRight(p);
                }else {
                    p=singleRotateRight(p);
                }
            }
        }else{
            ;//if exist do nothing
        }

        //重新计算各个结点的高度
        p.height = Math.max( height( p.left ), height( p.right ) ) + 1;

        return p;//返回根结点
    }
    /**
     * 删除方法
     * @param data
     */
    @Override
    public void remove(T data) {
        if (data==null){
            throw new RuntimeException("data can\'t not be null ");
        }
        this.root=remove(data,root);
    }

    /**
     * 关于平衡二叉树的删除，我们这里给出一种递归的实现方案，和二叉查找树中删除方法的实现类似，
     * 但是在移除结点后需要进行平衡检测，以便判断是否需要进行平衡修复，主要明白的是，这种实现
     * 方式在删除时效率并不高，不过我们并不打算过多讨论它，更复杂的删除操作过程将放在红黑树中
     * 进行讨论
     * @param data
     * @param p
     * @return
     */
    private AVLNode<T> remove(T data, AVLNode<T> p) {
        if(p ==null){
            return null;
        }
        int result=data.compareTo(p.data);
        //从左子树查找需要删除的元素
        if (result<0){
            p.left = remove(data,p.left);
            //检测是否平衡
            if (height(p.right)-height(p.left)==2){
                AVLNode<T> currentNode=p.right;
                //判断需要那种旋转
                if (height(currentNode.left)>height(currentNode.right)){
                    // LL
                    p = singleRotateLeft(p);
                }else {
                    // LR
                    p = doubleRotateWithRight(p);
                }
            }
        }
        //从右子树查找需要删除的元素
        else if(result>0){
            p.right = remove(data,p.right);
            //检测是否平衡
            if (height(p.left)-height(p.right)==2){
                AVLNode<T> currentNode=p.left;
                //判断需要那种旋转
                if (height(currentNode.right)>height(currentNode.left)){
                    // RR
                    p = singleRotateRight(p);
                }else {
                    // RL
                    p = doubleRotateWithLeft(p);
                }
            }
        }
        //已找到需要删除的元素,并且要删除的结点拥有两个子节点
        else if (p.right!=null&&p.left!=null){
            //寻找替换结点
            p.data=findMin(p.right).data;

            //移除用于替换的结点
            p.right = remove( p.data, p.right );
        }else {
            //只有一个孩子结点或者只是叶子结点的情
            p=(p.left!=null)? p.left:p.right;
        }
        //更新高度值
        if(p!=null) {
            p.height = Math.max(height(p.left), height(p.right)) + 1;
        }
        return p;
    }

    @Override
    public T findMax() {
        return findMax(root).data;
    }

    /**
     * 查找最大值结点
     * @param p
     * @return
     */
    private AVLNode<T> findMax(AVLNode<T> p){
        if (p==null)
            return null;
        else if (p.right==null)//如果没有右结点,那么t就是最大的
            return p;
        return findMax(p.right);
    }

    @Override
    public T findMin() {
        return findMin(root).data;
    }

    /**
     * 查找最小值结点
     * @param p
     * @return
     */
    private AVLNode<T> findMin(AVLNode<T> p){
        if (p==null)//结束条件
            return null;
        else if (p.left==null)//如果没有左结点,那么t就是最小的
            return p;
        return findMin(p.left);
    }

    @Override
    public BinaryNode findNode(T data) {
        /**
         * @see com.greworld.datatructure.tree.binarynode.BinarySearchTree#findNode(Comparable)
         * @return
         */
        return null;
    }

    @Override
    public boolean contains(T data) throws Exception {
        return data != null && contain(data, root);
    }

    public boolean contain(T data , AVLNode<T> subtree){

        if (subtree==null)
            return false;

        int result =data.compareTo(subtree.data);

        if (result<0){
            return contain(data,subtree.left);
        }else if(result>0){
            return contain(data,subtree.right);
        }else {
            return true;
        }
    }

    @Override
    public void clear() {
        this.root=null;
    }
    /**
     * 左左单旋转(LL旋转) w变为x的根结点, x变为w的右子树
     *                X  （失衡点）      W
     *               / \                / \
     *              W   C              A   X
     *             / \                    / \
     *            A   B                  B   C
     * @param x
     * @return
     */
    private AVLNode<T> singleRotateLeft(AVLNode<T> x){
        // 把W节点旋转为根节点
        AVLNode<T> w = x.left;
        //同时w的右子树变为x的左子树
        x.left = w.right;
        //x变为w的右子树
        w.right=x;
        //重新计算x/w的高度
        x.height=Math.max(height(x.left),height(x.right))+1;
        w.height=Math.max(height(w.left),x.height)+1;
        return w;//返回新的根结点
    }

    /**
     * 右右单旋转(RR旋转) x变为w的根结点, w变为x的左子树
     *                W （失衡点）  X
     *               / \          / \
     *              A   X        W   C
     *                / \       / \
     *               B   C     A   B
     * @param x
     * @return
     */
    private AVLNode<T> singleRotateRight(AVLNode<T> x){
        // 把W节点旋转为根节点
        AVLNode<T> w = x.right;
        //同时w的左子树变为x的右子树
        x.right = w.left;
        //x变为w的左子树
        w.left=x;
        //重新计算x/w的高度
        w.height=Math.max(height(w.left),height(w.right))+1;
        x.height=Math.max(height(x.left),w.height)+1;

        //返回新的根结点
        return x;
    }
    /**
     * 左右旋转(LR旋转) x(根) w y 结点 把y变成根结点
     *            X             X                   Y
     *           / \           / \                 / \
     *          W   D         Y   D               W   X
     *         / \           / \                /  \  / \
     *        A   Y         W   C              A   B  C  D
     *           / \       / \
     *          B   C     A   B
     *
     * @return
     */
    private AVLNode<T> doubleRotateWithLeft(AVLNode<T> x){
        //w先进行RR旋转
        x.left=singleRotateRight(x.left);
        //再进行x的LL旋转
        return singleRotateLeft(x);
    }

    /**
     * 右左旋转(RL旋转)
     * @param w
     * @return
     */
    private AVLNode<T> doubleRotateWithRight(AVLNode<T> w){
        //先进行LL旋转
        w.right=singleRotateLeft(w.right);
        //再进行RR旋转
        return singleRotateRight(w);
    }

    private void printTree( AVLNode<T> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.data );
            printTree( t.right );
        }
    }

    /**
     * 测试
     * @param arg
     */
    public  static void main(String arg[]) throws Exception {

        AVLTree<Integer> avlTree=new AVLTree<>();

        for (int i = 1; i <18 ; i++) {
            avlTree.insert(i);
        }

        avlTree.printTree(avlTree.root);
        //删除11,8以触发旋转平衡操作
        avlTree.remove(11);
        avlTree.remove(8);

        System.out.println("================");

        avlTree.printTree(avlTree.root);

        System.out.println("findMin:"+avlTree.findMin());

        System.out.println("findMax:"+avlTree.findMax());

        System.out.println("15 exist or not : " + avlTree.contains(15) );

        System.out.println("先根遍历:"+avlTree.preOrder());

        System.out.println("中根遍历:"+avlTree.inOrder());

        System.out.println("后根遍历:"+avlTree.postOrder());

    }
}
