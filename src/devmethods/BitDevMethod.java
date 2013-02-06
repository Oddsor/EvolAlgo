
package devmethods;

import evolalgo.IDevelopMethod;

/**
 * Development method for bit strings.
 * @author Odd
 */
public class BitDevMethod implements IDevelopMethod{

    @Override
    public Object[] developPheno(Object geno){
        
        String genostring = geno.toString();
        Object[] phenotype = new Object[genostring.length()];
        
        for(int i = 0; i < phenotype.length; i++){
            phenotype[i] = genostring.charAt(i);
        }
        return phenotype;
    }
    
    
}
