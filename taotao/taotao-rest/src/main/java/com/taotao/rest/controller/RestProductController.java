package com.taotao.rest.controller;

import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.IproductMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 13174
 */
@Controller
public class RestProductController {

    @Autowired
    private IproductMenuService productMenuService;


    @RequestMapping(value = "/productMenu/list")
    @ResponseBody
    public Object getProductMenuList(String callback) {
        /** 查询所有内容的节点*/
        CatResult productMenuList = productMenuService.getProductMenuList();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(productMenuList);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;


    }
}
