//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Frozen Statue
// Files: WinterCarnival.java, StarshipRobot.java, DancingBadger.java
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

/**
 * This class implements the frozen statue in the graphical simulation.
 * 
 */
public class FrozenStatue {
  protected float x;
  protected float y;
  protected boolean isFacingRight;
  protected String imageName;


  /**
   * Creates a new Frozen Statue object.
   * 
   * @param arrInput float array containing the x and y positions of the object.
   */
  public FrozenStatue(float[] arrInput) {
    this.x = arrInput[0];
    this.y = arrInput[1];
    this.isFacingRight = true;
    this.imageName = "images" + File.separator + "frozenStatue.png";
  }

  /**
   * Ensures that the draw method from the SimulationEngine class is called on the SimulationEngine
   * type object that is passed.
   * 
   * @param engine coin to be added to this elastic bank
   */
  public void update(SimulationEngine engine) {
    engine.draw(imageName, x, y, isFacingRight);
  }
}
