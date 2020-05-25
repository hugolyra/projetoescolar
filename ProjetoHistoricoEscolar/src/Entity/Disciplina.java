package Entity;

//import Entity.Professor;

public class Disciplina {

	private Long id;
	private String nome;
	private int semestre;
	//private Professor idProfessor;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getSemestre() {
		return semestre;
	}
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	/*public Professor getIdProfessor() {
		return idProfessor;
	}
	public void setIdProfessor(Professor idProfessor) {
		this.idProfessor = idProfessor;
	}*/
	
}
