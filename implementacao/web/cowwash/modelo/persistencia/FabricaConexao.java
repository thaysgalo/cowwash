package cowwash.modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class FabricaConexao {
	public Connection obterConexao() {
		try {
			return (DriverManager.getConnection("jdbc:mysql://localhost/COW_WASH?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", "cowwash", "123456"));
		}
		catch (SQLException excecaoSQL) {
			throw new RuntimeException(excecaoSQL);
		}
	}
}