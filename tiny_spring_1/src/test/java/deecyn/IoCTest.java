package deecyn;

import deecyn.ioc.xml.XmlBeanFactory;
import deecyn.vo.Car;
import deecyn.vo.Wheel;
import org.junit.Test;

/**
 * @author Deecyn
 * @version 0.1
 * Description:
 */
public class IoCTest {

    @Test
    public void testGetBean() throws Exception {
        System.out.println("--------- IoC Test -----------");
        String location = getClass().getClassLoader().getResource("spring-ioc.xml").getFile();
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(location);

        Wheel wheel = (Wheel) xmlBeanFactory.getBean("wheel");
        System.out.println(wheel);

        Car car = (Car) xmlBeanFactory.getBean("car");
        System.out.println(car);
    }
}
