package cowwash.modelo.logica;

import java.util.List;

import cowwash.modelo.Estado;
import cowwash.modelo.Valvula;
import cowwash.modelo.persistencia.EstadoDao;
import cowwash.modelo.persistencia.ValvulaDao;

public class ValvulaManager {
	public Valvula inserir(Valvula valvula) {
		new ValvulaDao().inserir(valvula);
		valvula.setEstado(new EstadoDao().obter(valvula.getEstado().getId()));

		return (valvula);
	}

	public List<Valvula> listar() {
		List<Valvula> valvulas = new ValvulaDao().listar();
		EstadoDao estadoDao = new EstadoDao();
		for (Valvula valvula : valvulas)
			valvula.setEstado(estadoDao.obter(valvula.getEstado().getId()));
		
		return (valvulas);
	}

	public Valvula obterAtual(Estado estado) {
		Valvula valvula = new ValvulaDao().obterAtual(estado);
		valvula.setEstado(new EstadoDao().obter(estado.getId()));
		
		return (valvula);
	}
}