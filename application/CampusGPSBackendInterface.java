package application;

import java.util.NoSuchElementException;

/**
 * Placeholder class for the Backend Developers Interface
 * 
 * @author Charles Caton
 *
 */
public interface CampusGPSBackendInterface {
  // public CampusGPSBackend (PathAtoB p)
  public String getShortestPath(String start, String[] stops, String end)
      throws NoSuchElementException;

  public String getMinimumSpanningTree();

  public String getLocationDetails(String location) throws NoSuchElementException;
}
