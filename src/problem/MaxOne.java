
package problem;

import evolalgo.IPhenotype;
import evolalgo.IProblem;
import evolalgo.IIndividual;
import java.util.List;

/**
 * Development method for bit strings.
 * @author Odd
 */
public class MaxOne implements IProblem{
    
    int[] targetArray;
    
    public MaxOne(String targetString){
        if(targetString.equals("")){
            targetArray = null;
        }else{
            targetArray = new int[targetString.length()];
            for (int i = 0; i < targetString.length(); i++){
                targetArray[i] = (int) targetString.charAt(i);
            }
        }
    }

    @Override
    public IPhenotype developPheno(Object geno) throws Exception{
        
        String genostring = geno.toString();
        Phenotype phenotype = new Phenotype(genostring.length());
        
        for(int i = 0; i < phenotype.pheno.length; i++){
            phenotype.pheno[i] = (int) genostring.charAt(i);
        }
        return phenotype;
    }
    
    @Override
    public List<IIndividual> calculateFitness(List<IIndividual> population) throws Exception{
        for(int i = 0; i < population.size(); i++){
            int fitnessFactor = 0;
            int[] phenoArray = (int[]) population.get(i).phenotype();
            //Blow up targetString and compare each bit
            if(targetArray.length != phenoArray.length){
                throw new Exception("Phenotype and bit string not of equal length!");
            }

            for(int j = 0; j < phenoArray.length; j++){
                if(phenoArray[j] == targetArray[j]){
                    fitnessFactor++;
                }
            }population.get(i).setFitness((double) fitnessFactor/ (double) phenoArray.length);
        }
        return population;
    }
}

class Phenotype implements IPhenotype{
    public int[] pheno;
    
    public Phenotype(int size){
        pheno = new int[size];
    }
}