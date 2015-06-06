
package evolalgo.adultselectors;

import evolalgo.Individual;
import java.util.ArrayList;
import java.util.List;

/**
 * This selection protocol produces more children than there are adult spots.
 * Not properly implemented yet!
 * @author Odd
 */
public class OverProduction extends AdultSelection implements IAdultSelection{
    private double overProductionRate;
    
    public OverProduction(double overProductionRate){
    	this.overProductionRate = overProductionRate;
    }
    
    @Override
    public List<Individual> getAdults(List<Individual> population) 
            throws Exception{
        int[] childAdult = findAdultChildRatio(population);
        if(childAdult[1] == 0){
            return growPopulation(population);
        }
        if(childAdult[0] <= childAdult[1]){
            throw new Exception("N children (" + childAdult[0] + ") smaller than or equal to number of "
                    + "adults (" + childAdult[1] + ")! Use replacement method or produce more.");
        }
        List<Individual> populationcopy = new ArrayList<Individual>();
        //Kill old people
        for(Individual i: population){
            if(i.age() == 0){
                populationcopy.add(i);
            }
        }
        //Use standard selection method after adults are dead
        return growPopulation(selectBestFit(populationcopy, childAdult[1]));
    }

	@Override
	public int getNumberOfChildren(List<Individual> population) {
		return population.size()+(int)(population.size()*overProductionRate);
	}
}
