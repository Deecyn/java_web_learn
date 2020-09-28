package deecyn.ioc.xml;

import deecyn.ioc.BeanDefinitionReader;
import deecyn.ioc.config.BeanDefinition;
import deecyn.ioc.config.BeanReference;
import deecyn.ioc.config.PropertyValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Deecyn
 * @version 0.1
 * Description:
 */
public class XmlBeanDefinitionReader implements BeanDefinitionReader {

    private Map<String, BeanDefinition> registry;

    public XmlBeanDefinitionReader() {
        this.registry = new HashMap<>();
    }

    public Map<String, BeanDefinition> getRegistry(){
        return registry;
    }

    /**  加载、解析 xml 配置文件 */
    @Override
    public void loadBeanDefinitions(String location) throws FileNotFoundException, Exception {
        InputStream inputStream = new FileInputStream(location);

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
        Element root = document.getDocumentElement();
        parseBeanDefinitionList(root);
    }

    /**  遍历根标签下的所有 bean 标签  */
    private void parseBeanDefinitionList(Element root) {
        // 遍历 bean 标签列表
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                parseBeanDefinition(ele);
            }
        }
    }

    /**  解析单个 bean 标签  */
    private void parseBeanDefinition(Element element) {
        String name = element.getAttribute("id");
        String className = element.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName(className);

        processProperty(element, beanDefinition);
        registry.put(name, beanDefinition);
    }

    /**  解析属性列表  */
    private void processProperty(Element element, BeanDefinition beanDefinition) {
        NodeList propertyNodeList = element.getElementsByTagName("property");
        for (int i = 0; i < propertyNodeList.getLength(); i++) {
            Node propertyNode = propertyNodeList.item(i);

            if (propertyNode instanceof Element) {
                Element propertyElement = (Element) propertyNode;
                String name = propertyElement.getAttribute("name");
                String value = propertyElement.getAttribute("value");

                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                } else {
                    String ref = propertyElement.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("ref config error");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
                }
            }
        }
    }


}
