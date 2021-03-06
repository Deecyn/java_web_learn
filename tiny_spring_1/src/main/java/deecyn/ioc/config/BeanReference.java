package deecyn.ioc.config;

/**
 * @author Deecyn
 * @version 0.1
 * Description: BeanReference 对象保存的是 bean 配置中 ref 属性对应的值，
 * 在后续 BeanFactory 实例化 bean 时，会根据 BeanReference 保存的值去实例化 bean 所依赖的其它 bean。
 */
public class BeanReference {
    private String name;
    private Object bean;

    public BeanReference() {
    }

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
