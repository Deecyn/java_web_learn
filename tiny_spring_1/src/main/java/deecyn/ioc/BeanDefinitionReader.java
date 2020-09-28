package deecyn.ioc;

import java.io.FileNotFoundException;

/**
 * @author Deecyn
 * @version 0.1
 * Description: 用于加载和解析配置文件
 */
public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws FileNotFoundException, Exception;
}
