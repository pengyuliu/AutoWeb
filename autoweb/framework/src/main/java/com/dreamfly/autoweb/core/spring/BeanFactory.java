package com.dreamfly.autoweb.core.spring;

import com.dreamfly.autoweb.core.exception.SysException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unchecked")
public class BeanFactory implements ApplicationContextAware {
    private static ApplicationContext context;

    /**
     * 根据bean的id获取实例对象，配置在applicationContext.xml的配置文件中
     * 注：参数objects不为空的时候，只能产生scope为prototype类型的对象
     */
    public static <T> T getBean(String beanName, Object... objects) {
        if (objects.length == 0) {
            return (T) context.getBean(beanName);
        } else {
            return (T) context.getBean(beanName, objects);
        }
    }

    /**
     * 根据类的对象或类的名称产生实例对象
     * 参数为Class或者String
     */
    public static <T> T getObject(Object clazz) {
        try {
            if (clazz instanceof String) {
                return (T) Class.forName((String) clazz).newInstance();
            } else {
                return (T) (((Class) clazz).newInstance());
            }
        } catch (Exception e) {
            throw new SysException();
        }
    }

    /**
     * 根据类的字符串名称产生类对象
     */
    public static Class getClass(String clazzName) {
        try {
            return Class.forName(clazzName);
        } catch (Exception e) {
            throw new SysException();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext appCtx) throws BeansException {
        context = appCtx;
    }
}
