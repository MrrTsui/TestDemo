package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.taotao.dao.IproductDao;
import com.taotao.dao.IproductMenuDao;
import com.taotao.pojo.ProductMenuVO;
import com.taotao.pojo.ProductVO;
import com.taotao.service.IproductService;

@Service
public class ProductServiceImpl implements IproductService {

	@Autowired
	private IproductDao productDao;

	@Autowired
	private IproductMenuDao productMenuDao;

	/**
	 * 代表执行完成的线程条数
	 */

	boolean isAok = false;
	boolean isBok = false;

	@Override
	public List<ProductVO> getProduct(Map<String, Object> paraMap) {
		List<ProductVO> products = productDao.selectByExample(paraMap);
		return products;
	}

	@Override
	public List<ProductMenuVO> getProductMenuVO(Map<String, Object> paraMap) {
		List<ProductMenuVO> productMenuVOs = productMenuDao.selectByExample(paraMap);
		return productMenuVOs;
	}

	@Override
	public Boolean saveProduct(Map<String, Object> paraMap) {
		// newId 代表成功的个数， paraMap 中的key productId返回为新增的序列
		try {
			productDao.saveCurrentObject(paraMap);
			productDao.saveCurrentObjectDesc(paraMap);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * * 线程中的通信需要wait()和notify()方法，其需要注意的事项
	 * 1.上述两个方法都不属于Thread类，而是属于object类。因为锁对象可以是任意对象
	 * 2.上述两个方法只能用在同步代码块或者同步方法中。因为锁的概念只在同步代码快和同步函数中存在
	 * 3.上述两个方法都必须由锁对象调用，否则报错(RunTimeException)。因为 wait和notify是以锁对象建立一个线程池来
	 * 装填等待状态的对象锁线程
	 */
	@Override
	public List<Map<String, Object>> getProductParaMenus(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		final List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();

		final List<ProductMenuVO> productMenuVOs = new ArrayList<ProductMenuVO>(30);
		final List<ProductMenuVO> productMenuParamGroups = new ArrayList<ProductMenuVO>(30);

		// 一条线程查询 商品分类集合
		new Thread(new Runnable() {

			@Override
			public void run() {
				List<ProductMenuVO> a = productMenuDao.selectByExample(null);
				productMenuVOs.addAll(a);
				isAok = true;
				System.out.println("a线程查询出a集合");
				while (true) {
					synchronized (returnList) {
						// 代表执行完成的线程条数
						if (isAok && isBok) {
							System.out.println("a唤醒主线程");
							returnList.notify();
							break;
						} else {
							// isWait++;
						}
					}
				}
			}
		}).start();
		// 一条线程查询分类下的规格组别
		new Thread(new Runnable() {

			@Override
			public void run() {
				List<ProductMenuVO> a = productMenuDao.getMenuParamGroups(null);
				productMenuParamGroups.addAll(a);
				System.out.println("b线程查询出b集合");
				isBok = true;
				// 2
				while (true) {
					synchronized (returnList) {
						if (isAok && isBok) {
							System.out.println("b唤醒主线程");
							returnList.notify();
							break;
						} else {
							// isWait++;
						}
					}
				}
			}
		}).start();

		// 根据分类和组别的关联关系组装前台viewVO并返回
		if (CollectionUtils.isEmpty(productMenuVOs) || CollectionUtils.isEmpty(productMenuParamGroups)) {
			try {
				synchronized (returnList) {
					System.out.println("主线程wait");
					returnList.wait();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("主线程代码继续运行，a b 集合组装清洗");
		Map<String, Object> itemParamListViewVo = null;
		for (ProductMenuVO a : productMenuVOs) {

			for (ProductMenuVO b : productMenuParamGroups) {
				if (a.getMenuId() != null && a.getMenuId().equals(b.getMenuId())) {
					itemParamListViewVo = new HashMap<String, Object>(10);
					itemParamListViewVo.put("id", "id");
					itemParamListViewVo.put("itemCatId", a.getMenuId());
					itemParamListViewVo.put("itemCatName", a.getMenuName());
					itemParamListViewVo.put("created", a.getCreateTime());
					itemParamListViewVo.put("updated", a.getUpdateTime());
					itemParamListViewVo.put("paramData", b.getParamGroups());
					returnList.add(itemParamListViewVo);
					break;
				}

			}
		}
		//
		isAok = isBok = false;
		return returnList;
	}

	@Override
	public List<ProductVO> selectProductDescByProductI(Map<String, Object> paraMap) {
		return productDao.selectProductDescByProductId(paraMap);
	}

	@Override
	public boolean updateProductByProductId(Map<String, Object> paraMap) {
		int num = productDao.updateCurrentObject(paraMap);
		return num > 0 ? true : false;
	}

	
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
	@Override
	public boolean instockOrReshelfProductByIds(Map<String, Object> paraMap) {
		int num = productDao.instockOrReshelfProductByIds(paraMap);
		return num > 0 ? true : false;
	}

}
