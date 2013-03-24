/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolalgo.problem.ctrnn;

import evolalgo.BinaryStrings;
import evolalgo.IIndividual;
import evolalgo.IReproduction;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Odd
 */
public class BinaryCTRNNStrings implements IReproduction{
    
    private double mutationRate;
    private double recombinationRate;
    private int maxMutations;
    
    public BinaryCTRNNStrings(double mutationRate, double recombinationRate, 
            int maxMutations){
        this.mutationRate = mutationRate;
        this.recombinationRate = recombinationRate;
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
    public Object[] crossover(List<IIndividual> parents){
        String gene1 = parents.get(0).getGenes().toString();
        String gene2 = parents.get(1).getGenes().toString();
        String[] genes = new String[2];
        genes[0] = gene1;
        genes[1] = gene2; 
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
    }

    @Override
    public Object[] reproduce(List<IIndividual> parents){
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
