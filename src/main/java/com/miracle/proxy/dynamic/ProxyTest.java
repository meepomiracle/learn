package com.miracle.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

    private ProxyTestInterface proxyTestImpl;

    public ProxyTest(ProxyTestInterface proxyTestImpl) {
        this.proxyTestImpl = proxyTestImpl;
    }

    public ProxyTestInterface getProxy(){
        return (ProxyTestInterface) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), proxyTestImpl.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object res = null;
                System.out.println("begin proxy");
                if(method.getName().equals("hello")){
                    res = method.invoke(proxyTestImpl,args);
                }
                System.out.println("end proxy");
                return res;
            }
        });
    }

    public static void main(String[] args) {
        ProxyTestInterface proxyTestImpl = new ProxyTestImpl();
        ProxyTest proxyTest = new ProxyTest(proxyTestImpl);
        ProxyTestInterface proxyTestInterface = proxyTest.getProxy();
        proxyTestInterface.pass();
        proxyTestInterface.hello("world");
    }
}
