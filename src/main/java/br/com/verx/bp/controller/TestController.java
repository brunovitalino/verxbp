package br.com.verx.bp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@RequestMapping("test")
	@ResponseBody
	public String working() {
		return "It's working!";
	}
}
