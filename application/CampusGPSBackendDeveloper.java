package application;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CampusGPSBackendDeveloper implements CampusGPSBackendInterface {
  private Digraph g;

  public CampusGPSBackendDeveloper(DataLoaderDW dataloader, Digraph g) {
    this.g = g;
//    for (BuildingNodeDW n : dataloader.listNodes())
//      g.insertNode(n);
//    for (RoadDW r : dataloader.loadNodesandEdges())
//      g.insertEdge(r.getStartingNode(), r.getEndingNode(), r.getWeight());
  }

  public String getShortestPath(String start, String[] stops, String end)
      throws NoSuchElementException {
    List<BuildingNodeDW> stopBuildings = new ArrayList<>();
    if (stops != null)
      for (int i = 0; i < stops.length; i++)
        stopBuildings.add(g.getNode(stops[i]));
    List<RoadDW> result =
        g.findShortestPathWithStops(g.getNode(start), stopBuildings, g.getNode(end));
    String ans = new String();
    for (int i = 0; i < result.size(); i++)
      ans += result.get(i).getName() + "\n";
    return ans;
  }

  public String getMinimumSpanningTree() {
    List<RoadDW> result = g.findMST();
    String ans = new String();
    for (int i = 0; i < result.size(); i++)
      ans += "Edge Start: " + result.get(i).getStartingNode().getName() + ", Edge End: "
          + result.get(i).getEndingNode().getName() + ", Edge Weight: " + result.get(i).getWeight()
          + "\n";
    return ans;
  }

  public String getLocationDetails(String location) throws NoSuchElementException {
    BuildingNodeDW n = g.getNode(location);
    return "Name: " + n.getName() + "\n Capacity: " + n.getCapacity() + "\n Number of Rooms: "
        + n.getNumOfRooms() + "\n Professor: " + n.getProfessor() + "\n";
  }
}
