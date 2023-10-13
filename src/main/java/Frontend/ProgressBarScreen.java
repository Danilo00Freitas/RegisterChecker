package Frontend;

import javax.swing.*;

public class ProgressBarScreen extends JFrame {
    private JProgressBar progressBar;
    private int max;

    public ProgressBarScreen() {
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);

        add(progressBar);

        setSize(300, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Progresso");
    }

    public void updateProgress(int progress) {
        progressBar.setValue(progress);
    }

    public void setMax(int max){
        progressBar.setMaximum(max);
    }


}


