package application;


import java.util.NoSuchElementException;

/**
 * Placeholder class for the Backend created methods
 * 
 * @author Charles Caton
 *
 */
public class CampusGPSBackendFD implements CampusGPSBackendInterface {

  /**
   * Placeholder constructor
   */
  //public CampusGPSBackendFD(PathAtoBFD p) {

  //}

  /**
   * Placeholder method that returns a pre set shortest path from point A to B
   */
  @Override
  public String getShortestPath(String start, String[] stops, String end)
      throws NoSuchElementException {
    String string = ("Bascom -> Starbucks -> Memorial Union -> Sterling");
    return string;
  }

  /**
   * Placeholder method that returns a pre set shortest way to get to all locations
   */
  @Override
  public String getMinimumSpanningTree() {
    String string = ("start -> stop1 -> stop 2 -> stop3 -> end");
    return string;
  }

  /**
   * Placeholder method that returns the information about a given location
   */
  @Override
  public String getLocationDetails(String location) throws NoSuchElementException {
    String string = ("Capacity: 1000\nYear Built: 1921\nClass: MATH 222\nProfessor: Dr. Caton");
    return string;
  }


}
