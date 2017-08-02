package com.sonwalker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author wuwl@19pay.com.cn
 * @date 2017-3-13
 * @describe 主页控制器,使用多线程异步发送请求到商户通知地址
 */

@Controller
public class IndexController {

//	/* 默认主页 */
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String toIndex() {
//		return "forward:/index.html";
//	}
//	
	/* 页面跳转 */
	@RequestMapping(value = "{page}", method = RequestMethod.GET)
	public String toPage(@PathVariable("page") String page) {
		return page;
	}
	
	/* 主页 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "forward:/";
	}

}
