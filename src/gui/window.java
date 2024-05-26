package gui;

import cliente.Cliente;
import javax.swing.JOptionPane;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class window extends javax.swing.JFrame {

    private Cliente cliente;
    private String usuario;
    private String token;

    public window(Cliente cliente, String usuario, String email, String token) {
        initComponents();
        this.cliente = cliente;
        this.usuario = usuario;
        this.token = token;
        this.visualizarUsusario(usuario, email);
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        inpNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        inpEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        inpSenha = new javax.swing.JTextField();
        bttnAtualizar = new javax.swing.JToggleButton();
        bttnLogout = new javax.swing.JToggleButton();
        bttnApagar = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(null);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Bem vindo(a)");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Nome");

        inpNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpNomeActionPerformed(evt);
            }
        });

        jLabel2.setText("E-mail");

        inpEmail.setEditable(false);
        inpEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpEmailActionPerformed(evt);
            }
        });

        jLabel4.setText("Senha");

        inpSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpSenhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inpNome, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(jLabel3)
                    .addContainerGap(276, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(inpNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(inpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(inpSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(137, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addComponent(jLabel3)
                    .addContainerGap(241, Short.MAX_VALUE)))
        );

        bttnAtualizar.setText("Atualizar dados");
        bttnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnAtualizarActionPerformed(evt);
            }
        });

        bttnLogout.setText("Sair");
        bttnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnLogoutActionPerformed(evt);
            }
        });

        bttnApagar.setText("Deletar conta");
        bttnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnApagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bttnAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bttnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bttnApagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bttnAtualizar)
                        .addGap(204, 204, 204)
                        .addComponent(bttnLogout)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bttnApagar))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bttnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnAtualizarActionPerformed
        String nome = this.inpNome.getText();
        String email = this.inpEmail.getText();
        String senha = this.inpSenha.getText();
        
        JSONObject request = new JSONObject();
        request.put("operacao", "atualizar"+this.usuario);
        request.put("email", email);
        request.put("senha", senha);
        request.put("nome", nome);
        
        String response = this.cliente.callServer(request);
        JSONObject responseJson = new JSONObject(response);
        System.out.println(responseJson);
        
        if(responseJson.getInt("status") == 201){
            JOptionPane.showMessageDialog(window.this, "Usuario atualizado com sucesso!", "Sucesso Atualizar Usuario", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(window.this, responseJson.getString("mensagem"), "Erro Atualizar Usuario", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_bttnAtualizarActionPerformed

    private void bttnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnLogoutActionPerformed
        JSONObject request = new JSONObject();
        request.put("operacao", "logout");
        request.put("token", this.token);
        
        String response = this.cliente.callServer(request);
        JSONObject responseJson = new JSONObject(response);
        
        if(responseJson.getInt("status") == 204){
            new Login(this.cliente).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(window.this, responseJson.getString("mensagem"), "Erro Logout", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bttnLogoutActionPerformed

    private void inpNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpNomeActionPerformed

    private void inpEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpEmailActionPerformed

    private void inpSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpSenhaActionPerformed

    private void bttnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnApagarActionPerformed
        JSONObject request = new JSONObject();

        request.put("operacao", "apagar" + this.usuario);
        request.put("email", this.inpEmail.getText());
        
        String response = this.cliente.callServer(request);
        JSONObject responseJson = new JSONObject(response);
        System.out.println(responseJson);
        if (responseJson.getInt("status") == 201) {
        	JOptionPane.showMessageDialog(window.this, "Usuário apagado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            new Login(this.cliente).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(window.this, responseJson.getString("mensagem"), "Erro ao apagar o Usuario", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bttnApagarActionPerformed

    private void visualizarUsusario(String usuario, String email) {
        JSONObject request = new JSONObject();

        request.put("operacao", "visualizar" + usuario);
        request.put("email", email);

        String response = this.cliente.callServer(request);
        System.out.println(response);
        JSONObject responseJson = new JSONObject(response);
        if (responseJson.getInt("status") == 201) {
            this.inpNome.setText(responseJson.getString("nome"));
            this.inpEmail.setText(email);
            this.inpSenha.setText(responseJson.getString("senha"));
        } else {
        	
            JOptionPane.showMessageDialog(window.this, responseJson.getString("mensagem"), "Erro Visualizar Usuario", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton bttnApagar;
    private javax.swing.JToggleButton bttnAtualizar;
    private javax.swing.JToggleButton bttnLogout;
    private javax.swing.JTextField inpEmail;
    private javax.swing.JTextField inpNome;
    private javax.swing.JTextField inpSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}