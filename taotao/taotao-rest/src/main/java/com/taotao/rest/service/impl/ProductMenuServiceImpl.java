package com.taotao.rest.service.impl;

import com.taotao.dao.IproductMenuDao;
import com.taotao.pojo.ProductMenuVO;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.IproductMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 13174
 */
@Service
public class ProductMenuServiceImpl implements IproductMenuService {
    @Autowired
    private IproductMenuDao productMenuDao;


    @Override
    public CatResult getProductMenuList() {

        CatResult catResult = new CatResult();
        catResult.setData(getMenuList(0L));
        return catResult;
    }

    private List<?> getMenuList(Long parentId) {
        Map<String, Object> map = new HashMap<>(10);
        map.put("parentId", parentId);
        /**用来查询当前parentId 下的所有子菜单*/
        List<ProductMenuVO> list = productMenuDao.selectByExample(map);
        /**菜单的样式长度有限，只能允许14层第一级展示*/
        int count = 0 ;
        //返回值List
        List resultList = new ArrayList<>(10);

        for (ProductMenuVO productMenuVO : list
                ) {
            //判断当前节点是否为叶子节点
            if (productMenuVO.getIsParent() == 1) {
                CatNode catNode = new CatNode();
                if (parentId == 0) {
                    catNode.setName("<a href='/products/" + productMenuVO.getMenuId() + ".html'>" + productMenuVO.getMenuName() + "</a>");
                } else {
                    catNode.setName(productMenuVO.getMenuName());
                }
                catNode.setUrl("/products/" + productMenuVO.getMenuId() + ".html");
                catNode.setItem(getMenuList(productMenuVO.getMenuId()));
                count++;
                if (count >= 14 && parentId == 0 ){
                    break;
                }
                resultList.add(catNode);

            } else {
                resultList.add("/products/" + productMenuVO.getMenuId() + ".html|" + productMenuVO.getMenuName());
            }
        }
        return resultList;
    }
}
