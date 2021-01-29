package com.miracle.proxy.cglib;

/**
 * @author xysy
 * @Date 2021/1/29 17:59
 */
public class CglibTest {

    public String doTest(){
        System.out.println("test ing");
        return "finished";
    }

    public static void main(String[] args) {
        CglibTest cglibTest = new CglibTest();
        ProxyTemplate proxyTemplate =  new ProxyTemplate(cglibTest, new ProxyTemplate.ProxyCallback() {
            @Override
            public void before() {
                System.out.println("before");
            }

            @Override
            public void after() {
                System.out.println("after");
            }
        });
        CglibTest proxyTest = (CglibTest) proxyTemplate.getProxyInstance();
        proxyTest.doTest();
    }
}
