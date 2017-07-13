package com.example.guanhuawu.directory_demo;

import com.example.guanhuawu.directory_demo.helper.Concert;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void Convert_isCorrect() throws Exception {
        System.out.println(Concert.getPingYin("我"));
        System.out.println(Concert.getPingYin("我阿斯蒂芬"));
        System.out.println(Concert.getPingYin("汉语拼音"));
    }
}