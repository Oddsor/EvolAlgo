
package evolalgo.adultselectors;

import evolalgo.Individual;
import java.util.ArrayList;
import java.util.List;

/**
 * This selection protocol mixes the best of the children with the best of the adults
 * @author Odd
 */
public class GenerationalMixing extends AdultSelection implements IAdultSelection{

	private int adultSpots;

	public GenerationalMixing(int adultSpots){
		this.adultSpots = adultSpots;
	}

	@Override
	public List<Individual> getAdults(List<Individual> population) throws Exception{
		//Use standard selection method
		int[] childAdult = findAdultChildRatio(population);
		int populationSize = childAdult[1]; //The size we want to reduce to.
		if(childAdult[1] == 0) return growPopulation(population);
		List<Individual> adults = new ArrayList<Individual>();
		List<Individual> children = new ArrayList<Individual>();
		for (Individual i: population){
			if (i.age() > 0) adults.add(i);
			else children.add(i);
		}
		//System.out.println("Pop size: " + populationSize);
		children = selectBestFit(children, populationSize - adultSpots);
		//System.out.println(children.size());
		adults = selectBestFit(adults, adultSpots);
		//System.out.println(adults.size());
		//adults.addAll(children);
		for(Individual i: children){
			adults.add(i);
		}
		//System.out.println(adults.size());
		return growPopulation(adults);
	}

	@Override
	public int getNumberOfChildren(List<Individual> population) {
		return population.size()-adultSpots;
	}

}
