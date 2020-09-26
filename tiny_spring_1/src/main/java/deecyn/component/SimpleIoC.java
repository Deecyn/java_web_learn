package deecyn.component;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Deecyn
 * @version 0.1
 * Description:
 */
public class SimpleIoC {
    private Map<String, Object> beanMap = new HashMap<>();

    public SimpleIoC(String location) throws Exception{
        loadBeans(location);
    }

    private void loadBeans(String location) throws Exception{
        // 加载 xml 配置文件
        InputStream inputStream = new FileInputStream(location);
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);

        Element root = document.getDocumentElement();
        NodeList nodes = root.getChildNodes();

        // 遍历 <bean> 标签
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node instanceof Element) {
                Element ele = (Element) node;
                String id = ele.getAttribute("id");
                String className = ele.getAttribute("class");

                Class beanClass = null;
                try {
                    beanClass = Class.forName(className);
                } catch (ClassNotFoundException e){
                    e.printStackTrace();
                    return;
                }

                Object bean = beanClass.newInstance();

                NodeList propertyNodes = ele.getElementsByTagName("property");
                // 遍历 <property> 标签
                for (int j = 0; j < propertyNodes.getLength(); j++) {
                    Node propertyNode = propertyNodes.item(j);
                    if (propertyNode instanceof Element) {
                        Element propertyElement = (Element) propertyNode;
                        String name = propertyElement.getAttribute("name");
                        String value = propertyElement.getAttribute("value");

                        Field declaredField = bean.getClass().getDeclaredField(name);
                        declaredField.setAccessible(true);

                        if (value != null && value.length() > 0) {
                            declaredField.set(bean, value);
                        } else {
                            String ref = propertyElement.getAttribute("ref");
                            if (ref == null || ref.length() == 0) {
                                throw new IllegalArgumentException("ref config error");
                            }

                            declaredField.set(bean, getBean(ref));
                        }
                        
                        registerBean(id, bean);
                    }
                }

            }
        }
    }

    private void registerBean(String id, Object bean) {
        beanMap.put(id, bean);
    }

    public Object getBean(String name) {
        Object bean = beanMap.get(name);
        if (bean == null) {
            throw new IllegalArgumentException("there is no bean name " + name);
        }
        return bean;
    }


}
