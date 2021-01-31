package com.miracle.proxy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 使用spring aop 需要引入spring-aop,aspectjrt,aspectjweaver包
 * 在j2se程序中使用spring似乎只有写xml配置文件，注入bean，以及配置aop的方式
 * 扫描配置
 * <context:component-scan base-package="com.miracle"/>
 * 开启aop注解方式
 * <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
 * 决定是基于接口的还是基于类的代理被创建 true表示基于类，需要cglib,false表示基于接口，使用jdk的动态代理
 * <aop:config proxy-target-class="true"/>
 *
 * 如果proxy-target-class=false或者不配，则对于没有实现接口的类，spring会使用cglib代理，实现接口的类使用jdk动态代理
 * 如果proxy-target-class=true，则都是用cglib代理
 *
 */
@Component
@Aspect
public class AopAspect {

    @Pointcut("execution(* com.miracle.proxy.aop.*.*(..))")
    public void pointCut1() {

    }

    @Around("pointCut1()")
    public Object around1(ProceedingJoinPoint point) throws Throwable {
        System.out.println("before");
        Object result = point.proceed();
        System.out.println("after");
        return result;
    }

    @Pointcut("execution(* com.miracle.proxy.aop.ComponentWithInterface.*(..))")
    public void pointCut2() {

    }

    @Around("pointCut2()")
    public Object around2(ProceedingJoinPoint point) throws Throwable {
        System.out.println("before 2");
        Object result = point.proceed();
        System.out.println("after 2");
        return result;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CompomentWithoutInterface compomentWithoutInterface = context.getBean(CompomentWithoutInterface.class);
//        compomentWithoutInterface.doSomething("component");

        SimpleInterface componentWithInterface = (SimpleInterface) context.getBean("componentWithInterface");
        componentWithInterface.hello("dynamic proxy");
    }
}
