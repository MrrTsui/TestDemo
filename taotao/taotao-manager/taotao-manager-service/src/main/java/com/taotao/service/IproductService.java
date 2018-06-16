package com.taotao.service;

import com.taotao.pojo.ContentCategoryVO;
import com.taotao.pojo.ProductMenuVO;
import com.taotao.pojo.ProductVO;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * <p>
 * Title: IproductService
 * </p>
 *
 * <p>
 * Description: 商品接口类
 * </p>
 *
 * @author wansiliang
 *
 * @date 2018年4月5日
 */
public interface IproductService {

	/**
	 *
	 *
	 * <p>
	 * Title: getProduct
	 * </p>
	 *
	 * <p>
	 * Description: 分页获取商品
	 * </p>
	 *
	 * @param paraMap
	 *            如果为空则获取全集
	 * @return
	 */
	List<ProductVO> getProduct(Map<String, Object> paraMap);

	/**
	 *
	 *
	 * <p>
	 * Title: getProductMenuVO
	 * </p>
	 *
	 * <p>
	 * Description: 分页获取商品类目
	 * </p>
	 *
	 * @param paraMap
	 *            如果为空则获取全集
	 * @return
	 */
	List<ProductMenuVO> getProductMenuVO(Map<String, Object> paraMap);


	/**
	 *

	 * <p>Title: saveProduct</p>

	 * <p>Description: 将vo对象转换为map进行保存</p>

	 * @param paraMap
	 * @return
	 */
	Boolean saveProduct(Map<String, Object> paraMap);

	/**
	 *

	 * <p>Title: getProductParaMenus</p>

	 * <p>Description: item-param-list 展示实现</p>

	 * @param paraMap
	 * @return
	 */
	List<Map<String, Object>> getProductParaMenus(Map<String, Object> paraMap);

	/**
	 *

	 * <p>Title: selectProductDescByProductI</p>

	 * <p>Description: 通过productId查询 产品描述</p>

	 * @param paraMap
	 * @return
	 */
	List<ProductVO> selectProductDescByProductI(Map<String, Object> paraMap);

	/**
	 *

	 * <p>Title: updateProductByProductId</p>

	 * <p>Description: 将商品vo转化为map对象 后根据productId 来修改商品</p>

	 * @param paraMap
	 * @return
	 */
	boolean updateProductByProductId(Map<String, Object> paraMap);

	/**
	 *
	 *
	 * <p>
	 * Title: instockOrReshelfProductByIds
	 * </p>
	 *
	 * <p>
	 * Description:根据 传入的  ids 来修改产品上下架的status值， 上架为1 下架为0  删除 3
	 * </p>
	 *
	 * @param paraMap
	 * @return
	 */
	boolean instockOrReshelfProductByIds(Map<String, Object> paraMap);

	/**
	 *

	 * <p>Title: getMenuParamGroupsByMenId</p>

	 * <p>Description: // 判断选择的类目是否已经添加过规格</p>

	 * @param paraMap
	 * @return
	 */
	List<ProductMenuVO> getMenuParamGroupsByMenId(Map<String, Object> paraMap);

	/**
	 *

	 * <p>Title: insertNewProductParam</p>

	 * <p>Description: 插入新的类目</p>
	 */
	void insertNewProductParam(Map<String, Object> paraMap);

	/**
	 * @param paraMap  根据parentId查询对应的层级子集
	 * @return
	 */
	List<ContentCategoryVO> getContentCatList(Map<String, Object> paraMap);

}
