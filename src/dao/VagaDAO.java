package dao;

import entities.Competencia;
import entities.Empresa;
import entities.Vaga;
import helper.ListarVaga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.mysql.cj.xdevapi.JsonArray;

public class VagaDAO {

	private Connection conn;

	public VagaDAO(Connection conn) {
		this.conn = conn;
	}

	public boolean verificarToken(String email, String token) throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;
		boolean success = false;

		try {
			String sql = "SELECT * FROM loginempresa l LEFT JOIN empresa c ON l.idEmpresa = c.idEmpresa WHERE l.token =? AND c.email =?";
			st = conn.prepareStatement(sql);
			st.setString(1, token);
			st.setString(2, email);
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

	public void cadastrarVaga(Vaga vaga) throws SQLException {
		PreparedStatement st = null;
		try {
			st = this.conn
					.prepareStatement("INSERT INTO vaga (nome, faixaSalarial, competencias, descricao, email, estado) "
							+ "VALUES(?,?,?,?,?,?)");
			st.setString(1, vaga.getNome());
			st.setDouble(2, vaga.getFaixaSalarial());
			List<String> competencias = vaga.getCompetencias();
			JSONArray competenciasJson = new JSONArray(competencias);
			st.setString(3, competenciasJson.toString());
			st.setString(4, vaga.getDescricao());
			st.setString(5, vaga.getEmail());
			st.setString(6, vaga.getEstado());

			System.out.println(st);
			st.executeUpdate();
		} finally {
			BancoDados.finalizarStatement(st);
		}
	}

	public Vaga visualizarVaga(int idVaga) throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM vaga WHERE idVaga = ?");
			st.setInt(1, idVaga);
			rs = st.executeQuery();

			if (rs.next()) {
				Vaga vaga = new Vaga();
				List<String> competencias = parseCompetencias(rs.getString("competencias"));
				vaga.setCodigo(rs.getInt("idVaga"));
				vaga.setNome(rs.getString("nome"));
				vaga.setEstado(rs.getString("estado"));
				vaga.setEmail(rs.getString("email"));
				vaga.setCompetencias(competencias);
				vaga.setFaixaSalarial(rs.getDouble("faixaSalarial"));
				vaga.setDescricao(rs.getString("descricao"));
				return vaga;
			}
			return null;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw ex;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
		}
	}

	public List<Vaga> buscarVagasPorCompetencia(List<Competencia> competencias, boolean utilizandoAnd)
			throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			List<Vaga> vagas = new ArrayList<>();

			StringBuilder whereClause = new StringBuilder();
			for (int i = 0; i < competencias.size(); i++) {
				whereClause.append("v.competencia LIKE ?");
				if (i < competencias.size() - 1 && utilizandoAnd) {
					whereClause.append(" AND ");
				} else if (i < competencias.size() - 1 && !utilizandoAnd) {
					whereClause.append(" OR ");
				}
			}
			String sql = "SELECT v.* FROM vaga v " + "JOIN vagacompetencia vc ON v.idVaga = vc.idVagaCompetencia"
					+ "WHERE " + whereClause.toString();

			st = conn.prepareStatement(sql);

			for (int i = 0; i < competencias.size(); i++) {
				st.setString(i + 1, "%" + competencias.get(i).toString() + "%");
			}

			rs = st.executeQuery();

			while (rs.next()) {
				Vaga vaga = new Vaga();
				vaga.setCodigo(rs.getInt("idVaga"));
				vaga.setNome(rs.getString("nome"));
				vaga.setFaixaSalarial(rs.getDouble("faixaSalarial"));
				vaga.setDescricao(rs.getString("descricao"));
				vagas.add(vaga);
			}

			return vagas;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
		}
	}

	public void atualizarVaga(Vaga vaga, Empresa empresa) throws SQLException {
		PreparedStatement st = null;
		try {

			st = conn.prepareStatement(
					"UPDATE vaga SET email = ?, nome = ?, descricao = ?, estado = ?, faixaSalarial = ?, competencias = ? WHERE idVaga = ?");
			st.setString(1, empresa.getEmail());
			st.setString(2, vaga.getNome());
			st.setString(3, vaga.getDescricao());
			st.setString(4, vaga.getEstado());
			st.setDouble(5, vaga.getFaixaSalarial());
			st.setString(6, vaga.getCompetencias().toString());
			st.setInt(7, vaga.getCodigo());
			st.executeUpdate();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw ex;
		}finally {
			if (st != null) {
				st.close();
			}
		}
	}

	public void apagarVaga(int idVaga) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM vaga WHERE idVaga = ?");
			st.setInt(1, idVaga);
			st.executeUpdate();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw ex;
		} finally {

			if (st != null) {
				st.close();
			}
		}
	}

	public List<ListarVaga> buscarVagaPorEmpresa(Empresa empresa) throws SQLException {
		List<ListarVaga> vagas = new ArrayList<>();
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT v.idVaga, v.nome FROM Vaga v WHERE v.email =?");
			st.setString(1, empresa.getEmail());

			rs = st.executeQuery();

			while (rs.next()) {
				ListarVaga vaga = new ListarVaga();
				vaga.setIdVaga(rs.getInt("idVaga"));
				vaga.setNome(rs.getString("nome"));
				vagas.add(vaga);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
		}
		return vagas;
	}

	public List<Vaga> filtrarVagasCandidato(String where) {
		List<Vaga> vagas = new ArrayList<>();
		String query = "SELECT * FROM Vaga " + where;

		try (PreparedStatement preparedStatement = conn.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Vaga vaga = new Vaga();
				vaga.setId(resultSet.getInt("idVaga"));
				vaga.setNome(resultSet.getString("nome"));
				vaga.setFaixaSalarial(resultSet.getDouble("faixaSalarial"));
				vaga.setDescricao(resultSet.getString("descricao"));
				vaga.setEstado(resultSet.getString("estado"));
				vaga.setEmail(resultSet.getString("email"));
				vaga.setCompetencias(parseCompetencias(resultSet.getString("competencias")));
				vagas.add(vaga);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vagas.isEmpty() ? null : vagas;
	}

	private List<String> parseCompetencias(String competencias) {
		JSONArray arr = new JSONArray(competencias);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < arr.length(); i++) {
			String item = arr.getString(i);
			list.add(item);
		}
		return list;
	}
}
