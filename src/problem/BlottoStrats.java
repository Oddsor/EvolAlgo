
package problem;

import evolalgo.IIndividual;
import evolalgo.IPhenotype;
import evolalgo.IProblem;
import java.util.ArrayList;
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
            double weight = 1 / total;
            for(int i = 0; i < segments; i++){
                phenotype[i] = phenotype[i] * weight;
            }
            IPhenotype pheno = new BlottoPheno(phenotype);
            individual.setPhenotype(pheno);
        }else{
            throw new Exception("Error in genotype");
        }
    }

    @Override
    public List<IIndividual> calculateFitness(List<IIndividual> population) 
            throws Exception {
        //scores table
        List<Integer> battleScores = new ArrayList<Integer>();
        BlottoPheno pheno = (BlottoPheno) population.get(0).phenotype();
        
        //For number of battles:
        for(int i = 0; i < pheno.pheno.length; i++){
            //Pick winner
            int winner = -1;
            boolean tie = false;
            for(IIndividual ind: population){
                //double battleStat = Double.parseDouble(ind.phenotype()[i].toString());
                if(winner == -1) winner = population.indexOf(ind);
                //If phenotype[i] in individual is greater than phenotype[i] in current winner
                else if(Double.parseDouble(ind.phenotype()[i].toString()) > 
                        Double.parseDouble(population.get(winner).phenotype()[i].toString())){
                    winner = population.indexOf(ind);
                }
            }
            //Winner found, check for ties.
            for(IIndividual ind: population){
                if(Double.parseDouble(population.get(winner).phenotype()[i].toString())
                        == Double.parseDouble(ind.phenotype()[i].toString())){
                    tie = true;
                }
            }
            //Set scores:
            for(int k = 0; k < population.size(); k++){
                if(k == winner && tie){
                    scores[k] += 1;
                    System.out.println("tie detected");
                }else if (k == winner){
                    scores[k] +=2;
                    System.out.println("found winner!");
                }else{
                    scores[k] += 0;
                }
            }
        }
        //Find highest possible score, and divide separate score to find fitness?
        
        double maxScore = 2 * pheno.pheno.length;
        
        for(int i = 0; i < population.size(); i++){
            population.get(i).setFitness(scores[i] / maxScore);
        }
        
        return population;
    }
}

class BlottoPheno implements IPhenotype{
    public double[] pheno;
    public List<IIndividual> fought;
    public BlottoPheno(double[] pheno){
        this.pheno = pheno;
        this.fought = new ArrayList<IIndividual>();
    }
}