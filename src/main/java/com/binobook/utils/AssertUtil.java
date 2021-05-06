package com.binobook.utils;

import com.binobook.exceptions.ParamsException;

/**
 */
public class AssertUtil {


    /**
     */
    public  static void isTrue(Boolean flag, String msg){
        if(flag){
            throw  new ParamsException(msg);
        }
    }

}
