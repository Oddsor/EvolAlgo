
package evolalgo.adultselectors;

import evolalgo.Individual;
import java.util.Iterator;
import java.util.List;

/**
 * This selection protocol kills all adults and lets all children become adults
 * @author Odd
 */
public class FullGenReplacement extends AdultSelection implements IAdultSelection{
    @Override
    public List<Individual> getAdults(List<Individual> population) 
            throws Exception{
        int[] childAdult = findAdultChildRatio(population);
        //If we have equal number of adults and children, begin replacement
        if(childAdult[0] == childAdult[1]){
            Iterator<Individual> it = population.iterator();
            while(it.hasNext()){
                Individual i = it.next();
                if(i.age() > 0){
                    it.remove();
                }
            }
            return growPopulation(population);
        }else if(childAdult[1] == 0){
            return growPopulation(population);
        }else throw new Exception("Population has uneven number of children and "
                + "adults!(" + childAdult[0] + " children, " + childAdult[1] + " adults)");
    }
    
	@Override
	public int getNumberOfChildren(List<Individual> population) {
		return population.size();
	}

}
