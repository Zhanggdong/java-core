package com.greworld.datatructure.tree.base.define.binarynode;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/2/26.
 */
public class EmptyTreeException extends RuntimeException {
    public EmptyTreeException(String s) {
        System.out.println("Tree must not be null!");
    }
}
