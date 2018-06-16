package com.taotao.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: BeanToMap</p>
 *
 * <p>Description:将一个对象转换为map </p>
 *
 * @author wansiliang
 * @date 2018年4月6日
 */
public class BeanToMap {

    public static <T> Map<String, Object> beanToMap(T t) throws Exception {
        // 声明一个map用来包装返回
        Map<String, Object> beanToMap = new HashMap<String, Object>(10);
        // 拿到当前map的迭代器
        // 首先拿到当前类的class类
        Class currentClass = Class.forName(t.getClass().getName());
        // 拿到所有的成员变量
        Field[] declaredFields = currentClass.getDeclaredFields();
        // 设置访问权限为true
        // 便利所有的值并且设置到beanToMap中
        for (Field field : declaredFields) {
            field.setAccessible(true);
            beanToMap.put(field.getName(), field.get(t));
        }
        return beanToMap;
    }
}
