package evolalgo.adultselectors;

import evolalgo.IIndividual;
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
    public static List<IIndividual> growPopulation(List<IIndividual> population){
        for(IIndividual i: population){
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
    public static List<IIndividual> selectBestFit(List<IIndividual> population, 
            int adultSpots) throws Exception{
        List<IIndividual> newPopulation = new ArrayList<IIndividual>();
        //Loop through and pick best candidates
        //List<IIndividual> populationcopy = new ArrayList<IIndividual>(population);
        while(newPopulation.size() < adultSpots){
            IIndividual highestIndividual = null;
            for(IIndividual i: population){
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
    public static int[] findAdultChildRatio(List<IIndividual> population){        
        int countChildren = 0;
        int countAdults = 0;
        for(IIndividual i: population){
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