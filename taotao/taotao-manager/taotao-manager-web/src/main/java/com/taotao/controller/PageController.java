package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 用来控制页面跳转
 * <p>
 * Title: PageController
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author wansiliang
 * 
 * @date 2018年4月5日
 */
@Controller
public class PageController {

	/**
	 * 
	 * http://localhost:8080/ 即可进入首页
	 * 
	 * <p>
	 * Title: pageToIndex
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return 之所以只需要 写 “index”是因为springmvc配置文件中加了前缀后缀
	 */
	@RequestMapping("/")
	public String pageToIndex() {
		return "index";
	}

	@RequestMapping("/{pageName}")
	public String pageToOthers(@PathVariable String pageName) {
		return pageName;
	}
}
