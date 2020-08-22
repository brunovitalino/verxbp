package br.com.verx.bp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.verx.bp.model.Users;
import br.com.verx.bp.repository.UsersRepository;

@Controller
public class TestController {
	
	@Autowired
	UsersRepository usersRepository;

	@RequestMapping("test")
	@ResponseBody
	public String working() {
		return "It's working!";
	}

	@RequestMapping("users")
	@ResponseBody
	public List<Users> findAll() {
		return usersRepository.findAll();
	}
}
