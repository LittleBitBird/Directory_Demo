package com.example.guanhuawu.directory_demo;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
//       Concert concert = new Concert();
        System.out.print(Concert.getPingYin("我").charAt(0));
    }
}