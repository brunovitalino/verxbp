package br.com.verx.bp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.verx.bp.model.Userr;
import br.com.verx.bp.repository.UserrRepository;

@Controller
public class ConfigurationController {
	
	@Autowired
	UserrRepository usersRepository;

	@GetMapping("/")
	public String status() {
		return "API online!";
	}

	@GetMapping("users")
	@ResponseBody
	public List<Userr> findAll() {
		return usersRepository.findAll();
	}

	@GetMapping("configdb")
	@ResponseBody
	public String configDatabase() {
		Userr user = usersRepository.findByEmail("admin@verx.com.br");
		System.err.println("user: " + user);
		if (user == null) {
			usersRepository.save(new Userr("Administrator", "admin@verx.com.br", "verx123"));
			return "Banco de dados preenchido!";
		} else {
			return "Banco de dados já está preenchido!";
		}
	}
}
