/* File: ScalerFilter.java
 * Author: Connor McGarty, cmcgarty@uab.edu 
 * Assignment: Programmette 7 EE333 Spring 2018
 * Vers: 1.0.0 - 04/03/18 csm - initial coding
 */


/**
 * The ScalerFilter holds one {@link Chainable} input and performs the following
 * operation on that input data: gain * x_i + base
 * 
 * @author Connor McGarty cmcgarty@uab.edu
 */
public class ScalerFilter implements Filter, Chainable {
    
    private Chainable        input;  // scalerFilter only has 1 input
    private DataStream       inData; // data from the input
    private DataStream       output; // output data
    private final Double     gain; // value to multiply input points by
    private final Double     base; // value to add to input points
    
    /**
     * Constructs a ScalerFilter object with supplied parameters. Negative 
     * values and zero are allowed.
     * 
     * @param gain value to multiply input points by
     * @param base value to add to product of input points and gain
     */
    public ScalerFilter(Double gain, Double base) {
        this.gain  =  gain;
        this.base  =  base;
    }
    
    /**
     * Wires the supplied object to the input of the ScalerFilter.
     * 
     * @param in object to connect to Scaler's input
     */
    @Override
    public void connectInput(Chainable in) {
        input = in;
    }    

    /**
     * Applies the ScalerFilter's operation.
     * 
     */
    @Override
    public void apply() {
        output = new DataStream();
        
        // gain * field + base
        for (int i = 1; i < inData.getLength() + 1; i++) {
            output.add(inData.getField(i) * gain + base);
        }
    }

    /**
     * Requests data from filter's inputs, then applies scaling operation.
     * 
     * If the filter's input is pure data, go ahead and apply the filter and
     * send the output.
     * If the filter's input is another filter, ask the child filter for
     * data, then apply the data it receives and send the output.
     * 
     * @return result of ScalerFilter's operation
     */
    @Override
    public DataStream requestData() {
        if (input instanceof DataStream) {
            inData = (DataStream) input;
            this.apply();
            return output;
        }
        else {
            inData = input.requestData();
            this.apply();
            return output;
        }
    }

    /**
     * Do not use this method, Scaler only takes one input. This method WILL
     * thow an exception. 
     * 
     * @param in1 don't use
     * @param in2 don't use
     */
    @Override
    public void connectInput(Chainable in1, Chainable in2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
