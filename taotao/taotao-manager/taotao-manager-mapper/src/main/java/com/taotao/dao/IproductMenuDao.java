package com.taotao.dao;

import java.util.List;
import java.util.Map;

import com.taotao.pojo.ProductMenuVO;

/**
 *
 * 商品类目 数据库交互接口
 * <p>
 * Title: IproductMenuDao
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * @author wansiliang
 *
 * @date 2018年4月6日
 */
public interface IproductMenuDao extends IbaseDao<IproductMenuDao> {

	/**
	 *
	 *
	 * <p>
	 * Title: getMenuGroups
	 * </p>
	 *
	 * <p>
	 * Description: 返回菜单下个规格组别
	 * </p>
	 *
	 * @param paraMap
	 * @return
	 */
	List<ProductMenuVO> getMenuParamGroups(Map<String, Object> paraMap);


	/**
	 *

	 * <p>Title: getMenuParamGroupsByMenId</p>

	 * <p>Description: 根据menId判读是否有paramGroups 以来新增 paramGroups</p>

	 * @param paraMap
	 * @return
	 */
	List<ProductMenuVO> getMenuParamGroupsByMenId(Map<String, Object> paraMap);

}
