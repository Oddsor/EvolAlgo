package evolalgo.adultselectors;

import evolalgo.Individual;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for the adult selectors. Contains helpful methods.
 * @author Odd
 */
abstract class AdultSelection{
    
    /**
     * Ages all members of the population by one generation.
     * @param population
     * @return Population list, one generation older
     */
    public static List<Individual> growPopulation(List<Individual> population){
        for(Individual i: population){
            i.growOlder();
        }
        return population;
    }
    
    /**
     * Used in generational mixing and over-production protocols.
     * Only difference is the input, provided by each respective method
     * @param population
     * @return List new population
     */
    public static List<Individual> selectBestFit(List<Individual> population, 
            int adultSpots) throws Exception{
        List<Individual> newPopulation = new ArrayList<Individual>();
        //Loop through and pick best candidates
        //List<IIndividual> populationcopy = new ArrayList<IIndividual>(population);
        while(newPopulation.size() < adultSpots){
            Individual highestIndividual = null;
            for(Individual i: population){
                if(highestIndividual == null){
                    highestIndividual = i;
                }else if(highestIndividual.fitness() < i.fitness()){
                    highestIndividual = i;
                }
            }
            newPopulation.add(highestIndividual);
            population.remove(highestIndividual);
        }
        return newPopulation;
    }
    
    /**
     * Used to find the ratio of adults and children
     * @param population
     * @return int[] Number of children and number of adults
     */
    public static int[] findAdultChildRatio(List<Individual> population){        
        int countChildren = 0;
        int countAdults = 0;
        for(Individual i: population){
            if(i.age() == 0){
                countChildren++;
            }else if(i.age() > 0){
                countAdults++;
            }
        }
        int[] count = {countChildren, countAdults};
        return count;
    }
}