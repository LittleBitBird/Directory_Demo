package com.example.guanhuawu.directory_demo.helper;

/**
 * Created by guanhua.wu on 2017/7/7.
 */

public class Validator {
    public boolean emailValidate(String Email){
//        String s = "/^(\\w)*@(\\w)*.(\\w)*$/";
        String s = "^\\w+@(\\w)+.(\\w)+";
        return Email.matches(s);
    }
}
