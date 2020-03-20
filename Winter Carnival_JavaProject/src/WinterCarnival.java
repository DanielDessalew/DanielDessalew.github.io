//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Winter Carnival
// Files: FrozenStatue.java, StarshipRobot.java, DancingBadger.java
// Course: CS 300 Spring 2020
//
// Author: Daniel Dessalew
// Email: dessalew@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////// PAIR PROGRAMMING (MAY SKIP WHEN WORKING INDIVIDUALLY) ////////////
//
// Partner Name: None
// Partner Email: N/A
// Partner Lecturer's Name: N/A
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X__ Write-up states that pair programming is allowed for this assignment.
// _X__ We have both read and understood the course Pair Programming Policy.
// _X__ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Students who get help from sources other than their partner and the course
// staff must fully acknowledge and credit those sources here. If you did not
// receive any help of any kind from outside sources, explicitly indicate NONE
// next to each of the labels below.
//
// Persons: None
// Online Sources: zyBooks
//
///////////////////////////////////////////////////////////////////////////////
import java.io.File;
import java.util.ArrayList;

/**
 * Creates a WinterCarnival class that inherits from the SimulationEngine class.
 * 
 */
public class WinterCarnival extends SimulationEngine {
  private ArrayList<FrozenStatue> objects;

  /**
   * Creates a new WinterCarnival object, and initializes the objects arraylist. Creates and adds
   * FrozenStatue, StarshipRobot, and DancingBager objects to the arraylist.
   * 
   */
  public WinterCarnival() {
    // Frozen statue objects
    this.objects = new ArrayList<FrozenStatue>();
    float[] frzStat1Position = new float[] {600, 100};
    FrozenStatue frzStat1 = new FrozenStatue(frzStat1Position);
    objects.add(frzStat1);
    float[] frzStat2Position = new float[] {200, 500};
    FrozenStatue frzStat2 = new FrozenStatue(frzStat2Position);
    objects.add(frzStat2);

    // Starship Robots objects
    float[][] starshipRob1Position = new float[][] {{0, 0}, {600, 100}};
    StarshipRobot starshipRob1 = new StarshipRobot(starshipRob1Position);
    objects.add(starshipRob1);
    float[][] starshipRob2Position = new float[][] {{800, 300}, {200, 500}};
    StarshipRobot starshipRob2 = new StarshipRobot(starshipRob2Position);
    objects.add(starshipRob2);

    // Dancing Badger objects
    DanceStep[] danceStepsSeq =
        {DanceStep.Left, DanceStep.Right, DanceStep.Right, DanceStep.Left, DanceStep.Down,
            DanceStep.Left, DanceStep.Right, DanceStep.Right, DanceStep.Left, DanceStep.Up};
    float[] badger1Start = new float[] {304, 268};
    DancingBadger badger1 = new DancingBadger(badger1Start, danceStepsSeq);
    objects.add(badger1);
    float[] badger2Start = new float[] {368, 268};
    DancingBadger badger2 = new DancingBadger(badger2Start, danceStepsSeq);
    objects.add(badger2);
    float[] badger3Start = new float[] {432, 268};
    DancingBadger badger3 = new DancingBadger(badger3Start, danceStepsSeq);
    objects.add(badger3);
    float[] badger4Start = new float[] {496, 268};
    DancingBadger badger4 = new DancingBadger(badger4Start, danceStepsSeq);
    objects.add(badger4);

  }

  /**
   * Ensures that the winter carnival calls update and draws each of the objects in its arraylist.
   * 
   * @Override
   */
  public void update() {
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).update(this);
    }
  }

  /**
   * Calls the WinterCarnival constructor to create a new object.
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    WinterCarnival sim = new WinterCarnival();

  }
}
