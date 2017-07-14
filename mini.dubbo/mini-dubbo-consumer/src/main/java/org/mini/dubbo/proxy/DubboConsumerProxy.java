package org.mini.dubbo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.mini.dubbo.support.Request;
import org.mini.dubbo.transporter.Transporters;

public class DubboConsumerProxy implements InvocationHandler{

    private Class<?> interfaces;

    public DubboConsumerProxy(Class<?> interfaces){
        this.interfaces = interfaces;
    }

    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(interfaces.getClassLoader(),new Class[]{interfaces},this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;

        //请求体
        Request request = new Request();
        request.setInterfaceName(interfaces.getName());
        request.setMethodName(method.getName());
        request.setParameterTypes(method.getParameterTypes());
        request.setArgs(args);

        //远程调用服务提供者
        result = Transporters.connectAndExecute(request);

        return result;
    }

}
