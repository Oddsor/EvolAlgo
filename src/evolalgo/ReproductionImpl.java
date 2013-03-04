package evolalgo;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Reproduction implementation for crossover and mutation of bitstrings
 * @author Odd
 */
public class ReproductionImpl implements IReproduction{

    private double recombinationRate;
    private int recombinationSplit;
    private double mutationRate;
    private int maxMutations;
    
    public ReproductionImpl(double mutationRate, double recombinationRate, 
            int recombinationSplit, int maxMutations) throws Exception{
        if(recombinationRate > 1.0 || recombinationRate < 0.0){
            throw new Exception("Crossover rate outside range 0.0-1.0");
        }
        this.recombinationRate = recombinationRate;
        if(recombinationSplit > 2 || recombinationSplit < 1){
            throw new Exception("Crossover split is outside range, accepted values are 1 and 2, got " + recombinationSplit);
        }
        this.recombinationSplit = recombinationSplit;
        if(mutationRate > 1.0 || mutationRate < 0.0){
            throw new Exception("Mutation rate outside range 0.0-1.0");
        }
        this.mutationRate = mutationRate;
        
        if(maxMutations < 1) throw new Exception("Can't have 0 or negative mutations!");
        this.maxMutations = maxMutations;
    }

    @Override
    public Object mutation(Object genotype) throws Exception{
        
        //We need a string to manipulate here, throw exception if wrong
        if(!(genotype instanceof String)){
            throw new Exception(genotype.getClass().getSimpleName() + "not a string! Input type: " 
                    + genotype.getClass().getSimpleName());
        }
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
    public Object[] recombination(List<IIndividual> parents) throws Exception{
        for (IIndividual parent: parents){
            if (!(parent instanceof IIndividual)){ 
                throw new Exception("Parent incorrect class?");
            }
        }
        String[] recombined = new String[2];
        recombined[0] = "";
        recombined[1] = "";
        if (Math.random() <= recombinationRate){
            String gene1 = parents.get(0).getGenes().toString();
            String gene2 = parents.get(1).getGenes().toString();
            Random rand = new Random();
            if (recombinationSplit == 1){
                int split = 1 + rand.nextInt(gene1.length() - 2);
                recombined[0] += gene1.substring(0, split) + 
                        gene2.substring(split);
                recombined[1] += gene2.substring(0, split) + 
                        gene1.substring(split);
            }else if (recombinationSplit == 2){
                int split1 = 1 + rand.nextInt((gene1.length() - 2) / 2);
                int split2 = ((gene1.length() / 2) + 1) + 
                        rand.nextInt((gene1.length() - 2) / 2);
                recombined[0] += gene1.substring(0, split1) + 
                        gene2.substring(split1, split2) + 
                        gene1.substring(split2);
                recombined[1] += gene2.substring(0, split1) + 
                        gene1.substring(split1, split2) + 
                        gene2.substring(split2);
            }
        }else{
            recombined[0] = parents.get(0).getGenes().toString();
            recombined[1] = parents.get(1).getGenes().toString();
        }
        return recombined;
    }

    @Override
    public Object[] reproduce(List<IIndividual> parents) throws Exception{
        //Recombine to two children and then mutate their genes
        
        Object[] genoTypes = recombination(parents);
        try {
            if(Math.random() <= mutationRate){
                for (int i = 0; i < maxMutations; i++){
                    genoTypes[0] = mutation(genoTypes[0]);
                    genoTypes[1] = mutation(genoTypes[1]);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ReproductionImpl.class.getName()).log(Level.SEVERE, null, ex);
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
