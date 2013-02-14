
package evolalgo.adultselectors;

import evolalgo.IIndividual;
import java.util.List;

/**
 * This selection protocol produces more children than there are adult spots.
 * Not properly implemented yet!
 * @author Odd
 */
public class OverProduction extends AdultSelectionImpl implements IAdultSelection{
    
    private int adultSpots;
    
    public OverProduction(int adultSpots){
        this.adultSpots = adultSpots;
    }
    
    @Override
    public List<IIndividual> getAdults(List<IIndividual> population) 
            throws Exception{
        //TODO test this method
        double[] childAdult = findAdultChildRatio(population);
        if(childAdult[0] <= adultSpots){
            throw new Exception("N children smaller than or equal to number of "
                    + "adult spots! Use replacement method or produce more.");
        }else if(childAdult[0] == 0){
            throw new Exception("Error, no children found!");
        }
        //Kill old people
        for(IIndividual i: population){
            if(i.age() > 0){
                population.remove(i);
            }
        }
        //Use standard selection method after adults are dead
        return selectBestFit(population, adultSpots);
    }
}
