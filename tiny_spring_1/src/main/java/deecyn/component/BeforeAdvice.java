package deecyn.component;

import java.lang.reflect.Method;

/**
 * @author Deecyn
 * @version 0.1
 * Description:
 */
public class BeforeAdvice implements MyAdvice {

    private Object bean;
    private MethodInvocation methodInvocation;

    public BeforeAdvice(Object bean, MethodInvocation methodInvocation) {
        this.bean = bean;
        this.methodInvocation = methodInvocation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        methodInvocation.invoke();
        return method.invoke(bean, args);
    }
}
