package Main_Views;

import Class.Itens;
import Class.Lista;
import Class.Usuario;
import DAO.ItensDAO;
import DAO.ListaDAO;
import DAO.ListaHasItensDAO;
import DAO.UsuarioDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;

public class View_Home1 extends javax.swing.JFrame {

    private int idUsuario;
    private int selectedListaId = -1;
    private List<Itens> displayedItensList = new ArrayList<>();

    public View_Home1() {
        initComponents();
    }

    public View_Home1(int idUsuario) {
        initComponents();
        this.idUsuario = idUsuario;
        PreencherTabelaListas();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.obterUsuarioPorId(idUsuario);
        preencherCamposUsuario(usuario);

        jButtonCompletar.setVisible(false);
        jButtonPendente.setVisible(false);
        jButtonCancelar.setVisible(false);

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onDelete(int row) {
                onDeleteRow(row);
            }

            @Override
            public void onView(int row) {
                onViewRow(row);
            }
        };

        jTableListas.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
        jTableListas.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event));
    }

    private void onDeleteRow(int row) {
        int option = JOptionPane.showConfirmDialog(this, "Tem certeza de que deseja excluir a lista selecionada?", "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            String nomeLista = (String) jTableListas.getValueAt(row, 0);

            ListaDAO listaDAO = new ListaDAO();
            boolean sucessoExclusao = listaDAO.dellListasDoUsuario(idUsuario, nomeLista);

            if (sucessoExclusao) {
                JOptionPane.showMessageDialog(this, "Lista excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                PreencherTabelaListas();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir a lista.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void onViewRow(int row) {
        System.out.println("Botão 'Visualizar' pressionado para a linha " + row);

        String nomeLista = (String) jTableListas.getValueAt(row, 0);

        ItensDAO itensDAO = new ItensDAO();
        List<Itens> itensList = itensDAO.obterItensDaListaPorNomeEUsuario(nomeLista, idUsuario);

        displayedItensList = new ArrayList<>(itensList);

        DefaultTableModel model = (DefaultTableModel) jTableAdicionarLista.getModel();
        model.setRowCount(0);

        for (Itens item : itensList) {
            Object[] rowValues = {item.getNome_Produto(), item.getMarca(), item.getQuantidade(), item.getValor_Produto()};
            model.addRow(rowValues);
        }

        TXTNomeLista.setText(nomeLista);

        ListaDAO listaDAO = new ListaDAO();
        selectedListaId = listaDAO.obterIdListaPorNomeEUsuario(nomeLista, idUsuario);

        jButtonCompletar.setVisible(true);
        jButtonPendente.setVisible(true);
        jButtonCancelar.setVisible(true);

        jButtonSalvar.setVisible(false);

        PnlCard.removeAll();
        PnlCard.add(AdicionarLista);
        PnlCard.repaint();
        PnlCard.revalidate();
    }

    private void calcularTotais() {
        double somaQuantidade = 0.0;
        double somaValor = 0.0;

        DefaultTableModel model = (DefaultTableModel) jTableAdicionarLista.getModel();

        for (int row = 0; row < model.getRowCount(); row++) {
            int quantidade = (int) model.getValueAt(row, 2);
            double valor = (double) model.getValueAt(row, 3);

            somaQuantidade += quantidade;
            somaValor += valor * quantidade;
        }

        System.out.println("Soma total da quantidade: " + somaQuantidade);
        System.out.println("Soma total do valor: " + somaValor);

        int idLista = selectedListaId;
        ListaDAO listaDAO = new ListaDAO();
        listaDAO.atualizarValorTotal(idLista, somaValor);
        listaDAO.atualizarQuantidadeTotal(idLista, (int) somaQuantidade);

        for (int row = 0; row < model.getRowCount(); row++) {
            int idItem = displayedItensList.get(row).getIdItens();
            ItensDAO itensDAO = new ItensDAO();

            itensDAO.atualizarQuantidadeValorItem(idItem, (int) model.getValueAt(row, 2), (double) model.getValueAt(row, 3));
        }
        PreencherTabelaListas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();
        BackgroundConteudo = new javax.swing.JPanel();
        jButton_irListas = new javax.swing.JButton();
        jButtonAdiconarLista = new javax.swing.JButton();
        jButtonConta = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();
        IconHome = new javax.swing.JLabel();
        PnlCard = new javax.swing.JPanel();
        TodasListas = new javax.swing.JPanel();
        TXT_Procurar = new javax.swing.JTextField();
        jButtonCancelados = new javax.swing.JButton();
        jButtonPendentes = new javax.swing.JButton();
        jButtonCompletados = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListas = new javax.swing.JTable();
        AdicionarLista = new javax.swing.JPanel();
        TitleNomeDaLista = new javax.swing.JLabel();
        TXTNomeLista = new javax.swing.JTextField();
        jButtonSalvar = new javax.swing.JButton();
        jButtonAdicionarLinha = new javax.swing.JButton();
        jButtonRemoverLista = new javax.swing.JButton();
        jButtonCompletar = new javax.swing.JButton();
        jButtonPendente = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableAdicionarLista = new javax.swing.JTable();
        jButtonVerificar = new javax.swing.JButton();
        TitleNomeProduto = new javax.swing.JLabel();
        TitleNomeMarca = new javax.swing.JLabel();
        TitleNomeQuantidade = new javax.swing.JLabel();
        TitleNomeValor = new javax.swing.JLabel();
        ButtonAtualizar = new javax.swing.JLabel();
        TXTProduto = new javax.swing.JTextField();
        TXTMarca = new javax.swing.JTextField();
        TXTQuantidade = new javax.swing.JTextField();
        TXTValor = new javax.swing.JTextField();
        InformaçõesConta = new javax.swing.JPanel();
        TitleNomeDeUsuario = new javax.swing.JLabel();
        TitleCelular = new javax.swing.JLabel();
        TitleAtualizarCelular = new javax.swing.JLabel();
        TitleEmail = new javax.swing.JLabel();
        TitleAtualizarEmail = new javax.swing.JLabel();
        TitleAtualizarSenha = new javax.swing.JLabel();
        TXT_Celular = new javax.swing.JTextField();
        TXT_AtualizarCelular = new javax.swing.JTextField();
        TXT_Email = new javax.swing.JTextField();
        TXT_AtualizarEmail = new javax.swing.JTextField();
        TXT_AtualizarSenha = new javax.swing.JPasswordField();
        jButtonAtualizarDados = new javax.swing.JButton();
        jButtonDelDados = new javax.swing.JButton();
        jButtonDelConta = new javax.swing.JButton();
        TXT_NomeDeUsuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Background.setBackground(new java.awt.Color(153, 153, 153));
        Background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BackgroundConteudo.setBackground(new java.awt.Color(38, 40, 55));

        jButton_irListas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_irListas.setForeground(new java.awt.Color(255, 255, 255));
        jButton_irListas.setText("Listas");
        jButton_irListas.setBorderPainted(false);
        jButton_irListas.setContentAreaFilled(false);
        jButton_irListas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_irListasActionPerformed(evt);
            }
        });

        jButtonAdiconarLista.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonAdiconarLista.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAdiconarLista.setText("Adicionar Lista");
        jButtonAdiconarLista.setBorderPainted(false);
        jButtonAdiconarLista.setContentAreaFilled(false);
        jButtonAdiconarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdiconarListaActionPerformed(evt);
            }
        });

        jButtonConta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonConta.setForeground(new java.awt.Color(255, 255, 255));
        jButtonConta.setText("Conta");
        jButtonConta.setBorderPainted(false);
        jButtonConta.setContentAreaFilled(false);
        jButtonConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonContaActionPerformed(evt);
            }
        });

        jButtonSair.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonSair.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSair.setText("Sair");
        jButtonSair.setBorderPainted(false);
        jButtonSair.setContentAreaFilled(false);
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        IconHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/home_64.png"))); // NOI18N

        javax.swing.GroupLayout BackgroundConteudoLayout = new javax.swing.GroupLayout(BackgroundConteudo);
        BackgroundConteudo.setLayout(BackgroundConteudoLayout);
        BackgroundConteudoLayout.setHorizontalGroup(
            BackgroundConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundConteudoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(BackgroundConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton_irListas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonConta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAdiconarLista, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(72, 72, 72))
            .addGroup(BackgroundConteudoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(IconHome)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BackgroundConteudoLayout.setVerticalGroup(
            BackgroundConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundConteudoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(IconHome)
                .addGap(18, 18, 18)
                .addComponent(jButton_irListas)
                .addGap(40, 40, 40)
                .addComponent(jButtonAdiconarLista)
                .addGap(40, 40, 40)
                .addComponent(jButtonConta)
                .addGap(40, 40, 40)
                .addComponent(jButtonSair)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        Background.add(BackgroundConteudo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 400));

        PnlCard.setLayout(new java.awt.CardLayout());

        TodasListas.setBackground(new java.awt.Color(58, 56, 77));

        TXT_Procurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_ProcurarActionPerformed(evt);
            }
        });

        jButtonCancelados.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonCancelados.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCancelados.setText("Cancelados");
        jButtonCancelados.setBorderPainted(false);
        jButtonCancelados.setContentAreaFilled(false);
        jButtonCancelados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCanceladosActionPerformed(evt);
            }
        });

        jButtonPendentes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonPendentes.setForeground(new java.awt.Color(255, 255, 255));
        jButtonPendentes.setText("Pendentes");
        jButtonPendentes.setBorderPainted(false);
        jButtonPendentes.setContentAreaFilled(false);
        jButtonPendentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPendentesActionPerformed(evt);
            }
        });

        jButtonCompletados.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonCompletados.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCompletados.setText("Completados");
        jButtonCompletados.setBorderPainted(false);
        jButtonCompletados.setContentAreaFilled(false);
        jButtonCompletados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCompletadosActionPerformed(evt);
            }
        });

        jTableListas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome da Lista", "Quantidade", "Valor total", "Status", "Ações"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableListas.setCellSelectionEnabled(true);
        jTableListas.setGridColor(new java.awt.Color(0, 0, 0));
        jTableListas.setInheritsPopupMenu(true);
        jTableListas.setRowHeight(28);
        jTableListas.setShowGrid(true);
        jScrollPane1.setViewportView(jTableListas);
        jTableListas.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout TodasListasLayout = new javax.swing.GroupLayout(TodasListas);
        TodasListas.setLayout(TodasListasLayout);
        TodasListasLayout.setHorizontalGroup(
            TodasListasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TodasListasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TXT_Procurar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCompletados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonPendentes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelados)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
        );
        TodasListasLayout.setVerticalGroup(
            TodasListasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TodasListasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(TodasListasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXT_Procurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCompletados)
                    .addComponent(jButtonPendentes)
                    .addComponent(jButtonCancelados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        PnlCard.add(TodasListas, "card2");

        AdicionarLista.setBackground(new java.awt.Color(58, 56, 77));

        TitleNomeDaLista.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TitleNomeDaLista.setForeground(new java.awt.Color(255, 255, 255));
        TitleNomeDaLista.setText("Nome da Lista");

        jButtonSalvar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonSalvar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSalvar.setText("Salvar");
        jButtonSalvar.setBorderPainted(false);
        jButtonSalvar.setContentAreaFilled(false);
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonAdicionarLinha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonAdicionarLinha.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAdicionarLinha.setText("Adicionar");
        jButtonAdicionarLinha.setBorderPainted(false);
        jButtonAdicionarLinha.setContentAreaFilled(false);
        jButtonAdicionarLinha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarLinhaActionPerformed(evt);
            }
        });

        jButtonRemoverLista.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonRemoverLista.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRemoverLista.setText("Remover");
        jButtonRemoverLista.setBorderPainted(false);
        jButtonRemoverLista.setContentAreaFilled(false);
        jButtonRemoverLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverListaActionPerformed(evt);
            }
        });

        jButtonCompletar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonCompletar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCompletar.setText("Completar");
        jButtonCompletar.setBorderPainted(false);
        jButtonCompletar.setContentAreaFilled(false);
        jButtonCompletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCompletarActionPerformed(evt);
            }
        });

        jButtonPendente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonPendente.setForeground(new java.awt.Color(255, 255, 255));
        jButtonPendente.setText("Pendente");
        jButtonPendente.setBorderPainted(false);
        jButtonPendente.setContentAreaFilled(false);
        jButtonPendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPendenteActionPerformed(evt);
            }
        });

        jButtonCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonCancelar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setBorderPainted(false);
        jButtonCancelar.setContentAreaFilled(false);
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jTableAdicionarLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Marca", "Quantidade", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAdicionarLista.setGridColor(new java.awt.Color(0, 0, 0));
        jTableAdicionarLista.setShowGrid(true);
        jTableAdicionarLista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableAdicionarListaMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTableAdicionarLista);

        jButtonVerificar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonVerificar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVerificar.setText("Verificar");
        jButtonVerificar.setBorderPainted(false);
        jButtonVerificar.setContentAreaFilled(false);
        jButtonVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerificarActionPerformed(evt);
            }
        });

        TitleNomeProduto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TitleNomeProduto.setForeground(new java.awt.Color(255, 255, 255));
        TitleNomeProduto.setText("Produto");

        TitleNomeMarca.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TitleNomeMarca.setForeground(new java.awt.Color(255, 255, 255));
        TitleNomeMarca.setText("Marca");

        TitleNomeQuantidade.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TitleNomeQuantidade.setForeground(new java.awt.Color(255, 255, 255));
        TitleNomeQuantidade.setText("Quantidade");

        TitleNomeValor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TitleNomeValor.setForeground(new java.awt.Color(255, 255, 255));
        TitleNomeValor.setText("Valor");

        ButtonAtualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ButtonAtualizar.setForeground(new java.awt.Color(255, 255, 255));
        ButtonAtualizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ButtonAtualizar.setText("Atualizar");
        ButtonAtualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ButtonAtualizarMousePressed(evt);
            }
        });

        javax.swing.GroupLayout AdicionarListaLayout = new javax.swing.GroupLayout(AdicionarLista);
        AdicionarLista.setLayout(AdicionarListaLayout);
        AdicionarListaLayout.setHorizontalGroup(
            AdicionarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdicionarListaLayout.createSequentialGroup()
                .addGroup(AdicionarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AdicionarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(AdicionarListaLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(AdicionarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TitleNomeDaLista)
                                .addGroup(AdicionarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(TitleNomeValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TitleNomeProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TitleNomeMarca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TitleNomeQuantidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(AdicionarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(AdicionarListaLayout.createSequentialGroup()
                                    .addComponent(TXTNomeLista)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonVerificar))
                                .addGroup(AdicionarListaLayout.createSequentialGroup()
                                    .addGroup(AdicionarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(TXTProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                                        .addComponent(TXTMarca)
                                        .addComponent(TXTQuantidade)
                                        .addComponent(TXTValor))
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(AdicionarListaLayout.createSequentialGroup()
                            .addComponent(jButtonCompletar)
                            .addGap(2, 2, 2)
                            .addComponent(jButtonPendente)
                            .addGap(1, 1, 1)
                            .addComponent(jButtonCancelar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ButtonAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonRemoverLista)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonAdicionarLinha)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        AdicionarListaLayout.setVerticalGroup(
            AdicionarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdicionarListaLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(AdicionarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TitleNomeDaLista)
                    .addGroup(AdicionarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TXTNomeLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonVerificar)))
                .addGap(12, 12, 12)
                .addGroup(AdicionarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TitleNomeProduto)
                    .addComponent(TXTProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(AdicionarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TitleNomeMarca)
                    .addComponent(TXTMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(AdicionarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TitleNomeQuantidade)
                    .addComponent(TXTQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(AdicionarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TitleNomeValor)
                    .addComponent(TXTValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(AdicionarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCompletar)
                    .addGroup(AdicionarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonPendente)
                        .addComponent(jButtonCancelar)
                        .addComponent(ButtonAtualizar)
                        .addComponent(jButtonRemoverLista)
                        .addComponent(jButtonAdicionarLinha)
                        .addComponent(jButtonSalvar)))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        PnlCard.add(AdicionarLista, "card3");

        InformaçõesConta.setBackground(new java.awt.Color(58, 56, 77));
        InformaçõesConta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TitleNomeDeUsuario.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        TitleNomeDeUsuario.setForeground(new java.awt.Color(255, 255, 255));
        TitleNomeDeUsuario.setText("Nome do Usuario");
        InformaçõesConta.add(TitleNomeDeUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 36, -1, -1));

        TitleCelular.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        TitleCelular.setForeground(new java.awt.Color(255, 255, 255));
        TitleCelular.setText("Celular");
        InformaçõesConta.add(TitleCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 78, 113, -1));

        TitleAtualizarCelular.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        TitleAtualizarCelular.setForeground(new java.awt.Color(255, 255, 255));
        TitleAtualizarCelular.setText("Atualizar Celular");
        InformaçõesConta.add(TitleAtualizarCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 119, 113, -1));

        TitleEmail.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        TitleEmail.setForeground(new java.awt.Color(255, 255, 255));
        TitleEmail.setText("Email");
        InformaçõesConta.add(TitleEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 159, 108, -1));

        TitleAtualizarEmail.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        TitleAtualizarEmail.setForeground(new java.awt.Color(255, 255, 255));
        TitleAtualizarEmail.setText("Atualizar Email");
        InformaçõesConta.add(TitleAtualizarEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 200, 108, -1));

        TitleAtualizarSenha.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        TitleAtualizarSenha.setForeground(new java.awt.Color(255, 255, 255));
        TitleAtualizarSenha.setText("Atualizar Senha");
        InformaçõesConta.add(TitleAtualizarSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 108, -1));
        InformaçõesConta.add(TXT_Celular, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 76, 411, -1));
        InformaçõesConta.add(TXT_AtualizarCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 116, 411, -1));
        InformaçõesConta.add(TXT_Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 157, 410, -1));
        InformaçõesConta.add(TXT_AtualizarEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 197, 410, -1));
        InformaçõesConta.add(TXT_AtualizarSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 410, -1));

        jButtonAtualizarDados.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonAtualizarDados.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAtualizarDados.setText("Atualizar");
        jButtonAtualizarDados.setBorderPainted(false);
        jButtonAtualizarDados.setContentAreaFilled(false);
        jButtonAtualizarDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtualizarDadosActionPerformed(evt);
            }
        });
        InformaçõesConta.add(jButtonAtualizarDados, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 300, 110, -1));

        jButtonDelDados.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonDelDados.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelDados.setText("Deletar Dados");
        jButtonDelDados.setBorderPainted(false);
        jButtonDelDados.setContentAreaFilled(false);
        jButtonDelDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelDadosActionPerformed(evt);
            }
        });
        InformaçõesConta.add(jButtonDelDados, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 340, -1, -1));

        jButtonDelConta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonDelConta.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelConta.setText("Deletar Conta");
        jButtonDelConta.setBorderPainted(false);
        jButtonDelConta.setContentAreaFilled(false);
        jButtonDelConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelContaActionPerformed(evt);
            }
        });
        InformaçõesConta.add(jButtonDelConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 340, -1, -1));
        InformaçõesConta.add(TXT_NomeDeUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 412, -1));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mostrar senha");
        InformaçõesConta.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, 80, -1));

        PnlCard.add(InformaçõesConta, "card4");

        Background.add(PnlCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 570, 400));

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

