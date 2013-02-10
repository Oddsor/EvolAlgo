
package problem;

import evolalgo.IIndividual;
import evolalgo.IProblem;
import java.util.List;

/**
 * Development method for the Blotto Strategies
 * @author Odd
 */
public class BlottoStrats implements IProblem{
    
    public BlottoStrats(){
        
    }

    /**
     * Creates phenotype
     * @param geno the genotype bitstring, where segments of 4 bits account for
     * men used in each battle
     * @return the phenotype
     * @throws Exception 
     */
    @Override
    public void developPheno(IIndividual individual) throws Exception{
        throw new Exception("Not done yet");
//        if(geno instanceof String){
//            int segments = geno.toString().length() / 4;
//            Object[] phenotype = new Object[segments];
//            double total = 0.0;
//            for(int i = 0; i < segments; i++){
//                int location = i * 4;
//                phenotype[i] = geno.toString().substring(location, location + 4);
//                //Convert from binary string to number:
//                phenotype[i] = Integer.parseInt(phenotype[i].toString(), 2);
//                //Count to the total number of forces
//                total += Double.parseDouble(phenotype[i].toString());
//            }
//            double weight = 1 / total;
//            for(int i = 0; i < segments; i++){
//                phenotype[i] = Double.parseDouble(phenotype[i].toString()) * weight;
//            }
//            //return phenotype;
//        }else{
//            throw new Exception("Error in genotype");
//        }
    }

    @Override
    public List<IIndividual> calculateFitness(List<IIndividual> population) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}