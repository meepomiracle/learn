package com.miracle.proxy.aop.alliance;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * aopalliance用法有点类似于cglib
 * 暂未得知在springboot中用法
 */
@Component
public class ComponentWithAlliance implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("before alliance");
        Object returnValue = methodInvocation.proceed();
        System.out.println("after alliance");
        return returnValue;
    }

    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        AllianceTestService allianceTestService = (AllianceTestService) context.getBean("allianceTestServiceProxy");
        allianceTestService.hello();
    }
}

