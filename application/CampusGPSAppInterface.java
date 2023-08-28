package application;


import java.util.NoSuchElementException;

/**
 * Interface for the CampusGPSApp
 * 
 * @author Charles Caton
 *
 */
public interface CampusGPSAppInterface {
  /**
   * Uses the backend code to compute the optimal path from the given start location to the final
   * destination covering all the optional stops and returns a string representation of it
   *
   * @param start - The name of the starting location
   * @param stops - The names of the optional stops
   * @param end   - The name of the final location
   * @return The formatted string representation of the optimal path
   *
   * @throws NoSuchElementException if the start location, final destination, or the optional stops
   *                                do not exist in the map
   */
  public String getPath(String start, String[] stops, String end) throws NoSuchElementException;

  /**
   * Get’s all the details of the given end-location using the Backend’s getLocationDetails(String)
   * method
   *
   * @param end - The name of the final location
   * @return The formatted string representation of the details of the end location
   *
   * @throws NoSuchElementException if the location does not exist in the map
   */
  public String getEndLocationDetails(String end) throws NoSuchElementException;

  /**
   * Gets a string representation of the minimum spanning tree of the graph by calling appropriate
   * methods from the backend
   *
   * @return the string representation of the MST of the graph (of the campus map)
   */
  public String getMST();
}

