package com.taotao.dao;

import com.taotao.pojo.ProductVO;

import java.util.List;
import java.util.Map;

/**
 * @author 13174
 */
public interface IproductDao extends IbaseDao<ProductVO> {


	/**
	 *
	 *
	 * <p>
	 * Title: saveCurrentObjectDesc
	 * </p>
	 *
	 * <p>
	 * Description: 保存当前对象
	 * </p>
	 *
	 * @param paraMap
	 * @return
	 */
	int saveCurrentObjectDesc(Map<String, Object> paraMap);

	List<ProductVO> selectProductDescByProductId(Map<String, Object> paraMap);

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
	int instockOrReshelfProductByIds(Map<String, Object> paraMap);

}
