
package gui;

import cliente.Cliente;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.json.JSONObject;
import javax.swing.GroupLayout.Alignment;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

@SuppressWarnings("serial")
public class CandidatoHome extends javax.swing.JFrame {

    private Cliente cliente;
    private String usuario;
    private String token;
    private String email;


    public CandidatoHome(Cliente cliente, String usuario, String email, String token) {
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
        initComponents();
        this.cliente = cliente;
        this.usuario = usuario;
        this.token = token;
        this.email = email;
        this.visualizarUsusario();
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pnCandidato = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        inpNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        inpEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        inpSenha = new javax.swing.JTextField();
        bttnAtualizar = new javax.swing.JToggleButton();
        bttnLogout = new javax.swing.JToggleButton();
        bttnApagar = new javax.swing.JToggleButton();
        btnCompetencias = new javax.swing.JToggleButton();
        btnVagas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(null);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Bem vindo Candidato");

        pnCandidato.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        javax.swing.GroupLayout pnCandidatoLayout = new javax.swing.GroupLayout(pnCandidato);
        pnCandidato.setLayout(pnCandidatoLayout);
        pnCandidatoLayout.setHorizontalGroup(
            pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCandidatoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inpNome, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnCandidatoLayout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(jLabel3)
                    .addContainerGap(292, Short.MAX_VALUE)))
        );
        pnCandidatoLayout.setVerticalGroup(
            pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCandidatoLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(inpNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(inpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(inpSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(142, Short.MAX_VALUE))
            .addGroup(pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnCandidatoLayout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addComponent(jLabel3)
                    .addContainerGap(246, Short.MAX_VALUE)))
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
        
        btnVagas.setText("Vagas");
        btnVagas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVagasActionPerformed(evt);
            }
        });

        bttnApagar.setText("Deletar conta");
        bttnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnApagarActionPerformed(evt);
            }
        });

        btnCompetencias.setText("Competências");
        btnCompetencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompetenciasActionPerformed(evt);
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
                .addComponent(pnCandidato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bttnAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bttnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bttnApagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCompetencias, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVagas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGap(18, 18, 18)
                        .addComponent(btnCompetencias)
                        .addGap(18, 18, 18)
                        .addComponent(btnVagas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bttnLogout)
                        .addGap(18, 18, 18)
                        .addComponent(bttnApagar))
                    .addComponent(pnCandidato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }


    private void bttnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnAtualizarActionPerformed
        String nome = this.inpNome.getText();
        String senha = this.inpSenha.getText();

        JSONObject request = new JSONObject();
        request.put("operacao", "atualizar" + this.usuario);
        request.put("email", this.email);
        request.put("senha", senha);
        request.put("nome", nome);
        request.put("token", token);

        String response = this.cliente.callServer(request);

        JSONObject responseJson = new JSONObject(response);

        if (responseJson.getInt("status") == 201) {
            JOptionPane.showMessageDialog(CandidatoHome.this, "Usuario atualizado com sucesso!", "Sucesso Atualizar Usuario", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(CandidatoHome.this, responseJson.getString("mensagem"), "Erro Atualizar Usuario", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_bttnAtualizarActionPerformed

    private void bttnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnLogoutActionPerformed
        JSONObject request = new JSONObject();
        request.put("operacao", "logout");
        request.put("token", this.token);

        String response = this.cliente.callServer(request);

        JSONObject responseJson = new JSONObject(response);

        if (responseJson.getInt("status") == 204) {
            new Login(this.cliente).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(CandidatoHome.this, responseJson.getString("mensagem"), "Erro Logout", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void btnVagasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVagasActionPerformed
        new Vaga(cliente, usuario, email, token).setVisible(true);
        dispose();
    }

    private void inpNomeActionPerformed(java.awt.event.ActionEvent evt) {
    }//GEN-LAST:event_inpNomeActionPerformed

    private void inpEmailActionPerformed(java.awt.event.ActionEvent evt) {
    }//GEN-LAST:event_inpEmailActionPerformed

    private void inpSenhaActionPerformed(java.awt.event.ActionEvent evt) {
    }//GEN-LAST:event_inpSenhaActionPerformed

    private void bttnApagarActionPerformed(java.awt.event.ActionEvent evt) {
        JSONObject request = new JSONObject();

        request.put("operacao", "apagar" + this.usuario);
        request.put("email", this.inpEmail.getText());
        request.put("token", this.token);

        String response = this.cliente.callServer(request);
        JSONObject responseJson = new JSONObject(response);

        if (responseJson.getInt("status") == 201) {
        	JOptionPane.showMessageDialog(CandidatoHome.this, "Usuário apagado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            new Login(this.cliente).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(CandidatoHome.this, responseJson.getString("mensagem"), "Erro Apagar Usuario", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bttnApagarActionPerformed

    private void btnCompetenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompetenciasActionPerformed
        new CompetenciaExperiencia(this.cliente, this.token, this.email).setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCompetenciasActionPerformed

    private void visualizarUsusario() {
        JSONObject request = new JSONObject();

        request.put("operacao", "visualizar" + this.usuario);
        request.put("email", this.email);
        request.put("token", this.token);
        System.out.println("visualizarUsusario: "+ request);
        String response = this.cliente.callServer(request);
        if(response == null) {
        	JOptionPane.showMessageDialog(CandidatoHome.this, "Não recebido resposta do servidor", "Erro Visualizar Usuario", JOptionPane.ERROR_MESSAGE);
        }
        JSONObject responseJson = new JSONObject(response);
        if (responseJson.getInt("status") == 201) {
            this.inpNome.setText(responseJson.getString("nome"));
            this.inpEmail.setText(email);
            this.inpSenha.setText(responseJson.getString("senha"));
        } else {
            JOptionPane.showMessageDialog(CandidatoHome.this, responseJson.getString("mensagem"), "Erro Visualizar Usuario", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnCompetencias;
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
    private javax.swing.JPanel pnCandidato;
    private javax.swing.JButton btnVagas;
}