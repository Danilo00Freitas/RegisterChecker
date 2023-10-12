package Frontend;

import Backend.FatorConnect.FatorConnect;
import Backend.FatorConnect.VerifyCnpjRegisterFatorconnet;

import javax.swing.*;

public class ProgressionScreen extends JFrame {
    private int totalIterations;
    private JLabel progressLabel;
    private FatorConnect fatorConnect;

    public ProgressionScreen(FatorConnect fatorConnect){
        this.fatorConnect = fatorConnect;

        setTitle("Progresso da execução");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.progressLabel = new JLabel("Progress: 0 / 0");
        add(progressLabel);
    }

    public void updateProgress(int currentIteration) {
        this.progressLabel.setText("Progress: " + currentIteration + " / " + totalIterations);
    }

    public void setTotalIterations(int totalIterations) {
        this.totalIterations = totalIterations;
        updateProgress(0);
    }

    public void showProgressionScreen(){
        setVisible(true);
    }

    public void closeProgressionScreen(){
        setVisible(false);
    }
}