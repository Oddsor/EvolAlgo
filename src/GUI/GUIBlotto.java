package GUI;


import evolalgo.adultselectors.FullGenReplacement;
import evolalgo.adultselectors.GenerationalMixing;
import evolalgo.adultselectors.OverProduction;
import evolalgo.Evolution;
import evolalgo.adultselectors.IAdultSelection;
import evolalgo.IIndividual;
import evolalgo.parentselectors.IParentSelection;
import evolalgo.problem.IProblem;
import evolalgo.IReproduction;
import evolalgo.problem.BlottoStrats;
import evolalgo.ReproductionImpl;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.math.plot.Plot2DPanel;
import evolalgo.parentselectors.FitnessProportionate;
import evolalgo.parentselectors.SigmaScaling;
import evolalgo.parentselectors.Tournament;

/**
 * The GUI for blotto strategies.
 * @author Odd
 * @deprecated Run EvoGUI instead
 */
@Deprecated
public class GUIBlotto extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    public GUIBlotto() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        outputScreen = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        generations = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        popSize = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        mutaRate = new javax.swing.JTextField();
        recombRate = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        bitStringSize = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        AdultSelectBox = new javax.swing.JComboBox();
        ParentSelectionBox = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        numChildren = new javax.swing.JTextField();
        StartButton = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        adultSpots = new javax.swing.JTextField();
        clearButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lossFraction = new javax.swing.JTextField();
        redeploymentRate = new javax.swing.JTextField();
        recombSplit = new javax.swing.JSlider();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        outputScreen.setColumns(20);
        outputScreen.setRows(5);
        jScrollPane1.setViewportView(outputScreen);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("EA Project 1b: Blotto Strategies");

        jLabel2.setText("Number of generations:");

        generations.setText("100");
        generations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generationsActionPerformed(evt);
            }
        });

        jLabel3.setText("Population size:");

        popSize.setText("50");

        jLabel4.setText("Mutation rate:");

        mutaRate.setText("0.05");
        mutaRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mutaRateActionPerformed(evt);
            }
        });

        recombRate.setText("0.05");

        jLabel5.setText("Recombination rate:");

        jLabel7.setText("# Battles:");

        bitStringSize.setText("10");

        jLabel8.setText("Adult Selection Method:");

        jLabel9.setText("Parent Selection Method:");

        AdultSelectBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Full gen replacement", "Gen mixing", "Over-production" }));
        AdultSelectBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdultSelectBoxActionPerformed(evt);
            }
        });

        ParentSelectionBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FitnessProportionate", "SigmaScaling", "Tournament" }));

        jLabel10.setText("#children (not in full gen):");

        numChildren.setText("50");
        numChildren.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numChildrenActionPerformed(evt);
            }
        });

        StartButton.setText("Start EvoLoop");
        StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButtonActionPerformed(evt);
            }
        });

        jLabel12.setText("Adult spots (gen mix):");

        adultSpots.setText("40");

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        jLabel6.setText("Redeployment Rate:");

        jLabel11.setText("Loss fraction:");

        lossFraction.setText("0.1");

        redeploymentRate.setText("1.0");

        recombSplit.setMaximum(2);
        recombSplit.setMinimum(1);

        jLabel13.setText("Recombination Split (1-2)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(generations, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mutaRate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                                    .addComponent(popSize, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(recombRate, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addComponent(recombSplit, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(44, 44, 44)
                                .addComponent(adultSpots, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(clearButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(StartButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(25, 25, 25)
                                .addComponent(numChildren))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ParentSelectionBox, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(AdultSelectBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(bitStringSize, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel11))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lossFraction, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                                            .addComponent(redeploymentRate))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(generations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(bitStringSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(popSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(redeploymentRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(mutaRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lossFraction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recombRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(AdultSelectBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(ParentSelectionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(recombSplit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(numChildren, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(adultSpots, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clearButton)
                            .addComponent(StartButton)))
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generationsActionPerformed
        
    }//GEN-LAST:event_generationsActionPerformed

    private void mutaRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mutaRateActionPerformed
        
    }//GEN-LAST:event_mutaRateActionPerformed

    private void numChildrenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numChildrenActionPerformed
        
    }//GEN-LAST:event_numChildrenActionPerformed

    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartButtonActionPerformed
        //Selecting a development method (only 1 so far)
        IProblem problem = new BlottoStrats(Integer.parseInt(bitStringSize.getText()), 
                Double.parseDouble(redeploymentRate.getText()), 
                Double.parseDouble(lossFraction.getText()));
        List<IIndividual> individuals = problem.createPopulation(Integer.parseInt(popSize.getText()));
        
        //Creating reproduction mechanism
        IReproduction reproductor;
        try{
            reproductor = new ReproductionImpl(
                    Double.parseDouble(mutaRate.getText()), 
                    Double.parseDouble(recombRate.getText()), 
                    recombSplit.getValue());
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return;
        }
        IAdultSelection adSel = null;
        int inumChildren = Integer.parseInt(numChildren.getText());
        //Selecting an adult selector (3 to pick from)
        if(AdultSelectBox.getSelectedItem().toString().equals("Full gen replacement")){
             adSel = new FullGenReplacement();
            //Full gen requires equal amount of adults and children
            inumChildren = Integer.parseInt(popSize.getText());
        }else if(AdultSelectBox.getSelectedItem().toString().equals("Gen mixing")){
            adSel = new GenerationalMixing(Integer.parseInt(adultSpots.getText()));
        }else if(AdultSelectBox.getSelectedItem().toString().equals("Over-production")){
            //adSel = new OverProduction();
        }        
        IParentSelection parSel = null;
        //Selecting a parent selector
        if(ParentSelectionBox.getSelectedItem().toString().equals("FitnessProportionate")){
            parSel = new FitnessProportionate();
        }else if(ParentSelectionBox.getSelectedItem().toString().equals("SigmaScaling")){
            parSel = new SigmaScaling();
        }else if(ParentSelectionBox.getSelectedItem().toString().equals("Tournament")){
            parSel = new Tournament(10, 0.3);
        }
        
        Evolution evoLoop = new Evolution(
                inumChildren, 
                reproductor, adSel, parSel, problem);
        int numGenerations = Integer.parseInt(generations.getText());
        double[] avgEntropy = new double[numGenerations];
        double[] stdDev = new double[numGenerations];
        try {
            for (int i = 0; i < numGenerations; i++){
                individuals = evoLoop.runGeneration(individuals);
                double[] fitnesses = new double[Integer.parseInt(popSize.getText())];
                int numParents = 0;
                for(IIndividual ind: individuals){
                    if(ind.age() > 0){
                        fitnesses[numParents] = ind.fitness();
                        String pheno = ind.phenotype().toString().trim();
                        String[] phenosplit = pheno.split(";");
                        double sum = 0.0;
                        for (int j = 0; j < phenosplit.length; j++){
                            phenosplit[j] = phenosplit[j].replace(',', '.');
                            double value = Double.parseDouble(phenosplit[j].trim());
                            if (value != 0.0){
                                double logcalc = (value * (Math.log(value)/Math.log(2.0)));
                                sum -= logcalc;
                            }
                        }
                        
                        avgEntropy[i] += sum;
                        numParents++;
                    }
                }
                avgEntropy[i] = avgEntropy[i] / (double)numParents;
                //stdDev[i] = StandardDeviation.StandardDeviationMean(fitnesses);
            }
            
        }catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        List<Map> statistics = evoLoop.getStatistics();
        
        //outputScreen.setText(EvolAlgo.evolAlgo(input).toString());
        String formattedString = "";
        double[] maxfitnessplot = new double[Integer.parseInt(generations.getText())];
        double[] avgfitnessplot = new double[Integer.parseInt(generations.getText())];
        int i = 0;
        for(Map m: statistics){
            formattedString += "Generation:" + (i+1) + "\t Best: " +
                    m.get("bestIndividual").toString() + "\n";
            maxfitnessplot[i] = Double.parseDouble(m.get("maxFitness").toString());
            avgfitnessplot[i] = Double.parseDouble(m.get("avgFitness").toString());
            i++;
        }
        Plot2DPanel plot = new Plot2DPanel();
        plot.addLinePlot("Max fitness", Color.RED, maxfitnessplot);
        plot.addLinePlot("Average fitness", Color.ORANGE, avgfitnessplot);
        plot.addLinePlot("Standard deviation", Color.BLACK, stdDev);
        plot.addLegend("SOUTH");
        Plot2DPanel plot2 = new Plot2DPanel();
        plot2.addLinePlot("Average entropy", Color.BLUE, avgEntropy);
        plot2.addLegend("SOUTH");
        javax.swing.JFrame frame = new javax.swing.JFrame("a plot panel");
        javax.swing.JFrame frame2 = new javax.swing.JFrame("a plot panel");
        frame.setContentPane(plot);
        frame.setSize(500, 400);
        frame.setVisible(true);
        frame2.setContentPane(plot2);
        frame2.setSize(500, 400);
        frame2.setVisible(true);
        outputScreen.setText(formattedString);
    }//GEN-LAST:event_StartButtonActionPerformed

    private void AdultSelectBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdultSelectBoxActionPerformed
        
    }//GEN-LAST:event_AdultSelectBoxActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        outputScreen.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIBlotto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIBlotto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIBlotto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIBlotto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new GUIBlotto().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox AdultSelectBox;
    private javax.swing.JComboBox ParentSelectionBox;
    private javax.swing.JButton StartButton;
    private javax.swing.JTextField adultSpots;
    private javax.swing.JTextField bitStringSize;
    private javax.swing.JButton clearButton;
    private javax.swing.JTextField generations;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField lossFraction;
    private javax.swing.JTextField mutaRate;
    private javax.swing.JTextField numChildren;
    private javax.swing.JTextArea outputScreen;
    private javax.swing.JTextField popSize;
    private javax.swing.JTextField recombRate;
    private javax.swing.JSlider recombSplit;
    private javax.swing.JTextField redeploymentRate;
    // End of variables declaration//GEN-END:variables
}