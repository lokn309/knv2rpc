package cn.lokn.knv2rpc.core.provider;

import cn.lokn.knv2rpc.core.annotation.KNProvider;
import cn.lokn.knv2rpc.core.api.RpcRequest;
import cn.lokn.knv2rpc.core.api.RpcResponse;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 生产者启动类
 * @author: lokn
 * @date: 2024/04/06 16:01
 */
@Data
public class ProviderBootstrap implements ApplicationContextAware {

    ApplicationContext applicationContext;

    Map<String, Object> skeleton = new HashMap<>();

    @PostConstruct
    public void buildProvider() {
        Map<String, Object> providers = applicationContext.getBeansWithAnnotation(KNProvider.class);
        providers.forEach((x, y) -> {
            System.out.println("beanName : " + x);
            System.out.println("value :" + y);
        });

        skeleton.putAll(providers);
        providers.values().forEach(x -> {
            genInterface(x);
        });

    }

    private void genInterface(Object impl) {
        final Class<?> anInterface = impl.getClass().getInterfaces()[0];
        final String canonicalName = anInterface.getCanonicalName();
        skeleton.put(canonicalName, impl);
    }

    public RpcResponse invoke(RpcRequest request) {
        final Object bean = skeleton.get(request.getService());
        try {
            final Method method = findMethod(bean.getClass(), request.getMethod());
            final Object result = method.invoke(bean, request.getArgs());
            return new RpcResponse(true, result);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Method findMethod(Class<?> aClass, String methodName) {
        final Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }

}