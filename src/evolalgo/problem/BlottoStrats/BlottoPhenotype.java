package evolalgo.problem.BlottoStrats;

import evolalgo.IIndividual;
import evolalgo.IPhenotype;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BlottoPhenotype implements IPhenotype{
    public double[] pheno;
    public List<IIndividual> fought;
    public int score;
    
    public BlottoPhenotype(double[] pheno){
        this.pheno = pheno;
        fought = new ArrayList<IIndividual>();
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        String output = "";
        for (int i = 0; i < pheno.length; i++){
            output += df.format(pheno[i]);
            if(i != pheno.length - 1) output += "; ";
        }
        return output;
    }
}