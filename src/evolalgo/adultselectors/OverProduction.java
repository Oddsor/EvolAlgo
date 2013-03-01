
package evolalgo.adultselectors;

import evolalgo.IIndividual;
import java.util.ArrayList;
import java.util.List;

/**
 * This selection protocol produces more children than there are adult spots.
 * Not properly implemented yet!
 * @author Odd
 */
public class OverProduction extends AdultSelection implements IAdultSelection{
    
    public OverProduction(){
    }
    
    @Override
    public List<IIndividual> getAdults(List<IIndividual> population) 
            throws Exception{
        int[] childAdult = findAdultChildRatio(population);
        if(childAdult[1] == 0){
            return growPopulation(population);
        }
        if(childAdult[0] <= childAdult[1]){
            throw new Exception("N children (" + childAdult[0] + ") smaller than or equal to number of "
                    + "adults (" + childAdult[1] + ")! Use replacement method or produce more.");
        }
        List<IIndividual> populationcopy = new ArrayList<IIndividual>();
        //Kill old people
        for(IIndividual i: population){
            if(i.age() == 0){
                populationcopy.add(i);
            }
        }
        //Use standard selection method after adults are dead
        return growPopulation(selectBestFit(populationcopy, childAdult[1]));
    }

	@Override
	public int getNumberOfChildren() {
		// TODO Auto-generated method stub
		return 0;
	}
}
