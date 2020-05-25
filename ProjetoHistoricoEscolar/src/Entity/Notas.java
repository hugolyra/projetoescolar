package Entity;

public class Notas {

	private Long id;
	private float primeiraUnidade;
	private float segundaUnidade;
	private float notaFinal;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public float getPrimeiraUnidade() {
		return primeiraUnidade;
	}
	public void setPrimeiraUnidade(float primeiraUnidade) {
		this.primeiraUnidade = primeiraUnidade;
	}
	public float getSegundaUnidade() {
		return segundaUnidade;
	}
	public void setSegundaUnidade(float segundaUnidade) {
		this.segundaUnidade = segundaUnidade;
	}
	public float getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(float notaFinal) {
		this.notaFinal = notaFinal;
	}
	
}
