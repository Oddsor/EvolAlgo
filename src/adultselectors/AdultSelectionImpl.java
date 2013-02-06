
package adultselectors;

import evolalgo.IIndividual;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for the adult selectors. Contains helpful methods.
 * @author Odd
 */
abstract class AdultSelectionImpl{
    
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
        //TODO fix this. Broken!
        List<IIndividual> newPopulation = new ArrayList<IIndividual>();
        //Loop through and pick best candidates
        while(newPopulation.size() < adultSpots){
            IIndividual highestIndividual = null;
            for(IIndividual i: population){
                if(highestIndividual == null){
                    highestIndividual = i;
                }else if(highestIndividual.fitness() == i.fitness()){
                    //TODO Two with same fitness, prioritize child?
                }else if(highestIndividual.fitness() < i.fitness() && 
                        !newPopulation.contains(i)){
                    highestIndividual = i;
                }
            }
            newPopulation.add(highestIndividual);
        }
        return growPopulation(newPopulation);
    }
    
    /**
     * Used to find the ratio of adults and children
     * @param population
     * @return double[] Number of children and number of adults
     */
    public static double[] findAdultChildRatio(List<IIndividual> population){        
        double countChildren = 0.0;
        double countAdults = 0.0;
        for(IIndividual i: population){
            if(i.age() == 0){
                countChildren++;
            }else if(i.age() > 0){
                countAdults++;
            }
        }
        double[] count = {countChildren, countAdults};
        return count;
    }
}