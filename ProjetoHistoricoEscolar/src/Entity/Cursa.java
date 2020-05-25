package Entity;

import Entity.Aluno;
import Entity.Disciplina;
import Entity.Notas;

public class Cursa {

	private int qtdFaltas;
	private String situacaoAluno;
	private Aluno matriculaAluno;
	private Notas idNotas;
	private Disciplina idDisciplina;
	
	public int getQtdFaltas() {
		return qtdFaltas;
	}
	public void setQtdFaltas(int qtdFaltas) {
		this.qtdFaltas = qtdFaltas;
	}
	public String getSituacaoAluno() {
		return situacaoAluno;
	}
	public void setSituacaoAluno(String situacaoAluno) {
		this.situacaoAluno = situacaoAluno;
	}
	public Aluno getMatriculaAluno() {
		return matriculaAluno;
	}
	public void setMatriculaAluno(Aluno matriculaAluno) {
		this.matriculaAluno = matriculaAluno;
	}
	public Notas getIdNotas() {
		return idNotas;
	}
	public void setIdNotas(Notas idNotas) {
		this.idNotas = idNotas;
	}
	public Disciplina getIdDisciplina() {
		return idDisciplina;
	}
	public void setIdDisciplina(Disciplina idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	
}