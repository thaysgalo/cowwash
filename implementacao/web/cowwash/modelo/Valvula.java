package cowwash.modelo;

import java.util.Date;

public class Valvula {
	private int id;
	private int periodo;
	private Date dataRegistro;
	private Estado estado;

	public Valvula(int periodo, int idEstado) {
		this.periodo = periodo;
		estado = new Estado(idEstado);
	}

	public Valvula(int id, int periodo, Date dataRegistro, int idEstado) {
		this(periodo, idEstado);
		this.id = id;
		this.dataRegistro = dataRegistro;
	}

	public int getId() {
		return (id);
	}

	public int getPeriodo() {
		return (periodo);
	}

	public Date getDataRegistro() {
		return (dataRegistro);
	}

	public Estado getEstado() {
		return (estado);
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}