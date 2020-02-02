/* File: TestMain3.java
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
 * This class showcases the functionality of the ScalerFilter class. 
 * 
 * @author Connor McGarty cmcgarty@uab.edu
 */
public class TestMain3 {

    /**
     * This method showcases the functionality of the ScalerFilter class. 
     * 
     * The file contains two columns: the first are the integers from 1 to 999,
     * the second contains the natural logarithm of the integer in the same row.
     * 
     * The scaler will be applied to the second column, multiplying the points
     * by a factor of 3 and adding 5. The expected result is the graph of the
     * function: f(x) = ln(x) * 3.0 + 5.0,  for x from 0 to 1000
     * 
     * 
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException if file cannot be opened
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        // use scaler
        StreamSet             dataSet;
        ArrayList<DataStream> streams;
        BufferedReader        r;
        ScalerFilter          sf1;
        Plotter               p;
        BufferedImage         img;
        
        String inputfile = "test3.csv";
        
        r = new BufferedReader(new FileReader(inputfile));
        dataSet = new StreamSet(r);
        dataSet.parseStreams();
        streams = dataSet.getStreams();
        
        // scale by a factor of 3, plus 5 to each point 
        sf1 = new ScalerFilter(3.0, 5.0);
        
        sf1.connectInput(streams.get(2));
        
        p = new Plotter(1, 999, 0, 30);
        
        DataStream xPlotInput = streams.get(1);
        DataStream yPlotInput = sf1.requestData();
        
        p.connectInput(xPlotInput, 'x');
        p.connectInput(yPlotInput, 'y');
        img = p.plot();
        ImageIO.write(img, "PNG", new File("plot.png"));
        
    }
    
}
