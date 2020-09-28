package deecyn.ioc.factory;

/**
 * @author Deecyn
 * @version 0.1
 * Description:
 */
public interface BeanFactoryAware {
    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
