package Frontend;

import Backend.FatorConnect.FatorConnect;
import Backend.FatorConnect.VerifyCnpjRegisterFatorconnet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingWorker;


public class MainScreen extends JFrame {
    private JPanel mainPanel = new JPanel();
    private JFileChooser fileChooser = new JFileChooser();
    private String[] corretoras = {"Am Life", "Assist", "Berkley", "Essor", "ezze", "FatorConnect", "GNDI", "Ituran", "Kovr", "MBM", "Starr"};
    private JComboBox<String> selectCorretora;

    public MainScreen() {
        // Criando a armação principal
        this.setTitle("Verificador de existência de cadastro");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(600, 500); // Ajuste o tamanho conforme necessário
        this.setResizable(false);

        // Usando um layout principal BorderLayout
        this.setLayout(new BorderLayout());

        // Adicionando rótulo "Selecione a planilha" ao painel superior
        JPanel topPanel = new JPanel();
        JLabel labelPlanilha = new JLabel("Selecione a planilha:");
        topPanel.add(labelPlanilha);
        this.add(topPanel, BorderLayout.NORTH);

        // Adicionando o painel principal (com o botão de escolha de arquivo)
        mainPanel.setLayout(new FlowLayout());
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        mainPanel.add(fileChooser);
        this.add(mainPanel, BorderLayout.CENTER);

        // Configurando o painel de configurações com GridBagLayout
        JPanel configPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        // Adicionando rótulo "Selecione a corretora"
        JLabel labelCorretora = new JLabel("Selecione a corretora:");
        configPanel.add(labelCorretora, constraints);

        // Criando combobox com as opções de corretoras
        selectCorretora = new JComboBox<>(corretoras);
        selectCorretora.setPreferredSize(new Dimension(200, 30));
        constraints.gridx = 1;
        configPanel.add(selectCorretora, constraints);

        // Configurando JButton com ActionListener
        JButton iniciarButton = new JButton("Iniciar");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        configPanel.add(iniciarButton, constraints);

        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                var selectedCorretora = selectCorretora.getSelectedItem();
                var filePath = fileChooser.getSelectedFile().getPath();
                System.out.println(selectedCorretora);

                if (selectedCorretora.equals("FatorConnect")) {
                    VerifyCnpjRegisterFatorconnet verifyCnpjRegisterFatorconnet = new VerifyCnpjRegisterFatorconnet();
                    FatorConnect fatorConnect = new FatorConnect(verifyCnpjRegisterFatorconnet);

                    // Crie um SwingWorker para executar o processo em segundo plano
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            fatorConnect.verifyFatorConnect(filePath);
                            return null;
                        }
                        @Override
                        protected void done() {
                            // O código aqui é executado quando o processo em segundo plano é concluído
                            JOptionPane.showMessageDialog(null, "Execução encerrada", "Encerrado", JOptionPane.INFORMATION_MESSAGE);
                        }
                    };

                    worker.execute(); // Inicie o SwingWorker
                }
            }
        });
        this.add(configPanel, BorderLayout.SOUTH);
    }

}
