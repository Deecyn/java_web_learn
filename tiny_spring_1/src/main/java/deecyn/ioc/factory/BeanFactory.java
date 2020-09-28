package deecyn.ioc.factory;

/**
 * @author Deecyn
 * @version 0.1
 * Description:
 */
public interface BeanFactory {
    Object getBean(String beanId) throws Exception;
}
