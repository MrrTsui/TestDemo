package com.taotao.rest.controller;

import com.taotao.povo.MenuNode;
import com.taotao.rest.service.IRestProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * rest服务器是用来与后台交互的服务器
 * <p>
 * des: 该controller是用来控制产品的相关属性
 * author :wansiliang
 */
@Controller
@RequestMapping(value = "/restProduct")
public class RestProductController {

    @Autowired
    private IRestProductService restProductService;

    /**
     * 查询 菜单列表
     *
     * @return
     */
    @RequestMapping(value = "/menuList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    public List<MenuNode> getMenu(@RequestParam(value = "callback") String callback, @RequestParam(value = "parentId", defaultValue = "0") String parentId) {
        Map<String, Object> paraMap = new HashMap<>(10);
        paraMap.put("parentId", parentId);
        List menu = restProductService.getMenu(paraMap);
        return menu;

    }

    @RequestMapping("/list")
    public String getTest() {
        return "index";
    }

}
