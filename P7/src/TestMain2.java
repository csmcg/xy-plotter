/* File: TestMain2.java
 * Author: Connor McGarty, cmcgarty@uab.edu 
 * Assignment: Programmette 7 EE333 Spring 2018
 * Vers: 1.0.0 - 05/31/18 csm - main class for testing
 */

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * This class showcases the functionality of the AdderFilter class.
 * @author Connor McGarty cmcgarty@uab.edu
 */
public class TestMain2 {

    /**
     * This method showcases the functionality of the AdderFilter class. 
     * 
     * Two pairs of datastreams are each routed into an AdderFilter object,
     * which are in turn routed into the plotter.
     * 
     * The expected results are a diagonal line that reaches the top of the plot,
     * and then transforms into a straight horizontal line across the top of the
     * plot.
     * 
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException exception if file cannot be opened
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        StreamSet             dataSet;
        ArrayList<DataStream> streams;
        BufferedReader        r;
        AdderFilter           af1, af2;
        Plotter               p;
        BufferedImage         img;
        
        String inputFile = "test2.csv";
        
        r = new BufferedReader(new FileReader(inputFile));
        dataSet = new StreamSet(r);
        dataSet.parseStreams();
        streams = dataSet.getStreams();
        
        af1 = new AdderFilter();
        af2 = new AdderFilter();
        
        p = new Plotter(1, 20, 0, 20);

        /* Connect the filter chain:

        Stream 1 ---
                    \
                     --- AdderFilter1 --- x - Plotter
                    /                    
        Stream 2 ---                      
                                           
        Stream 3 ---                      
                    \                    
                     --- AdderFilter2 --- y - Plotter
                    /
        Stream 4 ---
        */
        
        af1.connectInput(streams.get(1), streams.get(2));
        af2.connectInput(streams.get(3), streams.get(4));

        DataStream xPlotInput = af1.requestData();
        DataStream yPlotInput = af2.requestData();
        
        p.connectInput(xPlotInput, 'x');
        p.connectInput(yPlotInput, 'y');
        img = p.plot();
        
        ImageIO.write(img, "PNG", new File("plot.png"));        

    }
    
}
