package gui;

import cliente.Cliente;
import entities.Competencia;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.json.JSONArray;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class CadastrarCompetenciaExperiencia extends javax.swing.JPanel {

    private String email;
    private String token;
    private Cliente cliente;
    public JSONArray competencias;

    public CadastrarCompetenciaExperiencia(String email, String token, Cliente cliente) {
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
    	
        this.email = email;
        this.token = token;
        this.cliente = cliente;

        initComponents();
        for (Competencia competencia : Competencia.values()) {
            this.cbCompetencia.addItem(competencia.toString());
        }
        this.btnFinalizar.setEnabled(false);
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbCompetencia = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        inpExp = new javax.swing.JTextField();
        btnAddComp = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(347, 275));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Competência");

        jLabel2.setText("Experiência");

        btnAddComp.setText("Adicionar competência");
        btnAddComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCompActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(28, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAddComp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inpExp, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCompetencia, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(inpExp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(btnAddComp)
                .addGap(20, 20, 20))
        );

        btnFinalizar.setText("Finalizar");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnFinalizar)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFinalizar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    private void btnAddCompActionPerformed(java.awt.event.ActionEvent evt) {
        String competencia = this.cbCompetencia.getSelectedItem().toString();
        String exp = this.inpExp.getText();
        if (exp.equals("")) {
            exp = "0";
        }
        if (this.competencias == null) {
            this.competencias = new JSONArray();
        }
        JSONObject newObj = new JSONObject();
        newObj.put("competencia", competencia);
        newObj.put("experiencia", Integer.parseInt(exp));
        this.competencias.put(newObj);
        this.btnFinalizar.setEnabled(true);
        JOptionPane.showMessageDialog(CadastrarCompetenciaExperiencia.this, "Competência Adicionada", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {

        JSONObject request = new JSONObject();
        request.put("operacao", "cadastrarCompetenciaExperiencia");
        request.put("email", this.email);
        request.put("token", this.token);
        request.put("competenciaExperiencia", this.competencias);

        String response = this.cliente.callServer(request);
        if (response == null) {
            JOptionPane.showMessageDialog(CadastrarCompetenciaExperiencia.this, "Resposta não recebida", "Erro Cadastrar Competencia Experiencia", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JSONObject json = new JSONObject(response);
        
        if (json.getInt("status") == 201) {
            this.competencias = null;
            JOptionPane.showMessageDialog(CadastrarCompetenciaExperiencia.this, json.getString("mensagem"), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(CadastrarCompetenciaExperiencia.this, json.getString("mensagem"), "Erro Cadastrar Competencia Experiencia", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnFinalizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddComp;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JComboBox<String> cbCompetencia;
    private javax.swing.JTextField inpExp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}