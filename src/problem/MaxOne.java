
package problem;

import evolalgo.IProblem;
import evolalgo.IIndividual;
import java.util.List;

/**
 * Development method for bit strings.
 * @author Odd
 */
public class MaxOne implements IProblem{

    @Override
    public Object[] developPheno(Object geno){
        
        String genostring = geno.toString();
        Object[] phenotype = new Object[genostring.length()];
        
        for(int i = 0; i < phenotype.length; i++){
            phenotype[i] = genostring.charAt(i);
        }
        return phenotype;
    }
    
    @Override
    public List<IIndividual> calculateFitness(List<IIndividual> population) throws Exception{
        for(int i = 0; i < population.size(); i++){
            int fitnessFactor = 0;
            Object[] phenotype = population.get(i).phenotype();
            //Blow up targetString and compare each bit
            if(targetString.length != phenotype.length){
                throw new Exception("Phenotype and bit string not of equal length!");
            }

            for(int j = 0; j < phenotype.length; j++){
                if(phenotype[j].toString().equals(targetString[j].toString())){
                    fitnessFactor++;
                }
            }population.get(i).setFitness((double) fitnessFactor/ (double) phenotype.length);
        }
        
        

        return population;
    }
}
