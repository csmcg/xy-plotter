/* File: DataStream.java
 * Author: Connor McGarty, cmcgarty@uab.edu 
 * Assignment: Programmette 7 EE333 Spring 2018
 * Vers: 1.0.0 - 03/30/18 csm - initial coding, constructor and add()
 */

import java.util.ArrayList;

/**
 * The DataStream objects is an abstraction of an ArrayList of Double objects.
 * It implements the {@link Chainable} object, so data and procedures can be
 * treated equally and passed around a filter chain.
 *
 * @author Connor Mcgarty cmcgarty@uab.edu
 */
public class DataStream implements Chainable {
    
    private ArrayList<Double>   data; // the list of double in the stream
    private int                 length; // the number of doubles in the stream

    /**
     * Construct the DataStream object. 
     * 
     */
    public DataStream() {
        data = new ArrayList<>();
        data.add(0, null);
        length = 0;
    }
    
    /**
     * Append a double to the DataStream. 
     * 
     * @param field number to be added to stream
     */
    public void add(Double field) {
        data.add(field);
        length++;
    }
    
    /**
     * Return the length of the stream.
     * 
     * @return length of data stream
     */
    public int getLength() {
        return length;
    }
    
    /**
     * Return a particular field.
     * 
     * @param index index of field to returned from stream
     * @return field located at supplied index in stream
     */
    public Double getField(int index) {
        return data.get(index);
    }
    
    /**
     * Return the ArrayList of doubles.
     * 
     * @return ArrayList of doubles.
     */
    public ArrayList<Double> getStream() {
        return data;
    }
    
    /**
     * Returns itself. 
     * 
     * @return returns reference to the object that called the method 
     */
    @Override
    public DataStream requestData() {
        return this;
    }

    /**
     * Not implemented, do NOT use.
     * @param in1 don't use
     * @param in2 don't use
     */
    @Override
    public void connectInput(Chainable in1, Chainable in2) {
        ;
    }

    /**
     * Not implemented, do NOT use.
     * @param in don't use 
     */
    @Override
    public void connectInput(Chainable in) {
        ;
    }
}