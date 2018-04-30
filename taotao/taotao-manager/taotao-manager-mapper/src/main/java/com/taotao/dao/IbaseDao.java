package com.taotao.dao;

import java.util.List;
import java.util.Map;

/**
 *

 * <p>Title: IbaseDao</p>

 * <p>Description: Dao公共接口类，实现基本接口  </p>

 * @author wansiliang

 * @date 2018年4月5日
 */
public interface IbaseDao<T> {
	/**
	 *
	 * <p>Title: selectByExample</p>

	 * <p>Description: 根据对象查询单表,传入一个空map代表查询全集   ,包含分页查询  起始 key 为 startId,每页数key 为rows </p>

	 * @param t
	 * @return
	 */
	@SuppressWarnings("hiding")
	<T> List<T> selectByExample(Map<String, Object> t);

	/**
	 *

	 * <p>Title: saveCurrentObject</p>

	 * <p>Description: 插入当前对象</p>

	 * @param t
	 * @return 返回自动增长的主键,对应的新主键会封装到对应key中如id。int只是代表成功的条数
	 */
	int saveCurrentObject(Map<String, Object> t);

	/**
	 *

	 * <p>Title: updateCurrentObject</p>

	 * <p>Description:根据prodcutId来修改商品 </p>

	 * @param t
	 * @return
	 */
	int updateCurrentObject(Map<String, Object> t);

}
