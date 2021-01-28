package com.miracle.proxy.dynamic;

public class ProxyTestImpl implements ProxyTestInterface {
    @Override
    public String hello(String s) {
        String res = "res-"+s;
        System.out.println("hello:"+s);
        return res;
    }

    @Override
    public void pass() {
        System.out.println("pass is called");
    }
}
