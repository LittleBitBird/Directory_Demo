package com.example.guanhuawu.directory_demo;

import com.example.guanhuawu.directory_demo.Helper.validator;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Created by guanhua.wu on 2017/7/7.
 */

public class ValidatorTest {
    static validator validator;

    @BeforeClass
    public static void setup(){
        validator = new validator();
    }

    @Test
    public void TestEmail(){
        boolean b = validator.Email_Validate("12@12.com");
        Assert.assertTrue("邮箱验证失败",b);
        System.out.println("邮箱验证通过");
    }
}
