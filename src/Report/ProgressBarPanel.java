package Report;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ProgressBarPanel extends JPanel{

    JProgressBar progressBar;
    static int NUMLOOPS = 200;
    SwingWorker worker;
    JPanel centerPanel;
    JLabel status;

    public ProgressBarPanel(String text) {

        centerPanel = new JPanel();

        centerPanel.setLayout(new BorderLayout());

        progressBar = new JProgressBar();
        progressBar.setMaximum(NUMLOOPS);
        status = new JLabel(text);


        centerPanel.add(progressBar,BorderLayout.CENTER);
        centerPanel.add(status,BorderLayout.SOUTH);

        add(centerPanel);
    }



    void updateStatus(final int i) {
        Runnable doSetProgressBarValue = new Runnable() {
            public void run() {
                progressBar.setValue(i);
            }
        };
        SwingUtilities.invokeLater(doSetProgressBarValue);
    }

    Object doWork() {
        try {
            for(int i = 0; i < NUMLOOPS; i++) {
                updateStatus(i);
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
                Thread.sleep(50);
            }
        }
        catch (InterruptedException e) {
            updateStatus(0);
            return "Interrupted";  // SwingWorker.get() returns this
        }
        return "All Done";         // or this
    }

    public  void StartWork(final JDialog dialog,final JasperPrint jp){
        FWorker work = new FWorker() {

                public Object construct() {
                    return doWork();
                }
//                @Override
                public void finished() {
                    dialog.dispose();
                    JasperViewer.viewReport(jp,false);
                }
            };
            work.start();
    }

}
