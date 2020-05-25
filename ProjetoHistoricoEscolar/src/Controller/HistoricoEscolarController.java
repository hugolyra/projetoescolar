package Controller;

import java.util.List;

import Entity.Aluno;
import Entity.Contato;
import Entity.Cursa;
import Entity.Curso;
import Entity.Curso_Disciplina;
import Entity.Departamento;
import Entity.Disciplina;
import Entity.Leciona;
import Entity.Notas;
import Entity.Professor;
import Facade.HistoricoEscolarFacade;

public class HistoricoEscolarController {
	
	private HistoricoEscolarFacade facade;
	
	public HistoricoEscolarController(){
		this.facade = new HistoricoEscolarFacade();	
	}
	
	public int salvarAluno(Aluno aluno){
		return facade.saveAluno(aluno);
	}
	
	public int salvarContato(Contato contato){
		return facade.saveContato(contato);
	}
	
	public int salvarCursa(Cursa cursa){
		return facade.saveCursa(cursa);
	}
	
	public int salvarCurso_Disciplina(Curso_Disciplina curso_disciplina){
		return facade.saveCurso_Disciplina(curso_disciplina);
	}
	
	public int salvarCurso(Curso curso){
		return facade.saveCurso(curso);
	}
	
	public int salvarDisciplina(Disciplina disciplina){
		return facade.saveDisciplina(disciplina);
	}
	
	public int salvarLeciona(Leciona leciona){
		return facade.saveLeciona(leciona);
	}
	
	public int salvarNotas(Notas notas){
		return facade.saveNotas(notas);
	}
	
	public int salvarProfessor(Professor professor){
		return facade.saveProfessor(professor);
	}
	
	public int atualizarAluno(Aluno aluno){
		return facade.updateAluno(aluno);
	}
	
	public int atualizarContato(Contato contato){
		return facade.updateContato(contato);
	}
	
	public int atualizarNotas(Notas notas){
		return facade.updateNotas(notas);
	}
	
	public int atualizarProfessor(Professor professor){
		return facade.updateProfessor(professor);
	}
	
	public int atualizarDisciplina(Disciplina disciplina){
		return facade.updateDisciplina(disciplina);
	}
	
	public int atualizarCurso(Curso curso){
		return facade.updateCurso(curso);
	}
	
	public int atualizarCursa(Cursa cursa){
		return facade.updateCursa(cursa);
	}
	
	public int removerAluno(Long matricula){
		return facade.removeAluno(matricula);
	}
	
	public int removerCurso(Long id){
		return facade.removeCurso(id);
	}
	
	public int removerDisciplina(Long id){
		return facade.removeDisciplina(id);
	}
	
	public int removerProfessor(Long id){
		return facade.removeProfessor(id);
	}
	
	public int removerNotas(Long id){
		return facade.removeNotas(id);
	}
	
	public List<Curso> EncontreAllCurso (){
		return facade.findAllCurso();
	}
	
	public List<Professor> EncontreAllProfessor (){
		return facade.findAllProfessor();
	}
	
	public List<Aluno> EncontreAllAluno (){
		return facade.findAllAluno();
	}
	
	public List<Disciplina> EncontreAllDisciplina (){
		return facade.findAllDisciplina();
	}
	
	public List<Notas> EncontreAllNotas (){
		return facade.findAllNotas();
	}

	public int removerDepartamento(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int salvarDepartamento(Departamento departamento) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int atualizarDepartamento(Departamento departamento) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Departamento> EncontreAllDepartamento() {
		// TODO Auto-generated method stub
		return null;
	}
}
