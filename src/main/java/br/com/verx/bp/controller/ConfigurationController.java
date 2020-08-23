package br.com.verx.bp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import br.com.verx.bp.model.User;
import br.com.verx.bp.repository.UserRepository;

@RestController
public class ConfigurationController {
	
	@Autowired
	private UserRepository usersRepository;

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
		Optional<User> user = usersRepository.findByEmail("admin@verx.com.br");
		if (user.isPresent()) {
			return "Banco de dados já está preenchido!";
		}
		usersRepository.save(new User("Administrator", "admin@verx.com.br", "$2a$10$F9PxVWEgzbfRe48zwwDyRek/N/6lq2BZ/utop842rJPrvRsAfS.si"));
		return "Banco de dados preenchido!";
	}
}
