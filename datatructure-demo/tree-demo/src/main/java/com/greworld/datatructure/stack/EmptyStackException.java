package com.greworld.datatructure.stack;

import java.io.Serializable;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/2/28.
 */
public class EmptyStackException extends RuntimeException implements Serializable {


    private static final long serialVersionUID = -8766038608920134747L;


    public EmptyStackException(){
        super();
    }

    public EmptyStackException(String msg){
        super(msg);
    }
}
