package cowwash.modelo.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cowwash.modelo.Estado;

public class EstadoDao {
	private Connection conexao;

	public EstadoDao() {
		conexao = new FabricaConexao().obterConexao();
	}

	public Estado obter(Integer id) {
		try (PreparedStatement preparedStatement = conexao.prepareStatement("SELECT ESTA_TX_DESCRICAO FROM ESTADO WHERE ESTA_ID_ESTADO = ?;");) {
			preparedStatement.setInt(1, id);
			ResultSet conjuntoDados = preparedStatement.executeQuery();
			Estado estado = null;

			if (conjuntoDados.next()) {
				String descricao = conjuntoDados.getString("ESTA_TX_DESCRICAO");

				estado = new Estado(id, descricao);
			}
			conjuntoDados.close();

			return (estado);
		}
		catch (SQLException excecaoSQL) {
			throw new RuntimeException(excecaoSQL);
		}
	}
}