
package evolalgo.adultselectors;

import evolalgo.IIndividual;
import java.util.Iterator;
import java.util.List;

/**
 * This selection protocol kills all adults and lets all children become adults
 * @author Odd
 */
public class FullGenReplacement extends AdultSelectionImpl implements IAdultSelection{
    @Override
    public List<IIndividual> getAdults(List<IIndividual> population) 
            throws Exception{
        double[] childAdult = AdultSelectionImpl.findAdultChildRatio(population);
        //If we have equal number of adults and children, begin replacement
        if(childAdult[0] == childAdult[1]){
            Iterator<IIndividual> it = population.iterator();
            while(it.hasNext()){
                IIndividual i = it.next();
                if(i.age() > 0){
                    it.remove();
                }
            }
            return AdultSelectionImpl.growPopulation(population);
        }else if(childAdult[1] == 0.0){
            return AdultSelectionImpl.growPopulation(population);
        }else throw new Exception("Population has uneven number of children and "
                + "adults!(" + childAdult[0] + " children, " + childAdult[1] + " adults)");
    }
}
