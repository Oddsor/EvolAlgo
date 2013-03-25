/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evoalgo.problem.ctrnn.statisticscripts;

import evoalgo.problem.ctrnn.trackerSim.HitAwarder;
import evoalgo.problem.ctrnn.trackerSim.Simulation;
import evolalgo.Evolution;
import evolalgo.IIndividual;
import evolalgo.problem.IProblem;
import evolalgo.problem.ctrnn.CtrnnProblem;
import evolalgo.problem.ctrnn.ITracker;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Oddsor
 */
public class CTRNNStatisticThread extends Thread {
    int GENERATIONS;
    int POPULATION;
    int INDIVIDUALS=5;
    int SIMULATIONRUNS=20;
    Evolution evo;
    IProblem problem;
    
    public CTRNNStatisticThread(int GENERATIONS, int POPULATION, Evolution evo, IProblem problem){
        this.GENERATIONS = GENERATIONS;
        this.POPULATION = POPULATION;
        this.evo = evo;
        this.problem = problem;
    }
    
    @Override
    public void run() {

    	System.out.println("Starting statistics run");
    	long time = System.currentTimeMillis();
    	List<IIndividual> indv = new ArrayList<IIndividual>();
    
    	for(int k = 0; k < INDIVIDUALS; k++){
    	
    		System.out.println("Evolving tracker "+(k+1));
    		List<IIndividual> pop = problem.createPopulation(POPULATION);
        
        	for (int j = 0; j < GENERATIONS; j++){
        	if(j%10 ==0) System.out.println("Ping from generation "+ j+" evolving tracker "+(k+1));
            try{
                pop = evo.runGeneration(pop);
                Map m = evo.getStatistics().get(evo.getStatistics().size() - 1);
                double max = Double.parseDouble(m.get("maxFitness").toString());
                
                if(max == 1.0){
                	System.out.println("Fitness of 1.0 reached after "+j+" generations");
                	break; //No need to continue?
                }
            }catch(Exception e){
//                e.printStackTrace();
            }

        	}
        	
        	List<Map> stats = evo.getStatistics();
        	IIndividual temp = (IIndividual) stats.get(stats.size()-1).get("bestIndividual");
        	indv.add(temp);
        	
        	try {
				System.out.println("Tracker number "+k+" evolved a fitness of "+temp.fitness());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	 long time2 = System.currentTimeMillis()-time;
             System.out.println("Run time so far: "+((time2/1000)/60)+" minutes and "+ ((time2/1000)%60)+" seconds");
        }
     
    	double totalScore=0;
    	System.out.println("Running "+SIMULATIONRUNS+" simulations per tracker");
    	int c = 0;
    	for (IIndividual iIndividual : indv) {
    		System.out.println("Simulating tracker "+c++);
    		double score = 0;
    		for (int i = 0; i < SIMULATIONRUNS; i++) {
    		   ITracker tr = (ITracker) iIndividual.phenotype();
    		   Simulation sim = new Simulation();
    		   CtrnnProblem ct = (CtrnnProblem) problem;
    		   score += sim.simulate(tr, new HitAwarder(), null, ct.getObjectType());
		}
    		totalScore += score/SIMULATIONRUNS;
	} 
       double percentScore = totalScore/indv.size();
       System.out.println("Total score of "+INDIVIDUALS+ " individuals after "+SIMULATIONRUNS+" Simualtions :\n"+percentScore );
       time -= System.currentTimeMillis();
       System.out.println("Total run time: "+((-time/1000)/60)+" minutes and "+ ((-time/1000)%60)+" seconds");
    }
    
}
