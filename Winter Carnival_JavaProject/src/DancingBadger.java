//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Dancing Badger
// Files: FrozenStatue.java, StarshipRobot.java, WinterCarnival.java
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
 * This class implements the dancing badgers in the graphical simulation, inheriting from the
 * StarshipRobot class.
 * 
 */
public class DancingBadger extends StarshipRobot {
  protected DanceStep[] danceSteps;
  protected int stepIndex = 1;

  /**
   * Creates a new DancingBadger object.
   * 
   * @param startingPos float array containing the starting x and y position of the object.
   * @param danceSteps  DanceStep array containing the sequence of dance steps for the object.
   */
  public DancingBadger(float[] startingPos, DanceStep[] danceSteps) {
    super(new float[][] {{startingPos[0], startingPos[1]}, {}});
    this.imageName = "images" + File.separator + "dancingBadger.png";
    this.speed = 2;
    this.isFacingRight = true;
    this.x = startingPos[0];
    this.y = startingPos[1];
    this.destination = danceSteps[stepIndex].getPositionAfter(startingPos);
    this.danceSteps = danceSteps;
  }

  /**
   * Updates the destination of the object, by checking and incrementing the stepIndex when
   * appropriate.
   * 
   * @Override
   */
  protected void updateDestination() {
    // Checking index
    if (stepIndex == (danceSteps.length - 1)) {
      stepIndex = 0;
    } else {
      stepIndex++;
    }
    this.destination = danceSteps[stepIndex].getPositionAfter(destination);
  }

}
