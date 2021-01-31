package com.miracle.proxy.aop;

import org.springframework.stereotype.Component;

@Component
public class CompomentWithoutInterface {

    public String doSomething(String s){
        String res = "hello "+s;
        System.out.println("hi "+s);
        return res;
    }

}
