
package evolalgo;

import evolalgo.parentselectors.IParentSelection;
import evolalgo.adultselectors.IAdultSelection;
import evolalgo.problem.IProblem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the evolutionary loop.
 * @author Odd
 */
public class Evolution {
    
    private int numChildren;
    private IAdultSelection adSel;
    private IParentSelection parSel;
    private IProblem problem;
    private IReproduction rep;
    
    List<Map> stats;
    
    public Evolution(int numChildren, IReproduction rep,
            IAdultSelection adSel, IParentSelection parSel, 
            IProblem problem){
        this.rep = rep;
        this.numChildren = numChildren;
        this.adSel = adSel;
        this.parSel = parSel;
        this.problem = problem;
        stats = new ArrayList<Map>();
    }
    
    public void runGeneration(List<IIndividual> individuals) throws Exception{        
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
            throw new Exception("Error replacing generation!", e);
        }
        Map statistics = new HashMap();
        //Find average fitness and highest/lowest
        double countFitness = 0;
        double maxFitness = 0.0;
        double minFitness = 10.0;
        for(IIndividual i: individuals){
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
        }statistics.put("avgFitness", countFitness / individuals.size());
        //Start producing children.
        List<IIndividual> children = new ArrayList<IIndividual>();
        while(children.size() < numChildren){
            List<IIndividual> parents = new ArrayList<IIndividual>();
            //Try selecting parents!
            IIndividual firstParent = parSel.getParent(individuals);
            parents.add(firstParent);
            List<IIndividual> remainingindividuals = new ArrayList<IIndividual>(individuals);
            remainingindividuals.remove(firstParent);
            parents.add(parSel.getParent(remainingindividuals));
            try{
                Object[] newGenes = rep.reproduce(parents);
                for(int k = 0; k < newGenes.length; k++){
                    if(children.size() < numChildren){
                        children.add(new IndividualImpl(newGenes[k]));
                    }
                }
            }catch(Exception e){
                throw new Exception("Error in reproduction?",e);
            }
        }
        for(IIndividual c: children){
            individuals.add(c);
        }
        
        stats.add(statistics);
    }
    
    public void loop(int generations, List<IIndividual> individuals, boolean stop) throws Exception{
        for (int i = 0; i < generations; i++){
            runGeneration(individuals);
            if(stop){
                Map m = stats.get(stats.size() - 1);
                if (Double.parseDouble(m.get("maxFitness").toString()) == 1.0){
                    break;
                }
            }
        }
    }
    
    public List<Map> getStatistics(){
        return stats;
    }
}
