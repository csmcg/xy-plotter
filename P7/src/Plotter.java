/* File: Plotter.java
 * Author: Connor McGarty, cmcgarty@uab.edu 
 * Assignment: Programmette 7 EE333 Spring 2018
 * Vers: 1.0.0 - 04/03/18 csm - initial coding
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * The Plotter class is responsible for starting the flow of data from the 
 * bottom of the filter chain that will eventually be received by the Plotter
 * object via its two inputs. Once it has received two DataStream objects from
 * its inputs, it scales the data based on the provided data range to to 
 * canvas size (500x500 pixels) and produces a BufferedImage of the plot.
 * 
 * @author Connor McGarty cmcgarty@uab.edu
 */
public class Plotter implements Chainable {
    
    private final double      xMin; // minimum X point to be plotted
    private final double      xMax; // max X point to be plotted
    private final double      yMin; // min Y point to be plotted
    private final double      yMax; // max Y point to be plotted
    private Chainable         inputX; // obj to be connected to X input 
    private Chainable         inputY; // obj connected to Y input
    private DataStream        xData; // data received from X input
    private DataStream        yData; // data received from Y input
    

    /**
     * Constructs a Plotter object. 
     * 
     * @param xMin minimum X value to be displayed on plot
     * @param xMax maximum X value to be displayed on plot
     * @param yMin minimum Y value to be displayed on plot
     * @param yMax maximum Y value to be displayed on plot
     */
    public Plotter(double xMin, double xMax, double yMin, double yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }
    
    /**
     * Connects a {@link Chainable} object to the supplied input of the Plotter. 
     * 
     * 
     * @param inData {@link Chainable} object to be connected to Plotter 
     * @param input character denoting which input to wire inData to, X
     * or Y (case insensitive)
     * 
     */
    public void connectInput(Chainable inData, char input) {
        if (input == 'x' || input == 'X')
            inputX = inData;
        if (input == 'y' || input =='Y')
            inputY = inData;
    }
    
    /**
     * Scales data from the X input so it can fit to the image.
     * 
     * @param pt point to scale
     * @param min the minimum X value plotted
     * @param max the maximum X value plotted
     * @return scaled data point
     */
    private int scaleX(double pt, double min, double max) {
        return (int) (Math.abs(min - pt) * (500 / (max - min)));
    }
    
    /**
     * Scales data from the Y input so it can be fit to the image.
     * 
     * @param pt point to scale
     * @param min minimum Y value plotted
     * @param max maximum Y value plotted
     * @return scaled data point
     */
    private int scaleY(double pt, double min, double max) {
        return (int) (Math.abs(max - pt) * (500 / (max - min)));
    }    

    /**
     * Constructs the image and graphic objects to write data to image, scales
     * each point to fit the 500x500 image resolution, and draws lines between
     * each successive pair of points. Calls requestData methods on each input,
     * starting the flow of data from the "leaves" of the {@link Chainable} tree 
     * to the Plotter.
     * 
     * @return {@link BufferedImage} object containing the plot of the data
     */
    public BufferedImage plot() {
        
        
        /* scale the data points according to the following formula:
             
            | x/yMin/MaximumPoint - x/yDataPoint | * ((500) / (x/yMax - x/yMin) = 
                                 = x/yPixelPoint
        */ 
        
        BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        Graphics2D    gfx = img.createGraphics();
        gfx.fillRect(0, 0, 500, 500);
        gfx.setPaint(Color.BLACK);
        
        int lastXCoord, lastYCoord, 
            nextXCoord, nextYCoord;
        
        xData = inputX.requestData();
        yData = inputY.requestData();

                        
        lastXCoord = scaleX(xData.getField(1), xMin, xMax);
        lastYCoord = scaleY(yData.getField(1), yMin, yMax);
        
        for (int i = 1; i < xData.getLength(); i++) {
                nextXCoord = scaleX(xData.getField(i+1), xMin, xMax);                        
                nextYCoord = scaleY(yData.getField(i+1), yMin, yMax);
                gfx.drawLine(lastXCoord, lastYCoord, nextXCoord, nextYCoord);
                lastXCoord = nextXCoord;
                lastYCoord = nextYCoord;
        }
            
        return img;
    }

    /**
     * Not implemented. Do not use this method.
     * 
     * @return dont use
     */
    @Override
    public DataStream requestData() {
        return null;
    }

    /**
     * Not implemented. Do not use this method.
     * 
     * @param in dont use
     */
    @Override
    public void connectInput(Chainable in) {
        ;
    }

    /**
     * Not implemented. Do not use this method.
     * 
     * @param in1 dont use
     * @param in2 dont use
     */
    @Override
    public void connectInput(Chainable in1, Chainable in2) {
        ;
    }
    
    
}
