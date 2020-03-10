package Junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JunitTest {
    public int add(int a, int b){
        return a + b;
    }
    //在执行test之前自动执行
    @Before
    public void init(){
        System.out.println("init......");
    }
    //在执行test之后自动执行
    @After
    public void close(){
        System.out.println("close.....");
    }
    @Test
    public void testAdd(){
        int result = add(2,4);
        System.out.println(result);
        //断言，我断言结果，不是这个结果就报错
        Assert.assertEquals(6,result);
    }
}
