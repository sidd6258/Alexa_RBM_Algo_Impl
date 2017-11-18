/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package drivers;

import deeplearning.tools.rbmforcollaborativefiltering.CollaborativeFilteringRBM;
import deeplearning.tools.rbmforcollaborativefiltering.PredictionType;
import genutils.RbmOptions;
import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 * @author thanos
 */
public class TestCollaborativeFilteringRBM {
    
    private static final Logger _logger = Logger.getLogger(TestCollaborativeFilteringRBM.class.getName());    
    
    public static void main(String[] args) throws IOException {
        
        
        _logger.info("Loading data..");        
               
        RbmOptions options = new RbmOptions();
        options.maxepoch = 2;
        options.avglast = 5;
        options.numhid = 100;
        options.debug = false;
        
        //CollaborativeFilteringLayer fit = CollaborativeFilteringRBM.fit(data, options);
        CollaborativeFilteringRBM rbmCF = new CollaborativeFilteringRBM();
        rbmCF.loadRatings("./data/" + "booking.txt");
        
        rbmCF.fit(options);
        
        System.out.println("Max prediction = " + rbmCF.predict("siddharth.gupta@sjsu.edu", "5a0e211af4896d0558d6433b", PredictionType.MAX));               
        System.out.println("Mean prediction = " + rbmCF.predict("siddharth.gupta@sjsu.edu", "5a0e211af4896d0558d6433b", PredictionType.MEAN));               
       
    }
    
}
