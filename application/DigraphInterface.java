package application;

import java.util.List;
import java.util.NoSuchElementException;

public interface DigraphInterface{
  // public Digraph();
  public List<RoadDW> findShortestPathWithStops(BuildingNodeDW start, List<BuildingNodeDW> stops,
      BuildingNodeDW end);

  public List<RoadDW> findMST();

  BuildingNodeDW getNode(String nodeName) throws NoSuchElementException;
}
