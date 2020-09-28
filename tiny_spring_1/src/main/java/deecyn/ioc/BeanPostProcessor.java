package deecyn.ioc;

/**
 * @author Deecyn
 * @version 0.1
 * Description:
 */
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;

    Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;
}
