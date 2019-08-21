package com.dev.comapp.controllers;

import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.comapp.models.Turma;
import com.dev.comapp.repository.TurmaRepository;
import com.dev.comapp.repository.TurmaRepository;

@Controller
public class TurmaController {

	@Autowired
	private TurmaRepository repository;

	@GetMapping("/turmas")
	public ModelAndView buscarTodos() {

		ModelAndView mv = new ModelAndView("/turmasLista");
		mv.addObject("turmas", repository.findAll());

		return mv;
	}

	@GetMapping("/addTurma")
	public ModelAndView add(Turma categoria) {

		ModelAndView mv = new ModelAndView("/turmaAdd");
		mv.addObject("turma", categoria);

		return mv;
	}

	@GetMapping("/editarTurma/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {

		Optional<Turma> categoria = repository.findById(id);
		Turma e = categoria.get();

		return add(e);
	}

	@GetMapping("/removerTurma/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {

		Optional<Turma> categoria = repository.findById(id);
		Turma e = categoria.get();
		repository.delete(e);

		return buscarTodos();
	}

	@PostMapping("/salvarTurma")
	public ModelAndView save(@Valid Turma turma, BindingResult result) {

		if (result.hasErrors() || turma.getQuantidadeVagas() < 10 
				|| turma.getQuantidadeVagas() > 50) {
			return add(turma);
		}

		repository.saveAndFlush(turma);

		return buscarTodos();
	}

}
