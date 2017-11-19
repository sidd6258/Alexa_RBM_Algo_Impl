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

import db.DbConnection;

/**
 *
 * @author thanos
 */
public class TestCollaborativeFilteringRBM {
    
    private static final Logger _logger = Logger.getLogger(TestCollaborativeFilteringRBM.class.getName());    
    
    public static void main(String[] args) throws Exception {
        
        
        _logger.info("Loading data..");        
               
        RbmOptions options = new RbmOptions();
        options.maxepoch = 0;
        options.avglast = 5;
        options.numhid = 100;
        options.debug = false;
        
        DbConnection.RatingsFileGen();
        //CollaborativeFilteringLayer fit = CollaborativeFilteringRBM.fit(data, options);
        CollaborativeFilteringRBM rbmCF = new CollaborativeFilteringRBM();
        rbmCF.loadRatings("./data/" + "booking.txt");
        
        rbmCF.fit(options);
        
        rbmCF.predict(PredictionType.MAX);                              
       
    }
    
}
