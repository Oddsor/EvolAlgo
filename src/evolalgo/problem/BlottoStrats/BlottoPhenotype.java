package evolalgo.problem.BlottoStrats;

import evolalgo.Individual;
import evolalgo.Phenotype;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BlottoPhenotype implements Phenotype{
    public double[] pheno;
    public List<Individual> fought;
    public int score;
    
    public BlottoPhenotype(double[] pheno){
        this.pheno = pheno;
        fought = new ArrayList<Individual>();
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setMinimumFractionDigits(2);
        String output = "";
        for (int i = 0; i < pheno.length; i++){
            output += "B" + (i+1) + ": " + df.format(pheno[i]);
            if(i != pheno.length - 1) output += "; ";
        }
        return output;
    }
}