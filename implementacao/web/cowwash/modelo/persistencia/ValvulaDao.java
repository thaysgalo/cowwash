package cowwash.modelo.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import cowwash.modelo.Estado;
import cowwash.modelo.Valvula;
import cowwash.modelo.Util;

public class ValvulaDao {
	private Connection conexao;

	public ValvulaDao() {
		conexao = new FabricaConexao().obterConexao();
	}

	public void inserir(Valvula valvula) {
		String scriptSQL = "INSERT INTO VALVULA (VALV_NU_PERIODO, VALV_DT_REGISTRO, ESTA_ID_ESTADO) VALUES (?, ?, ?);";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(scriptSQL);) {
			preparedStatement.setInt(1, valvula.getPeriodo());
			preparedStatement.setString(2, Util.obterTexto(valvula.getDataRegistro()));
			preparedStatement.setInt(1, valvula.getEstado().getId());

			preparedStatement.execute();
		}
		catch (SQLException excecaoSQL) {
			throw new RuntimeException(excecaoSQL);
		}
	}

	public void remover(Valvula valvula) {
		String scriptSQL = "DELETE FROM VALVULA WHERE VALV_ID_VALVULA = ?;";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(scriptSQL);) {
			preparedStatement.setInt(1, valvula.getId());

			preparedStatement.execute();
		}
		catch (SQLException excecaoSQL) {
			throw new RuntimeException(excecaoSQL);
		}
	}

	public List<Valvula> listar() {
		try (PreparedStatement preparedStatement = conexao.prepareStatement("SELECT VALV_ID_VALVULA, VALV_NU_PERIODO, VALV_DT_REGISTRO, ESTA_ID_ESTADO FROM VALVULA;");
			ResultSet conjuntoDados = preparedStatement.executeQuery();) {
			List<Valvula> valvulas = new ArrayList<>();
			
			while (conjuntoDados.next()) {
				int id = conjuntoDados.getInt("VALV_ID_VALVULA");
				int periodo = conjuntoDados.getInt("VALV_NU_PERIODO");
				Date dataRegistro = Util.construirData(conjuntoDados.getString("VALV_DT_REGISTRO"));
				int idEstado = conjuntoDados.getInt("ESTA_ID_ESTADO");

				valvulas.add(new Valvula(id, periodo, dataRegistro, idEstado));
			}

			return (valvulas);
		}
		catch (SQLException excecaoSQL) {
			throw new RuntimeException(excecaoSQL);
		}
	}

	public Valvula obterAtual(Estado estado) {
		try (PreparedStatement preparedStatement = conexao.prepareStatement("SELECT VALV_ID_VALVULA, VALV_NU_PERIODO, VALV_DT_REGISTRO FROM VALVULA WHERE VALV_ID_VALVULA = (SELECT MAX(VALV_ID_VALVULA) FROM VALVULA WHERE ESTA_ID_ESTADO = ?);");) {
			preparedStatement.setInt(1, estado.getId());
			ResultSet conjuntoDados = preparedStatement.executeQuery();
			Valvula valvula = null;
			
			if (conjuntoDados.next()) {
				int id = conjuntoDados.getInt("VALV_ID_VALVULA");
				int periodo = conjuntoDados.getInt("VALV_NU_PERIODO");
				Date dataRegistro = Util.construirData(conjuntoDados.getString("VALV_DT_REGISTRO"));

				valvula = new Valvula(id, periodo, dataRegistro, estado.getId());
			}
			conjuntoDados.close();

			return (valvula);
		}
		catch (SQLException excecaoSQL) {
			throw new RuntimeException(excecaoSQL);
		}
	}
}