package com.springinaction.knights;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zk on 17-6-2.
 *
 * 几种常用的 Context
 * - FileSystemXmlApplicationContext
 * - ClassPathXmlApplicationContext
 * - AnnotationConfigApplicationContext
 * - XmlWebApplicationContext
 * - AnnotationConfigWebApplicationContext
 */
public class KnightMain {

    public static void main(String[] args) throws Exception {
        // 加载 Bean Context
        ApplicationContext context = getAnnotationContext();

        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();

        if (context instanceof ClassPathXmlApplicationContext) {
            ((ClassPathXmlApplicationContext)context).close();
        }
    }

    // 基于 XML 配置的
    private static ApplicationContext getXmlContext() {
        return new ClassPathXmlApplicationContext("knights.xml");
    }

    // 基于 java Annotation 配置的
    private static ApplicationContext getAnnotationContext() {
        return new AnnotationConfigApplicationContext(KnightConfig.class);
    }

}
