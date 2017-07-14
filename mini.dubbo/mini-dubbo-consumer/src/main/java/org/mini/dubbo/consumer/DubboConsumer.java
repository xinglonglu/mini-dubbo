package org.mini.dubbo.consumer;

import org.mini.dubbo.proxy.DubboConsumerProxy;

public class DubboConsumer<T> {
	   //接口名字
    private String interfaceName;

    //接口
    private Class<?> interfaceClass;

    //代理类
    private T ref;

    public T get(){
        //获取代理
        ref = new DubboConsumerProxy(interfaceClass).getProxy();
        return ref;
    }



    //--------------------以下是get和set方法----------------//
    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterface(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public void setInterface(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
        setInterface(interfaceClass == null ? (String) null : interfaceClass.getName());
    }

    public Class<?> getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }
}
