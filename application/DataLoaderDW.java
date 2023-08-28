package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataLoaderDW implements DataLoaderInterface {
  String filename;

  public DataLoaderDW(String filename) {
    this.filename = filename;
  }

  /*
   * This method parses the dot file and loads all the roads (edges) into a List
   * 
   */
  public List<RoadDW> loadNodesandEdges() {
    List<RoadDW> x = new ArrayList<RoadDW>(); // creates a list with element Road (the edges)
    File file = new File(filename); // creates a file based on the inputed filename
    try {
      Scanner newScan = new Scanner(file); // creates a scanner
      while (newScan.hasNextLine()) { // loop runs while there is a another line in the file
        String fullValue = newScan.nextLine(); // creates a string with the contents of the line
        if (fullValue.indexOf("--") >= 0) { // checks to make sure the line contains "--", because
                                            // that means it is an edge
          String firstBuilding = fullValue.substring(0, fullValue.indexOf("-")).trim(); // string
                                                                                        // containing
                                                                                        // the first
                                                                                        // building
          BuildingNodeDW startingBuilding = new BuildingNodeDW(firstBuilding); // creates a
                                                                               // BuildingNode
                                                                               // object with the
                                                                               // name of this first
                                                                               // building
          String restOf = fullValue.substring(fullValue.indexOf("-")); // String with the rest of
                                                                       // the line minus the first
                                                                       // building
          String secondBuilding = restOf.substring(2, restOf.indexOf("[")).trim(); // String
                                                                                   // containing the
                                                                                   // second
                                                                                   // building
          BuildingNodeDW endingBuilding = new BuildingNodeDW(secondBuilding); // Building object
                                                                              // containing this
                                                                              // second building
          String afterSecond = restOf.substring(restOf.indexOf("\"") + 1); // String containing the
                                                                           // remaining line after
                                                                           // the first and second
                                                                           // building
          String roadName = afterSecond.substring(0, afterSecond.indexOf("\"")).trim(); // String
                                                                                        // containing
                                                                                        // the name
                                                                                        // of the
                                                                                        // road
          int weightValue = Integer.parseInt(
              afterSecond.substring(afterSecond.indexOf("=") + 1, afterSecond.indexOf("]")).trim()); // int
                                                                                                     // containing
                                                                                                     // the
                                                                                                     // weight
          RoadDW newRoad = new RoadDW(startingBuilding, endingBuilding, roadName, weightValue); // a
                                                                                                // road
                                                                                                // object
                                                                                                // containing
                                                                                                // all
                                                                                                // the
                                                                                                // correct
                                                                                                // parameters
          x.add(newRoad); // adds the road object to the list
        }
      }
      newScan.close(); // closes the scanner
    } catch (FileNotFoundException e) { // catches the exception if the file is not found
      System.out.println("File not found!");
    }
    return x;
  }

  /*
   * This method parses the dot file and loads all the buildings (nodes) in a list
   * 
   */
  public List<BuildingNodeDW> listNodes() {
    List<BuildingNodeDW> x = new ArrayList<BuildingNodeDW>(); // list with element BuildingNode
    File file = new File(filename); // creates a file passed on the file parameter put through the
                                    // constructor
    try {
      Scanner newScan = new Scanner(file); // creates a scanner
      while (newScan.hasNextLine()) { // loop runs while there is a another line in the file
        String fullValue = newScan.nextLine(); // A string of the entire line
        if (fullValue.indexOf("size") > 0) { // checks to make sure size is in the string because
                                             // this means its a node and not a road
          String nameValue = fullValue.substring(0, fullValue.indexOf("[")).trim(); // gets the name
                                                                                    // of the node
          BuildingNodeDW newBuilding = new BuildingNodeDW(nameValue); // creates a BuildingNode
                                                                      // object with the name
          x.add(newBuilding); // adds the building to the list
        }
      }
      newScan.close(); // closes the scanner
    } catch (FileNotFoundException e) { // catches the exception if the file is not found
      System.out.println("File not found!");
    }
    return x;
  }
}
