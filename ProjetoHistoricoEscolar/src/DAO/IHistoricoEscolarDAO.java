package DAO;

import java.util.List;

import Entity.*;

public interface IHistoricoEscolarDAO {
	
	//Metodos para Inserção nas Tabelas
	public int saveAluno(Aluno aluno);
	public int saveContato(Contato contato);
	public int saveCursa(Cursa cursa);
	public int saveCurso_Disciplina(Curso_Disciplina curso_disciplina);
	public int saveCurso(Curso curso);
	public int saveDisciplina(Disciplina disciplina);
	public int saveLeciona(Leciona leciona);
	public int saveNotas(Notas notas);
	public int saveProfessor(Professor professor);
		
	//Metodos para Atualizar
	public int updateAluno(Aluno aluno);
	public int updateContato(Contato contato);
	public int updateCursa(Cursa cursa);
	public int updateCurso(Curso curso);
	public int updateDisciplina(Disciplina disciplina);
	public int updateNotas(Notas notas);
	public int updateProfessor(Professor professor);
	
	//Metodos para Deleção nas Tabelas
	public int removeAluno(Long matricula);
	public int removeCurso(Long id);
	public int removeDisciplina(Long id);
	public int removeProfessor(Long id);
	public int removeNotas(Long id);
			
	//Metodos para Busca
	//public int visaoAluno(Aluno a, Disciplina d, Cursa c, Professor p, Notas n);
	//public int visaoProfessor(Disciplina d, Aluno a, Cursa c, Notas n);
	//public int visaoSecretaria(Curso_Disciplina cdi, Curso c, Cursa ca, Aluno a, Professor p, Disciplina d, Notas n);
		
	//Metodos para Listar
	List<Curso> findAllCurso();
	List<Professor> findAllProfessor();
	List<Disciplina> findAllDisciplina();
	List<Notas> findAllNotas();
	List<Aluno> findAllAluno();
	
}
