package Main_Views;

import Class.Usuario;
import DAO.UsuarioDAO;
import java.util.Random;
import javax.swing.JOptionPane;

public class View_Login extends javax.swing.JFrame {

    public View_Login() {
        initComponents();
        verSenha.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();
        IconLogin = new javax.swing.JLabel();
        jButtonSemLogin = new javax.swing.JButton();
        BackgroundConteudo = new javax.swing.JPanel();
        TitleLogin = new javax.swing.JLabel();
        verSenha = new javax.swing.JLabel();
        TitleEmailLogin = new javax.swing.JLabel();
        TitleSenhaLogin = new javax.swing.JLabel();
        TitleNotCadastro = new javax.swing.JLabel();
        jTextEmailLogin = new javax.swing.JTextField();
        jPasswordSenha = new javax.swing.JPasswordField();
        jButtonEntrar = new javax.swing.JButton();
        jButtonEsqueciSenha = new javax.swing.JButton();
        jButtonCadastrarSe = new javax.swing.JButton();
        nãoVerSenha = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Background.setBackground(new java.awt.Color(58, 56, 77));

        IconLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/login-user_128.png"))); // NOI18N

        jButtonSemLogin.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSemLogin.setText("jButton1");
        jButtonSemLogin.setBorderPainted(false);
        jButtonSemLogin.setContentAreaFilled(false);
        jButtonSemLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSemLoginActionPerformed(evt);
            }
        });

        BackgroundConteudo.setBackground(new java.awt.Color(38, 40, 55));
        BackgroundConteudo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TitleLogin.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        TitleLogin.setForeground(new java.awt.Color(255, 255, 255));
        TitleLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TitleLogin.setText("Login");
        TitleLogin.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        TitleLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BackgroundConteudo.add(TitleLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 23, 319, 42));

        verSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hide_24.png"))); // NOI18N
        verSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                verSenhaMousePressed(evt);
            }
        });
        BackgroundConteudo.add(verSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, -1, -1));

        TitleEmailLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TitleEmailLogin.setForeground(new java.awt.Color(255, 255, 255));
        TitleEmailLogin.setText("Email");
        BackgroundConteudo.add(TitleEmailLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 78, 319, -1));

        TitleSenhaLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TitleSenhaLogin.setForeground(new java.awt.Color(255, 255, 255));
        TitleSenhaLogin.setText("Senha");
        BackgroundConteudo.add(TitleSenhaLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 143, 319, -1));

        TitleNotCadastro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TitleNotCadastro.setForeground(new java.awt.Color(255, 255, 255));
        TitleNotCadastro.setText("Caso não tenha conta --->");
        BackgroundConteudo.add(TitleNotCadastro, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 256, 178, -1));
        BackgroundConteudo.add(jTextEmailLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 105, 290, 25));
        BackgroundConteudo.add(jPasswordSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 170, 290, 25));

        jButtonEntrar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButtonEntrar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEntrar.setText("Entrar");
        jButtonEntrar.setBorderPainted(false);
        jButtonEntrar.setContentAreaFilled(false);
        jButtonEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEntrarActionPerformed(evt);
            }
        });
        BackgroundConteudo.add(jButtonEntrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 208, 101, -1));

        jButtonEsqueciSenha.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButtonEsqueciSenha.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEsqueciSenha.setText("Esqueci senha");
        jButtonEsqueciSenha.setBorderPainted(false);
        jButtonEsqueciSenha.setContentAreaFilled(false);
        jButtonEsqueciSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEsqueciSenhaActionPerformed(evt);
            }
        });
        BackgroundConteudo.add(jButtonEsqueciSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 209, -1, 25));

        jButtonCadastrarSe.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButtonCadastrarSe.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCadastrarSe.setText("Cadastrar-se");
        jButtonCadastrarSe.setBorderPainted(false);
        jButtonCadastrarSe.setContentAreaFilled(false);
        jButtonCadastrarSe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarSeActionPerformed(evt);
            }
        });
        BackgroundConteudo.add(jButtonCadastrarSe, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 254, -1, -1));

        nãoVerSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/view_24.png"))); // NOI18N
        nãoVerSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nãoVerSenhaMousePressed(evt);
            }
        });
        BackgroundConteudo.add(nãoVerSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, -1, -1));

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(BackgroundConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSemLogin)
                        .addContainerGap())
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(IconLogin)
                        .addContainerGap(69, Short.MAX_VALUE))))
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(IconLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jButtonSemLogin)
                .addGap(20, 20, 20))
            .addComponent(BackgroundConteudo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEsqueciSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEsqueciSenhaActionPerformed
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int idUsuario;
        int random_number;
        int confirmar;
        String numeroInformadoStr;

        confirmar = JOptionPane.showConfirmDialog(null, "Você deseja mesmo trocar a sua senha?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            String userInput = JOptionPane.showInputDialog("Informe seu Email");

            if (usuarioDAO.inputDoUsuarioEValido(userInput)) {
                Random rand = new Random();
                random_number = rand.nextInt(9000) + 1000;
                JOptionPane.showMessageDialog(null, "Este é seu número: " + random_number);

                numeroInformadoStr = JOptionPane.showInputDialog("Digite o número que você recebeu:");

                if (numeroInformadoStr != null && !numeroInformadoStr.isEmpty()) {
                    try {
                        int numeroInformado = Integer.parseInt(numeroInformadoStr);

                        if (numeroInformado == random_number) {
                            String novaSenha = JOptionPane.showInputDialog("Digite sua nova senha:");
                            String confirmarSenha = JOptionPane.showInputDialog("Confirme sua nova senha:");

                            if (novaSenha != null && !novaSenha.isEmpty() && novaSenha.equals(confirmarSenha)) {
                                idUsuario = usuarioDAO.getIdUsuarioPorInput(userInput);
                                if (idUsuario != -1) {
                                    usuarioDAO.atualizarSenha(idUsuario, novaSenha);
                                    JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro: Usuário não encontrado.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "As senhas não coincidem ou são inválidas. Tente novamente.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Número incorreto. Tente novamente.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Por favor, insira um número válido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, insira o número que você recebeu.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Email ou número de celular inválido. Tente novamente.");
            }
        } else {
            return;
        }

    }//GEN-LAST:event_jButtonEsqueciSenhaActionPerformed

    private void jButtonEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEntrarActionPerformed
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String email = jTextEmailLogin.getText();
        String senha = new String(jPasswordSenha.getPassword());

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos de login.");
            return;
        }

        if (!usuarioDAO.verificarEmailSenha(email, senha)) {
            JOptionPane.showMessageDialog(this, "Email ou Senha incorreta. Por favor, verifique os dados informados.");
            return;
        }
        int idUsuario = usuarioDAO.obterIdUsuarioPorEmail(email);
        Usuario usuario = usuarioDAO.obterUsuarioPorId(idUsuario);
        System.out.println("id do usuario:" + idUsuario);

        new View_Home1(idUsuario).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonEntrarActionPerformed

    private void jButtonCadastrarSeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarSeActionPerformed
        View_Cadastro ViewCadastro = new View_Cadastro();
        ViewCadastro.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonCadastrarSeActionPerformed

    private void jButtonSemLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSemLoginActionPerformed
        String informacoes = "Usuarios não logados so teram suas listas salvas durante o app rodando";

        JOptionPane.showMessageDialog(this, informacoes);

        View_Home1 home = new View_Home1();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonSemLoginActionPerformed

    private void verSenhaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verSenhaMousePressed
      nãoVerSenha.setVisible(true);
      verSenha.setVisible(false);
      jPasswordSenha.setEchoChar('*');
    }//GEN-LAST:event_verSenhaMousePressed

    private void nãoVerSenhaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nãoVerSenhaMousePressed
       verSenha.setVisible(true);
      nãoVerSenha.setVisible(false);
      jPasswordSenha.setEchoChar((char)0);
    }//GEN-LAST:event_nãoVerSenhaMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View_Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JPanel BackgroundConteudo;
    private javax.swing.JLabel IconLogin;
    private javax.swing.JLabel TitleEmailLogin;
    private javax.swing.JLabel TitleLogin;
    private javax.swing.JLabel TitleNotCadastro;
    private javax.swing.JLabel TitleSenhaLogin;
    private javax.swing.JButton jButtonCadastrarSe;
    private javax.swing.JButton jButtonEntrar;
    private javax.swing.JButton jButtonEsqueciSenha;
    private javax.swing.JButton jButtonSemLogin;
    private javax.swing.JPasswordField jPasswordSenha;
    private javax.swing.JTextField jTextEmailLogin;
    private javax.swing.JLabel nãoVerSenha;
    private javax.swing.JLabel verSenha;
    // End of variables declaration//GEN-END:variables
}
