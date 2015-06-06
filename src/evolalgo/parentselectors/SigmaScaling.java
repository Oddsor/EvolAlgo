package evolalgo.parentselectors;

import evolalgo.Individual;
import java.util.List;

/**
 * This parent selection method uses standard deviation to scale
 * individuals before they enter the spinning wheel. Uses a standard deviation
 * function stolen from the internet.
 * @author Odd
 */
public class SigmaScaling extends ParentSelection implements IParentSelection{
    @Override
    public Individual getParent(List<Individual> population) throws Exception{
        double[] weights = new double[population.size()];
        
        //Find fitness average of population
        double fitnessCount = 0.0;
        for(int i = 0; i < population.size(); i++){
            fitnessCount += population.get(i).fitness();
        }
        double avgFitness = fitnessCount / population.size();
        
        //Use StandardDeviation-class to find std
        double fitnessdata[] = new double[population.size()];
        for(int i = 0; i < population.size(); i++){
            fitnessdata[i] = population.get(i).fitness();
        }
        double deviation = StandardDeviation.standardDeviationCalculate(fitnessdata);
        if (deviation == 0.0) deviation = 0.000001;
        for(int i = 0; i < population.size(); i++){
            weights[i] = 1 + ((population.get(i).fitness() - avgFitness) 
                    / (2*deviation));
        }
        return spinWheel(weights, population);
    }
}

/**
 * Standard deviation calculation shamelessly stolen from the internets
 * @author Odd
 */
class StandardDeviation
{

    public static double StandardDeviationMean ( double[] data )
    {
        // sd is sqrt of sum of (values-mean) squared divided by n - 1
        // Calculate the mean
        double mean = 0;
        final int n = data.length;
        if ( n < 2 )
        {
        return Double.NaN;
        }
        for ( int i=0; i<n; i++ )
        {
        mean += data[i];
        }
        mean /= n;
        // calculate the sum of squares
        double sum = 0;
        for ( int i=0; i<n; i++ )
        {
        final double v = data[i] - mean;
        sum += v * v;
        }
        // Change to ( n - 1 ) to n if you have complete data instead of a sample.
        return Math.sqrt( sum / ( n - 1 ) );
    }

    public static double standardDeviationCalculate ( double[] data )
    {
        final int n = data.length;
        if ( n < 2 )
        {
            return Double.NaN;
        }
        double avg = data[0];
        double sum = 0;
        for ( int i = 1; i < data.length; i++ )
        {
            double newavg = avg + ( data[i] - avg ) / ( i + 1 );
            sum += ( data[i] - avg ) * ( data [i] -newavg ) ;
            avg = newavg;
        }
        // Change to ( n - 1 ) to n if you have complete data instead of a sample.
        return Math.sqrt( sum / ( n ) );
    }
}
