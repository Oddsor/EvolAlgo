package evolalgo.problem.BlottoStrats;

import evolalgo.Individual;
import evolalgo.Phenotype;
import evolalgo.IndividualImpl;
import evolalgo.problem.IProblem;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Development method for the Blotto Strategies
 * @author Odd
 */
public class BlottoStratsProblem implements IProblem{
    private int numBattles;
    private double redeploymentRate;
    private double defectionRate;
    /**
     * 
     * @param B Number of battles
     * @param R The re-deployment factor when fights are won
     * @param L Defection factor when fights are lost
     */
    public BlottoStratsProblem(int B, double R, double L){
        numBattles = B;
        redeploymentRate = R;
        defectionRate = L;
    }
    
    /**
     * Creates phenotype
     * @param geno the genotype bitstring, where segments of 4 bits account for
     * men used in each battle
     * @return the phenotype
     * @throws Exception 
     */
    @Override
    public void developPheno(Individual individual) throws Exception{
        String geno = individual.getGenes().toString();
        if(geno instanceof String){
            int segments = geno.toString().length() / 4;
            double[] phenotype = new double[segments];
            double total = 0.0;
            for(int i = 0; i < segments; i++){
                int location = i * 4;
                String binary = geno.toString().substring(location, location + 4);
                phenotype[i] = Integer.parseInt(binary, 2);
                total += phenotype[i];
            }
            double weight;
            if (total == 0.0) weight = 0.001;
            else weight = 1.0 / total;
            for(int i = 0; i < segments; i++){
                phenotype[i] = phenotype[i] * weight;
            }
            Phenotype pheno = new BlottoPhenotype(phenotype);
            individual.setPhenotype(pheno);
        }else{
            throw new Exception("Error in genotype");
        }
    }

    @Override
    public void calculateFitness(List<Individual> population) 
            throws Exception {
        //Have every individual fight every other individual and increase fitness as they score
        int warpoints = 0;
        for (Individual fighter: population){
            BlottoPhenotype fighterPheno = (BlottoPhenotype) fighter.phenotype();
            fighter.setFitness(0.0);
            for(Individual opponent: population){
                if(!fighter.equals(opponent) && !fighterPheno.fought.contains(opponent)){
                    BlottoPhenotype opponentPheno = (BlottoPhenotype) opponent.phenotype();
                    int fighterWins = 0;
                    int opponentWins = 0;
                    double[] fighterWeights = new double[numBattles];
                    System.arraycopy(fighterPheno.pheno, 0, fighterWeights, 0, numBattles);
                    double[] opponentWeights = new double[numBattles];
                    System.arraycopy(opponentPheno.pheno, 0, opponentWeights, 0, numBattles);
                    for(int battle = 0; battle < numBattles; battle++){
                        if(fighterWeights[battle] > opponentWeights[battle]){
                            fighterWins += 1;
                            double redistribution = fighterWeights[battle] - opponentWeights[battle];
                            int remainingBattles = numBattles - (battle + 1);
                            redistribution = redistribution / remainingBattles;
                            for (int i = battle + 1; i < numBattles; i++){
                                fighterWeights[i] += redeploymentRate * redistribution;
                                opponentWeights[i] *= 1 - defectionRate;
                            }
                        }else if(fighterWeights[battle] > opponentWeights[battle]){
                            opponentWins += 1;
                            double redistribution = opponentWeights[battle] - fighterWeights[battle];
                            int remainingBattles = numBattles - (battle + 1);
                            redistribution = redistribution / remainingBattles;
                            for (int i = battle + 1; i < numBattles; i++){
                                opponentWeights[i] += redeploymentRate * redistribution;
                                fighterWeights[i] *= 1 - defectionRate;
                            }
                        }
                    }
                    if (fighterWins == opponentWins){
                        fighterPheno.score++;
                        opponentPheno.score++;
                    }else if(fighterWins > opponentWins){
                        fighterPheno.score += 2;
                    }else if(fighterWins < opponentWins){
                        opponentPheno.score += 2;
                    }
                    warpoints += 2;
                    
                    //Note that they've fought eachother
                    fighterPheno.fought.add(opponent);
                    opponentPheno.fought.add(fighter);
                }
            }
        }
        int topScore = 0;
        for (Individual individual: population){
            BlottoPhenotype ph = (BlottoPhenotype) individual.phenotype();
            if (ph.score > topScore) topScore = ph.score;
        }
        for (Individual individual: population){
            BlottoPhenotype ph = (BlottoPhenotype) individual.phenotype();
            individual.setFitness((double)ph.score / (double)topScore);
            ph.fought = null;
        }
    }
    
    @Override
    public List<Individual> createPopulation(int individuals) {
        List<Object> genotypes = new ArrayList();
        for(int i = 0; i < individuals; i++){
            String bitstring = "";
            Random rand = new Random();
            for(int j = 0; j < (numBattles * 4); j++){
                if(rand.nextBoolean() == true) bitstring += "1";
                else bitstring += "0";
            }
            genotypes.add(bitstring);
        }
        
        //Setting up list of individuals
        List<Individual> population = new ArrayList();
        
        for(Object o: genotypes){
            population.add(new IndividualImpl(o));
        }
        return population;
    }
}