//                     jPanel "BackgroundConteudo"                              \\
    private void jButton_irListasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_irListasActionPerformed

        PnlCard.removeAll();
        PnlCard.add(TodasListas);
        PnlCard.repaint();
        PnlCard.revalidate();
        PreencherTabelaListas();
    }//GEN-LAST:event_jButton_irListasActionPerformed

    private void jButtonAdiconarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdiconarListaActionPerformed
        PnlCard.removeAll();
        PnlCard.add(AdicionarLista);
        PnlCard.repaint();
        PnlCard.revalidate();

        DefaultTableModel model = (DefaultTableModel) jTableAdicionarLista.getModel();
        model.setRowCount(0);

        TXTNomeLista.setText("");
        
        jButtonCompletar.setVisible(false);
        jButtonPendente.setVisible(false);
        jButtonCancelar.setVisible(false);
        jButtonSalvar.setVisible(true);
    }//GEN-LAST:event_jButtonAdiconarListaActionPerformed

    private void jButtonContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonContaActionPerformed
        PnlCard.removeAll();
        PnlCard.add(InformaçõesConta);
        PnlCard.repaint();
        PnlCard.revalidate();
    }//GEN-LAST:event_jButtonContaActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        View_Login ViewLogin = new View_Login();
        ViewLogin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonSairActionPerformed
