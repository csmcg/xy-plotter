/* File: Chainable.java
 * Author: Connor McGarty, cmcgarty@uab.edu
 * Assignment: Programmette 7 EE333 Spring 2018
 * Vers: 1.0.0 csm - 03/27/18 initial coding
 *
 */

/**
 * The Chainable interface provides public methods for connecting Chainable
 * objects one another, and passing data between them.
 * 
 * @author Connor McGarty cmcgarty@uab.edu
 */
public interface Chainable {
    
    public void       connectInput(Chainable in1, Chainable in2);
    public void       connectInput(Chainable in);
    public DataStream requestData(); // get data from inputs
}