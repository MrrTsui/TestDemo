package com.taotao.rest.service.impl;

import com.taotao.dao.IproductMenuDao;
import com.taotao.pojo.ProductMenuVO;
import com.taotao.povo.MenuNode;
import com.taotao.rest.service.IRestProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RestProductServiceImpl implements IRestProductService {

    @Autowired
    private IproductMenuDao productMenuDao;


    /**
     * * 查询 菜单列表
     * *
     * * @return
     *
     * @param paraMap
     * @return
     */
    @Override
    public List<MenuNode> getMenu(Map<String, Object> paraMap) {
        List<ProductMenuVO> menuByParentId = productMenuDao.selectByExample(paraMap);
        return returnMenuNode(menuByParentId);
    }

    /**
     * 清洗查询出来的菜单集合，构造能返回给给前台的对象集合
     */
    private List<MenuNode> returnMenuNode(List<ProductMenuVO> paraMap) {
        MenuNode menuNode = null;

        List<MenuNode> resultList = new ArrayList<>(10);
        for (ProductMenuVO productMenuVO : paraMap
                ) {
            menuNode = new MenuNode();
            //如果是父节点，则设置跳转标签和对应的子节点
            if (productMenuVO.getIsParent() == 1) {
                menuNode.setMenuName("<a href='/products/" + productMenuVO.getMenuId() + ".html'>" + productMenuVO.getMenuName() + "</a>");
                menuNode.setUrl("/products/" + productMenuVO.getMenuId() + ".html");
            } else {//如果不是父节点，则设置


            }
            resultList.add(menuNode);
        }
        return resultList;
    }

}
