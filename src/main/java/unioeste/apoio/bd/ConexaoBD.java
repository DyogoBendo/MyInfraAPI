package unioeste.apoio.bd;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConexaoBD {

    public Connection getConexaoBD() throws Exception {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/MeuBanco");
            return ds.getConnection();

        } catch (NamingException | SQLException e) {
            throw new Exception("Erro ao obter conexão do pool do Tomcat: " + e.getMessage(), e);
        }
    }

    public void encerrarConexoes(ResultSet resultSet, PreparedStatement stmt, Connection conexao) {
        try {
            if (resultSet != null) resultSet.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            if (conexao != null) conexao.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}