/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import evoalgo.problem.ctrnn.trackerSim.HitAndAvoidAwarder;
import evoalgo.problem.ctrnn.trackerSim.HitAwarder;
import evoalgo.problem.ctrnn.trackerSim.Simulation;
import evoalgo.problem.ctrnn.trackerSim.SimulationAnimation;
import evolalgo.Evolution;
import evolalgo.IIndividual;
import evolalgo.problem.IProblem;
import evolalgo.problem.ctrnn.CtrnnProblem;
import evolalgo.problem.ctrnn.ITracker;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import org.math.plot.Plot2DPanel;

/**
 *
 * @author Oddsor
 */
public class CTRNNThread extends Thread {
    int GENERATIONS;
    int POPULATION;
    Evolution evo;
    IProblem problem;
    
    public CTRNNThread(int GENERATIONS, int POPULATION, Evolution evo, IProblem problem){
        this.GENERATIONS = GENERATIONS;
        this.POPULATION = POPULATION;
        this.evo = evo;
        this.problem = problem;
    }
    
    @Override
    public void run() {

        System.out.println("Pop size: " + POPULATION);
        Plot2DPanel plot = new Plot2DPanel();
        double[] Y = new double[GENERATIONS];
//        for (int i = 0; i < GENERATIONS; i++){
//            Y[i] = 0.0;
//        }
        double[] scale = {1.0};
        double[] scale2 = {0.0};
        plot.addLinePlot("Fitness of best individual", Color.BLUE, Y);
        //plot.addScatterPlot("", scale);
        //plot.addScatterPlot("", scale2);
        plot.addLegend("SOUTH");
        javax.swing.JFrame frame = new javax.swing.JFrame("Best of generation");
        frame.setContentPane(plot);
        frame.setSize(500, 400);
        frame.setVisible(true);
        List<IIndividual> pop = problem.createPopulation(POPULATION);
        System.out.println(pop.size());
        for (int j = 0; j < GENERATIONS; j++){
            try{
                pop = evo.runGeneration(pop);
                Map m = evo.getStatistics().get(evo.getStatistics().size() - 1);
                Double max = Double.parseDouble(m.get("maxFitness").toString());
                Y[j] = max;        
               if(max == 1.0) break;
                System.out.println(j + ", " + Y[j]);
            }catch(Exception e){
                e.printStackTrace();
            }
            plot.removeAllPlots();
            //plot.addScatterPlot("", scale);
            //plot.addScatterPlot("", scale2);
            plot.addLinePlot("Fitness of best individual", Color.BLUE, Y);
            //plot.changePlotData(0, Y);
        }
        List<Map> stats = evo.getStatistics();
        evo.drawBestFitnessPlot();
        IIndividual ind = (IIndividual) stats.get(stats.size()-1).get("bestIndividual");
        ITracker tr = (ITracker) ind.phenotype();
        CtrnnProblem ct = (CtrnnProblem) problem;
      
        SimulationAnimation sim = new SimulationAnimation(tr, new HitAwarder(), ct.getObjectType());
        
       
    }
    
}
