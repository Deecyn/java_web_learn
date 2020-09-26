package deecyn.component;

import java.lang.reflect.Proxy;

/**
 * @author Deecyn
 * @version 0.1
 * Description:
 */
public class SimpleAOP {
    public static Object getProxy(Object bean, MyAdvice myAdvice) {
        return Proxy.newProxyInstance(SimpleAOP.class.getClassLoader(), bean.getClass().getInterfaces(), myAdvice);
    }
}
