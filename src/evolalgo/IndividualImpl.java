/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolalgo;

import evolalgo.IIndividual;
import evolalgo.IPhenotype;

/**
 *
 * @author Odd
 */
public class IndividualImpl implements IIndividual{
    
    private Object genotype;
    private IPhenotype phenotype;
    private int age;
    private double fitness;
    
    public IndividualImpl(Object genotype){
        this.genotype = genotype;
        this.age = 0;
        this.fitness = -1.0;
        this.phenotype = null;
    }

    @Override
    public void setPhenotype(IPhenotype phenotype) {
        this.phenotype = phenotype;
    }

    @Override
    public IPhenotype phenotype(){
        return phenotype;
    }

    @Override
    public Object getGenes() {
        return genotype;
    }

    @Override
    public void growOlder() {
        age++;
    }

    @Override
    public int age() {
        return age;
    }
    
    @Override
    public String toString(){
        return "I: " + phenotype.toString() + " (" + genotype.toString() + ")";
    }

    @Override
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public double fitness() throws Exception {
        if(fitness >= 0.0 && fitness <= 1.0){
            return fitness;
        }else{
            throw new Exception("Fitness undefined!");
        }
    }
}
