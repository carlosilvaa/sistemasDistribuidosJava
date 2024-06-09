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
            st = this.conn.prepareStatement("INSERT INTO vaga (nome, faixaSalarial, descricao) VALUES(?,?,?)");
            st.setString(1, vaga.getNome());
            st.setDouble(2, vaga.getFaixaSalarial());
            st.setString(3, vaga.getDescricao());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                int idVaga = rs.getInt(1);
                vaga.setCodigo(idVaga);
            }
        } finally {
            if (st != null) {
                st.close();
            }
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
                vaga.setCodigo(rs.getInt("idVaga"));
                vaga.setNome(rs.getString("nome"));
                vaga.setFaixaSalarial(rs.getDouble("faixaSalarial"));
                vaga.setDescricao(rs.getString("descricao"));
                return vaga;
            }
            return null; 
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        }
    }
    
    public List<Vaga> buscarVagasPorCompetencia(List<Competencia> competencias, boolean utilizandoAnd) throws SQLException {
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
            String sql = "SELECT v.* FROM vaga v " +
                         "JOIN vagacompetencia vc ON v.idVaga = vc.idVagaCompetencia" +
                         "WHERE " + whereClause.toString();

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
        	String competenciasAsString = String.join(",", vaga.getCompetencias());
        	
        	st = conn.prepareStatement("UPDATE vaga SET idEmpresa = ?, nome = ?, descricao = ?, estado = ?, faixaSalarial = ?, competencias = ? WHERE idVaga = ?");
            st.setInt(1, empresa.getId());
            st.setString(2, vaga.getNome());
            st.setString(3, vaga.getDescricao());
            st.setString(4, vaga.getEstado());
            st.setDouble(5, vaga.getFaixaSalarial());
            st.setString(6, competenciasAsString);
            st.setInt(7, vaga.getCodigo());
            st.executeUpdate();

        } finally {
            if (st != null) {
                st.close();
            }
        }
    }

    public void apagarVaga(int idVaga) throws SQLException {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM vaga WHERE id = ?");
            st.setInt(1, idVaga);
            st.executeUpdate();

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
            st = conn.prepareStatement("SELECT v.idVaga, v.nome FROM Vaga v WHERE v.empresa =?");
            st.setLong(1, empresa.getId());

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
                vaga.setId(resultSet.getInt("id"));
                vaga.setNome(resultSet.getString("nome"));
                vaga.setFaixaSalarial(resultSet.getDouble("faixaSalarial"));
                vaga.setDescricao(resultSet.getString("descricao"));
                vaga.setEstado(resultSet.getString("estado"));
                vaga.setCompetencias(parseCompetencias(resultSet.getString("competencias")));
              

                vagas.add(vaga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vagas.isEmpty() ? null : vagas;
    }
    
    private List<String> parseCompetencias(String competencias) {
        List<String> competenciasList = new ArrayList<>();
        if (competencias != null && !competencias.isEmpty()) {
            String[] competenciasArray = competencias.split(",");
            for (String competencia : competenciasArray) {
                competenciasList.add(competencia.trim());
            }
        }
        return competenciasList;
    }
}
