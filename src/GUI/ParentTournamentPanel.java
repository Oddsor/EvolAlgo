/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Odd
 */
public class ParentTournamentPanel extends javax.swing.JPanel {

    /**
     * Creates new form AdultTournamentGUI
     */
    public ParentTournamentPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tournamentSizeLabel = new javax.swing.JLabel();
        tournamentSizeField = new javax.swing.JTextField();
        pickBestField = new javax.swing.JTextField();
        PickBestLabel = new javax.swing.JLabel();

        tournamentSizeLabel.setText("Tournament Size:");

        tournamentSizeField.setText("10");
        tournamentSizeField.setToolTipText("Set the size of the free-for-all arenas that parents get thrown into during parent selection. Happens multiple times, and parents may be thrown into arenas multiple times.");

        pickBestField.setText("30");

        PickBestLabel.setText("Pick best %:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tournamentSizeLabel)
                    .addComponent(PickBestLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pickBestField, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(tournamentSizeField)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tournamentSizeLabel)
                    .addComponent(tournamentSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pickBestField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PickBestLabel)))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PickBestLabel;
    public javax.swing.JTextField pickBestField;
    public javax.swing.JTextField tournamentSizeField;
    private javax.swing.JLabel tournamentSizeLabel;
    // End of variables declaration//GEN-END:variables
}
