
package evolalgo.problem.ctrnn;

import evolalgo.Individual;
import evolalgo.Reproduction;
import java.util.List;
import java.util.Random;

/**
 * An adapted implementation of the regular binary strings that swaps every
 * other attribute.
 * @author Odd
 */
public class BinaryCTRNNStrings implements Reproduction{
    
    private double mutationRate;
    private double crossoverRate;
    private int maxMutations;
    
    public BinaryCTRNNStrings(double mutationRate, double crossoverRate, 
            int maxMutations){
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.maxMutations = maxMutations;
    }

    @Override
    public Object mutation(Object genotype){
        String genotypeString = (String) genotype;
        Random rand = new Random();
        int mutatedBit = rand.nextInt(genotypeString.length());
        char[] genoArray = genotypeString.toCharArray();
        if(genoArray[mutatedBit] == '1') genoArray[mutatedBit] = '0';
        else genoArray[mutatedBit] = '1';
        
        String newGenotype = "";
        for(int i = 0; i < genoArray.length; i++){
            newGenotype += genoArray[i];
            
        }
        return newGenotype;
    }

    @Override
    public Object[] crossover(List<Individual> parents){
        
        String gene1 = parents.get(0).getGenes().toString();
        String gene2 = parents.get(1).getGenes().toString();
        String[] genes = new String[2];
        genes[0] = gene1;
        genes[1] = gene2; 
        if (Math.random() <= crossoverRate){
            boolean cycler = true;
            String[] newGenes = new String[2];
            newGenes[0] = "";
            newGenes[1] = "";

            for(int i = 0; i < (int) (gene1.length() / 8); i++){

                newGenes[0] += genes[cycler ? 1 : 0].substring((i * 8), (i * 8 + 8));
                newGenes[1] += genes[cycler ? 0 : 1].substring((i * 8), (i * 8 + 8));

                cycler = !cycler;
            }

            return newGenes;
        }else{
            return genes;
        }
    }

    @Override
    public Object[] reproduce(List<Individual> parents){
        Object[] genoTypes = crossover(parents);
        try {
            if(Math.random() <= mutationRate){
                for (int i = 0; i < maxMutations; i++){
                    genoTypes[0] = mutation(genoTypes[0]);
                    genoTypes[1] = mutation(genoTypes[1]);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return genoTypes;
    }

    @Override
    public double getMutationRate() {
        return mutationRate;
    }

    @Override
    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }
    
}
