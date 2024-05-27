package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Empresa;

public class EmpresaDAO {

    private Connection conn;

    public EmpresaDAO(Connection conn) {
        this.conn = conn;
    }

    public void cadastrarEmpresa(Empresa empresa) throws SQLException {
        PreparedStatement st = null;
        System.out.println("empresaDAO: " + empresa);
        try {
            st = this.conn.prepareStatement("INSERT INTO empresa (razaoSocial, email, senha, cnpj, ramo, descricao ) VALUES(?,?,?,?,?,?)");
            st.setString(1, empresa.getRazaoSocial());
            st.setString(2, empresa.getEmail());
            st.setString(3, empresa.getSenha());
            st.setString(4, empresa.getCNPJ());
            st.setString(5, empresa.getRamo());
            st.setString(6, empresa.getDescricao());
            st.executeUpdate();	

        } finally {
            BancoDados.finalizarStatement(st);
        }
    }

    public int excluirEmpresa(String email) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT idEmpresa FROM empresa WHERE email = ?");
            st.setString(1, email);
            rs = st.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("idEmpresa");
                st = conn.prepareStatement("DELETE FROM empresa WHERE idEmpresa = ?");
                st.setInt(1, id);
                int returnDelete = st.executeUpdate();
                return returnDelete;
            } else {
                return 0; // Empresa não encontrada
            }
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public boolean verificarEmpresa(String email) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean success = false;

        try {
            String sql = "SELECT * FROM empresa WHERE email = ?";
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

    public boolean editarEmpresa(String cnpj, String email, String novaRazaoSocial, String senha, String descricao, String ramo) throws SQLException {
        boolean empresaExiste = verificarEmpresa(email);

        if (empresaExiste) {
            PreparedStatement st = null;
            try {
                String sql = "UPDATE empresa SET razaoSocial =?, senha =?, descricao =?, ramo =?, cnpj =? WHERE email =?";
                st = conn.prepareStatement(sql);
                st.setString(1, novaRazaoSocial);
                st.setString(2, senha);
                st.setString(3, descricao);
                st.setString(4, ramo);
                st.setString(5, cnpj);
                st.setString(6, email);
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
            System.out.println("{\"operacao\": \"editarEmpresa\", \"status\": 404, \"mensagem\": \"Empresa não encontrada\"}");
            return false;
        }
    }

    public List<Empresa> buscarTodasEmpresas() throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM empresa");
            rs = st.executeQuery();

            List<Empresa> listaEmpresas = new ArrayList<>();

            while (rs.next()) {
                Empresa empresa = new Empresa();

                empresa.setId(rs.getInt("idEmpresa"));
                empresa.setRazaoSocial(rs.getString("razaoSocial"));
                empresa.setEmail(rs.getString("email"));
                empresa.setCNPJ(rs.getString("cnpj"));
                empresa.setSenha(rs.getString("senha"));
                empresa.setDescricao(rs.getString("descricao"));
                empresa.setRamo(rs.getString("ramo"));

                listaEmpresas.add(empresa);

            }
            return listaEmpresas;

        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public Empresa buscarPorEmail(String email) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM empresa WHERE email = ?");
            st.setString(1, email);
            rs = st.executeQuery();
            if (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setRazaoSocial(rs.getString("razaoSocial"));
                empresa.setEmail(rs.getString("email"));
                empresa.setSenha(rs.getString("senha"));
                empresa.setCNPJ(rs.getString("cnpj"));
                empresa.setDescricao(rs.getString("descricao"));
                empresa.setRamo(rs.getString("ramo"));
                return empresa;
            }
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
        return null;
    }

}