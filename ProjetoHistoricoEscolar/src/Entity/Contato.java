package Entity;

public class Contato {
	
	private Long id;
	private String email;
	private String telefone;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contato [email=").append(email).append(", telefone=")
				.append(telefone).append("]");
		return builder.toString();
	}
	
	
}
