package gui;

import cliente.Cliente;
import helper.FormatarJSONString;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

@SuppressWarnings("serial")
public class Vaga extends javax.swing.JFrame {

    private Cliente cliente;
    private String usuario;
    private String token;
    private String email;
    
    public Vaga(Cliente cliente, String usuario, String email, String token) {
    	try {
            // Aplicar tema Metal
        	UIManager.setLookAndFeel(new NimbusLookAndFeel());

            // Customize NimbusLookAndFeel
            UIManager.put("nimbusBase", new Color(255, 255, 255)); // Set background color to white
            UIManager.put("nimbusBlueGrey", new Color(137, 177, 177)); // Set blue-grey color to dark grey
            UIManager.put("controlFont", new Font("Arial", Font.BOLD, 14)); // Set font to Arial bold 14
            // UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (Exception e) {
            System.err.println("Erro ao aplicar tema: " + e.getMessage());
        }
        this.cliente = cliente;
        this.usuario = usuario;
        this.token = token;
        this.email = email;

        initComponents();
        
        try {
            // Aplicar tema Metal
            //UIManager.setLookAndFeel(new MetalLookAndFeel());
             UIManager.setLookAndFeel(new NimbusLookAndFeel());
            // UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (Exception e) {
            System.err.println("Erro ao aplicar tema: " + e.getMessage());
        }

        if (usuario.equals("Empresa")) {
            this.btnFiltrar.setVisible(false);
            this.btnAlterar.setEnabled(false);
            this.btnApagar.setEnabled(false);
            this.visualizarVagasEmpresa();
        } else {
            this.btnAlterar.setVisible(false);
            this.btnApagar.setVisible(false);
            this.btnCadastrar.setVisible(false);
        }

    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVagas = new javax.swing.JTable();
        btnFiltrar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        btnCadastrar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnApagar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        
        jLabel1.setText("Vagas");

        tblVagas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Vaga", "Nome", "Email", "Faixa Salarial", "Descricao", "Estado", "Competência"
            }
        ) {
            @SuppressWarnings("rawtypes")
			Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            @SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVagas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblVagas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVagasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVagas);

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });
        
        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnApagar.setText("Apagar");
        btnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnApagar)
                        .addGap(30, 30, 30)
                        .addComponent(btnAlterar)
                        .addGap(30, 30, 30)
                        .addComponent(btnCadastrar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(26, Short.MAX_VALUE)
                        .addComponent(btnVoltar)
                        .addGap(140, 140, 140)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                        .addComponent(btnFiltrar)))
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnFiltrar)
                    .addComponent(btnVoltar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar)
                    .addComponent(btnAlterar)
                    .addComponent(btnApagar))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        if (this.usuario.equals("Candidato")) {
            new CandidatoHome(this.cliente, "Candidato", this.email, this.token).setVisible(true);
            dispose();
        } else {
            new EmpresaHome(this.cliente, "Empresa", this.email, this.token).setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        if (this.tblVagas.isVisible()) {
            this.tblVagas.setVisible(false);
            this.jScrollPane1.setViewportView(new CadastrarVaga(this.cliente, this.email, this.token));
            this.btnAlterar.setEnabled(false);
            this.btnApagar.setEnabled(false);
            this.btnCadastrar.setText("Visualizar");
        } else {
            this.tblVagas.setVisible(true);
            this.jScrollPane1.setViewportView(this.tblVagas);
            this.visualizarVagasEmpresa();
            this.btnCadastrar.setText("Cadastrar");
        }
    }

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {

        int row = this.tblVagas.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(Vaga.this, "Nenhuma linha foi selecionada", "Erro ao Alterar Vaga", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel tblModel = (DefaultTableModel) this.tblVagas.getModel();

        AlterarVaga popup = new AlterarVaga(this.cliente, this.email, this.token, this);
        popup.completeInputs(
                Integer.parseInt(tblModel.getValueAt(row, 0).toString()),
                tblModel.getValueAt(row, 2).toString(),
                tblModel.getValueAt(row, 1).toString(),
                tblModel.getValueAt(row, 5).toString(),
                tblModel.getValueAt(row, 4).toString(),
                Float.parseFloat(tblModel.getValueAt(row, 3).toString()),
                tblModel.getValueAt(row, 6).toString()
        );
        popup.setVisible(true);

    }

    private void tblVagasMouseClicked(java.awt.event.MouseEvent evt) {
        this.btnAlterar.setEnabled(true);
        this.btnApagar.setEnabled(true);
    }

    private void btnApagarActionPerformed(java.awt.event.ActionEvent evt) {
        int row = this.tblVagas.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(Vaga.this, "Nenhuma linha foi selecionada", "Erro ao Alterar Vaga", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel tblModel = (DefaultTableModel) this.tblVagas.getModel();
        JSONObject request = new JSONObject();
        request.put("operacao", "apagarVaga");
        request.put("email", this.email);
        request.put("token", this.token);
        request.put("idVaga", tblModel.getValueAt(row, 0));
        
        String response = this.cliente.callServer(request);
        if (response == null) {
            JOptionPane.showMessageDialog(Vaga.this, "Resposta não recebida", "Erro ao alterar vaga", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JSONObject responseJSON = new JSONObject(response);

        
        if (responseJSON.getInt("status") == 201) {
            JOptionPane.showMessageDialog(Vaga.this, responseJSON.getString("mensagem"), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            this.visualizarVagasEmpresa();
        } else {
            JOptionPane.showMessageDialog(Vaga.this, responseJSON.getString("mensagem"), "Erro alterar vaga", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        new VagaFiltrar(this).setVisible(true);
    }//GEN-LAST:event_btnFiltrarActionPerformed

    public void visualizarVagasCadidato(JSONObject filtros) {

        JSONObject request = new JSONObject();
        request.put("operacao", "filtrarVagas");
        request.put("email", this.email);
        request.put("token", this.token);
        request.put("filtros", filtros);

        String response = this.cliente.callServer(request);
        if (response == null) {
            JOptionPane.showMessageDialog(Vaga.this, "Resposta não recebida", "Erro ao Filtrar Vagas", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JSONObject responseJSON = new JSONObject(response);

        if (responseJSON.getInt("status") == 201) {
            DefaultTableModel tblModel = (DefaultTableModel) this.tblVagas.getModel();
            tblModel.setRowCount(0);
            JSONArray vagas = responseJSON.getJSONArray("vagas");
            for (Object vagaObj : vagas) {
                try {
                    JSONObject vaga = new JSONObject(vagaObj.toString());
                    System.out.println(vaga);
                    tblModel.addRow(new Object[]{
                        vaga.getInt("idVaga"),
                        vaga.getString("nome"),
                        this.email,
                        vaga.getFloat("faixaSalarial"),
                        vaga.getString("descricao"),
                        vaga.getString("estado"),
                        FormatarJSONString.arrayToJson(vaga.getJSONArray("competencias"))
                    });

                } catch (Exception ex) {
                    
                    JOptionPane.showMessageDialog(Vaga.this, ex.getMessage(), "Erro Filtrar Vaga", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        } else {
            JOptionPane.showMessageDialog(Vaga.this, responseJSON.getString("mensagem"), "Erro Filtrar Vagas", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void visualizarVagasEmpresa() {
    	
        JSONObject request = new JSONObject();
        request.put("operacao", "listarVagas");
        request.put("email", this.email);
        request.put("token", this.token);

        String response = this.cliente.callServer(request);
        if (response == null) {
            JOptionPane.showMessageDialog(Vaga.this, "Resposta não recebida", "Erro ao Listar Vagas", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JSONObject responseJSON = new JSONObject(response);

        if (responseJSON.getInt("status") == 201) {
            DefaultTableModel tblModel = (DefaultTableModel) this.tblVagas.getModel();
            tblModel.setRowCount(0);
            JSONArray vagas = responseJSON.getJSONArray("vagas");
            for (Object vagaObj : vagas) {
                try {
                    JSONObject vaga = new JSONObject(vagaObj.toString());
                    request = new JSONObject();
                    request.put("operacao", "visualizarVaga");
                    request.put("email", this.email);
                    request.put("token", this.token);
                    request.put("idVaga", vaga.getInt("idVaga"));

                    response = this.cliente.callServer(request);
                    if (response == null) {
                        JOptionPane.showMessageDialog(Vaga.this, "Resposta não recebida", "Erro ao Listar Vagas", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    responseJSON = new JSONObject(response);

                    if (responseJSON.getInt("status") == 201) {
                        tblModel.addRow(new Object[]{
                            vaga.getInt("idVaga"),
                            vaga.getString("nome"),
                            this.email,
                            responseJSON.getFloat("faixaSalarial"),
                            responseJSON.getString("descricao"),
                            responseJSON.getString("estado"),
                            FormatarJSONString.arrayToJson(responseJSON.getJSONArray("competencias")),});
                    } else {
                        JOptionPane.showMessageDialog(Vaga.this, responseJSON.getString("mensagem"), "Erro Visualziar Vaga", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Vaga.this, ex.getMessage(), "Erro Visualizar Vaga", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        } else {
            JOptionPane.showMessageDialog(Vaga.this, responseJSON.getString("mensagem"), "Erro Listar Vagas", JOptionPane.ERROR_MESSAGE);
        }
    }

    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnApagar;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVagas;
}