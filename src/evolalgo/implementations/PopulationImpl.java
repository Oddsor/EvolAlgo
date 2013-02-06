/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolalgo.implementations;

import evolalgo.IPopulation;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Odd
 */
public class PopulationImpl implements IPopulation {

    @Override
    public List<Object> createPopulation(int individuals, int size) {
        //throw new UnsupportedOperationException("Not supported yet.");
        
        List<Object> list = new ArrayList();
        for(int i = 0; i < individuals; i++){
            String bitstring = "";
            Random rand = new Random();
            for(int j = 0; j < size; j++){
                if(rand.nextBoolean() == true) bitstring += "1";
                else bitstring += "0";
                
            }
            list.add(bitstring);
        }
        return list;
    }
}