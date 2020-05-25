package Facade;

import java.util.List;

import DAO.HistoricoEscolarDAO;
import DAO.IHistoricoEscolarDAO;
import Entity.Aluno;
import Entity.Contato;
import Entity.Cursa;
import Entity.Curso;
import Entity.Curso_Disciplina;
import Entity.Disciplina;
import Entity.Leciona;
import Entity.Notas;
import Entity.Professor;

public class HistoricoEscolarFacade {
	
	private IHistoricoEscolarDAO dao;
	
	public HistoricoEscolarFacade(){
		this.dao = new HistoricoEscolarDAO();
	}
	
	public int saveAluno(Aluno aluno){
		return dao.saveAluno(aluno);
	}
	
	public int saveContato(Contato contato){
		return dao.saveContato(contato);
	}
	
	public int saveCursa(Cursa cursa){
		return dao.saveCursa(cursa);
	}
	
	public int saveCurso_Disciplina(Curso_Disciplina curso_disciplina){
		return dao.saveCurso_Disciplina(curso_disciplina);
	}
	
	public int saveCurso(Curso curso){
		return dao.saveCurso(curso);
	}
	
	public int saveDisciplina(Disciplina disciplina){
		return dao.saveDisciplina(disciplina);
	}
	
	public int saveLeciona(Leciona leciona){
		return dao.saveLeciona(leciona);
	}
	
	public int saveNotas(Notas notas){
		return dao.saveNotas(notas);
	}
	
	public int saveProfessor(Professor professor){
		return dao.saveProfessor(professor);
	}
	
	public int updateAluno(Aluno aluno){
		return dao.updateAluno(aluno);
	}
	
	public int updateContato(Contato contato){
		return dao.updateContato(contato);
	}
	
	public int updateNotas(Notas notas){
		return dao.updateNotas(notas);
	}
	
	public int updateProfessor(Professor professor){
		return dao.updateProfessor(professor);
	}
	
	public int updateDisciplina(Disciplina disciplina){
		return dao.updateDisciplina(disciplina);
	}
	
	public int updateCurso(Curso curso){
		return dao.updateCurso(curso);
	}
	
	public int updateCursa(Cursa cursa){
		return dao.updateCursa(cursa);
	}
	
	public int removeAluno(Long matricula){
		return dao.removeAluno(matricula);
	}
	
	public int removeCurso(Long id){
		return dao.removeCurso(id);
	}
	
	public int removeDisciplina(Long id){
		return dao.removeDisciplina(id);
	}
	
	public int removeProfessor(Long id){
		return dao.removeProfessor(id);
	}
	
	public int removeNotas(Long id){
		return dao.removeNotas(id);
	}
	
	public List<Curso> findAllCurso (){
		return dao.findAllCurso();
	}
	
	public List<Professor> findAllProfessor (){
		return dao.findAllProfessor();
	}
	
	public List<Aluno> findAllAluno (){
		return dao.findAllAluno();
	}
	
	public List<Disciplina> findAllDisciplina (){
		return dao.findAllDisciplina();
	}
	
	public List<Notas> findAllNotas (){
		return dao.findAllNotas();
	}
}