//==============================================================================\\

//                         jPanel "AdicionarLista"                              \\
    private void atualizarStatusLista(String novoStatus) {
        int selectedRow = jTableListas.getSelectedRow();
        if (selectedRow >= 0) {
            int idLista = Integer.parseInt(jTableListas.getValueAt(selectedRow, 0).toString());

            ListaDAO listaDAO = new ListaDAO();
            listaDAO.atualizarStatus(idLista, novoStatus);

            jTableListas.setValueAt(novoStatus, selectedRow, 5);

            JOptionPane.showMessageDialog(this, "Status da lista atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma lista na tabela para atualizar o status.", "Lista não selecionada", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void jButtonVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerificarActionPerformed
        String nomeLista = TXTNomeLista.getText();

        ListaDAO listaDAO = new ListaDAO();
        if (listaDAO.verificarExistenciaListaPorUsuario(idUsuario, nomeLista)) {
            JOptionPane.showMessageDialog(this, "O nome da lista já está em uso. Por favor, escolha outro nome.", "Nome da Lista Repetido", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }//GEN-LAST:event_jButtonVerificarActionPerformed

    private void jButtonCompletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCompletarActionPerformed
        atualizarStatusLista("Completada");
    }//GEN-LAST:event_jButtonCompletarActionPerformed

    private void jButtonPendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPendenteActionPerformed
        atualizarStatusLista("Pendente");
    }//GEN-LAST:event_jButtonPendenteActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        atualizarStatusLista("Cancelada");
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void ButtonAtualizarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonAtualizarMousePressed
        int selectedRow = jTableAdicionarLista.getSelectedRow();

        if (selectedRow >= 0 && selectedListaId != -1) {
            String novoProduto = TXTProduto.getText();
            String novaMarca = TXTMarca.getText();
            int novaQuantidade = Integer.parseInt(TXTQuantidade.getText());
            double novoValor = Double.parseDouble(TXTValor.getText());

            Itens itemAtualizado = displayedItensList.get(selectedRow);
            itemAtualizado.setNome_Produto(novoProduto);
            itemAtualizado.setMarca(novaMarca);
            itemAtualizado.setQuantidade(novaQuantidade);
            itemAtualizado.setValor_Produto(novoValor);

            double novoValorTotalItem = novaQuantidade * novoValor;
            itemAtualizado.setValorTotal(novoValorTotalItem);

            DefaultTableModel model = (DefaultTableModel) jTableAdicionarLista.getModel();
            model.setValueAt(novoProduto, selectedRow, 0);
            model.setValueAt(novaMarca, selectedRow, 1);
            model.setValueAt(novaQuantidade, selectedRow, 2);
            model.setValueAt(novoValor, selectedRow, 3);

            displayedItensList.set(selectedRow, itemAtualizado);

            calcularTotais();

            int idItem = displayedItensList.get(selectedRow).getIdItens();
            ItensDAO itensDAO = new ItensDAO();
            itensDAO.atualizarNomeProduto(idItem, novoProduto);

            TXTProduto.setText("");
            TXTMarca.setText("");
            TXTQuantidade.setText("");
            TXTValor.setText("");

            JOptionPane.showMessageDialog(this, "Linha atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma linha na tabela para atualizar.", "Linha não selecionada", JOptionPane.WARNING_MESSAGE);
        }
        PreencherTabelaListas();
    }//GEN-LAST:event_ButtonAtualizarMousePressed

    private void jButtonRemoverListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverListaActionPerformed
        int selectedRow = jTableAdicionarLista.getSelectedRow();

        if (selectedRow >= 0) {
            if (jButtonSalvar.isVisible()) {
                DefaultTableModel model = (DefaultTableModel) jTableAdicionarLista.getModel();
                model.removeRow(selectedRow);
            } else {
                Itens itemRemover = displayedItensList.get(selectedRow);
                int idItem = itemRemover.getIdItens();

                ListaHasItensDAO listaHasItensDAO = new ListaHasItensDAO();
                listaHasItensDAO.removerItem(selectedListaId, idItem); // Desassocia o item da lista
                listaHasItensDAO.desassociarItemDeLista(selectedListaId, idItem); // Remove a associação do item com a lista

                displayedItensList.remove(selectedRow);
                DefaultTableModel model = (DefaultTableModel) jTableAdicionarLista.getModel();
                model.removeRow(selectedRow);

                calcularTotais();
                ItensDAO itensDAO = new ItensDAO();
                itensDAO.removerItem(idItem);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma linha na tabela para remover o item.", "Linha não selecionada", JOptionPane.WARNING_MESSAGE);
        }
        PreencherTabelaListas();
    }//GEN-LAST:event_jButtonRemoverListaActionPerformed

    private void jButtonAdicionarLinhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarLinhaActionPerformed
        String produto = TXTProduto.getText();
        String marca = TXTMarca.getText();
        int quantidade = Integer.parseInt(TXTQuantidade.getText());
        double valor = Double.parseDouble(TXTValor.getText());

        if (jButtonSalvar.isVisible()) {
            DefaultTableModel model = (DefaultTableModel) jTableAdicionarLista.getModel();
            model.addRow(new Object[]{produto, marca, quantidade, valor});
        } else {
            ItensDAO itensDAO = new ItensDAO();
            int idItem = itensDAO.inserirItem(produto, marca, quantidade, valor);

            ListaHasItensDAO listaHasItensDAO = new ListaHasItensDAO();
            listaHasItensDAO.associarItemALista(selectedListaId, idItem);

            Itens novoItem = new Itens();
            novoItem.setIdItens(idItem);
            novoItem.setNome_Produto(produto);
            novoItem.setMarca(marca);
            novoItem.setQuantidade(quantidade);
            novoItem.setValor_Produto(valor);
            novoItem.setValorTotal(quantidade * valor);

            displayedItensList.add(novoItem);

            calcularTotais();

        }
        TXTProduto.setText("");
        TXTMarca.setText("");
        TXTQuantidade.setText("");
        TXTValor.setText("");
        PreencherTabelaListas();
    }//GEN-LAST:event_jButtonAdicionarLinhaActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        String nomeLista = TXTNomeLista.getText();

        System.out.println("Nome da lista: " + nomeLista);

        ListaDAO listaDAO = new ListaDAO();
        if (listaDAO.verificarExistenciaListaPorUsuario(idUsuario, nomeLista)) {
            JOptionPane.showMessageDialog(this, "O nome da lista já está em uso. Por favor, escolha outro nome.", "Nome da Lista Repetido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Lista novaLista = new Lista();
        novaLista.setUsuario_idUsuario(idUsuario);
        novaLista.setNome_Lista(nomeLista);
        novaLista.setQuantidade_Total(0);
        novaLista.setValor_Total(0.0);
        novaLista.setStatus("pendente");

        System.out.println("ID do usuário ao salvar a lista: " + novaLista.getUsuario_idUsuario());
        int idLista = listaDAO.criarLista(novaLista);

        List<Itens> itensList = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) jTableAdicionarLista.getModel();
        
        for (int i = 0; i < model.getRowCount(); i++) {
            String produto = model.getValueAt(i, 0).toString();
            String marca = model.getValueAt(i, 1).toString();
            int quantidade = Integer.parseInt(model.getValueAt(i, 2).toString());
            double valor = Double.parseDouble(model.getValueAt(i, 3).toString());

            ItensDAO itensDAO = new ItensDAO();
            int idItem = itensDAO.criarItem(produto, marca, quantidade, valor);

            Itens item = new Itens();
            item.setIdItens(idItem);
            item.setLista_idLista(idLista);
            item.setNome_Produto(produto);
            item.setMarca(marca);
            item.setQuantidade(quantidade);
            item.setValor_Produto(valor);
            itensList.add(item);
        }

        ListaHasItensDAO listaHasItensDAO = new ListaHasItensDAO();
        for (Itens item : itensList) {
            listaHasItensDAO.Insert(idLista, item.getIdItens());
        }
        int quantidadeTotal = 0;
        double valorTotal = 0.0;

        for (Itens item : itensList) {
            quantidadeTotal += item.getQuantidade();
            valorTotal += item.getQuantidade() * item.getValor_Produto();
        }
        listaDAO.atualizarQuantidadeValor(idLista, quantidadeTotal, valorTotal);
        PreencherTabelaListas();
        JOptionPane.showMessageDialog(this, "Lista salva com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jTableAdicionarListaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAdicionarListaMousePressed
        int row = jTableAdicionarLista.rowAtPoint(evt.getPoint());
        if (row >= 0) {
            String produto = jTableAdicionarLista.getValueAt(row, 0).toString();
            String marca = jTableAdicionarLista.getValueAt(row, 1).toString();
            String quantidade = jTableAdicionarLista.getValueAt(row, 2).toString();
            String valor = jTableAdicionarLista.getValueAt(row, 3).toString();

            TXTProduto.setText(produto);
            TXTMarca.setText(marca);
            TXTQuantidade.setText(quantidade);
            TXTValor.setText(valor);
        }
        PreencherTabelaListas();
    }//GEN-LAST:event_jTableAdicionarListaMousePressed
//==============================================================================\\



//                         jPanel "TodasListas"                                 \\   
    private void PreencherTabelaListas() {
        System.out.println("PreencherTabelaListas - idUsuario: " + idUsuario);

        List<Lista> listasDoUsuario = new ListaDAO().getListasPorUsuario(idUsuario);

        if (listasDoUsuario == null) {
            System.out.println("PreencherTabelaListas - listasDoUsuario é nulo");
        } else {
            System.out.println("PreencherTabelaListas - Quantidade de listas: " + listasDoUsuario.size());
        }

        exibirListasNaTabela(listasDoUsuario);
    }

    private void exibirListasNaTabela(List<Lista> listas) {
        DefaultTableModel model = (DefaultTableModel) jTableListas.getModel();
        model.setRowCount(0);

        for (Lista lista : listas) {
            Object[] row = {lista.getNome_Lista(), lista.getQuantidade_Total(), lista.getValor_Total(), lista.getStatus()};
            model.addRow(row);
        }

    }

    private List<Lista> filtrarListasPorStatus(String status, List<Lista> listas) {
        List<Lista> listasFiltradas = new ArrayList<>();

        for (Lista lista : listas) {
            if (lista.getStatus().equals(status)) {
                listasFiltradas.add(lista);
            }
        }

        return listasFiltradas;
    }

    private List<Lista> filtrarListasPorTextoBusca(String textoBusca, List<Lista> listas) {
        List<Lista> listasFiltradas = new ArrayList<>();

        for (Lista lista : listas) {
            if (lista.getNome_Lista().toLowerCase().contains(textoBusca.toLowerCase())) {
                listasFiltradas.add(lista);
            }
        }

        return listasFiltradas;
    }

    private void TXT_ProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_ProcurarActionPerformed
        String textoBusca = TXT_Procurar.getText();
        List<Lista> listasDoUsuario = new ListaDAO().getListasPorUsuario(idUsuario);
        List<Lista> listasFiltradas = filtrarListasPorTextoBusca(textoBusca, listasDoUsuario);
        exibirListasNaTabela(listasFiltradas);
    }//GEN-LAST:event_TXT_ProcurarActionPerformed

    private void jButtonCompletadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCompletadosActionPerformed
        List<Lista> listasDoUsuario = new ListaDAO().getListasPorUsuario(idUsuario);
        List<Lista> listasFiltradas = filtrarListasPorStatus("Completada", listasDoUsuario);
        exibirListasNaTabela(listasFiltradas);
    }//GEN-LAST:event_jButtonCompletadosActionPerformed

    private void jButtonPendentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPendentesActionPerformed
        List<Lista> listasDoUsuario = new ListaDAO().getListasPorUsuario(idUsuario);
        List<Lista> listasFiltradas = filtrarListasPorStatus("Pendente", listasDoUsuario);
        exibirListasNaTabela(listasFiltradas);
    }//GEN-LAST:event_jButtonPendentesActionPerformed

    private void jButtonCanceladosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCanceladosActionPerformed
        List<Lista> listasDoUsuario = new ListaDAO().getListasPorUsuario(idUsuario);
        List<Lista> listasFiltradas = filtrarListasPorStatus("Cancelada", listasDoUsuario);
        exibirListasNaTabela(listasFiltradas);
    }//GEN-LAST:event_jButtonCanceladosActionPerformed
