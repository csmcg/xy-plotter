/* File: TestMain.java
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
 * This class showcases the most basic functionality of the plotter using
 * no filters; two data streams mapped directly to the plotter's inputs.
 * The result should be a diagonal line from the bottom left to the top right
 * of the output image.
 * 
 * @author Connor McGarty cmcgarty@uab.edu
 */
public class TestMain {

    /**
     * This method showcases the most basic functionality of the plotter using
     * no filters; two data streams mapped directly to the plotter's inputs.
     * The result should be a diagonal line from the bottom left to the top right
     * of the output image. 
     * 
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException exception if file cannot be opened
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        StreamSet dataSet;
        BufferedReader r;
        ArrayList<DataStream> streams;
        Plotter p;
        BufferedImage img;
        
        String inputFile = "test1.csv";
        
        r = new BufferedReader(new FileReader(inputFile));

        // open the file, get the streams
        dataSet = new StreamSet(r);
        dataSet.parseStreams();
        streams = dataSet.getStreams();
        
        // Create the Plotter object, supply the range of the view of the plot
        p = new Plotter(0, 5, 0, 5);

        // "connect" the first data stream to the X input and the second
        // data stream to the Y input
        p.connectInput(streams.get(1), 'x');
        p.connectInput(streams.get(2), 'y');
        
        img = p.plot(); // plot the image 
        ImageIO.write(img, "PNG", new File("plot.png"));        
    }
}
