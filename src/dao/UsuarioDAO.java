package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entities.Usuario;

public class UsuarioDAO {

	private Connection conn;

	public UsuarioDAO(Connection conn) {
		this.conn = conn;
	}

	public void cadastrarCandidato(Usuario usuario) throws SQLException {
		PreparedStatement st = null;

		try {
			st = this.conn.prepareStatement("INSERT INTO candidato (nome, email, senha) VALUES(?,?,?)");
			st.setString(1, usuario.getNome());
			st.setString(2, usuario.getEmail());
			st.setString(3, usuario.getSenha());

			st.executeUpdate();	

		} finally {
			BancoDados.finalizarStatement(st);
		}
	}

	public int excluirCandidato(String email) throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT idCandidato FROM candidato WHERE email = ?");
			st.setString(1, email);
			rs = st.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("idCandidato");
				st = conn.prepareStatement("DELETE FROM candidato WHERE idCandidato =?");
				st.setInt(1, id);
				int returnDelete = st.executeUpdate();
				return returnDelete;
			} else {
				return 0; // Candidato não encontrado
			}
		} finally {
			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
		}
	}

	public boolean verificarUsuario(String email) throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;
		boolean success = false;

		try {
			String sql = "SELECT * FROM candidato WHERE email =?";
			st = conn.prepareStatement(sql);
			st.setString(1, email);
			rs = st.executeQuery();

			if (rs.next()) {
				success = true;
			}
		} finally {
			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
		}
		return success;
	}

	public boolean editarCandidato(String email, String novoNome, String senha) throws SQLException {
		boolean candidatoExiste = verificarUsuario(email);

		if (candidatoExiste) {
			PreparedStatement st = null;
			try {
				String sql = "UPDATE candidato SET nome =?, senha =? WHERE email =?";
				st = conn.prepareStatement(sql);
				st.setString(1, novoNome);
				st.setString(2, senha);
				st.setString(3, email);
				int rowsAffected = st.executeUpdate();

				if (rowsAffected > 0) {
					return true;
				} else {
					return false;
				}
			} finally {
				BancoDados.finalizarStatement(st);
			}
		} else {
			System.out.println(
					"{\"operacao\": \"editarCandidato\", \"status\": 404, \"mensagem\": \"Candidato não encontrado\"}");
			return false;
		}
	}

	public List<Usuario> buscarTodos() throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM candidato");
			rs = st.executeQuery();

			List<Usuario> listaUsuarios = new ArrayList<>();

			while (rs.next()) {
				Usuario usuario = new Usuario();

				usuario.setId(rs.getInt("idCandidato"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));

				listaUsuarios.add(usuario);

			}
			return listaUsuarios;

		} finally {
			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
		}
	}

	public Usuario buscarPorEmail(String email) throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM candidato WHERE email = ?");
			st.setString(1, email);
			rs = st.executeQuery();

			if (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setEmail(rs.getString("email"));
				return usuario;
			}
		} finally {
			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
		}

		return null;
	}

}
