/* File: TestMain4.java
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
 * This method showcases the combination of multiple filter objects into a chain.
 * 
 * @author Connor McGarty cmcgarty@uab.edu
 */
public class TestMain4 {

    /**
     * This method showcases the combination of multiple filter objects into a
     * chain. The file contains slightly more interesting input data; a few
     * sinusoid functions.
     * 
     * The input data file contains 3 columns: the first contains the integers 
     * from -1000 to 1000, which will be mapped directly to the x-input of the
     * plotter. 
     * The remaining columns contain data point that are the output of the
     * following functions, where x is the integers in the first column.
     * 
     * Col 2: 15sin(3x) + 4
     * Col 3: cos(x)
     * Col 4: cos(-x/2) - 2
     * 
     * The filter chain will add stream 2 to stream 3, add the results of that
     * operation to stream 4, and then scale that results by 2.5.
     * 
     * The plot will show the output of this filtering on the input on the domain
     * -100 to 100, with range -50 to 50.
     * 
     * 
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException file couldnt be opened/found
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        StreamSet             dataSet;
        ArrayList<DataStream> streams;
        BufferedReader        r;
        AdderFilter           af1, af2;
        ScalerFilter          sf1;
        Plotter               p;
        BufferedImage         img;
        
        String inputfile = "test4.csv";
        
        r = new BufferedReader(new FileReader(inputfile));
        dataSet = new StreamSet(r);
        dataSet.parseStreams(); // read the file, separate into streams
        streams = dataSet.getStreams(); // get the list of streams
        
        af1 = new AdderFilter();
        af2 = new AdderFilter();
        
        af1.connectInput(streams.get(2), streams.get(3)); // add column 2 to 3
        // add results of previous filter to column 4
        af2.connectInput(af1, streams.get(4));


        sf1 = new ScalerFilter(2.5, 0.0);
        // scale the 2nd adder's output by 2.5
        sf1.connectInput(af2);

        // plot the output of the scaler filter on its domain
        DataStream xPlotInput = streams.get(1);
        DataStream yPlotInput = sf1.requestData(); 
        
        p = new Plotter(-50, 50, -60, 60); // creat plotter object

        // connect the plotter
        p.connectInput(xPlotInput, 'x');
        p.connectInput(yPlotInput, 'y');
        
        img = p.plot();
        ImageIO.write(img, "PNG", new File("plot.png"));
        
    }
    
}

