package evolalgo;

import evolalgo.adultselectors.IAdultSelection;
import evolalgo.parentselectors.IParentSelection;
import evolalgo.problem.IProblem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.math.plot.Plot2DPanel;

/**
 * This is the evolutionary loop, which handles the control structure of the
 * Evolutionary Algorithm and glues together the various other modules.
 * 
 * @author Odd Andreas Sørsæther
 * @author Andreas Hagen
 */
public class Evolution {
    public static final int VARIABLE_MUTATION = 1;
    
    private int populationSize;
    private IAdultSelection adSel;
    private IParentSelection parSel;
    private IProblem problem;
    private IReproduction rep;
    private double baseMutationRate;
    private boolean variable_mutation = false;
    
    List<Map> stats;
    
    public Evolution(int populationSize, IReproduction rep,
            IAdultSelection adSel, IParentSelection parSel, 
            IProblem problem, int... options){
        
        this.rep = rep;
        baseMutationRate = rep.getMutationRate();
        this.populationSize = populationSize;
        this.adSel = adSel;
        this.parSel = parSel;
        this.problem = problem;
        stats = new ArrayList<Map>();
        
        if(options != null){
            for (int i = 0; i < options.length; i++){
                if (options[i] == VARIABLE_MUTATION){
                    variable_mutation = true;
                    System.out.println("Variable mutation rate activated");
                }
            }
        }
        
    }
    
    public List<IIndividual> runGeneration(List<IIndividual> individuals) throws Exception{
        
        //Make phenotypes if they don't already exist.
        for(int i = 0; i < individuals.size(); i++){
            problem.developPheno(individuals.get(i));
        }
        
        //Finding fitness
        problem.calculateFitness(individuals);
        //Try replacing the generation
        try{
            individuals = adSel.getAdults(individuals);
        }catch(Exception e){
        	String error = "Error replacing generation using "+adSel.toString();
            throw new Exception(error,e);
        }
        
        Map statistics = fitnessCalculations(individuals);
        //If the option of variable mutation rate is toggled, increase mutation rate
        if(variable_mutation && stats.size() > 10) variableMutation();
        //Start producing children.
        produceChildren(individuals);
        stats.add(statistics);
        return individuals;
        
    }
    
    private void variableMutation(){
    	
        //Check if any max fitness the last few generations has differed greatly. If not increase mutation, if yes then reduce
        HashMap temp = (HashMap) stats.get(stats.size()-1);
        double lastFitness = (Double) temp.get("maxFitness");
        boolean thresholdBreached = false;
        for (int i = 2; i < 6; i++){

            double someFitness = (Double) temp.get("maxFitness");
            if (Math.max(lastFitness, someFitness) - 
                    Math.min(lastFitness, someFitness) > 0.05) thresholdBreached = true;
        }
        if(thresholdBreached){

           if(rep.getMutationRate() - 0.01 > baseMutationRate){
                rep.setMutationRate(baseMutationRate);
                System.out.println("Mutation rate reduced to "+ rep.getMutationRate() +"!");
            }
        }else{
            if(rep.getMutationRate() + 0.01 < 1.0){
                rep.setMutationRate(rep.getMutationRate() + 0.01);
                System.out.println("Mutation rate increased to "+ rep.getMutationRate() +"!");
            }
        }
    }

    public void loop(int generations, boolean stop) throws Exception{
        
        List<IIndividual> individuals = problem.createPopulation(populationSize);
        for (int i = 0; i < generations; i++){
            individuals = runGeneration(individuals);
            if(stop){
                Map m = stats.get(stats.size() - 1);
                if (Double.parseDouble(m.get("maxFitness").toString()) == 1.0){
                  drawBestFitnessPlot();
                	break;
                }
            }
        }
        
    }
    
    public void drawBestFitnessPlot(){
    	double bestOfGenerations[] = new double[stats.size()];
    	for (int i =0; i < stats.size(); i++) {
    		HashMap temp = (HashMap) stats.get(i);
            bestOfGenerations[i] = (Double) temp.get("maxFitness");

                }

        Plot2DPanel plot = new Plot2DPanel();
        plot.addLinePlot("Fitness of best individual", Color.BLUE, bestOfGenerations);
        plot.addLegend("SOUTH");
        javax.swing.JFrame frame = new javax.swing.JFrame("Best of generation");
        frame.setContentPane(plot);
        frame.setSize(500, 400);
        frame.setVisible(true);
    }
    
    private Map fitnessCalculations(List<IIndividual> individuals){
    	Map statistics = new HashMap();
    	//Find average fitness and highest/lowest
    	double countFitness = 0;
    	double maxFitness = 0.0;
    	double minFitness = 10.0;
    	for(IIndividual i: individuals){
    		try {
    			countFitness += i.fitness();
    			if(i.fitness() > maxFitness){
    				maxFitness = i.fitness();
    				statistics.put("maxFitness", maxFitness);
    				statistics.put("bestIndividual", i);
    			}
    			if(i.fitness() < minFitness){
    				minFitness = i.fitness();
    				statistics.put("minFitness", minFitness);
    			}

    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		
    	}
    	statistics.put("avgFitness", countFitness / individuals.size());
    	
    	return statistics;
    }
    
    private void produceChildren(List<IIndividual> individuals) throws Exception{
    	List<IIndividual> children = new ArrayList<IIndividual>();
    	while(children.size() < adSel.getNumberOfChildren(individuals)){
            List<IIndividual> parents = new ArrayList<IIndividual>();
            //Try selecting parents!
            IIndividual firstParent = parSel.getParent(individuals);
            parents.add(firstParent);
            List<IIndividual> otherindividuals = new ArrayList<IIndividual>(individuals);
            otherindividuals.remove(firstParent);
            parents.add(parSel.getParent(otherindividuals));
            try{
                Object[] newGenes = rep.reproduce(parents);
                for(int k = 0; k < newGenes.length; k++){
                    if(children.size() < adSel.getNumberOfChildren(individuals)){ 
                        children.add(new IndividualImpl(newGenes[k]));
                    }
                }
            }catch(Exception e){
                throw new Exception("Error in reproduction?",e);
            }
        }
        individuals.addAll(children);
    }
    
    public List<Map> getStatistics(){
        return stats;
    }
}
