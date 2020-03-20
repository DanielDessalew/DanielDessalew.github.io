//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Starship Robot
// Files: FrozenStatue.java, WinterCarnival.java, DancingBadger.java
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
 * This class implements the starship robots in the graphical simulation, inheriting from the
 * FrozenStatue class.
 * 
 */
public class StarshipRobot extends FrozenStatue {
  protected float[][] beginAndEnd;
  protected float[] destination;
  protected float speed;

  /**
   * Creates a new Starship object.
   * 
   * @param beginAndEnd float 2D array containing the x and y values for the beginning and the
   *                    ending position.
   */
  public StarshipRobot(float[][] beginAndEnd) {
    super(beginAndEnd[0]);
    this.beginAndEnd = beginAndEnd;
    this.x = beginAndEnd[0][0];
    this.y = beginAndEnd[0][1];
    this.destination = beginAndEnd[1];
    this.speed = 6;
    this.isFacingRight = true;
    this.imageName = "images" + File.separator + "starshipRobot.png";

  }

  /**
   * Updates the position of the object, moving it speed units closer to its destination.
   * 
   * @return true when the distance between the beginning and ending position is less than 2 times
   *         the speed of the object, false otherwise.
   */
  protected boolean moveTowardDestination() {
    float distance;
    distance =
        (float) Math.sqrt(Math.pow(destination[0] - x, 2.0) + Math.pow(destination[1] - y, 2.0));
    float newX = x + (((speed) * (destination[0] - x)) / distance);
    float newY = y + (((speed) * (destination[1] - y)) / distance);
    this.x = newX;
    this.y = newY;
    if (destination[0] > this.x) {
      isFacingRight = true;
    } else {
      isFacingRight = false;
    }
    if (distance < (speed * 2)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Updates the destination of the object, by switching the begin and end position when
   * appropriate.
   * 
   */
  protected void updateDestination() {
    if ((this.destination[0] == beginAndEnd[0][0]) && (this.destination[1] == beginAndEnd[0][1])) {
      this.destination = beginAndEnd[1];
    } else {
      this.destination = beginAndEnd[0];
    }
  }

  /**
   * Calls the updateDestination method when the object is close enough to its destination.
   * 
   * @Override
   */
  public void update(SimulationEngine engine) {
    boolean closeEnough = moveTowardDestination();
    if (closeEnough) {
      updateDestination();
    }
    super.update(engine);
  }

}