//==============================================================================\\



//                         jPanel "InformaçõesConta"                            \\ 
    private void preencherCamposUsuario(Usuario usuario) {
        TXT_NomeDeUsuario.setText(usuario.getNomeDeUsuario());
        TXT_Celular.setText(usuario.getCelular());
        TXT_Email.setText(usuario.getEmail());
    }

    private void jButtonAtualizarDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizarDadosActionPerformed
        String novoNomeDeUsuario = TXT_NomeDeUsuario.getText();
        String novoCelular = TXT_AtualizarCelular.getText();
        String novoEmail = TXT_AtualizarEmail.getText();
        String novaSenha = TXT_AtualizarSenha.getText();
        String email = TXT_Email.getText();
        /*
        System.out.println("Novo nome de usuário: " + novoNomeDeUsuario);
        System.out.println("Novo celular: " + novoCelular);
        System.out.println("Novo email: " + novoEmail);
        System.out.println("Nova senha: " + novaSenha);
        System.out.println("Email atual: " + email);
         */
        if (novoEmail.equals(email)) {
            JOptionPane.showMessageDialog(this, "O novo email não pode ser igual ao atual.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!novoEmail.contains("@")) {
            JOptionPane.showMessageDialog(this, "O novo email deve conter o símbolo '@'.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!novoCelular.isEmpty() && !novoCelular.matches("\\d{11}")) {
            JOptionPane.showMessageDialog(this, "O novo número de celular deve conter exatamente 11 dígitos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setIdUsuario(idUsuario);
        usuarioAtualizado.setNomeDeUsuario(novoNomeDeUsuario);
        usuarioAtualizado.setEmail(novoEmail);

        if (!novaSenha.isEmpty()) {
            usuarioAtualizado.setSenha(novaSenha);
        } else {
            Usuario usuarioNoBanco = usuarioDAO.obterUsuarioPorId(idUsuario);
            usuarioAtualizado.setSenha(usuarioNoBanco.getSenha());
        }

        if (!novoCelular.isEmpty()) {
            usuarioAtualizado.setCelular(novoCelular);
        } else {
            Usuario usuarioNoBanco = usuarioDAO.obterUsuarioPorId(idUsuario);
            usuarioAtualizado.setCelular(usuarioNoBanco.getCelular());
        }

        usuarioDAO.atualizarDadosUsuario(usuarioAtualizado);

        TXT_AtualizarCelular.setText("");
        TXT_AtualizarEmail.setText("");
        TXT_AtualizarSenha.setText("");

        preencherCamposUsuario(usuarioAtualizado);
        JOptionPane.showMessageDialog(this, "Dados do usuário atualizados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButtonAtualizarDadosActionPerformed

    private void jButtonDelDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelDadosActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Tem certeza de que deseja excluir suas listas?", "Confirmação de Exclusão de Listas", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            ListaDAO listaDAO = new ListaDAO();
            if (listaDAO.excluirListasDoUsuario(idUsuario)) {
                JOptionPane.showMessageDialog(this, "Suas listas foram excluídas com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir suas listas.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonDelDadosActionPerformed

    private void jButtonDelContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelContaActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Tem certeza de que deseja excluir sua conta? Essa ação é irreversível.", "Confirmação de Exclusão de Conta", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            if (usuarioDAO.excluirListasDoUsuario(idUsuario)) {
                if (usuarioDAO.excluirContaUsuario(idUsuario)) {
                    JOptionPane.showMessageDialog(this, "Sua conta foi excluída com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    
                    new View_Login().setVisible(true);
                    this.dispose();

                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir sua conta.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir suas listas.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonDelContaActionPerformed
//==============================================================================\\

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
            java.util.logging.Logger.getLogger(View_Home1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View_Home1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View_Home1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View_Home1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new View_Home1().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AdicionarLista;
    private javax.swing.JPanel Background;
    private javax.swing.JPanel BackgroundConteudo;
    private javax.swing.JLabel ButtonAtualizar;
    private javax.swing.JLabel IconHome;
    private javax.swing.JPanel InformaçõesConta;
    private javax.swing.JPanel PnlCard;
    private javax.swing.JTextField TXTMarca;
    private javax.swing.JTextField TXTNomeLista;
    private javax.swing.JTextField TXTProduto;
    private javax.swing.JTextField TXTQuantidade;
    private javax.swing.JTextField TXTValor;
    private javax.swing.JTextField TXT_AtualizarCelular;
    private javax.swing.JTextField TXT_AtualizarEmail;
    private javax.swing.JPasswordField TXT_AtualizarSenha;
    private javax.swing.JTextField TXT_Celular;
    private javax.swing.JTextField TXT_Email;
    private javax.swing.JTextField TXT_NomeDeUsuario;
    private javax.swing.JTextField TXT_Procurar;
    private javax.swing.JLabel TitleAtualizarCelular;
    private javax.swing.JLabel TitleAtualizarEmail;
    private javax.swing.JLabel TitleAtualizarSenha;
    private javax.swing.JLabel TitleCelular;
    private javax.swing.JLabel TitleEmail;
    private javax.swing.JLabel TitleNomeDaLista;
    private javax.swing.JLabel TitleNomeDeUsuario;
    private javax.swing.JLabel TitleNomeMarca;
    private javax.swing.JLabel TitleNomeProduto;
    private javax.swing.JLabel TitleNomeQuantidade;
    private javax.swing.JLabel TitleNomeValor;
    private javax.swing.JPanel TodasListas;
    private javax.swing.JButton jButtonAdicionarLinha;
    private javax.swing.JButton jButtonAdiconarLista;
    private javax.swing.JButton jButtonAtualizarDados;
    private javax.swing.JButton jButtonCancelados;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonCompletados;
    private javax.swing.JButton jButtonCompletar;
    private javax.swing.JButton jButtonConta;
    private javax.swing.JButton jButtonDelConta;
    private javax.swing.JButton jButtonDelDados;
    private javax.swing.JButton jButtonPendente;
    private javax.swing.JButton jButtonPendentes;
    private javax.swing.JButton jButtonRemoverLista;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonVerificar;
    private javax.swing.JButton jButton_irListas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableAdicionarLista;
    private javax.swing.JTable jTableListas;
    // End of variables declaration//GEN-END:variables
}
