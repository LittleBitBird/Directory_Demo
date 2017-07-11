package com.example.guanhuawu.directory_demo;

import com.example.guanhuawu.directory_demo.Helper.Validator;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Created by guanhua.wu on 2017/7/7.
 */

public class ValidatorTest {
    static Validator Validator;

    @BeforeClass
    public static void setup(){
        Validator = new Validator();
    }

    @Test
    public void TestEmail(){
        boolean b = Validator.Email_Validate("12@12.com");
        Assert.assertTrue("邮箱验证失败",b);
        System.out.println("邮箱验证通过");
    }
}
