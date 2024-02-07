/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mytask;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.lang.model.SourceVersion;
import javax.swing.JOptionPane;
import javax.swing.JTextField;




class HardCodedQuoteProvider {
    // quotes to be used if the time is A.M.
    private final String  AM_QUOTES[] = {
        "Be yourself; everyone else is already taken.― Oscar Wilde",
        "A room without books is like a body without a soul. ― Marcus Tullius Cicero",
        "Be the change that you wish to see in the world. ― Mahatma Gandhi",
        "If you tell the truth, you don't have to remember anything. ― Mark Twain",
        "If you want to know what a man's like, take a good look at how he treats his inferiors, not his equals.― J.K. Rowling",
        "To live is the rarest thing in the world. Most people exist, that is all.― Oscar Wilde"
        };
    // quotes to be used if the time is P.M.
    private final String PM_QUOTES[] = {
        "Without music, life would be a mistake. ― Friedrich Nietzsche",
        "Always forgive your enemies, nothing annoys them so much. ― Oscar Wilde",
        "Life isn't about getting and having, it's about giving and being. –Kevin Kruse",
        "Whatever the mind of man can conceive and believe, it can achieve. –Napoleon Hill",
        "Strive not to be a success, but rather to be of value. –Albert Einstein"                          
        }; 

    // the time when a quote was last used is stored in the file
    // the value is 0 if the quote was previously not used
    private Path historyPath = Paths.get("history.txt");

    private long[] lastUsedAM = new long[AM_QUOTES.length];
    private long[] lastUsedPM = new long[PM_QUOTES.length];

    public HardCodedQuoteProvider() throws IOException {
        // history has to be read from the history file to intialize the
        // lastUsed arrays
        readHistory();
    }

     public String getQuote() throws IOException {
        String[] QUOTES;
        long[] lastUsed;
        if (!isPM()) {
            QUOTES = AM_QUOTES;
            lastUsed = lastUsedAM;
        } else {
            QUOTES = PM_QUOTES;
            lastUsed = lastUsedPM;
        }

        String quote;
        while (true) {
            int index = ThreadLocalRandom.current().nextInt(0, QUOTES.length);
            if (lastUsed[index] == 0) {
                lastUsed[index] = System.currentTimeMillis();
                quote = QUOTES[index];
                break;
            }
        } 
        writeHistory();
        return quote;
    }
     
     private void readHistory() throws IOException {
        if (Files.exists(historyPath)){
            List<String> lines =
                Files.readAllLines(historyPath, Charset.defaultCharset());
            int i = 0;
            for (String line: lines) {
                Long time = Long.parseLong(line);
                if (i < lastUsedAM.length) {
                    lastUsedAM[i] = time;
                } else {
                    lastUsedPM[i - lastUsedAM.length] = time;
                }
                i++;
            }
        }
    }
        
     
      private void writeHistory() throws IOException {
        try {
            Files.createFile(historyPath);
        } catch (FileAlreadyExistsException e) {
        }

        List<String> lines = new ArrayList<>();
        for (long time : lastUsedAM) {
            lines.add(Long.toString(time));
        }

        for (long time : lastUsedPM) {
            lines.add(Long.toString(time));
        }

        Files.write(historyPath, lines, Charset.defaultCharset());
    }

    public void clearHistory() throws IOException {
        Arrays.fill(lastUsedAM, 0);
        Arrays.fill(lastUsedPM, 0);
        writeHistory();
    }

    public List<String> getHistory() {
        List<String> history = new ArrayList<>();
        history.addAll(getHistoryFromArrays(AM_QUOTES, lastUsedAM));
        history.addAll(getHistoryFromArrays(PM_QUOTES, lastUsedPM));
        return history;
    }

    private List<String> getHistoryFromArrays(String[] QUOTES,
            long[] lastUsed) {
        List<String> history = new ArrayList<>();
        for (int i = 0; i < QUOTES.length; i++) {
            if (lastUsed[i] != 0) {
                String timestamp =
                    new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                    .format(new Date(lastUsed[i]));
                history.add(QUOTES[i] + " " + timestamp);
            }
        }
        return history;
    }

    private boolean isPM() {
        String currentTimeStamp = getCurrentTimeStamp();
        return currentTimeStamp.substring(currentTimeStamp.length() - 1)
            .equals(""
                    + "PM");
    }

    private String getCurrentTimeStamp() {
        return new SimpleDateFormat("h:mm a").format(new Date());
    }
}



public class mytasks extends javax.swing.JFrame {
        

    public  mytasks () {
        initComponents();
    }
 
    

   

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        home = new javax.swing.JPanel();
        t = new javax.swing.JTextField();
        b = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        home.setBackground(new java.awt.Color(255, 255, 255));
        home.setPreferredSize(new java.awt.Dimension(742, 538));

        t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tActionPerformed(evt);
            }
        });

        b.setText("jButton1");
        b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout homeLayout = new javax.swing.GroupLayout(home);
        home.setLayout(homeLayout);
        homeLayout.setHorizontalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeLayout.createSequentialGroup()
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homeLayout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addComponent(t, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(homeLayout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addComponent(b)))
                .addContainerGap(296, Short.MAX_VALUE))
        );
        homeLayout.setVerticalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeLayout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(t, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(b)
                .addContainerGap(417, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 1026, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tActionPerformed
      
    }//GEN-LAST:event_tActionPerformed

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
   HardCodedQuoteProvider quoteProvider;
    try {
        quoteProvider = new HardCodedQuoteProvider();
        String quote = quoteProvider.getQuote();
        t.setText(quote);
    } catch (IOException ex) {
        // Handle IOException
        ex.printStackTrace();
    }
    }//GEN-LAST:event_bActionPerformed
         
    
    /**
     * @param args the command line arguments
     */
    
     
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mytasks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mytasks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mytasks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mytasks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mytasks().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b;
    private javax.swing.JPanel home;
    private javax.swing.JTextField t;
    // End of variables declaration//GEN-END:variables

   

}


    



