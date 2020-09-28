package deecyn.ioc.config;

/**
 * @author Deecyn
 * @version 0.1
 * Description: PropertyValue 中的 name 和 value 两个字段，用于记录 bean 配置中的标签的属性值。
 */
public class PropertyValue {
    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
