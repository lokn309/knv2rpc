package cn.lokn.knv2rpc.core.consumer;

import cn.lokn.knv2rpc.core.annotation.KNConsumer;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: lokn
 * @date: 2024/04/09 23:56
 */
@Data
public class ConsumerBootstrap implements ApplicationContextAware {

    ApplicationContext applicationContext;

    Map<String, Object> stub = new HashMap<>();

    public void start() {
        // 获取所有的Bean实例
        final String[] names = applicationContext.getBeanDefinitionNames();
        for (String name : names) {
            final Object bean = applicationContext.getBean(name);
            List<Field> fields = findAnnotationField(bean.getClass());
        }
    }

    /**
     * 查找指定类中所有被KNConsumer注解标记的字段。
     *
     * @param aClass 需要进行查找的类
     * @return 返回一个包含所有被KNConsumer注解标记的字段的列表
     */
    private List<Field> findAnnotationField(Class<?> aClass) {
        List<Field> result = new ArrayList<>();
        // 获取指定类的所有公有字段
        final Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            // 如果字段上存在KNConsumer注解，则加入到结果列表中
            if (field.isAnnotationPresent(KNConsumer.class)) {
                result.add(field);
            }
        }
        return result;
    }


}
