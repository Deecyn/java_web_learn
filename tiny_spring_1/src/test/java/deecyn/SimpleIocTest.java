package deecyn;

import deecyn.vo.Car;
import deecyn.vo.Wheel;
import deecyn.simple_demo.SimpleIoC;
import org.junit.Test;

/**
 * @author Deecyn
 * @version 0.1
 * Description:
 */
public class SimpleIocTest {

    @Test
    public void getBean() throws Exception {
        String location = SimpleIoC.class.getClassLoader().getResource("spring-ioc.xml").getFile();

        SimpleIoC simpleIoC = new SimpleIoC(location);
        Wheel wheel = (Wheel) simpleIoC.getBean("wheel");
        System.out.println(wheel);

        Car car = (Car) simpleIoC.getBean("car");
        System.out.println(car);
    }
}
