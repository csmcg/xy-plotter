/* File: StreamSet.java
 * Author: Connor McGarty, cmcgarty@uab.edu 
 * Assignment: Programmette 7 EE333 Spring 2018
 * Vers: 1.0.0 - 03/30/18 csm - initial coding, parseStream() completed
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**
 * StreamSet object has methods for transforming .csv files into an ArrayList of 
 * DataStream objects, where a DataStream objects holds all of the values in one
 * column of the file. 
 * 
 * A StreamSet is 1 indexed, not 0 indexed.
 *
 * @author Connor McGarty cmcgarty@uab.edu
 */
public final class StreamSet {
    
    static final int                       BUFF_SIZE   = 10000; // buffer 
                                                                //size for counting
                                                                // streams. increase for large files
    private final ArrayList<DataStream>    streams; // the List of DS objs
    private final int                      numStreams; // the number of streams (columns in file)
    private final BufferedReader           r; // reader objects passed to constructor

    /**
     * Constructs a StreamSet object and counts the streams (columns) in a file.
     * 
     * A StreamSet is 1 indexed, not 0 indexed.
     * 
     * @param r BufferedReader of a .csv file
     * 
     * @throws FileNotFoundException exception if file cant be read
     * @throws IOException exception if file cant be read
     */
    public StreamSet(BufferedReader r) throws FileNotFoundException, IOException {
        this.r = r;
        streams = new ArrayList<>();
        streams.add(0, null);            // add the "bonus" stream
        numStreams = this.countStreams();
    }
    
    /**
     * Parses the supplied .csv file on construction into an ArrayList of 
     * DataStream objects.
     * 
     * @throws IOException exception if file cannot be read
     */
    public void parseStreams() throws IOException {
        String   line;
        String[] row;
        Double   field;
        
        for (int j = 0; j < numStreams + 1; j++) {
            streams.add(j, new DataStream());      // create DS objs and add to
                                                   // list
        }

        do {
            line = r.readLine();
            
            try {
                row = line.split(",");
                
                for (int i = 0; i < numStreams; i++) {
                    field = Double.valueOf(row[i]);
                    streams.get(i + 1).add(field);
                }
            }
            catch (NullPointerException e) {
                break;
            }
        } while (true);
    }
    
    /**
     * Count the number of columns in a .csv file. 
     * 
     * @return number of columns in supplied .csv file
     * @throws IOException 
     */
    private int countStreams() throws IOException {
        String firstLine;
        String[] firstLineStr;
        
        r.mark(BUFF_SIZE);
        firstLine = r.readLine();
        firstLineStr = firstLine.split(",");
        r.reset();
        
        return firstLineStr.length;
    }
    
    /**
     * Return the number of streams (columns) in the file.
     * 
     * @return number of streams in file
     */
    public int getNumStreams() {
        return numStreams;
    }
    
    /**
     * Return the ArrayList of streams.
     * 
     * @return ArrayList of DataStream objects parsed from the file
     */
    public ArrayList<DataStream> getStreams() {
        return streams;
    }
    

}
