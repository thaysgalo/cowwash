package cowwash.modelo;

import java.util.Date;

public class Valvula {
    private int id;
    private int periodo;
    private Date dataRegistro;
    private Estado estado;

    public Valvula(int id, int periodo, Date dataRegistro, int idEstado) {
        this.id = id;
        this.periodo = periodo;
        this.dataRegistro = dataRegistro;
        estado = new Estado(idEstado);
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