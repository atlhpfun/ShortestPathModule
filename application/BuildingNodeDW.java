package application;
public class BuildingNodeDW implements BuildingNodeInterface {
  String name;
  int capacity;
  int numRooms;
  String professor;

  public BuildingNodeDW(String name) {
    this.name = name;
  }

  public BuildingNodeDW(String name, int capacity, int numRooms, String professor) {
    this.name = name;
    this.capacity = capacity;
    this.numRooms = numRooms;
    this.professor = professor;
  }

  public String getName() {
    return name;
  }

  public int getCapacity() {
    return capacity;
  }

  public int getNumOfRooms() {
    return numRooms;
  }

  public String getProfessor() {
    return professor;
  }

}
