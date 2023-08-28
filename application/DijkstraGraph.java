package application;

// --== CS400 File Header Information ==--
// Name: Rohan Balachander
// Email: rbalachander@wisc.edu
// Group and Team: DD Blue
// Group TA: Callie Kim
// Lecturer: Florian Heimerl
// Notes to Grader:

import java.util.PriorityQueue;

// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.Test;

import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class extends the BaseGraph data structure with additional methods for computing the total
 * cost and list of node data along the shortest path connecting a provided starting to ending
 * nodes. This class makes use of Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number> extends BaseGraph<NodeType, EdgeType>
    implements GraphADT<NodeType, EdgeType> {

  /**
   * While searching for the shortest path between two nodes, a SearchNode contains data about one
   * specific path between the start node and another node in the graph. The final node in this path
   * is stored in it's node field. The total cost of this path is stored in its cost field. And the
   * predecessor SearchNode within this path is referened by the predecessor field (this field is
   * null within the SearchNode containing the starting node in it's node field).
   *
   * SearchNodes are Comparable and are sorted by cost so that the lowest cost SearchNode has the
   * highest priority within a java.util.PriorityQueue.
   */
  protected class SearchNode implements Comparable<SearchNode> {
    public Node node;
    public double cost;
    public SearchNode predecessor;

    public SearchNode(Node node, double cost, SearchNode predecessor) {
      this.node = node;
      this.cost = cost;
      this.predecessor = predecessor;
    }

    public int compareTo(SearchNode other) {
      if (cost > other.cost)
        return +1;
      if (cost < other.cost)
        return -1;
      return 0;
    }
  }

  /**
   * This helper method creates a network of SearchNodes while computing the shortest path between
   * the provided start and end locations. The SearchNode that is returned by this method is
   * represents the end of the shortest path that is found: it's cost is the cost of that shortest
   * path, and the nodes linked together through predecessor references represent all of the nodes
   * along that shortest path (ordered from end to start).
   *
   * @param start the data item in the starting node for the path
   * @param end   the data item in the destination node for the path
   * @return SearchNode for the final end node within the shortest path
   * @throws NoSuchElementException when no path from start to end is found or when either start or
   *                                end data do not correspond to a graph node
   */
  protected SearchNode computeShortestPath(NodeType start, NodeType end)
      throws NoSuchElementException {
    if (super.containsNode(start) == false || super.containsNode(end) == false) { // checks if
                                                                                  // graphs contains
                                                                                  // start and end
                                                                                  // nodetype
      throw new NoSuchElementException("start or end is null");
    }
    PriorityQueue<SearchNode> queue = new PriorityQueue<SearchNode>(); // creates priorityqueue to
                                                                       // add searchNodes
    Hashtable<Node, SearchNode> path = new Hashtable<Node, SearchNode>(); // creates hashtable to
                                                                          // add paths
    SearchNode first = new SearchNode(nodes.get(start), 0, null);
    queue.add(first);
    while (!queue.isEmpty()) { // loops through queue by cost until empty
      SearchNode hold = queue.poll(); // takes top searchNode
      if (!path.containsKey(hold.node)) { // checks if SearchNode node is in the hashtable
        path.put(hold.node, hold); // if not in hashtable, add to path
        for (int i = 0; i < hold.node.edgesLeaving.size(); i++) { // loop through leaving edges
          double addedCost = hold.cost + hold.node.edgesLeaving.get(i).data.doubleValue(); // creates new cost variable and adds to previous cost so far
          SearchNode add = new SearchNode(hold.node.edgesLeaving.get(i).successor, addedCost, hold); // Creates new SearchNode for each successor
          queue.add(add); // adds to queue
        }
      }
    }
    if (!path.containsKey(nodes.get(end))) { // Checks if hashtable doesn't have end node
      throw new NoSuchElementException("no path from start to end");
    } else {
      return path.get(nodes.get(end)); // returns SearchNode containing cost and predecessor
    }

  }

  /**
   * Returns the list of data values from nodes along the shortest path from the node with the
   * provided start value through the node with the provided end value. This list of data values
   * starts with the start value, ends with the end value, and contains intermediary values in the
   * order they are encountered while traversing this shorteset path. This method uses Dijkstra's
   * shortest path algorithm to find this solution.
   *
   * @param start the data item in the starting node for the path
   * @param end   the data item in the destination node for the path
   * @return list of data item from node along this shortest path
   */
  public List<NodeType> shortestPathData(NodeType start, NodeType end) {
    List<NodeType> use = new LinkedList<NodeType>(); // linked list to store path
    SearchNode test = computeShortestPath(start, end); // Gets SearchNode of shortestPath
    while (test.predecessor != null) { // loops through shortest path from back to front
      use.add(0, test.node.data); // adds each node data to front of linked list
      test = test.predecessor; // sets test to predecessor
    }
    use.add(0, start); // adds start node to front
    return use; // returns list of path
  }

  /**
   * Returns the cost of the path (sum over edge weights) of the shortest path freom the node
   * containing the start data to the node containing the end data. This method uses Dijkstra's
   * shortest path algorithm to find this solution.
   *
   * @param start the data item in the starting node for the path
   * @param end   the data item in the destination node for the path
   * @return the cost of the shortest path between these nodes
   */
  public double shortestPathCost(NodeType start, NodeType end) {
    SearchNode use = computeShortestPath(start, end); // gets searchNode of shortest path
    return use.cost; // returns cost of shortest Path
  }
}
