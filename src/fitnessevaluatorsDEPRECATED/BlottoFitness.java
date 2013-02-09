/*
package fitnessevaluatorsDEPRECATED;

import evolalgo.IFitnessEval;
import evolalgo.IIndividual;
import java.util.List;

public class BlottoFitness implements IFitnessEval{
    
    private double reployFraction;
    private double lossFraction;
    
    public BlottoFitness(double reployFraction, double lossFraction){
        this.reployFraction = reployFraction;
        this.lossFraction = lossFraction;
    }


    @Override
    public List<IIndividual> calculateFitness(List<IIndividual> population) 
            throws Exception {
        //scores table
        int[] scores = new int[population.size()];
        
        //For number of battles:
        for(int i = 0; i < population.get(0).phenotype().length; i++){
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
        
        double maxScore = 2 * population.get(0).phenotype().length;
        
        for(int i = 0; i < population.size(); i++){
            population.get(i).setFitness(scores[i] / maxScore);
        }
        
        return population;
    }
}*/
