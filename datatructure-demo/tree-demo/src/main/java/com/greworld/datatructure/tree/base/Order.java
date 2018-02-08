package com.greworld.datatructure.tree.base;

/**
 * @author 风骚的GRE
 * @Description 树的遍历
 * @date 2018/2/6.
 */
public class Order implements Visit{
    /**
     * 先根遍历
     * @param root 要的根结点
     */
    public void preOrder(Tree root) {
        if(!root.isEmpty()) {
            visit(root);
            for(Tree child : root.getChilds()) {
                if(child != null) {
                    preOrder(child);
                }
            }
        }
    }
    /**
     * 后根遍历
     * @param root 树的根结点
     */
    public void postOrder(Tree root) {
        if(!root.isEmpty()) {
            for(Tree child : root.getChilds()) {
                if(child != null) {
                    preOrder(child);
                }
            }
            visit(root);
        }
    }

    public void visit(Tree tree) {
        System.out.print("\t" + tree.getRootData());
    }
}
