
package problem;

import evolalgo.IProblem;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public Object[] developPheno(Object geno) throws Exception{
        if(geno instanceof String){
            int segments = geno.toString().length() / 4;
            Object[] phenotype = new Object[segments];
            double total = 0.0;
            for(int i = 0; i < segments; i++){
                int location = i * 4;
                phenotype[i] = geno.toString().substring(location, location + 4);
                //Convert from binary string to number:
                phenotype[i] = Integer.parseInt(phenotype[i].toString(), 2);
                //Count to the total number of forces
                total += Double.parseDouble(phenotype[i].toString());
            }
            double weight = 1 / total;
            for(int i = 0; i < segments; i++){
                phenotype[i] = Double.parseDouble(phenotype[i].toString()) * weight;
            }
            return phenotype;
        }else{
            throw new Exception("Error in genotype");
        }
    }
    
    public static void main(String[] args){
        BlottoStrats blot = new BlottoStrats();
        Object[] pheno;
        try {
            pheno = blot.developPheno("11110001011001011001");
            double count = 0.0;
            for(int i = 0; i < pheno.length; i++){
                System.out.println(pheno[i]);
                count += Double.parseDouble(pheno[i].toString());
            }
            System.out.println("Total: " + count);
        } catch (Exception ex) {
            Logger.getLogger(BlottoStrats.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
