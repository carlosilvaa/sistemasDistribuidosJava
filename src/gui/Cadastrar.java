package gui;

import cliente.Cliente;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.json.JSONObject;


@SuppressWarnings("serial")
public class Cadastrar extends javax.swing.JFrame {

    private Cliente cliente;

    public Cadastrar(Cliente cliente) {
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
        this.selecionarUsuario();
    }

                         
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pnEmpresa = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        inpRazaoSocial = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        inpCnpj = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        inpEmailEmpresa = new javax.swing.JTextField();
        inpSenhaEmpresa = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        inpDescricao = new javax.swing.JTextField();
        inpRamo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        pnCandidato = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        inpEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        inpNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        inpSenha = new javax.swing.JTextField();
        bttnCadastrar = new javax.swing.JButton();
        cbUsuario = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Cadastrar ");

        jLabel5.setText("Razão Social");

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

        jLabel7.setText("E-mail");

        inpEmailEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpEmailEmpresaActionPerformed(evt);
            }
        });

        inpSenhaEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpSenhaEmpresaActionPerformed(evt);
            }
        });

        jLabel8.setText("Senha");

        jLabel9.setText("Descrição");

        inpDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpDescricaoActionPerformed(evt);
            }
        });

        inpRamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpRamoActionPerformed(evt);
            }
        });

        jLabel10.setText("Ramo");

        javax.swing.GroupLayout pnEmpresaLayout = new javax.swing.GroupLayout(pnEmpresa);
        pnEmpresa.setLayout(pnEmpresaLayout);
        pnEmpresaLayout.setHorizontalGroup(
            pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnEmpresaLayout.createSequentialGroup()
                .addGap(0, 16, Short.MAX_VALUE)
                .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnEmpresaLayout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addGap(20, 20, 20)
                            .addComponent(inpSenhaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnEmpresaLayout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(18, 18, 18)
                            .addComponent(inpEmailEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnEmpresaLayout.createSequentialGroup()
                            .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel5))
                            .addGap(18, 18, 18)
                            .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(inpCnpj, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                                .addComponent(inpRazaoSocial)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnEmpresaLayout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(18, 18, 18)
                            .addComponent(inpDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnEmpresaLayout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(18, 18, 18)
                            .addComponent(inpRamo, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        pnEmpresaLayout.setVerticalGroup(
            pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnEmpresaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(inpRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(inpCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(inpEmailEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(inpSenhaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(inpDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(inpRamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel2.setText("Nome");

        jLabel3.setText("E-mail");

        jLabel4.setText("Senha");

        javax.swing.GroupLayout pnCandidatoLayout = new javax.swing.GroupLayout(pnCandidato);
        pnCandidato.setLayout(pnCandidatoLayout);
        pnCandidatoLayout.setHorizontalGroup(
            pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCandidatoLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(pnCandidatoLayout.createSequentialGroup()
                        .addGroup(pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(27, 27, 27)
                        .addGroup(pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inpEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                            .addComponent(inpNome)
                            .addComponent(inpSenha))))
                .addGap(0, 34, Short.MAX_VALUE))
        );
        pnCandidatoLayout.setVerticalGroup(
            pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCandidatoLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(inpNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(inpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(inpSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        bttnCadastrar.setText("Cadastrar");
        bttnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnCadastrarActionPerformed(evt);
            }
        });

        cbUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Candidato", "Empresa" }));
        cbUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(218, 218, 218)
                .addComponent(jLabel1)
                .addGap(52, 52, 52)
                .addComponent(cbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnCandidato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttnCadastrar))
                .addGap(76, 76, 76))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(37, Short.MAX_VALUE)
                    .addComponent(pnEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(58, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(pnCandidato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bttnCadastrar)
                .addGap(27, 27, 27))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(58, Short.MAX_VALUE)
                    .addComponent(pnEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(55, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void bttnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {                                              
        JSONObject request = new JSONObject();
        if (this.cbUsuario.getSelectedItem().equals("Candidato")) {
        	
            String nome = this.inpNome.getText();
            String email = this.inpEmail.getText();
            String senha = this.inpSenha.getText();

            request.put("operacao", "cadastrarCandidato");
            request.put("nome", nome);
            request.put("email", email);
            request.put("senha", senha);

        } else {
            String razaoSocial = this.inpRazaoSocial.getText();
            String cnpj = this.inpCnpj.getText();
            String email = this.inpEmailEmpresa.getText();
            String senha = this.inpSenhaEmpresa.getText();
            String descricao = this.inpDescricao.getText();
            String ramo = this.inpRamo.getText();

            request.put("operacao", "cadastrarEmpresa");
            request.put("razaoSocial", razaoSocial);
            request.put("cnpj", cnpj);
            request.put("email", email);
            request.put("senha", senha);
            request.put("descricao", descricao);
            request.put("ramo", ramo);
        }
        
        String response = this.cliente.callServer(request);
        JSONObject responseJson = new JSONObject(response);
        System.out.println(responseJson);
        if (responseJson.getInt("status") == 201) {
        	String mensagem = "Usuário cadastrado com sucesso!";
            JOptionPane.showMessageDialog(Cadastrar.this, mensagem, "Cadastro Realizado", JOptionPane.INFORMATION_MESSAGE);
            new Login(this.cliente).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(Cadastrar.this, responseJson.getString("mensagem"), "Erro Cadastrar Candidato", JOptionPane.ERROR_MESSAGE);
        }
    }                                             

    private void cbUsuarioActionPerformed(java.awt.event.ActionEvent evt) {                                          
        this.selecionarUsuario();
    }                                         

    private void inpRazaoSocialActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void inpCnpjActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void inpEmailEmpresaActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void inpSenhaEmpresaActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void inpDescricaoActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void inpRamoActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }         

    private void selecionarUsuario() {
        if (cbUsuario.getSelectedIndex() == 0) {
            pnEmpresa.setVisible(false);
            pnCandidato.setVisible(true);
        } else {
            pnEmpresa.setVisible(true);
            pnCandidato.setVisible(false);
        }
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton bttnCadastrar;
    private javax.swing.JComboBox<String> cbUsuario;
    private javax.swing.JTextField inpCnpj;
    private javax.swing.JTextField inpDescricao;
    private javax.swing.JTextField inpNome;
    private javax.swing.JTextField inpEmailEmpresa;
    private javax.swing.JTextField inpEmail;
    private javax.swing.JTextField inpRamo;
    private javax.swing.JTextField inpRazaoSocial;
    private javax.swing.JTextField inpSenha;
    private javax.swing.JTextField inpSenhaEmpresa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel pnCandidato;
    private javax.swing.JPanel pnEmpresa;
    // End of variables declaration                   
}