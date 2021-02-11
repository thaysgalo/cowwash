package cowwash.modelo.logica;

import java.util.List;

import cowwash.modelo.Valvula;
import cowwash.modelo.persistencia.EstadoDao;
import cowwash.modelo.persistencia.ValvulaDao;

public class ValvulaManager {
    public List<Valvula> listar() {
        List<Valvula> valvulas = new ValvulaDao().listar();
		EstadoDao estadoDao = new EstadoDao();
		for (Valvula valvula : valvulas)
			valvula.setEstado(estadoDao.obter(valvula.getEstado().getId()));
		return (valvulas);
    }
}