package com.miracle.proxy.aop;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class ComponentWithInterface implements SimpleInterface, InitializingBean {

    @Override
    public String hello(String s) {
        String res = "hello "+s;
        System.out.println("hi "+s);
        return res;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("init ComponentWithInterface");
    }
}
