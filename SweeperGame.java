/* 
 * TCSS 143 - Autumn 2021
 * Instructor: Raghavi Sakhpal
 * Object Class for Treasure Game
 */
 
import java.util.*;
import java.io.*;

/**
 * The program provides the main program with information abou the 
 * treasure coordinates, and the formating of what the display should
 * look like when the class is called in main
 *
 * @author Amtoj Kaur amtojk@uw.edu
 * @version 15 October 2021
 */
 
public class SweeperGame {
   
   //private instance feilds
   private char[][] gameBoard;
   private int treasureX;
   private int treasureY;
   private int totalMoves;
   private boolean found;
   
   /**
    * SweeperGame Cosntructor Method - Receives two integer 
    * parameters as the user’s input for the height (number of rows)
    * and width (numbers of columns) of the grid where the treasure 
    * is buried. Instantiates the 2D array “gameBoard” as a character
    * array with the first parameter for the height(height) and the 
    * second parameter as the width(width). Initialize gameBoard’s
    * cells, each to contain a space ‘ ’(done with single quotes).
    * Also initializes above private instance feilds
    * 
    * @param height - sets verticle index of the array
    * @param width - sets horizontal index of the array
    * 
    * @return - void
    */
   public SweeperGame (int height, int width) { 
      if (height == 0 && width == 0) {
         throw new IllegalArgumentException("IlegalArgumentException");
      }
      gameBoard = new char[height][width];
      
      // Instantiates array with empty spaces
      for (int i = 0; i < height; i++) {     
         for (int j = 0; j < width; j++) {
            gameBoard[i][j] = ' ';
         }
      }
      // generates random within bounds for treasure
      Random r = new Random();      
      treasureY = r.nextInt(height);
      treasureX = r.nextInt(width);
      
      //sets array location of treasure for 'T
      gameBoard[treasureY][treasureX] = 'T'; 
      
      found = false; //Initializing private variables
      totalMoves = 0;
      
   }
   
   /**
    * Method beenSwept - receives the x coordinate and y coordinate
    * to be searched and returns true if the space has already been
    * searched, false otherwise. 
    * 
    * @param int x - user x-coordinate in main to find treasure
    * @param int y - user y-coordinate in main to find treasure
    * 
    * @return boolean swept - returns true: space searched/else false
    */
   public boolean beenSwept(int x, int y) { 
      boolean swept = false;
      
      // checks for already searched or not
      if (gameBoard[y][x] != ' ' &&   
          gameBoard[y][x] != gameBoard[treasureY][treasureX]) {
         swept = true;
      }
      return swept;
   }
   
   /**
    * Method treasureFound - receives the x coordinate and 
    * y coordinate to be searched and returns true if the
    * treasure is found there, false otherwise.  
    * 
    * @param int x - user x-coordinate in main to find treasure
    * @param int y - user y-coordinate in main to find treasure
    * 
    * @return boolean t - true if treasure, else false
    */
   public boolean treasureFound(int x, int y) { 
      boolean t = false;
      
      if (gameBoard[y][x] == gameBoard[treasureY][treasureX]) {
         t = true;
      }
      return t;
   }
   
   /**
    * Method checkOutOfBounds - receives the x coordinate and 
    * y coordinate to be searched and returns true if the values 
    * are within the array indices range, false otherwise (used to
    * check that these indices will not cause an error when applied 
    * to the array).   
    * 
    * @param int x - user x-coordinate in main to find treasure
    * @param int y - user y-coordinate in main to find treasure
    * 
    * @return boolean bounds - true if within array, else false
    */
   public boolean checkOutOfBounds(int x, int y) { 
      boolean bounds = false;
      if ((x >= 0 && x < this.getBoardWidth()) && 
          (y >= 0 && y < this.getBoardHeight())) {
         bounds = true;
      }
      return bounds;
   }
   
   /**
    * Get Method getBoardHeight - returns the height of array   
    * 
    * @param - none
    * 
    * @return int gameBoard.length - gets height of array
    */
   public int getBoardHeight() { 
      return gameBoard.length;
   }
  
  /**
    * Get Method getBoardWidth - returns the width of array   
    * 
    * @param - none
    * 
    * @return int gameBoard[0].length - gets width of array
    */
   public int getBoardWidth() {  
      return gameBoard[0].length;
   }
   
   /**
    * Get Method getBoardHeight - returns the total moves by user  
    * 
    * @param - none
    * 
    * @return int totalMoves - gets total amount of moves
    */
   public int getTotalMoves() {  
      return totalMoves;
   }
   
   /**
    * Method digSand - receives the x coordinate and y coordinate
    * to be searched returns true if the treasure is found, false 
    * otherwise and sets the found field to true. If the treasure
    * is NOT found digSand also sets the gameBoard array at the 
    * received x coordinate and y coordinate location to display
    * the “Manhattan distance” to the treasure. Increment the number
    * of moves taken if the treasure has not been found and the space
    * has not been previously searched.   
    * 
    * @param int x - user x-coordinate in main to find treasure
    * @param int y - user y-coordinate in main to find treasure
    * 
    * @return boolean found - increments moves to match attempts
    */
   public boolean digSand(int x, int y) { 
      
      if (gameBoard[y][x] == gameBoard[treasureY][treasureX]) {
         found = true;
      } else {
         
         //looked this up online didn't know what this was beforehand
         int manhattan = Math.abs((y - treasureY)) +  
                          Math.abs((x - treasureX));  
         // sets location to manhatten distance if no treasure                
         char distance = (char)(manhattan + 48);
         gameBoard[y][x] = distance;
         
      }
      totalMoves++;
      return found;
   }
   
   /**
    * Method toString - displays the current gameBoard array and it’s
    * contents EXCEPT the location of the treasurewhich remains 
    * hidden until he/she is found at which point toString will be 
    * called (by the driver) andthe ‘T’ will be displayed.   
    * 
    * @param - none
    * 
    * @return String s - creates the front end display for program
    */
   public String toString() {
        StringBuilder s = new StringBuilder();     // Creates new StringBuilder object to turn gameBoard into a string.
                                                   // I couldn't find the right way to do it without this becuase it just 
                                                   // wouldn't hide the treasure no matter what I tried.
        // Displays the game board. 
        for (int i = 0; i < (gameBoard.length * 3) + 1; i++) {
            s.append("-");                                    // sets the top of the entire grid 
        }
        s.append("\n");
        for (int i = getBoardHeight() - 1; 0 < i + 1; i--) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == 'T' && !found) {
                    s.append("|  ");                       // sets box with treasure to match empty if not found
                } else {
                    s.append("| " + gameBoard[i][j]);      // sets walls for entire grid (other than treasure coordinate)
                }
            }
            s.append("|\n");
            for (int k = 0; k < (gameBoard[i].length * 3) + 1; k++) {    //for loop adds dash marks to form horizontal columns
                s.append("-");                                          // for the grid including the bottom but not top
            }
            s.append("\n");
        }
        return s.toString();
    }
}