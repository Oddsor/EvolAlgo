/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolalgo.parentselectors;

import evolalgo.IIndividual;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Finds the strongest parent in a sub-group of the population
 * @author Odd
 */
public class Tournament implements IParentSelection{
    
    private int tournamentSize = 10;
    private double chanceBestPick = 0.3;
    
    public Tournament(int tournamentSize, double chanceBestPick){
        this.tournamentSize = tournamentSize;
        this.chanceBestPick = chanceBestPick;
    }
    
    @Override
    public IIndividual getParent(List<IIndividual> population) throws Exception{
        Random rand = new Random();
        List<IIndividual> local = new ArrayList<IIndividual>();
        IIndividual best = null;
        //Form the sample group
        while(local.size() < tournamentSize){
            int random = rand.nextInt(population.size());
            if(!local.contains(population.get(random))){
                local.add(population.get(random));
                if(best == null) best = population.get(random);
                else if(best.fitness() < population.get(random).fitness()){
                    best = population.get(random);
                }
            }
        }
        //When group is full, roll to check if we're picking the best
        if((1.0-chanceBestPick) <= Math.random()){
            return best;
        }else{
            while(true){
                if(!local.get(rand.nextInt(local.size())).equals(best)){
                    return local.get(rand.nextInt(local.size()));
                }
            }
        }
    }
}
