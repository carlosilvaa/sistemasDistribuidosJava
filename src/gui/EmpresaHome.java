package gui;

import cliente.Cliente;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.json.JSONObject;

@SuppressWarnings("serial")
public class EmpresaHome extends javax.swing.JFrame {

    private Cliente cliente;
    private String usuario;
    private String token;
    private String email;

    public EmpresaHome(Cliente cliente, String usuario, String email, String token) {
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
        this.visualizarUsusario(usuario, email);
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pnEmpresa = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        inpRazaoSocial = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        inpCnpj = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        inpEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        inpSenha = new javax.swing.JTextField();
        inpRamo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inpDescricao = new javax.swing.JTextArea();
        bttnAtualizar = new javax.swing.JToggleButton();
        bttnLogout = new javax.swing.JToggleButton();
        bttnApagar = new javax.swing.JToggleButton();
        btnVagas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Bem vindo(a)");

        pnEmpresa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Razão Social");

        inpRazaoSocial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpRazaoSocialActionPerformed(evt);
            }
        });

        jLabel6.setText("CNPJ");

        inpCnpj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpCnpjActionPerformed(evt);
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

        inpRamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpRamoActionPerformed(evt);
            }
        });

        jLabel7.setText("Ramo");

        jLabel5.setText("Descrição");

        inpDescricao.setColumns(10);
        inpDescricao.setLineWrap(true);
        inpDescricao.setRows(1);
        inpDescricao.setAutoscrolls(false);
        jScrollPane1.setViewportView(inpDescricao);

        javax.swing.GroupLayout pnEmpresaLayout = new javax.swing.GroupLayout(pnEmpresa);
        pnEmpresa.setLayout(pnEmpresaLayout);
        pnEmpresaLayout.setHorizontalGroup(
            pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnEmpresaLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnEmpresaLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnEmpresaLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(inpRamo, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(pnEmpresaLayout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(inpEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(inpSenha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnEmpresaLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(inpCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnEmpresaLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(inpRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        pnEmpresaLayout.setVerticalGroup(
            pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnEmpresaLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpRamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(inpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(inpSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
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

        btnVagas.setText("Vagas");
        btnVagas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVagasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(216, 216, 216)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(pnEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bttnAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bttnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bttnApagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVagas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11))
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
                        .addComponent(btnVagas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bttnLogout)
                        .addGap(18, 18, 18)
                        .addComponent(bttnApagar))
                    .addComponent(pnEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void bttnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {
        String razaoSocial = this.inpRazaoSocial.getText();
        String cnpj = this.inpCnpj.getText();
        String ramo = this.inpRamo.getText();
        String email = this.inpEmail.getText();
        String senha = this.inpSenha.getText();
        String descricao = this.inpDescricao.getText();

        JSONObject request = new JSONObject();
        request.put("operacao", "atualizar" + this.usuario);
        request.put("razaoSocial", razaoSocial);
        request.put("cnpj", cnpj);
        request.put("ramo", ramo);
        request.put("email", email);
        request.put("senha", senha);
        request.put("descricao", descricao);
        request.put("token", this.token);

        String response = this.cliente.callServer(request);

        JSONObject responseJson = new JSONObject(response);

        if (responseJson.getInt("status") == 201) {
            JOptionPane.showMessageDialog(EmpresaHome.this, "Usuario atualizado com sucesso!", "Sucesso Atualizar Usuario", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(EmpresaHome.this, responseJson.getString("mensagem"), "Erro Atualizar Usuario", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void bttnLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        JSONObject request = new JSONObject();
        request.put("operacao", "logout");
        request.put("token", this.token);

        String response = this.cliente.callServer(request);

        JSONObject responseJson = new JSONObject(response);

        if (responseJson.getInt("status") == 204) {
            new Login(this.cliente).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(EmpresaHome.this, responseJson.getString("mensagem"), "Erro Logout", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void inpRazaoSocialActionPerformed(java.awt.event.ActionEvent evt) {
       
    }

    private void inpEmailActionPerformed(java.awt.event.ActionEvent evt) {
        
    }

    private void inpSenhaActionPerformed(java.awt.event.ActionEvent evt) {
        
    }

    private void bttnApagarActionPerformed(java.awt.event.ActionEvent evt) {
        JSONObject request = new JSONObject();

        request.put("operacao", "apagar" + this.usuario);
        request.put("email", this.inpEmail.getText());
        request.put("token", this.token);

        String response = this.cliente.callServer(request);

        JSONObject responseJson = new JSONObject(response);

        if (responseJson.getInt("status") == 201) {
        	JOptionPane.showMessageDialog(EmpresaHome.this, "Usuário apagado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            new Login(this.cliente).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(EmpresaHome.this, responseJson.getString("mensagem"), "Erro Apagar Usuario", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void inpCnpjActionPerformed(java.awt.event.ActionEvent evt) {
        
    }

    private void inpRamoActionPerformed(java.awt.event.ActionEvent evt) {
        
    }

    private void btnVagasActionPerformed(java.awt.event.ActionEvent evt) {
        new Vaga(this.cliente, "Empresa", this.email, this.token).setVisible(true);
        dispose();
    }

    private void visualizarUsusario(String usuario, String email) {
        JSONObject request = new JSONObject();

        request.put("operacao", "visualizar" + usuario);
        request.put("email", email);
        request.put("token", this.token);

        String response = this.cliente.callServer(request);

        JSONObject responseJson = new JSONObject(response);
        if (responseJson.getInt("status") == 201) {
            this.inpRazaoSocial.setText(responseJson.getString("razaoSocial"));
            this.inpCnpj.setText(responseJson.getString("cnpj"));
            this.inpRamo.setText(responseJson.getString("ramo"));
            this.inpEmail.setText(email);
            this.inpSenha.setText(responseJson.getString("senha"));
            this.inpDescricao.setText(responseJson.getString("descricao"));
        } else {
            JOptionPane.showMessageDialog(EmpresaHome.this, responseJson.getString("mensagem"), "Erro Visualizar Usuario", JOptionPane.ERROR_MESSAGE);
        }
    }

    private javax.swing.JButton btnVagas;
    private javax.swing.JToggleButton bttnApagar;
    private javax.swing.JToggleButton bttnAtualizar;
    private javax.swing.JToggleButton bttnLogout;
    private javax.swing.JTextField inpCnpj;
    private javax.swing.JTextArea inpDescricao;
    private javax.swing.JTextField inpEmail;
    private javax.swing.JTextField inpRamo;
    private javax.swing.JTextField inpRazaoSocial;
    private javax.swing.JTextField inpSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnEmpresa;
}