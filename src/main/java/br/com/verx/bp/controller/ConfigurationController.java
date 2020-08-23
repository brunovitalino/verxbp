package br.com.verx.bp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import br.com.verx.bp.model.User;
import br.com.verx.bp.repository.UserRepository;

@Controller
public class ConfigurationController {
	
	@Autowired
	UserRepository usersRepository;

	@GetMapping("/")
	public RedirectView barra(RedirectAttributes attributes) {
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        return new RedirectView("status");
	}

	@GetMapping("/status")
	@ResponseBody
	public String status() {
		return "API online!";
	}

	@GetMapping("/configdb")
	@ResponseBody
	public String configDatabase() {
		User user = usersRepository.findByEmail("admin@verx.com.br");
		if (user == null) {
			usersRepository.save(new User("Administrator", "admin@verx.com.br", "verx123"));
			return "Banco de dados preenchido!";
		} else {
			return "Banco de dados já está preenchido!";
		}
	}
}
