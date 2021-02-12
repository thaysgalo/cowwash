package cowwash.modelo;

public class Estado {
	private int id;
	private String descricao;

	public Estado(int id) {
		this.id = id;
	}

	public Estado(int id, String descricao) {
		this(id);
		this.descricao = descricao;
	}

	public int getId() {
		return (id);
	}

	public String getDescricao() {
		return (descricao);
	}
}