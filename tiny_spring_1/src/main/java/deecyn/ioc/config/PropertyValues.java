package deecyn.ioc.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Deecyn
 * @version 0.1
 * Description:
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv) {
        // 在这里添加之前可以对参数值 propertyValueList 做一些处理，如果直接使用 List，就不行了
        this.propertyValueList.add(pv);
    }

    public List<PropertyValue> getPropertyValueList() {
        return this.propertyValueList;
    }

}
