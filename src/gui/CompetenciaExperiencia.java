package gui;

import cliente.Cliente;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class CompetenciaExperiencia extends javax.swing.JFrame {

    private Cliente cliente;
    private String token;
    private String email;
    private JSONArray editCells;
    private JSONArray removeCells;
    private boolean flag;

    public CompetenciaExperiencia(Cliente cliente, String token, String email) {
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
        this.token = token;
        this.email = email;
        initComponents();
        this.tblCompetenciaExperiencia.setVisible(true);
        this.visualizarCompetenciaExperiencia();
        this.flag = false;
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCompetenciaExperiencia = new javax.swing.JTable();
        btnCadastrar = new javax.swing.JButton();
        btnApagar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        bttnAtualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Competências");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tblCompetenciaExperiencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Competência", "Experiência"
            }
        ) {
            @SuppressWarnings("rawtypes")
			Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            @SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCompetenciaExperiencia.setColumnSelectionAllowed(true);
        tblCompetenciaExperiencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCompetenciaExperienciaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCompetenciaExperiencia);
        tblCompetenciaExperiencia.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        btnCadastrar.setText("<html><center>"+"Cadastrar"+"<br>"+"Competência"+"</center></html>");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnApagar.setText("<html><center>"+"Apagar"+"<br>"+"Competências"+"</center></html>");
        btnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarActionPerformed(evt);
            }
        });

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        bttnAtualizar.setText("<html><center>"+"Atualizar"+"<br>"+"Competências"+"</center></html>");
        bttnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnAtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnVoltar)
                        .addGap(115, 115, 115)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnApagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bttnAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnVoltar))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(bttnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        if (!flag) {
            this.tblCompetenciaExperiencia.setVisible(false);
            this.jScrollPane1.setViewportView(new CadastrarCompetenciaExperiencia(this.email, this.token, this.cliente));
            this.btnCadastrar.setText("<html>Visualizar<br>Competência</html>");
            this.bttnAtualizar.setEnabled(false);
            this.btnApagar.setEnabled(false);
        } else {
            this.tblCompetenciaExperiencia.setVisible(true);
            this.jScrollPane1.setViewportView(this.tblCompetenciaExperiencia);
            this.btnCadastrar.setText("<html>Cadastrar<br>Competência</html>");
            this.visualizarCompetenciaExperiencia();
        }
        flag = !flag;
    }

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {
        new CandidatoHome(this.cliente, "Candidato", this.email, this.token).setVisible(true);
        dispose();
    }

    private void bttnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.editCells != null) {
            JSONObject request = new JSONObject();
            request.put("operacao", "atualizarCompetenciaExperiencia");
            request.put("email", this.email);
            request.put("token", this.token);
            request.put("competenciaExperiencia", this.editCells);

            String response = this.cliente.callServer(request);
            if (response == null) {
                JOptionPane.showMessageDialog(CompetenciaExperiencia.this, "Resposta não recebida", "Erro ao Atualizar competência", JOptionPane.ERROR_MESSAGE);
                return;
            }
            System.out.println("Recebido do Servidor: " + response);
            JSONObject responseJSON = new JSONObject(response);

            if (responseJSON.getInt("status") == 201) {
                this.editCells = null;
                JOptionPane.showMessageDialog(CompetenciaExperiencia.this, responseJSON.getString("mensagem"), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(CompetenciaExperiencia.this, responseJSON.getString("mensagem"), "Erro Atualizar Competencia Experiencia", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void tblCompetenciaExperienciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCompetenciaExperienciaMouseClicked
        DefaultTableModel tblModel = (DefaultTableModel) this.tblCompetenciaExperiencia.getModel();
        int row = this.tblCompetenciaExperiencia.getSelectedRow();
        Object exp = tblModel.getValueAt(row, 1);
        Object comp = tblModel.getValueAt(row, 0);
        if (this.removeCells == null) {
            this.removeCells = new JSONArray();
        }

        JSONObject newCell = new JSONObject();
        newCell.put("competencia", comp);
        newCell.put("experiencia", Integer.parseInt(exp.toString()));
        btnApagar.setEnabled(true);

        this.removeCells.put(newCell);
    }

    private void btnApagarActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.removeCells != null) {
            JSONObject request = new JSONObject();
            request.put("operacao", "apagarCompetenciaExperiencia");
            request.put("email", this.email);
            request.put("token", this.token);
            request.put("competenciaExperiencia", this.removeCells);

            String response = this.cliente.callServer(request);
            if (response == null) {
                JOptionPane.showMessageDialog(CompetenciaExperiencia.this, "Resposta não recebida", "Erro ao Apagar competência", JOptionPane.ERROR_MESSAGE);
                return;
            }
            System.out.println("Recebido do Servidor: " + response);
            JSONObject responseJSON = new JSONObject(response);

            if (responseJSON.getInt("status") == 201) {
                JOptionPane.showMessageDialog(CompetenciaExperiencia.this, responseJSON.getString("mensagem"), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                this.visualizarCompetenciaExperiencia();
            } else {
                JOptionPane.showMessageDialog(CompetenciaExperiencia.this, responseJSON.getString("mensagem"), "Erro Apagar Competencia Experiencia", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void visualizarCompetenciaExperiencia() {
        this.editCells = null;
        this.removeCells = null;
        this.bttnAtualizar.setEnabled(false);
        this.btnApagar.setEnabled(false);
        JSONObject request = new JSONObject();
        request.put("operacao", "visualizarCompetenciaExperiencia");
        request.put("email", this.email);
        request.put("token", this.token);

        String response = this.cliente.callServer(request);
        if (response == null) {
            JOptionPane.showMessageDialog(CompetenciaExperiencia.this, "Resposta não recebida", "Erro ao visualizar competência", JOptionPane.ERROR_MESSAGE);
            return;
        }
        System.out.println("Recebido do Servidor: " + response);
        JSONObject responseJSON = new JSONObject(response);

        if (responseJSON.getInt("status") == 201) {
            DefaultTableModel tblModel = (DefaultTableModel) this.tblCompetenciaExperiencia.getModel();
            tblModel.setRowCount(0);
            JSONArray competenciaExperienciaArray = responseJSON.getJSONArray("competenciaExperiencia");
            if (competenciaExperienciaArray.length() == 0) {
                return;
            }

            for (int i = 0; i < competenciaExperienciaArray.length(); i++) {
                JSONObject competenciaObj = competenciaExperienciaArray.getJSONObject(i);
                String competencia = competenciaObj.getString("competencia");
                int experiencia = competenciaObj.getInt("experiencia");
                tblModel.addRow(new Object[]{competencia, experiencia});
            }

            tblModel.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    if (e.getType() == TableModelEvent.UPDATE) {
                        int row = e.getFirstRow();
                        Object exp = tblModel.getValueAt(row, 1);
                        Object comp = tblModel.getValueAt(row, 0);

                        if (editCells == null) {
                            editCells = new JSONArray();
                        }
                        JSONObject newCell = new JSONObject();
                        newCell.put("competencia", comp);
                        newCell.put("experiencia", Integer.parseInt(exp.toString()));
                        bttnAtualizar.setEnabled(true);

                        editCells.put(newCell);
                    }
                }
            });
        } else {
            JOptionPane.showMessageDialog(CompetenciaExperiencia.this, responseJSON.getString("mensagem"), "Erro ao visualizar competência", JOptionPane.ERROR_MESSAGE);
        }

    }

    private javax.swing.JButton btnApagar;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JButton bttnAtualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCompetenciaExperiencia;
}