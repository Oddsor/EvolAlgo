
package evolalgo;

import evolalgo.implementations.IndividualImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the evolutionary loop.
 * @author Odd
 */
public class EvolutionaryLoop {
    
    private int numChildren;
    private IAdultSelection adSel;
    private IParentSelection parSel;
    private IProblem devMethod;
    private IFitnessEval fit;
    private IReproduction rep;
    
    public EvolutionaryLoop(int numChildren, IReproduction rep,
            IAdultSelection adSel, IParentSelection parSel, 
            IProblem devMethod, IFitnessEval fit){
        this.rep = rep;
        this.numChildren = numChildren;
        this.adSel = adSel;
        this.parSel = parSel;
        this.devMethod = devMethod;
        this.fit = fit;
    }
    
    public Map loop(List<IIndividual> individuals) throws Exception{        
        //Make phenotypes
        for(int i = 0; i < individuals.size(); i++){
            individuals.get(i).setPhenotype(
                    devMethod.developPheno(individuals.get(i).getGenes()));
        }
        
        //Finding fitness
        individuals = fit.calculateFitness(individuals);
            
        
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
            }
            if(i.fitness() < minFitness){
                minFitness = i.fitness();
                statistics.put("minFitness", minFitness);
            }
        }statistics.put("avgFitness", countFitness / individuals.size());
        
        /*System.out.println("Average fitness: " + (countFitness / individuals.size()) 
                + ". Maximum fitness: " + maxFitness + ". Minimum fitness: " + minFitness);*/
        
        //Start producing children.
        List<IIndividual> children = new ArrayList<IIndividual>();
        //System.out.println("Creating children");
        while(children.size() < numChildren){
            List<IIndividual> parents = new ArrayList<IIndividual>();
            //Try selecting parents!
            IIndividual firstParent = parSel.getParent(individuals);
            boolean different = false;
            IIndividual secondParent;
            while(!different){
                secondParent = 
                        parSel.getParent(individuals);
                if(firstParent.getGenes().toString().equals(
                        secondParent.getGenes().toString())){
                    different = true;
                    parents.add(firstParent);
                    parents.add(secondParent);
                }
            }
            try{
                Object[] newGenes = rep.reproduce(parents);
                for(int k = 0; k < newGenes.length; k++){
                    if(children.size() < numChildren){
                        children.add(new IndividualImpl(newGenes[k]));
                        //System.out.println("New child with genes: " + newGenes[k]);
                    }
                }
            }catch(Exception e){
                throw new Exception("Error in reproduction?",e);
            }
        }
        for(IIndividual c: children){
            individuals.add(c);
        }
        return statistics;
    }
}
