
package evolalgo.parentselectors;

import evolalgo.IIndividual;
import java.util.List;

/**
 * This class contains various common methods used to find parents
 * @author Odd
 */
public class ParentSelection{
    
    IIndividual spinWheel(double[] weights, 
            List<IIndividual> population){
        IIndividual chosenParent = null;
        
        double random = Math.random();
        for(int i = 0; i < population.size(); i++){
            boolean inRange = false;
            if(i == 0 && weights[i] >= random){
                inRange = true;
            }else if(i > 0 && weights[i-1] < random && weights[i] >= random){
                inRange = true;
            }
            
            if(inRange){
                chosenParent = population.get(i);
            }
        }
        return chosenParent;
    }    
}
