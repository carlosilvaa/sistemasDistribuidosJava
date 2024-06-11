package gui;

import cliente.Cliente;
import entities.Competencia;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.json.JSONObject;

@SuppressWarnings("serial")
public class AlterarVaga extends javax.swing.JFrame {

    private Cliente cliente;
    private String email;
    private String token;
    private DefaultListModel<String> listModel;
    private Vaga vagaView;
    /**
     * Creates new form AlterarVaga2
     */
    public AlterarVaga(Cliente cliente, String email, String token, Vaga vagaView) {
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
        this.vagaView = vagaView;
        
        initComponents();

        this.listModel = new DefaultListModel<>();
        for (Competencia competencia : Competencia.values()) {
            this.listModel.addElement(competencia.toString());
        }
        this.liCompetencias.setModel(this.listModel);
    }

    private void initComponents() {

        btnAlterar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        inpIdVaga = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        inpEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        liCompetencias = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        inpNome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        inpFaixaSalarial = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        inpDescricao = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        jLabel1.setText("ID Vaga");

        inpIdVaga.setEditable(false);

        jLabel2.setText("E-mail");

        inpEmail.setEditable(false);

        jLabel3.setText("Competências");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jScrollPane1.setViewportView(liCompetencias);

        jLabel4.setText("Nome");

        jLabel5.setText("Faixa Salarial");

        jLabel6.setText("Estado");

        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Divulgavel", "Disponivel", "Indisponivel" }));

        jLabel7.setText("Descrição");

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        inpDescricao.setTabSize(4);
        jScrollPane2.setViewportView(inpDescricao);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inpIdVaga, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(inpEmail))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                            .addComponent(inpNome)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(inpFaixaSalarial, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(cbEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnAlterar))
                            .addComponent(jScrollPane2))))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inpIdVaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(inpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(inpNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(inpFaixaSalarial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnAlterar)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {
        
        JSONObject request = new JSONObject();
        request.put("operacao", "atualizarVaga");
        request.put("email", this.email);
        request.put("token", this.token);
        request.put("idVaga", Integer.parseInt(this.inpIdVaga.getText()));
        request.put("faixaSalarial", Float.parseFloat(this.inpFaixaSalarial.getText()));
        request.put("descricao", this.inpDescricao.getText());
        request.put("nome", this.inpNome.getText());
        request.put("estado", this.cbEstado.getSelectedItem().toString());
        request.put("competencias", this.liCompetencias.getSelectedValuesList());

       
        String response = this.cliente.callServer(request);
        if (response == null) {
            JOptionPane.showMessageDialog(AlterarVaga.this, "Resposta não recebida", "Erro ao alterar vaga", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JSONObject responseJSON = new JSONObject(response);

        if (responseJSON.getInt("status") == 201) {
            this.vagaView.visualizarVagasEmpresa();
            dispose();
            JOptionPane.showMessageDialog(this.vagaView, responseJSON.getString("mensagem"), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(AlterarVaga.this, responseJSON.getString("mensagem"), "Erro alterar vaga", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    public void completeInputs(int idVaga, String email, String nome, String estado, String descricao, float faixaSalarial, String competencias) {
        this.inpIdVaga.setText(String.valueOf(idVaga));
        this.inpEmail.setText(email);
        this.inpFaixaSalarial.setText(String.valueOf(faixaSalarial));
        this.inpNome.setText(nome);
        this.inpDescricao.setText(descricao);
        this.cbEstado.setSelectedItem(estado);
        List<Integer> indices = new ArrayList<>();
        String[] items = competencias.split(", ");
        for (String competencia : items) {
            int index = listModel.indexOf(competencia);
            if (index != -1) {
                indices.add(index);
            }
        }
        int[] selectedIndices = indices.stream().mapToInt(i -> i).toArray();
        this.liCompetencias.setSelectedIndices(selectedIndices);
        this.liCompetencias.ensureIndexIsVisible(selectedIndices[0]);

    }
    private javax.swing.JButton btnAlterar;
    private javax.swing.JComboBox<String> cbEstado;
    private javax.swing.JTextArea inpDescricao;
    private javax.swing.JTextField inpEmail;
    private javax.swing.JTextField inpFaixaSalarial;
    private javax.swing.JTextField inpIdVaga;
    private javax.swing.JTextField inpNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> liCompetencias;
}