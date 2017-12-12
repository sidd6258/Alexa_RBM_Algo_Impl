
package drivers;

import deeplearning.tools.rbmforcollaborativefiltering.CollaborativeFilteringRBM;
import deeplearning.tools.rbmforcollaborativefiltering.PredictionType;
import genutils.RbmOptions;
import java.io.IOException;
import java.util.logging.Logger;

import db.DbConnection;

public class TestCollaborativeFilteringRBM {
    
    private static final Logger _logger = Logger.getLogger(TestCollaborativeFilteringRBM.class.getName());    
    
    public static void main(String[] args) throws Exception {
        
        
        _logger.info("Loading data..");        
               
        RbmOptions options = new RbmOptions();
        options.maxepoch = 0;
        options.avglast = 5;
        options.numhid = 100;
        options.debug = false;
       
        DbConnection.RatingsFileGen("hotel");
        CollaborativeFilteringRBM rbmCF = new CollaborativeFilteringRBM();
        rbmCF.loadRatings("./data/" + "hotel_booking.txt");
        rbmCF.fit(options);
        rbmCF.predict("hotel",PredictionType.MAX);       
        
        DbConnection.RatingsFileGen("car");
        CollaborativeFilteringRBM rbmCF1 = new CollaborativeFilteringRBM();
        rbmCF1.loadRatings("./data/" + "car_booking.txt");
        rbmCF1.fit(options);
        rbmCF1.predict("car",PredictionType.MAX);                              

        DbConnection.RatingsFileGen("flight");
        CollaborativeFilteringRBM rbmCF2 = new CollaborativeFilteringRBM();
        rbmCF2.loadRatings("./data/" + "flight_booking.txt");
        rbmCF2.fit(options);
        rbmCF2.predict("flight",PredictionType.MAX);                              

       
    }
    
}
