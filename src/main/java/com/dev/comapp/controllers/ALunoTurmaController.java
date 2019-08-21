package com.dev.comapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.comapp.models.AlunoTurma;
import com.dev.comapp.models.Turma;
import com.dev.comapp.repository.AlunoRepository;
import com.dev.comapp.repository.AlunoTurmaRepository;
import com.dev.comapp.repository.TurmaRepository;

@Controller
public class ALunoTurmaController {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private AlunoTurmaRepository alunoTurmaRepository;

	@GetMapping("/matricula")
	public ModelAndView add(AlunoTurma alunoTurma) {

		ModelAndView mv = new ModelAndView("/alunoTurma");
		mv.addObject("alunos", alunoRepository.findAll());
		mv.addObject("turmas", turmaRepository.findAll());
		mv.addObject("alunoTurma", alunoTurma);

		return mv;
	}

	@PostMapping("/inserirMatricula")
	public ModelAndView save(@Valid AlunoTurma alunosDaTurma, BindingResult result) {

		Turma turma = alunosDaTurma.getTurma();
		// alunosDaTurma.getTurma().setQuantidadeVagas(alunosDaTurma.getTurma()
		// .getQuantidadeVagas() - 1);

		if (!turma.getVagasRestantes().equals(0)) {
			if (turma.getVagasRestantes().equals(-1)) {
				turma.setVagasRestantes(turma.getQuantidadeVagas() - 1);
			} else {
				turma.setVagasRestantes(turma.getVagasRestantes() - 1);
			}

			this.turmaRepository.saveAndFlush(turma);

			this.alunoTurmaRepository.saveAndFlush(alunosDaTurma);
		}

		return new ModelAndView("redirect:/matricula");
	}

}
