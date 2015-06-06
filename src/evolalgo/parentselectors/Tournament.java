package evolalgo.parentselectors;

import evolalgo.Individual;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Tournament selection finds the strongest parent in a sub-group of the population
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
    public Individual getParent(List<Individual> population) throws Exception{
        Random rand = new Random();
        List<Individual> local = new ArrayList<Individual>();
        Individual best = null;
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
        if(chanceBestPick >= Math.random()){
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
