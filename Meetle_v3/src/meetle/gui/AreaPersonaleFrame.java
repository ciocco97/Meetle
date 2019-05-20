package meetle.gui;

import java.awt.Component;
import java.util.ArrayList;
import meetle.Meetle;
import meetle.eventi.Evento;
import meetle.utenti.Notifica;

public class AreaPersonaleFrame extends javax.swing.JFrame {

    private static final int ISCRIZIONI = 0, CREAZIONI = 1, NOTIFICHE = 2;

    private final String uID;

    public AreaPersonaleFrame() {
        uID = Meetle.getIstanza().getUtenteLoggatoID();
        
        initComponents();
        jButton1.setVisible(false);
        jLabelTitolo.setText(uID);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        
        inizializza();
        aggiorna();
    }

    public void inizializza() {
        jPanelMadre.removeAll();
        jPanelMadre.repaint();
        jPanelMadre.validate();
        
        switch (selettore.getSelectedIndex()) {
            case ISCRIZIONI: {
                ArrayList<Evento> l = Meetle.getIstanza().getBacheca().getEventiByIscrittoID(uID);
                for (Evento e : l) 
                    jPanelMadre.add(new EventoPanel(e.getID(), EventoPanel.POS_ISCRIZIONI));
                
                break;
            }
            case CREAZIONI: {
                ArrayList<Evento> l = Meetle.getIstanza().getBacheca().getEventiByCreatoreID(uID);
                for (Evento e : l) 
                    jPanelMadre.add(new EventoPanel(e.getID(), EventoPanel.POS_POSSEDUTI));
                
//                    Meetle.getIstanza().getBacheca().getEventiByCreatoreID(uID).stream()
//                            .forEach((e) -> { jPanelMadre.add(new EventoPanel(e.getID(), EventoPanel.MODE_MODIFICA)); });
                break;
            }
            case NOTIFICHE:
                for (Notifica n : Meetle.getIstanza().getNotifiche()) {
                    jPanelMadre.add(new NotificaPanel(n));
                }
                break;
        }
    }
    int i=0;
    public void aggiorna() {
        Component componenti[] = jPanelMadre.getComponents();
//        System.out.println("Agggiorna -> comp.lenght: " + comp.length);
        for (Component componente : componenti) {
            try {
                ((EventoPanel) componente).aggiorna();
                System.out.println(i++);
            } catch (Exception e) {}
        }
        jPanelMadre.validate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanelHeader = new javax.swing.JPanel();
        jLabelTitolo = new javax.swing.JLabel();
        selettore = new javax.swing.JComboBox<>();
        jScrollPane = new javax.swing.JScrollPane();
        jPanelMadre = new javax.swing.JPanel();

        jButton1.setText("Torna a bacheca");

        setTitle("Area Utente");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(getMinimumSize());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanelHeader.setBackground(new java.awt.Color(0, 115, 150));
        jPanelHeader.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        jLabelTitolo.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        jLabelTitolo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitolo.setText("AreaPersonale");

        selettore.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Eventi a cui sei iscritto", "Eventi creati da te", "Le tue notifiche" }));
        selettore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selettoreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelHeaderLayout = new javax.swing.GroupLayout(jPanelHeader);
        jPanelHeader.setLayout(jPanelHeaderLayout);
        jPanelHeaderLayout.setHorizontalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitolo)
                    .addComponent(selettore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelHeaderLayout.setVerticalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitolo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selettore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelHeader, java.awt.BorderLayout.NORTH);

        jScrollPane.setBorder(null);
        jScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanelMadre.setBackground(new java.awt.Color(0, 150, 155));
        jPanelMadre.setLayout(new javax.swing.BoxLayout(jPanelMadre, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane.setViewportView(jPanelMadre);

        getContentPane().add(jScrollPane, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void selettoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selettoreActionPerformed
        inizializza();
        aggiorna();
    }//GEN-LAST:event_selettoreActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Meetle.getIstanza().mostraBacheca();
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabelTitolo;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelMadre;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JComboBox<String> selettore;
    // End of variables declaration//GEN-END:variables
}
