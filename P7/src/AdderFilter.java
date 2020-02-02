/* File: AdderFilter.java
 * Author: Connor McGarty, cmcgarty@uab.edu 
 * Assignment: Programmette 7 EE333 Spring 2018
 * Vers: 1.0.0 - 03/27/18 csm - no implementation started
 */


/**
 * The AdderFilter adds points from its two inputs and produces a 
 * {@link DataStream} object containing the sums. 
 *
 * @author Connor McGarty cmcgarty@uab.edu
 */
public class AdderFilter implements Filter, Chainable {
    
    private Chainable  input1; // addend 1
    private Chainable  input2; // addend 2
    private DataStream inData1; // data from addend 1
    private DataStream inData2; // data from addend 2
    private DataStream output; // output data
    
    /**
     * Constructs an AdderFilter.
     * 
     */
    public AdderFilter() {
        input1 = null;
        input2 = null;
    }
    
    /**
     * Applies the filter's operation. Adds the values from two inputs' equivalent
     * indices and appends each sum to the output stream.
     * 
     */
    @Override
    public void apply() {
        output = new DataStream();
        
        for (int i = 1; i < inData1.getLength() + 1; i++) {
            output.add(inData1.getField(i) + inData2.getField(i));
        }
    }

    /**
     * Do not use. AdderFilter requires two inputs. WILL throw exception.
     * 
     * @param input do not use
     */
    @Override
    public void connectInput(Chainable input) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Connects the two addend input Chainable objects.
     * 
     * @param in1 addend 1 object
     * @param in2 addend 2 object
     */
    @Override
    public void connectInput(Chainable in1, Chainable in2) {
        input1 = in1;
        input2 = in2;
    }

    /**
     * Gets the data from filter's inputs, and once received, calls its apply() 
     * method.
     * 
     * @return output of the filter operation 
     */
    @Override
    public DataStream requestData() {
        if (input1 instanceof DataStream && input2 instanceof DataStream) {
            inData1 = (DataStream) input1;
            inData2 = (DataStream) input2;
            this.apply();
            return output;
        } else if (input1 instanceof DataStream && input2 instanceof Filter) {
            inData1 = (DataStream) input1;
            inData2 = (DataStream) input2.requestData();
            this.apply();
            return output;
        } else if (input1 instanceof Filter && input2 instanceof DataStream) {
            inData1 = (DataStream) input1.requestData();
            inData2 = (DataStream) input2;
            this.apply();
            return output;
        } else {
            inData1 = (DataStream) input1.requestData();
            inData2 = (DataStream) input2.requestData();
            this.apply();
            return output;
        }
    }
}
