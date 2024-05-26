package gui;

import cliente.Cliente;
import javax.swing.JOptionPane;
import org.json.JSONObject;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class windowEmpresa extends javax.swing.JFrame {

    private Cliente cliente;
    private String usuario;
    private String token;

    public windowEmpresa(Cliente cliente, String usuario, String email, String token) {
        initComponents();
        this.cliente = cliente;
        this.usuario = usuario;
        this.token = token;
        this.visualizarUsusario(usuario, email);
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        bttnAtualizar = new javax.swing.JToggleButton();
        bttnLogout = new javax.swing.JToggleButton();
        bttnApagar = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(null);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Bem vindo(a)");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        
        textAreaDescricao = new JTextArea();
        
        jLabel4 = new JLabel();
        jLabel4.setText("Descrição");
        
        inpRamo = new JTextField();
        
        jLabel4_1 = new JLabel();
        jLabel4_1.setText("Ramo");
        
        inpCNPJ = new JTextField();
        
        jLabel4_2 = new JLabel();
        jLabel4_2.setText("CNPJ");
        
        inpSenha = new JTextField();
        
        jLabel4_3 = new JLabel();
        jLabel4_3.setText("Senha");
        
        inpEmail = new JTextField();
        inpEmail.setEditable(false);
        
        lblEmailEmpresa = new JLabel();
        lblEmailEmpresa.setText("E-mail");
        
        inpRazaoSocial = new JTextField();
        
        lblRazaoSocial = new JLabel();
        lblRazaoSocial.setText("Razão Social");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(32)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(2)
        					.addComponent(lblRazaoSocial, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
        				.addComponent(lblEmailEmpresa, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
        				.addComponent(jLabel4_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jLabel4_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jLabel4_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jLabel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addGap(18)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(inpRazaoSocial, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
        				.addComponent(inpEmail, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
        				.addComponent(inpSenha, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
        				.addComponent(inpCNPJ, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
        				.addComponent(inpRamo, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
        				.addComponent(textAreaDescricao, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(186, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(23)
        					.addComponent(lblRazaoSocial)
        					.addGap(15)
        					.addComponent(lblEmailEmpresa)
        					.addGap(14)
        					.addComponent(jLabel4_3)
        					.addGap(17)
        					.addComponent(jLabel4_2)
        					.addGap(12)
        					.addComponent(jLabel4_1)
        					.addGap(19)
        					.addComponent(jLabel4))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(20)
        					.addComponent(inpRazaoSocial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(6)
        					.addComponent(inpEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(11)
        					.addComponent(inpSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(11)
        					.addComponent(inpCNPJ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(6)
        					.addComponent(inpRamo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(11)
        					.addComponent(textAreaDescricao, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel1.setLayout(jPanel1Layout);

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
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(201)
        			.addComponent(jLabel1)
        			.addContainerGap(193, Short.MAX_VALUE))
        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        			.addGap(41)
        			.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(bttnAtualizar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(bttnLogout, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(bttnApagar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addGap(20))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(14)
        			.addComponent(jLabel1)
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(bttnAtualizar)
        					.addGap(204)
        					.addComponent(bttnLogout)
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(bttnApagar)
        					.addContainerGap(15, Short.MAX_VALUE))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap())))
        );
        getContentPane().setLayout(layout);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bttnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnAtualizarActionPerformed
        String razaoSocial = this.inpRazaoSocial.getText();
        String email = this.inpEmail.getText();
        String senha = this.inpSenha.getText();
        String cnpj = this.inpCNPJ.getText();
        String ramo = this.inpRamo.getText();
        String descricao = this.textAreaDescricao.getText();
        
        JSONObject request = new JSONObject();
        request.put("operacao", "atualizar"+this.usuario);
        request.put("email", email);
        request.put("senha", senha);
        request.put("razaoSocial", razaoSocial);
        request.put("cnpj", cnpj);
        request.put("ramo", ramo);
        request.put("descricao", descricao);
          
        
        String response = this.cliente.callServer(request);
        JSONObject responseJson = new JSONObject(response);
        System.out.println(responseJson);
        if(responseJson.getInt("status") == 201){
            JOptionPane.showMessageDialog(windowEmpresa.this, "Usuario atualizado com sucesso!", "Sucesso Atualizar Usuario", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(windowEmpresa.this, responseJson.getString("mensagem"), "Erro Atualizar Usuario", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_bttnAtualizarActionPerformed

    private void bttnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnLogoutActionPerformed
        JSONObject request = new JSONObject();
        request.put("operacao", "logout");
        request.put("token", this.token);
        
        String response = this.cliente.callServer(request);
        JSONObject responseJson = new JSONObject(response);
        System.out.println(responseJson);
        if(responseJson.getInt("status") == 204){
            new Login(this.cliente).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(windowEmpresa.this, responseJson.getString("mensagem"), "Erro Logout", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bttnLogoutActionPerformed

    private void bttnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnApagarActionPerformed
        JSONObject request = new JSONObject();

        request.put("operacao", "apagar" + this.usuario);
        request.put("email", this.inpEmail.getText());
        
        String response = this.cliente.callServer(request);
        JSONObject responseJson = new JSONObject(response);
        System.out.println(responseJson);
        if (responseJson.getInt("status") == 201) {
            JOptionPane.showMessageDialog(windowEmpresa.this, "Usuário apagado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            new Login(this.cliente).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(windowEmpresa.this, responseJson.getString("mensagem"), "Erro ao apagar o Usuario", JOptionPane.ERROR_MESSAGE);
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
            this.inpRazaoSocial.setText(responseJson.getString("razaoSocial"));
            this.inpEmail.setText(email);
            this.inpSenha.setText(responseJson.getString("senha"));
            this.inpCNPJ.setText(responseJson.getString("cnpj"));
            this.inpRamo.setText(responseJson.getString("ramo"));
            this.textAreaDescricao.setText(responseJson.getString("descricao"));
        } else {
        	
            JOptionPane.showMessageDialog(windowEmpresa.this, responseJson.getString("mensagem"), "Erro Visualizar Usuario", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton bttnApagar;
    private javax.swing.JToggleButton bttnAtualizar;
    private javax.swing.JToggleButton bttnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private JTextArea textAreaDescricao;
    private JLabel jLabel4;
    private JTextField inpRamo;
    private JLabel jLabel4_1;
    private JTextField inpCNPJ;
    private JLabel jLabel4_2;
    private JTextField inpSenha;
    private JLabel jLabel4_3;
    private JTextField inpEmail;
    private JLabel lblEmailEmpresa;
    private JTextField inpRazaoSocial;
    private JLabel lblRazaoSocial;
    // End of variables declaration//GEN-END:variables
}