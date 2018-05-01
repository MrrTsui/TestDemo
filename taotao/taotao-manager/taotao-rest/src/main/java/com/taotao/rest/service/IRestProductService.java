package com.taotao.rest.service;


import java.util.List;
import java.util.Map;

/**
 * rest服务器的产品service层
 * <p>
 * author :wansiliang
 * time : 14点18分
 */
public interface IRestProductService {

    /**
     * 拿到所有的目录菜单并包装给前台
     * <p>
     * 包装结构如下
     * 第一层：u、n（包含a标签）、i
     * 第二层：u、n、i
     * 第三层：字符串
     *
     * @return
     */
    List getMenu(Map<String, Object> paraMap);

}
