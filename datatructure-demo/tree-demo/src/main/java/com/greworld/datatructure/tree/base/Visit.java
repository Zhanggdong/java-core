package com.greworld.datatructure.tree.base;

/**
 * @author 风骚的GRE
 * @Description 对结点进行操作的接口,规定树的遍历的类必须实现这个接口
 * @date 2018/2/6.
 */
public interface Visit {
    /**
     * 对结点进行某种操作
     * @param tree 树的结点
     */
    public void visit(Tree tree);
}
