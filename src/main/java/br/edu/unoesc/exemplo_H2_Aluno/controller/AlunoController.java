package br.edu.unoesc.exemplo_H2_Aluno.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unoesc.exemplo_H2_Aluno.model.Aluno;
import br.edu.unoesc.exemplo_H2_Aluno.repository.AlunoRepository;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
	@Autowired
	private AlunoRepository repositorio;

	@GetMapping("/filtro")
	List<Aluno> listarComFiltro(@RequestParam("filtro") String filtro) {
		return repositorio.findByFiltro(filtro);
	}
	
	@GetMapping
	public Iterable<Aluno> listarTudo() {
		return repositorio.findAll();
	}
	
	@GetMapping("/salario/{salario}")
	public List<Aluno> listarPorSalario(@PathVariable BigDecimal salario) {
		return repositorio.porSalario(salario);
	}
	
	@PostMapping()
	public Aluno incluir(@RequestBody Aluno aluno) {
		return repositorio.save(aluno);
	}
	
	@PutMapping("/{id}")
	public Aluno atualizar(@PathVariable("id") Long id, @RequestBody Aluno aluno) {
		// Versão mais simplificada, não faz uso do id nem teste se o recurso existe
		return repositorio.save(aluno);
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable("id") Long id) {
		repositorio.deleteById(id);
	}
	
}
