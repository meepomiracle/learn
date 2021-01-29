package com.miracle.proxy.cglib;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xysy
 * @Date 2021/1/29 17:37
 */
@Data
@AllArgsConstructor
public class ProxyTemplate implements MethodInterceptor {

    private Object target;

    private ProxyCallback proxyCallback;
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        proxyCallback.before();
        Object returnValue = method.invoke(target,objects);
        proxyCallback.after();
        return returnValue;
    }

    /**
     * 给目标对象创建代理对象
     */
    public Object getProxyInstance(){
        //1. 工具类
        Enhancer en = new Enhancer();
        //2. 设置父类
        en.setSuperclass(target.getClass());
        //3. 设置回调函数
        en.setCallback(this);
        //4. 创建子类(代理对象)
        return en.create();
    }

    public interface ProxyCallback{
        void before();

        void after();
    }

}
