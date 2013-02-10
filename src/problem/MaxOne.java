
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
    public void developPheno(IIndividual individual) throws Exception{
        if (individual.phenotype() != null) return;
        String geno = individual.getGenes().toString();
        String genostring = geno.toString();
        MaxOnePheno phenotype = new MaxOnePheno(genostring.length());
        
        for(int i = 0; i < phenotype.pheno.length; i++){
            phenotype.pheno[i] = (int) genostring.charAt(i);
        }
        individual.setPhenotype(phenotype);
    }
    
    @Override
    public List<IIndividual> calculateFitness(List<IIndividual> population) throws Exception{
        for(int i = 0; i < population.size(); i++){
            try{
                population.get(i).fitness();
            }catch (Exception e){
                int fitnessFactor = 0;
                MaxOnePheno pheno = (MaxOnePheno) population.get(i).phenotype();
                int[] phenoArray = pheno.pheno;
                //Blow up targetString and compare each bit
                if(targetArray != null && targetArray.length != phenoArray.length){
                    throw new Exception("Phenotype and bit string not of equal length!");
                }

                for(int j = 0; j < phenoArray.length; j++){
                    if(targetArray != null && phenoArray[j] == targetArray[j]){
                        fitnessFactor++;
                    }else if(phenoArray[j] == '1') fitnessFactor++;
                }population.get(i).setFitness((double) fitnessFactor/ (double) phenoArray.length);
            }
        }
        return population;
    }
}

class MaxOnePheno implements IPhenotype{
    public int[] pheno;
    
    public MaxOnePheno(int size){
        pheno = new int[size];
    }
}