package application;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class Digraph implements DigraphInterface {
  public DataLoaderDW input;

  public Digraph(DataLoaderDW input) {
    this.input = input;
  }

  protected class Cost implements Comparable<Cost> {
    public RoadDW road;
    public double costSoFar;
    public Cost previous;

    public Cost(RoadDW road, double costSoFar, Cost previous) {
      this.road = road;
      this.costSoFar = costSoFar;
      this.previous = previous;
    }
    @Override
    public int compareTo(Cost other) {
      if (costSoFar > other.costSoFar)
        return +1;
      if (costSoFar < other.costSoFar)
        return -1;
      return 0;
    }
    
  }

  @Override
  public List<RoadDW> findShortestPathWithStops(BuildingNodeDW start, List<BuildingNodeDW> stops, BuildingNodeDW end) {
    if (stops.size() == 0) {
      return findShortestPath(start, end);
    }
    List<RoadDW> totalPath = findShortestPath(start, stops.get(0));
    for (int i = 1; i < stops.size(); i++) {
      if (i == stops.size() - 1) {
        totalPath.addAll(findShortestPath(stops.get(i), end));
      } else {
        totalPath.addAll(findShortestPath(stops.get(i), stops.get(i + 1)));
      }
    }
    return totalPath;
  }

  private List<RoadDW> findShortestPath(BuildingNodeDW start, BuildingNodeDW end) throws NoSuchElementException {
    int checker = 0;
    for(int i = 0 ; i < input.listNodes().size(); i++) {
      if(input.listNodes().get(i).getName().equals(end.getName())) {
        checker++;
      }
      if(input.listNodes().get(i).getName().equals(start.getName())) {
        checker++;
      }
   //   System.out.println(input.listNodes().get(i).getName());
    }
    if(checker < 2) {
      throw new NoSuchElementException("Start or end ain't in the map");
    }
//    if (!input.listNodes().contains(start) || !input.listNodes().contains(end)) {
//      throw new NoSuchElementException("Start or end is not in the map.");
//    }
    List<Cost> counting = new ArrayList<Cost>();
    for (int i = 0; i < input.listNodes().size(); i++) {
      counting.add(new Cost(input.loadNodesandEdges().get(i), 0, null));
    }
    PriorityQueue<Cost> queue = new PriorityQueue<Cost>();
    Hashtable<String, Cost> path = new Hashtable<String, Cost>();
  //  BuildingNodeDW repeat = new BuildingNodeDW("repeat");
    Cost repeater = new Cost(new RoadDW(start, start, "Default", 99), 0, null);
    queue.add(repeater);
    path.put(start.getName(), repeater);
 //   System.out.println(path.containsKey(repeater.road.getEndingNode().getName()));
    while (!queue.isEmpty()) {
    //  System.out.println("bruh");
      Cost hold = queue.poll();
      System.out.println(hold.road.toString() + " totalCostSoFar " + hold.costSoFar + " queue size " + queue.size());
      if(hold.road.getName().equals("Default") ) {
    	  for(int i = 0; i < counting.size(); i++) {
    		  if(counting.get(i).road.start.getName().equals(start.getName()) || counting.get(i).road.end.getName().equals(start.getName())) {
    			  queue.add(counting.get(i));
    		  }
    	  }
      }
      else if(path.containsKey(hold.road.getStartingNode().getName()) && path.containsKey(hold.road.getEndingNode().getName())) {
    	  
      }
      else if (!path.containsKey(hold.road.getStartingNode().getName())) {
    	  System.out.println("Doesn't contain " + hold.road.getStartingNode().name);
        path.put(hold.road.getStartingNode().getName(), hold);
        System.out.println(path.containsKey(hold.road.getStartingNode().getName()));
        for (int i = 0; i < counting.size(); i++) {
          if ((counting.get(i).road.start.getName().equals(hold.road.getStartingNode().getName()) && !path.containsKey(counting.get(i).road.end.getName())) || (counting.get(i).road.end.getName().equals(hold.road.getStartingNode().getName()) && !path.containsKey(counting.get(i).road.start.getName()))) {
       //   System.out.println("bro " + counting.get(i).road.toString());
        	double addedCost = hold.costSoFar + hold.road.weight;
            queue.add(new Cost(counting.get(i).road, addedCost, hold));
          }
        }
      } 
      else if (!path.containsKey(hold.road.getEndingNode().getName())) {
    	  System.out.println("Doesn't contain " + hold.road.getEndingNode().name);
        path.put(hold.road.getEndingNode().getName(), hold);
        System.out.println(path.containsKey(hold.road.getEndingNode().getName()));
        for (int i = 0; i < counting.size(); i++) {
          if ((counting.get(i).road.start.getName().equals(hold.road.getEndingNode().getName()) && !path.containsKey(counting.get(i).road.end.getName())) || (counting.get(i).road.end.getName().equals(hold.road.getEndingNode().getName())) && !path.containsKey(counting.get(i).road.start.getName())) {
        	//  System.out.println("bruh " + counting.get(i).road.toString());
        	  double addedCost = hold.costSoFar + hold.road.weight;
            queue.add(new Cost(counting.get(i).road, addedCost, hold));
          }
        }
      }
      System.out.println(path.size());


    }
    if(!path.containsKey(end.getName())) {
    	throw new NoSuchElementException("path from start to end does not exist.");
    }
    else {
    	List<Cost> holder = new ArrayList<Cost>();
    	Cost current = path.get(end.getName());
    	while(!(current.road.end.getName().equals(start.getName()) || current.road.start.getName().equals(start.getName()))) {
    		holder.add(0, current);
    		current = current.previous;
    	}
    	List<RoadDW> returning = new ArrayList<RoadDW>();
    	for(int i = 0; i < holder.size(); i++) {
    		returning.add(0, holder.get(i).road);
    	}
    	return returning;
    }
    
    
  }

  @Override
  public List<RoadDW> findMST() {
    PriorityQueue<RoadDW> roads = new PriorityQueue<RoadDW>();
    ArrayList<BuildingNodeDW> visited = new ArrayList<BuildingNodeDW>();
    List<RoadDW> graph = new ArrayList<RoadDW>();
    BuildingNodeDW first = (BuildingNodeDW) input.listNodes().get(0);
    visited.add(first);
    for (int i = 0; i < input.loadNodesandEdges().size(); i++) {
      if (input.loadNodesandEdges().get(i).getStartingNode().equals(first)
          || input.loadNodesandEdges().get(i).getEndingNode().equals(first)) {
        roads.add(input.loadNodesandEdges().get(i));
      }
    }
    while (!roads.isEmpty()) {
      RoadDW hold = roads.remove();
      if (!visited.contains(hold.getStartingNode())) {
        visited.add(hold.getStartingNode());
        for (int i = 0; i < input.loadNodesandEdges().size(); i++) {
          if (input.loadNodesandEdges().get(i).getStartingNode().equals(hold.getStartingNode())
              && !input.loadNodesandEdges().get(i).getEndingNode().equals(hold.getEndingNode())) {
            roads.add(input.loadNodesandEdges().get(i));
          } else if (input.loadNodesandEdges().get(i).getEndingNode().equals(hold.getStartingNode())
              && !input.loadNodesandEdges().get(i).getEndingNode().equals(hold.getEndingNode())) {
            roads.add(input.loadNodesandEdges().get(i));
          }
        }
        graph.add(hold);
      }
      if (!visited.contains(hold.getEndingNode())) {
        visited.add(hold.getEndingNode());
        for (int i = 0; i < input.loadNodesandEdges().size(); i++) {
          if (input.loadNodesandEdges().get(i).getStartingNode().equals(hold.getEndingNode())
              && !input.loadNodesandEdges().get(i).getEndingNode().equals(hold.getStartingNode())) {
            roads.add(input.loadNodesandEdges().get(i));
          } else if (input.loadNodesandEdges().get(i).getEndingNode().equals(hold.getEndingNode())
              && !input.loadNodesandEdges().get(i).getEndingNode().equals(hold.getStartingNode())) {
            roads.add(input.loadNodesandEdges().get(i));
          }
        }
        graph.add(hold);
      }


    }
    return graph;
  }

  @Override
  public BuildingNodeDW getNode(String nodeName) throws NoSuchElementException {
    int size = input.listNodes().size();
    for (int i = 0; i < size; i++) {
      if (input.listNodes().get(i).getName().equals(nodeName)) {
        return input.listNodes().get(i);
      }
    }
    throw new NoSuchElementException("Building of that name does not exist");
  }

}
