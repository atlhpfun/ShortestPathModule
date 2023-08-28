package application;

public class RoadDW implements RoadInterface, Comparable<RoadDW>{
  public BuildingNodeDW start;
  public BuildingNodeDW end;
  public String name;
  public int weight;

  public RoadDW(BuildingNodeDW startingNode, BuildingNodeDW endingNode, String name, int weight) {
    this.start = startingNode;
    this.end = endingNode;
    this.name = name;
    this.weight = weight;
  }

  public String getName() {
    return name;
  }

  public int getWeight() {
    return weight;
  }

  public BuildingNodeDW getStartingNode() {
    return start;
  }

  public BuildingNodeDW getEndingNode() {
    return end;
  }
  public String toString() {
    return "From: " + this.start.name + " to: " + this.end.name + " on: " + name + " weight " + weight; 
  }
  @Override
  public int compareTo(RoadDW x) {
    if (this.getWeight() == x.getWeight()) {
      return 0;
    }
    if (this.getWeight() < x.getWeight()) {
      return -1;
    } else {
      return 1;
    }
  }
}
