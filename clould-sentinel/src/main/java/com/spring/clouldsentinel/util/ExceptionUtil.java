package com.spring.clouldsentinel.util;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class ExceptionUtil {
    public static String handleException(BlockException ex) {
        return "扛不住了啊....";
    }
}
