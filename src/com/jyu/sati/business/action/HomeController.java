package com.jyu.sati.business.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/home")
@Controller
public class HomeController extends BaseController {

	/**
	 * 打开主页
	 * @return
	 */
	@RequestMapping("/homepage")
	public String home() {
		return HOMEPAGE;
	}
	
	
}
