package cowwash.modelo.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cowwash.modelo.Estado;

public class EstadoDao {
	public List<Estado> listar() {
		try (Connection conexao = new FabricaConexao().obterConexao();
			PreparedStatement preparedStatement = conexao.prepareStatement("SELECT ESTA_ID_ESTADO, ESTA_TX_DESCRICAO FROM ESTADO;");
			ResultSet conjuntoDados = preparedStatement.executeQuery();) {
			List<Estado> estados = new ArrayList<>();
			
			while (conjuntoDados.next()) {
				int id = conjuntoDados.getInt("ESTA_ID_ESTADO");
				String descricao = conjuntoDados.getString("ESTA_TX_DESCRICAO");
				
				estados.add(new Estado(id, descricao));
			}

			return (estados);
		}
		catch (SQLException excecaoSQL) {
			throw new RuntimeException(excecaoSQL);
		}
	}

	public Estado obter(Integer id) {
		try (Connection conexao = new FabricaConexao().obterConexao();
			PreparedStatement preparedStatement = conexao.prepareStatement("SELECT ESTA_TX_DESCRICAO FROM ESTADO WHERE ESTA_ID_ESTADO = ?;");) {
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