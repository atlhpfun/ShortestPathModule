package application;


import java.util.NoSuchElementException;

/**
 * Class of methods that get information from the backend and allows them to be put into the javaFX
 * application
 * 
 * @author Charles Caton
 *
 */

//TODO worst case swap stuff to my placeholders
public class CampusGPSApp implements CampusGPSAppInterface {
  DataLoaderDW loader = new DataLoaderDW("network.dot");
  //RoadDW a2b = new RoadDW(null, null, null, 0);
  //TODO swap to pathAtoB if needed
  Digraph digraph = new Digraph(loader);
  //Starting Ending Name Value
  CampusGPSBackendDeveloper bd = new CampusGPSBackendDeveloper(loader, digraph);
  //dataLoader and digraph

  /**
   * Gets the best path from a given starting point to a given end with any stops along the way
   * 
   * @param start The user decided starting location
   * @param stops An array of places the user wants to stop during this trek
   * @param end   The user decided stopping location
   * @return a string representation of the path from A to B
   */
  @Override
  public String getPath(String start, String[] stops, String end) throws NoSuchElementException {
    String string = bd.getShortestPath(start, stops, end);
    return string;
  }

  /**
   * Gets the details about the given location from the backend code
   * 
   * @param end Given end location the user decided
   * @return A string of information about said location
   */
  @Override
  public String getEndLocationDetails(String end) throws NoSuchElementException {
    String string = bd.getLocationDetails(end);
    return string;
  }

  /**
   * Gets a representation of the minimum spanning tree that the backend creates
   * 
   * @return A string representation of the MST
   */
  @Override
  public String getMST() {
    String string = bd.getMinimumSpanningTree();
    return string;
  }

}
