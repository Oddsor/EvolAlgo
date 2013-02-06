
package adultselectors;

import evolalgo.IAdultSelection;
import evolalgo.IIndividual;
import java.util.List;

/**
 * This selection protocol mixes the best of the children with the best of the adults
 * @author Odd
 */
public class GenerationalMixing extends AdultSelectionImpl implements IAdultSelection{
    
    private int adultSpots;
    
    public GenerationalMixing(int adultSpots){
        this.adultSpots = adultSpots;
    }
    
    @Override
    public List<IIndividual> getAdults(List<IIndividual> population) throws Exception{
        if(population.size() < adultSpots){
            //TODO remove this exception?
            throw new Exception("Population count equal to number of adult spots." 
                    + "Spots: " + adultSpots + ", population: " + population.size());
        }
        //Use standard selection method
        return selectBestFit(population, adultSpots);
    }
}
