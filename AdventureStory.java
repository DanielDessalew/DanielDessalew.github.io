/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: AdventureStory
// Files: Milestone 1
// Course: CS 200 001 Spring 2019
//
// Author: Layne Hailu
// Email: lhailz eu@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: (identify each person and describe their help in detail)
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class AdventureStory {
    //we initialize public enum to keep track of the transitions and states in parseStory method
    public enum RoomState {
        BEGIN, ROOMID, ROOMDESC, ROOMTRANS, ERROR
    };


    /**
     * This is the main method for the Story Adventure game. It consists of the main game loop and
     * play again loop with calls to the various supporting methods. This method will evolve over
     * the 3 milestones.
     * 
     * The Scanner object to read from System.in and the Random object with a seed of Config.SEED
     * will be created in the main method and used as arguments for the supporting methods as
     * required.
     *
     * Milestone #1: - Print out the welcome message: "Welcome to this choose your own adventure
     * system!" - Begin the play again loop: - Prompt for a filename using the promptString method
     * with the prompt: "Please enter the story filename: " - Prompt for a char using the promptChar
     * method with the prompt: "Do you want to try again? " - Repeat until the character returned by
     * promptChar is an 'n' - Print out "Thank you for playing!", terminated by a newline.
     *
     * Milestone #2: - Print out the welcome message: "Welcome to this choose your own adventure
     * system!" - Begin the play again loop: - Prompt for a filename using the promptString method
     * with the prompt: "Please enter the story filename: " - If the file is successfully parsed
     * using the parseFile method: - Begin the game loop with the current room ID being that in the
     * 0 index of the String array passed into the parseFile method as the 4th parameter - Output
     * the room details via the displayRoom method - Output the transitions via the
     * displayTransitions method - If the current transition is not terminal: - Prompt the user for
     * a number between -1 and the number of transitions minus 1, using the promptInt method with a
     * prompt of "Choose: " - If the returned value is -1: - read a char using promptChar with a
     * prompt of "Are you sure you want to quit the adventure? " - Set the current room ID to
     * Config.FAIL if that character returned is 'y' - Otherwise: Set the current room ID to the
     * room ID at index Config.TRAN_ROOM_ID of the selected transition. - Otherwise, the current
     * transition is terminal: Set the current room ID to the terminal state in the transition
     * String array. - Continue the game loop until the current room ID is Config.SUCCESS or
     * Config.FAIL - If the current room ID is Config.FAIL, print out the message (terminated by a
     * line): "You failed to complete the adventure. Better luck next time!" - Otherwise: print out
     * the message (terminated by a line): "Congratulations! You successfully completed the
     * adventure!" - Prompt for a char using the promptChar method with the prompt: "Do you want to
     * try again? " - Repeat until the character returned by promptChar is an 'n' - Print out "Thank
     * you for playing!", terminated by a newline.
     *
     * Milestone #3: - Print out the welcome message: "Welcome to this choose your own adventure
     * system!" - Begin the play again loop: - Prompt for a filename using the promptString method
     * with the prompt: "Please enter the story filename: " - If the file is successfully parsed
     * using the parseFile method: - Begin the game loop with the current room ID being that in the
     * 0 index of the String array passed into the parseFile method as the 4th parameter - Output
     * the room details via the displayRoom method - Output the transitions via the
     * displayTransitions method - If the current transition is not terminal: - If the value
     * returned by the probTrans method is null: - Prompt the user for a number between -2 and the
     * number of transitions minus 1, using the promptInt method with a prompt of "Choose: " - If
     * the returned value is -1: - read a char using promptChar with a prompt of "Are you sure you
     * want to quit the adventure? " - Set the current room ID to Config.FAIL if that character
     * returned is 'y' - If the returned value is -2: - read a String using the promptString method
     * with a prompt of: "Bookmarking current location: curRoom. Enter bookmark filename: ", where
     * curRoom is the current room ID. - Call the saveBookmark method and output (terminated by a
     * new line): - if successful: "Bookmark saved in fSave" - if unsuccessful: "Error saving
     * bookmark in fSave" where fSave is the String returned by promptString. - Otherwise: Set the
     * current room ID to the room id at index Config.TRAN_ROOM_ID of the selected transition. -
     * Otherwise, the value returned by probTrans is not null: make this value the current room ID.
     * - Continue the game loop until the current room ID is Config.SUCCESS or Config.FAIL. - If the
     * current room ID is Config.FAIL, print out the message (terminated by a line): "You failed to
     * complete the adventure. Better luck next time!" - Otherwise: print out the message
     * (terminated by a line): "Congratulations! You successfully completed the adventure!" - Prompt
     * for a char using the promptChar method with the prompt: "Do you want to try again? " - Repeat
     * until the character returned by promptChar is an 'n' - Print out "Thank you for playing!",
     * terminated by a newline.
     *
     * @param args Unused
     */


    public static void main(String[] args) {
        //we initialize an empty string arrayList of room and allocate new memory
        ArrayList<String[]> room = new ArrayList<String[]>();
        //create an arrayList of array for transitioning to rooms
        ArrayList<ArrayList<String[]>> transRoom = new ArrayList<ArrayList<String[]>>();
        //user inputs
        Scanner userInput = new Scanner(System.in);
        //random number generator
        Random rand = new Random(Config.SEED);
        //yes or no character at the end
        char x;
        //give the string array of current room with a room number
        int indexReturn = 0;
        String[] currentRoom = new String[1];
        boolean startGame = true;
        File roomFile = null;
        String fileName = "";
        ArrayList<String[]> currentTrans = new ArrayList<String[]>();

        //print out a welcome message
        System.out.println("Welcome to this choose your own adventure system!");
        do {
            //promt for a file name using the prompt string method with the prompt
            fileName = promptString(userInput, "Please enter the story filename: ");
            room = new ArrayList<String[]>();
            //create a new memory allocation for our current transitioning room
            transRoom = new ArrayList<ArrayList<String[]>>();
            roomFile = new File(fileName);
            //set the room id of the current room to 1, the first index
            currentRoom = new String[1];
            //checks if the file is successfully parsed
            if (parseFile(fileName, room, transRoom, currentRoom)) {
                //we begin a game loop with the current ID being that in the 0 index of string array
                String gameID = currentRoom[0];
                while (startGame == true) {
                    //output the room details via the display room method
                    displayRoom(gameID, room);
                    //output transitions via the displayTransitions mehtod
                    currentTrans = displayTransitions(gameID, room, transRoom);
                    //if the current transition is not terminal
                    if (currentTrans.get(0)[Config.TRAN_DESC] != Config.SUCCESS
                        && currentTrans.get(0)[Config.TRAN_DESC] != Config.FAIL) {

                        String transProb = probTrans(rand, currentTrans);
                        if (transProb == null) {
                            indexReturn = promptInt(userInput, "Choose: ", -2, currentTrans.size() - 1);
                            //if returned value is -1
                            if (indexReturn == -1) {
                                //read a char using the promptChar method
                                char ch = promptChar(userInput,
                                    "Are you sure you want to quit the adventure? ");
                                if (ch == 'y') {
                                    gameID = Config.FAIL;
                                    startGame = false;
                                } else {
                                    continue;
                                }
                            }
                            //if the returned value is -2
                            if (indexReturn == -2) {
                                //read a string using the promptString method
                                String fileBookMark = promptString(userInput, "Bookmarking current location: "
                                        + gameID + ". Enter bookmark filename: ");
                                //call the saveBookmark method
                                boolean flag = saveBookmark(fileName, gameID, fileBookMark);

                                if (flag) {
                                    System.out.println("Bookmark saved in " + fileBookMark);
                                }

                                else {
                                    System.out.println("Error saving bookmark in " + fileBookMark);
                                }
                            } else {
                                gameID = currentTrans.get(indexReturn)[Config.TRAN_ROOM_ID];
                            }
                        } else {
                            gameID = transProb;
                        }


                    } else {
                        startGame = false;
                        break;
                    }

                }
                if (currentTrans.get(0)[Config.TRAN_DESC] == Config.SUCCESS) {
                    System.out
                        .println("Congratulations! You successfully completed the adventure!");


                }
                if (currentTrans.get(0)[Config.TRAN_DESC] == Config.FAIL || gameID == Config.FAIL) {
                    System.out
                        .println("You failed to complete the adventure. Better luck next time!");

                }
            }
            x = promptChar(userInput, "Do you want to try again? ");
            startGame = true;
        }
        //keep playing till the input is n
        while ((x != 'n'));
        System.out.println("Thank you for playing!");
    }



    /**
     * Prompts the user for a value by displaying prompt.
     * Note: This method should not add a new line to the output of prompt. 
     *
     * After prompting the user, the method will consume an entire line of input while reading an 
     * int. 
     * If the value read is between min and max (inclusive), that value is returned.
     * Otherwise, "Invalid value." terminated by a new line is output and the user is prompted 
     * again. 
     *
     * @param sc The Scanner instance to read from System.in.
     * @param prompt The name of the value for which the user is prompted.
     * @param min The minimum acceptable int value (inclusive).
     * @param max The maximum acceptable int value (inclusive).
     * @return Returns the value read from the user.
     */

    public static int promptInt(Scanner sc, String prompt, int min, int max) {
        //declare int variable that will be set to user input
        int index = 0;
        //declare a boolean variable for the do-while loop
        boolean flag = true;
        do {
            //prompts the user for a value by displaying prompt
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                //consumes the line from the user
                index = sc.nextInt();
                //reads the next line
                sc.nextLine();
                //checks if the value read is b/n min and max inclusive
                if (index >= min && index <= max) {
                    //sets our boolean variable to false
                    flag = false;
                } else {
                    System.out.println("Invalid value.");
                }
            }
        } while (flag);

        return index;
    }

    /**
     * Prompts the user for a char value by displaying prompt.
     * Note: This method should not add a new line to the output of prompt. 
     *
     * After prompting the user, the method will read an entire line of input and return the first
     * non-whitespace character converted to lower case.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the first non-whitespace character (in lower case) read from the user. If 
     *         there are no non-whitespace characters read, the null character is returned.
     */
    public static char promptChar(Scanner sc, String prompt) {
        //prompts the user for a value by displaying prompt
        System.out.print(prompt);
        //declare a string variable for the user to input
        String userPrompt;
        //declare a char variable for the user to input
        char charPrompt;
        //checks if there any input
        if (sc.hasNext()) {
            //consumes an entire line of input
            userPrompt = sc.nextLine();
            //the line is trimmed and set to lower case
            userPrompt = userPrompt.trim().toLowerCase();
            //returns the first non-white space character converted to lower case
            charPrompt = userPrompt.charAt(0);
            //returns the user char input
            return charPrompt;
        } else {
            //if there are no non-whitespace characters read, null char is returned
            charPrompt = '\0';
        }

        return charPrompt;
    }

    /**
     * Prompts the user for a string value by displaying prompt.
     * Note: This method should not add a new line to the output of prompt. 
     *
     * After prompting the user, the method will read an entire line of input, removing any leading and 
     * trailing whitespace.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the string entered by the user with leading and trailing whitespace removed.
     */
    public static String promptString(Scanner sc, String prompt) {
        // displays an output by prompting the user
        System.out.print(prompt);
        // a string variable of string with the name 'userInput'
        // the value of userInput is trimmed to remove any trailing and leading whitespace
        String userInput = sc.nextLine().trim();
        return userInput;
    }

    /**
     * Saves the current position in the story to a file.
     *
     * The format of the bookmark file is as follows:
     * Line 1: The value of Config.MAGIC_BOOKMARK
     * Line 2: The filename of the story file from storyFile
     * Line 3: The current room id from curRoomc
     *
     * Note: use PrintWriter to print to the file.
     *
     * @param storyFile The filename containing the cyoa story.
     * @param curRoom The id of the current room.
     * @param bookmarkFile The filename of the bookmark file.
     * @return false on an IOException, and true otherwise.
     */
    public static boolean saveBookmark(String storyFile, String curRoom, String bookmarkFile) {


        try {
            File bookFile = new File(bookmarkFile);
            PrintWriter currentPos = new PrintWriter(bookFile);
            currentPos.println(Config.MAGIC_BOOKMARK);
            currentPos.println(storyFile);
            currentPos.print(curRoom);
            currentPos.close();

        } catch (FileNotFoundException e) {

            return false;
        }


        return true;
    }

    /**
     * Loads the story and current location from a file either a story file or a bookmark file. 
     * NOTE: This method is partially implementd in Milestone 2 and then finished in Milestone 3.
     * 
     * The type of the file will be determined by reading the first line of the file. The first
     * line of the file should be trimmed of whitespace.
     *
     * If the first line is Config.MAGIC_STORY, then the file is parsed using the parseStory method.
     * If the first line is Config.MAGIC_BOOKMARK, the the file is parsed using the parseBookmark
     * method.
     * Otherwise, print an error message, terminated by a new line, to System.out, displaying: 
     * "First line: trimmedLineRead does not correspond to known value.", where trimmedLineRead is 
     * the trimmed value of the first line from the file. 
     *
     * If there is an IOException, print an error message, terminated by a new line, to System.out,
     * saying "Error reading file: fName", where fName is the value of the parameter.
     *
     * If there is an error reading the first line, print an error message, terminated by a new 
     * line, to System.out, displaying: "Unable to read first line from file: fName", where fName is
     * the value of the parameter. 
     *
     * This method will be partially implemented in Milestone #2 and completed in Milestone #3 as 
     * described below.
     *
     * Milestone #2: Open the file, handling the IOExceptions as described above. Do not read the
     * the first line: Assume the file is a story file and call the parseStory method.
     *
     * Milestone #3: Complete the implementation of this method by reading the first line from the
     * file and following the rules of the method as described above.
     *
     * @param fName The name of the file to read.
     * @param rooms The ArrayList structure that will contain the room details. A parallel ArrayList
     *              trans.
     * @param trans The ArrayList structure that will contain the transition details. A parallel 
     *              ArrayList to rooms. Since the rooms can have multiple transitions, each room 
     *              will be an ArrayList<String[]> with one String[] per transition with the 
     *              overall structure being an ArrayList of ArrayLists of String[].
     * @param curRoom An array of at least length 1. The current room id will be stored in the cell
     *                at index 0.
     * @return false if there is an IOException or a parsing error. Otherwise, true. 
     */
    public static boolean parseFile(String fName, ArrayList<String[]> rooms,
        ArrayList<ArrayList<String[]>> trans, String[] curRoom) {
        File file = new File(fName);
        Scanner scnr = null;
        String trimmedReadLine = "";

        try {
            scnr = new Scanner(file);
            if (scnr.hasNextLine()) {
                trimmedReadLine = scnr.nextLine().trim();

                if (trimmedReadLine.equals(Config.MAGIC_STORY)) {
                    return parseStory(scnr, rooms, trans, curRoom);
                } else if (trimmedReadLine.equals(Config.MAGIC_BOOKMARK)) {
                    return parseBookmark(scnr, rooms, trans, curRoom);
                } else {
                    System.out.println(
                        "First line: " + trimmedReadLine + " does not correspond to known value.");
                    scnr.close();
                    return false;
                }
            } else {
                System.out.println("Unable to read first line from file: " + fName);
                scnr.close();
                return false;
            }
        } catch (IOException except) {
            System.out.println("Error reading file: " + fName);
            return false;
        }

    }

    /**    
     * Loads the story and the current room from a bookmark file. This method assumes that the first
     * line of the file, containing Config.MAGIC_BOOKMARK, has already been read from the Scanner.
     *
     * The format of a bookmark file is as follows:
     * Line No: Contents
     *       1: Config.MAGIC_BOOKMARK
     *       2: Story filename
     *       3: Current room id
     *
     * As an example, the following contents would load the story Goldilocks.story and set the 
     * current room to id 7.
     *
     * #!BOOKMARK
     * Goldilocks.story
     * 7
     *
     * Your method should not duplicate the code from the parseFile method. It must use the
     * parseFile method to populate the rooms and trans methods based on the contents of the story
     * filename read and trimmed from line 2 of the file. The value of for the cell at index 0 of 
     * curRoom is the trimmed value read on line 3 of the file.
     *
     * @param sc The Scanner object buffering the input file to read.
     * @param rooms The ArrayList structure that will contain the room details. A parallel ArrayList
     *              trans.
     * @param trans The ArrayList structure that will contain the transition details. A parallel 
     *              ArrayList to rooms.
     * @param curRoom An array of at least length 1. The current room id will be stored in the cell
     *                at index 0.
     * @return false if there is a parsing error. Otherwise, true. 
     */
    public static boolean parseBookmark(Scanner sc, ArrayList<String[]> rooms,
        ArrayList<ArrayList<String[]>> trans, String[] curRoom) {
        // initialize a string variable bookMarkLine
        try {

            String bookMarkLine = "";
            String roomDetails = "";
            // set the variable bookMarkLine to the first line of the file to be read
            bookMarkLine = sc.nextLine().trim();
            roomDetails = sc.nextLine().trim();
            // we set the value of the index 0 of curRoom to the file
            curRoom[0] = roomDetails;
            if (parseFile(bookMarkLine, rooms, trans, curRoom) == false) {
                return false;
            } else {
                parseFile(bookMarkLine, rooms, trans, curRoom);

            }
        } catch (NullPointerException d) {
            return false;
        }
        return true;

    }

    /**
     * This method parses a story adventure file.
     *
     * The method will read the contents from the Scanner, line by line, and populate the parallel 
     * ArrayLists rooms and trans. As such the story files have a specific structure. The order of
     * the rooms in the story file correspond to the order in which they will be stored in the 
     * parallel ArrayLists.
     *
     * When reading the file line-by-line, whitespace at the beginning and end of the line should be
     * trimmed. The file format described below assumes that whitespace has been trimmed.
     *
     * Story file format:
     *
     * - Any line (outside of a room's description) that begins with a '#' is considered a comment 
     *   and should be ignored.
     * - Room details begin with a line starting with 'R' followed by the room id, terminated with 
     *   a ':'. Everything  after the first colon is the room title. The substrings of the room id 
     *   and the room title should be trimmed.
     * - The room description begins on the line immediate following the line prefixed with 'R',
     *   containing the room id, and continues until a line of ";;;" is read.
     *   - The room description may be multi-line. Every line after the first one, should be 
     *     prefixed with a newline character ('\n'), and concatenated to the previous description 
     *     lines read for the current room.
     * - The room transitions begin immediately after the line of ";;;", and continue until a line
     *   beginning with 'R' is encountered. There are 3 types of transition lines:
     *   - 1 -- Terminal Transition: A terminal transition is either Config.SUCCESS or 
     *                               Config.FAIL. This room is the end of the story. 
     *                               This value should be stored as a transition with the String at
     *                               index Config.TRAN_DESC set to the value read. The rest of the 
     *                               Strings in the transition String array should be null.
     *                               A room with a terminal transition can only have one transition 
     *                               associated with it. Any additional transitions should result in
     *                               a parse error.
     *   - 2 -- Normal Transition: The line begins with ':' followed by the transition description, 
     *                             followed by " -> " (note the spaces), followed by the room id to 
     *                             transition to. For normal transitions (those without a transition
     *                             weight), set the value at index Config.TRAN_PROB to null.
     *   - 3 -- Weighted Transition: Similar to a normal transition except that there is a 
     *                               probability weight associated with the transition. After the 
     *                               room id (as described in the normal transition) is a '?' 
     *                               followed by the probability weight. 
     *   - You can assume that room ids do not contain a '?'.
     *   - You can assume that Config.SUCCESS and Config.FAIL do not start with a ':'.
     *
     * In the parallel ArrayLists rooms and trans, the internal structures are as follows:
     *
     * The String array structure for each room has a length of Config.ROOM_DET_LEN. The entries in
     * the array are as follows:
     * Index              | Description
     * --------------------------------------------
     * Config.ROOM_ID     | The room id
     * Config.ROOM_TITLE  | The room's title
     * Config.ROOM_DESC   | The room's description
     *
     * The String array structure for each transition. Note that each room can have multiple 
     * transitions, hence, the ArrayList of ArrayLists of String[]. The length of the String[] is
     * Config.TRAN_DET_LEN. The entries in the String[] are as follows:
     * Index               | Description
     * ------------------------------------------------------------------
     * Config.TRAN_DESC    | The transition description
     * Config.TRAN_ROOM_ID | The transition destination (id of the room) 
     * Config.TRAN_PROB    | The probability weight for the transition
     *
     * If you encounter a line that violates the story file format, the method should print out an 
     * error message, terminated by a new line, to System.out displaying: 
     * "Error parsing file on line: lineNo: lineRead", where lineNo is the number of lines read
     * by the parseStory method (i.e. ignoring the magic number if Milestone #3), and lineRead is 
     * the offending trimmed line read from the Scanner.
     *
     * After parsing the file, if rooms or trans have zero size, or they have different sizes, print
     * out an error message, terminated by a new line, to System.out displaying:
     * "Error parsing file: rooms or transitions not properly parsed."
     *
     * After parsing the file, if curRoom is not null, store the reference of the id of the room at 
     * index 0 of the rooms ArrayList into the cell at index 0 of curRoom. 
     *
     * Hint: This method only needs a single loop, reading the file line-by-line.
     * 
     * Hint: To successfully parse the file, you will need to maintain a state of where you are in 
     *       the file. I.e., are you parsing the description, parsing the transitions; is there an 
             error; etc? One suggestion would be to use an enum to enumerate the different states. 
     *
     * @param sc The Scanner object buffering the input file to read.
     * @param rooms The ArrayList structure that will contain the room details.
     * @param trans The ArrayList structure that will contain the transition details.
     * @param curRoom An array of at least length 1. The current room id will be stored in the cell
     *                at index 0.
     * @return false if there is a parsing error. Otherwise, true. 
     */



    public static boolean parseStory(Scanner sc, ArrayList<String[]> rooms,
        ArrayList<ArrayList<String[]>> trans, String[] curRoom) {
        //declare the roomState with roomCondition variable
        RoomState roomCondition;
        String userInput = null;
        //roomState is declared as BEGIN
        roomCondition = RoomState.BEGIN;
        String[] currentRoom = new String[Config.ROOM_DET_LEN];
        currentRoom = null;
        //create a string array with room details
        String[] roomDetails = new String[Config.ROOM_DET_LEN];
        ArrayList<String[]> roomTrans = new ArrayList<String[]>();
        roomTrans = null;

        String[] currentTrans = new String[Config.TRAN_DET_LEN];

        //checks if there is a user input
        while (sc.hasNextLine()) {
            //sets user value to the next line and trimms it
            userInput = sc.nextLine().trim();
            if (userInput != null && userInput.length() != 0) {


                if (userInput.charAt(0) == '#' && roomDetails[Config.ROOM_DESC] != null) {
                    roomCondition = RoomState.BEGIN;
                }
                //checks if there is ;;; to start transitioning to other rooms
                if (userInput.contains(";;;")) {
                    //roomState is in ROOMTRANS condition
                    roomCondition = RoomState.ROOMTRANS;
                    continue;
                    //if the state is still checking the room description
                } else if (roomCondition == RoomState.ROOMDESC) {
                    //checks if the room is empty
                    if (currentRoom[Config.ROOM_DESC].isEmpty()) {
                        currentRoom[Config.ROOM_DESC] = userInput.trim();
                    } else {
                        //concatenates with the user input of fills our current room
                        currentRoom[Config.ROOM_DESC] =
                            currentRoom[Config.ROOM_DESC] + "\n" + userInput.trim();
                    }
                }
                //checks the first character if it is 'R'
                else if (userInput.charAt(0) == 'R') {
                    if (currentRoom != null) {
                        //fills the room of the our initialized arrays
                        trans.add(roomTrans);
                        rooms.add(currentRoom);
                    }
                    //allocates new memory location
                    roomTrans = new ArrayList<String[]>();
                    currentRoom = new String[Config.ROOM_DET_LEN];

                    String[] roomItem = userInput.split(":");
                    currentRoom[Config.ROOM_ID] = roomItem[0].substring(1).trim();
                    if (roomItem.length < 2) {
                        currentRoom[Config.ROOM_TITLE] = "";
                    } else {
                        currentRoom[Config.ROOM_TITLE] = roomItem[1].trim();
                    }
                    currentRoom[Config.ROOM_DESC] = "";
                    roomCondition = RoomState.ROOMDESC;

                } else if (roomCondition == RoomState.ROOMTRANS) {
                    //trans details
                    currentTrans = new String[Config.TRAN_DET_LEN];
                    if (userInput.contains(Config.SUCCESS)) {
                        currentTrans[Config.TRAN_DESC] = Config.SUCCESS;
                        curRoom[0] = Config.SUCCESS;
                        roomTrans.add(currentTrans);
                    } else if (userInput.contains(Config.FAIL)) {
                        currentTrans[Config.TRAN_DESC] = Config.FAIL;
                        curRoom[0] = Config.FAIL;
                        roomTrans.add(currentTrans);
                    } else if (userInput.charAt(0) == ':') {
                        if (userInput.contains("?")) {
                            String[] roomTransition = userInput.split(" -> ");
                            int index = roomTransition[1].indexOf("?");
                            currentTrans[Config.TRAN_DESC] = roomTransition[0].substring(1).trim();
                            currentTrans[Config.TRAN_ROOM_ID] =
                                roomTransition[1].substring(0, index).trim();
                            currentTrans[Config.TRAN_PROB] =
                                roomTransition[1].substring(index + 1).trim();
                        } else {
                            String[] trans3 = userInput.split(" -> ");
                            currentTrans[Config.TRAN_DESC] = trans3[0].substring(1).trim();
                            currentTrans[Config.TRAN_ROOM_ID] = trans3[1];
                            currentTrans[Config.TRAN_PROB] = null;
                        }
                        roomTrans.add(currentTrans);
                    }

                }
            } else if (userInput.isEmpty() && roomCondition == RoomState.ROOMDESC) {
                if (currentRoom[Config.ROOM_DESC] != null) {
                    currentRoom[Config.ROOM_DESC] = currentRoom[Config.ROOM_DESC] + "\n";
                }
            }

        }//fills rooms
        rooms.add(currentRoom);
        trans.add(roomTrans);

        if ((rooms.size() == 0 || trans.size() == 0) || (rooms.size() != trans.size())) {
            System.out.println("Error parsing file: rooms or transitions not properly parsed.");
        }
        if (curRoom != null) {
            curRoom[0] = rooms.get(0)[Config.ROOM_ID];
        }
        
        return true;
    }


    /**
     * Returns the index of the given room id in an ArrayList of rooms. 
     *
     * Each entry in the ArrayList contain a String array, containing the details of a room. The 
     * String array structure, which has a length of Config.ROOM_DET_LEN, and has the following 
     * entries:
     * Index              | Description
     * --------------------------------------------
     * Config.ROOM_ID     | The room id
     * Config.ROOM_TITLE  | The room's title
     * Config.ROOM_DESC   | The room's description
     *
     * @param id The room id to search for.
     * @param rooms The ArrayList of rooms.
     * @return The index of the room with the given id if found in rooms. Otherwise, -1.
     */
    public static int getRoomIndex(String id, ArrayList<String[]> rooms) {
        int i = 0;
        String[] roomIndex = null;
        while (i < rooms.size()) {
            roomIndex = rooms.get(i);
            if (roomIndex[Config.ROOM_ID].equals(id)) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    /**
     * Returns the room String array of the given room id in an ArrayList of rooms. 
     *
     * Remember to avoid code duplication!
     *
     * @param id The room id to search for.
     * @param rooms The ArrayList of rooms.
     * @return The reference to the String array in rooms with the room id of id. Otherwise, null.
     */
    public static String[] getRoomDetails(String id, ArrayList<String[]> rooms) {

        if (getRoomIndex(id, rooms) == -1) {
            return null;
        } else {
            String[] currDetails = rooms.get(getRoomIndex(id, rooms));
            return currDetails;
        }

    }

    /**
     * Prints out a line of characters to System.out. The line should be terminated by a new line.
     *
     * @param len The number of times to print out c. 
     * @param c The character to print out.
     */
    public static void printLine(int len, char c) {
        for (int i = 0; i < len; i++) {
            System.out.print(c);
        }
        System.out.println("");
    }

    /**
     * Prints out a String to System.out, formatting it into lines of length no more than len 
     * characters.
     * 
     * This method will need to print the string out character-by-character, counting the number of
     * characters printed per line. 
     * If the character to output is a newline, print it out and reset your counter.
     * If it reaches the maximum number of characters per line, len, and the next character is:
     *   - whitespace (as defined by the Character.isWhitespace method): print a new line 
     *     character, and move onto the next character.
     *   - NOT a letter or digit (as defined by the Character.isLetterOrDigit method): print out the
     *     character, a new line, and move onto the next character.
     *   - Otherwise:
     *       - If the previous character is whitespace, print a new line then the character.
     *       - Otherwise, print a '-', a new line, and then the character. 
     * Remember to reset the counter when starting a new line. 
     *
     * After printing out the characters in the string, a new line is output.
     *
     * @param len The maximum number of characters to print out.
     * @param val The string to print out.
     */
    public static void printString(int len, String val) {

        //we initialize int variable of num count
        //print out each character line by line
        for (int numCount = 1, i = 0; i < val.length(); i++, numCount++) {
            //checks if the character to output is a new line
            if (val.charAt(i) == '\n') {
                numCount = 0;
            }
            //checks if the numCountis the max number of characters to print out
            if (numCount == len) { 
                numCount = 1;
                //if it reaches the max number of characters, next character is whitespace
                if (Character.isWhitespace(val.charAt(i))) {
                    System.out.println();
                    //resets the counter when starting a new line
                    numCount = 0;
                //next character is not a letter or digit
                } else if (!Character.isLetterOrDigit(val.charAt(i))) {
                    System.out.println(val.charAt(i));
                    numCount = 0;
                } else if (Character.isWhitespace(val.charAt(i - 1))) {
                    System.out.print("\n" + val.charAt(i));
                //otherwise the previous character is whitespace 
                } else {
                    System.out.print("-\n" + val.charAt(i));
                }
            } else {
                System.out.print(val.charAt(i));
            }
        }
        //print out a new line
        System.out.println();

    }

    /**
     * This method prints out the room title and description to System.out. Specifically, it first
     * loads the room details, using the getRoomDetails method. If no room is found, the method
     * should return, avoiding any runtime errors.
     *
     * If the room is found, first a line of Config.LINE_CHAR of length Config.DISPLAY_WIDTH is 
     * output. Followed by the room's title, a new line, and the room's description. Both the title
     * and the description should be printed using the printString method with a maximum length of
     * Config.DISPLAY_WIDTH. Finally, a line of Config.LINE_CHAR of length Config.DISPLAY_WIDTH is 
     * output.
     *
     * @param id Room ID to display
     * @param rooms ArrayList containing the room details.
     */
    public static void displayRoom(String id, ArrayList<String[]> rooms) {
     // create a string array and load getRoomDetails within the array
        String[] roomDetails = AdventureStory.getRoomDetails(id, rooms);
        // check if the array is null or there is no room found
        if (roomDetails == null) {
            // the method is returned
            return;
        } else {
            // create a while loop and check if i is less than the display width
            for (int i = 0; i < Config.DISPLAY_WIDTH; ++i) {
                // print out first line of Config.LINE_CHAR of length Config.DISPLAY_WIDTH
                System.out.print(Config.LINE_CHAR);
            }
            System.out.println();
            // room description are printed out using the printString method
            AdventureStory.printString(Config.DISPLAY_WIDTH, roomDetails[Config.ROOM_TITLE]);
            System.out.println();
            // room title is printed out using the printString method
            AdventureStory.printString(Config.DISPLAY_WIDTH, roomDetails[Config.ROOM_DESC]);
            System.out.println();
            // a line of Config.LINE_CHAR of length Config.DISPLAY_WIDTH is printed out
            for (int i = 0; i < Config.DISPLAY_WIDTH; ++i) {
                System.out.print(Config.LINE_CHAR);
            }
            System.out.println();
        }
    }

    /**
     * Prints out and returns the transitions for a given room. 
     *
     * If the room ID of id cannot be found, nothing should be output to System.out and null should
     * be returned.
     *
     * If the room is a terminal room, i.e., the transition list is consists of only a single 
     * transition with the value at index Config.TRAN_DESC being either Config.SUCCESS or 
     * Config.FAIL, nothing should be printed out.
     *
     * The transitions should be output in the same order in which they are in the ArrayList, and 
     * only if the transition probability (String at index TRAN_PROB) is null. Each transition 
     * should be output on its own line with the following format:
     * idx) transDesc
     * where idx is the index in the transition ArrayList and transDesc is the String at index 
     * Config.TRAN_DESC in the transition String array.
     *
     * See parseStory method for the details of the transition String array.
     *
     * @param id The room id of the transitions to output and return.
     * @param rooms The ArrayList structure that contains the room details.
     * @param trans The ArrayList structure that contains the transition details.
     * @return null if the id cannot be found in rooms. Otherwise, the reference to the ArrayList of
     *         transitions for the given room.
     */
    public static ArrayList<String[]> displayTransitions(String id, ArrayList<String[]> rooms,
        ArrayList<ArrayList<String[]>> trans) {
        //checks if the room is not empty
        if (getRoomDetails(id, rooms) != null) {
            //initializes the int variable curRoomID with the room number and id
            int curRoomID = getRoomIndex(id, rooms);
            //transitions are printed out in the same order with this for loop
            for (int i = 0; i < trans.get(curRoomID).size(); i++) {
                //if the transition is terminal
                if (trans.get(curRoomID).get(i)[Config.TRAN_DESC].equals(Config.SUCCESS)
                    || trans.get(curRoomID).get(i)[Config.TRAN_DESC].equals(Config.FAIL)) {
                    continue;
                } else if (trans.get(curRoomID).get(i)[Config.TRAN_PROB] == null) {
                    System.out.println(i + ") " + trans.get(curRoomID).get(i)[Config.TRAN_DESC]);
                }
            }
            
            return trans.get(curRoomID);

        } else {
            //return null if the id cannot be found in rooms
            return null;
        }



    }


    /**
     * Returns the next room id, selected randomly based on the transition probability weights.
     *
     * If curTrans is null or the total sum of all the probability weights is 0, then return null. 
     * Use Integer.parseInt to convert the Strings at index Config.TRAN_PROB of the transition
     * String array to integers. If there is a NumberFormatException, return null.
     *
     * It is important to follow the specifications of the random process exactly. Any deviation may
     * result in failed tests. The random transition work as follows:
     *   - Let totalWeight be the sum of the all the transition probability weights in curTrans.
     *   - Draw a random integer between 0 and totalWeight - 1 (inclusive) from rand.
     *   - From the beginning of the ArrayList curTrans, start summing up the transition probability 
     *     weights.
     *   - Return the String at index Config.TRAN_ROOM_ID of the first transition that causes the 
     *     running sum of probability weights to exceed the random integer.   
     *
     * See parseStory method for the details of the transition String array.
     *
     * @param rand The Random class from which to draw random values.
     * @param curTrans The ArrayList structure that contains the transition details.
     * @return The room id that was randomly selected if the sum of probabilities is greater than 0.
     *         Otherwise, return null. Also, return null if there is a NumberFormatException. 
     */
    public static String probTrans(Random rand, ArrayList<String[]> curTrans) {
        //initializes the total weight
        int sumWeights = 0;
        if (curTrans == null) {
            return null;
        }
        try {
            for (int i = 0; i < curTrans.size(); i++) {
                sumWeights += Integer.parseInt(curTrans.get(i)[Config.TRAN_PROB]);

            }
            //checks if the sum is 0
            if (sumWeights == 0) {
                return null;
            }
            //draw a random integer between 0 and total weight -1
            int randSumWeight = rand.nextInt(sumWeights);

            int tranSum = 0;

            for (int i = 0; i < curTrans.size(); i++) {
                tranSum += Integer.parseInt(curTrans.get(i)[Config.TRAN_PROB]);

                if (tranSum > randSumWeight) {
                    return curTrans.get(i)[Config.TRAN_ROOM_ID];
                }
            }


        } catch (NumberFormatException e) {
            return null;
        }
        return null;
    }

}

