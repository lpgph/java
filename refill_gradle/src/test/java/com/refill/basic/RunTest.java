package com.refill.basic;

import com.refill.util.StringUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by peter on 6/18/17.
 */
public class RunTest {

    /**
     * slf4j日志记录
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    public void runTest(){
//        String s1 = new StringBuilder("go")
//                .append("od").toString();
//        System.out.println(s1.intern() == s1);
//        String s2 = new StringBuilder("ja")
//                .append("va").toString();
        //四舍五入
//        System.out.println(Math.round(10.4));
//        logger.debug("{} == {}",31*2,(2 << 5)-2);
        String[] s = {};
        List<String> stringList = new ArrayList<>();
        Boolean b = null;
        String str = null;
        logger.debug("{} ", Arrays.toString(s));
        logger.debug("{} ", s.toString());
    }


}
