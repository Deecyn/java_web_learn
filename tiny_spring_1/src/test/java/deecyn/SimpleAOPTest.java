package deecyn;

import deecyn.simple_demo.MyAdvice;
import deecyn.simple_demo.BeforeAdvice;
import deecyn.simple_demo.MethodInvocation;
import deecyn.simple_demo.SimpleAOP;
import deecyn.service.HelloService;
import deecyn.service.HelloServiceImpl;
import org.junit.Test;

/**
 * @author Deecyn
 * @version 0.1
 * Description:
 */
public class SimpleAOPTest {

    @Test
    public void getProxy(){
        MethodInvocation logTask = () -> System.out.println("log task start.");
        HelloServiceImpl helloServiceImpl = new HelloServiceImpl();

        MyAdvice beforeAdvice = new BeforeAdvice(helloServiceImpl, logTask);

        HelloService helloServiceImplProxy = (HelloService) SimpleAOP.getProxy(helloServiceImpl, beforeAdvice);
        helloServiceImplProxy.sayHelloWorld();
    }
}
