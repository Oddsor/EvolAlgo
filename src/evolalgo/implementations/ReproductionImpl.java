
package evolalgo.implementations;

import evolalgo.IIndividual;
import evolalgo.IReproduction;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The reproduction implementation
 * @author Odd
 */
public class ReproductionImpl implements IReproduction{

    private double recombinationRate;
    private int recombinationSplit;
    private double mutationRate;
    
    public ReproductionImpl(double mutationRate, double recombinationRate, 
            int recombinationSplit){
        this.recombinationRate = recombinationRate;
        this.recombinationSplit = recombinationSplit;
        this.mutationRate = mutationRate;
    }

    @Override
    public Object mutation(Object genotype) throws Exception{
        
        //We need a string to manipulate here, throw exception if wrong
        if(!(genotype instanceof String)){
            throw new Exception(genotype.toString() + "not a string! Input type: " 
                    + genotype.getClass().getName());
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
    public Object[] recombination(List<IIndividual> parents) {
        String[] recombined = new String[2];
        recombined[0] = "";
        recombined[1] = "";
        if(Math.random() <= recombinationRate){
            String gene1 = parents.get(0).getGenes().toString();
            String gene2 = parents.get(1).getGenes().toString();
            int geneLength = gene1.length();
            //Find splitpoints first
            int[] splits = new int[recombinationSplit];
            Random rand = new Random();
            int splitsRemaining = recombinationSplit;
            int genesRemaining = geneLength;
            for (int i = 0; i < recombinationSplit; i++){
                int split = 1 + rand.nextInt(genesRemaining - 2); //Must split inside gene, each end never considered.
                if (splitsRemaining > 0 && split > genesRemaining / 2) split = geneLength - split; //Idea here is to make sure we split off a smaller portion unless we're near the end.
                genesRemaining -= split;
                splitsRemaining -= 1;
                splits[i] = split;
            }
            for (int i = 0; i < splits.length; i++){
                String subgene1 = gene1.substring(splits[i]);
                String subgene2 = gene2.substring(splits[i]);
                if (i % 2 == 0){
                    recombined[0] += subgene1;
                    recombined[1] += subgene2;
                }else{
                    recombined[0] += subgene2;
                    recombined[1] += subgene1;
                }
            }
//            for(int i = 0; i < recombined.length; i++){
//                int splitCount = 0;
//                boolean done = false;
//                boolean alternate = false;
//                if(i == 1){
//                    alternate = true;
//                }
//                while(!done){
//                    if(!alternate){
//                        try{
//                            recombined[i] += gene1.substring(splitCount, splitCount + 
//                                recombinationSplit);
//                        }catch(Exception e){
//                            recombined[i] += gene1.substring(splitCount);
//                            done = true;
//                        }
//                    }else if(alternate){
//                        try{
//                            recombined[i] += gene2.substring(splitCount, splitCount + 
//                                recombinationSplit);
//                        }catch(Exception e){
//                            recombined[i] += gene2.substring(splitCount);
//                            done = true;
//                        }
//                    }
//                    splitCount += recombinationSplit;
//                    alternate = !alternate;
//
//                }
//            }
        }else{
            recombined[0] = parents.get(0).getGenes().toString();
            recombined[1] = parents.get(1).getGenes().toString();
        }
        return recombined;
    }

    @Override
    public Object[] reproduce(List<IIndividual> parents) {
        //Recombine to two children and then mutate their genes
        
        Object[] genoTypes = recombination(parents);
        try {
            genoTypes[0] = mutation(genoTypes[0]);
            genoTypes[1] = mutation(genoTypes[1]);
        } catch (Exception ex) {
            Logger.getLogger(ReproductionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return genoTypes;
    }

    public static void main(String[] args){
        //Testing av klassen
        List<IIndividual> parents = new ArrayList<IIndividual>();
        parents.add(new IndividualImpl("1112223334445556"));
        parents.add(new IndividualImpl("6667778889990008"));
        
        ReproductionImpl rep = new ReproductionImpl(0.0, 1.0, 2);
        Object[] recombination = rep.recombination(parents);
        System.out.println(recombination[0] + ", " + recombination[1]);
    }
}
