// **********************************************************
// Assignment2:

// Student1:
// UTORID user_name: liangho2
// UT Student #:1002408085
// Author: Hong Xuan David Liang
//
// Student2:
// UTORID user_name:jinze1
// UT Student #:1003155863
// Author: Ze Jin
//
// Student3:
// UTORID user_name: seyedayd
// UT Student #: 1002471201
// Author: Aydin Baradaran-Seyed
//
// Student4:
// UTORID user_name: vezocluc
// UT Student #: 1002403749
// Author: Luca Vezoc
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************

package shell;

import java.util.ArrayList;

/**
 * Class which stores explicit user input.
 * 
 * @author liangho2
 */
public class UserInputHistory {

  private ArrayList<String> commandHistory = new ArrayList<>();

  /**
   * Constructor for UserInputHistory.
   */
  public UserInputHistory() {

  }

  /**
   * Pushes a line of user input to ArrayList commandHistory.
   * 
   * @param input string of user input
   */
  public void pushLine(String input) {
    // Push a line of user input to commandHistory.
    commandHistory.add(input);
  }

  /**
   * Pops the most recent line of user input from ArrayList commandHistory.
   * 
   * @return String of most recent line of user input
   * @throws IndexOutOfBoundsException if commandHistory is empty
   */
  public String popLine() throws IndexOutOfBoundsException {
    try {
      // Pop the most recent element from commandHistory.
      return commandHistory.remove(commandHistory.size() - 1);
    } catch (IndexOutOfBoundsException exception) {
      throw exception;
    }
  }

  /**
   * Gets a line of user input at a given index from ArrayList commandHistory.
   * 
   * @return a line of user input (string)
   * @throws IndexOutOfBoundsException if index < 0 or index >
   *         commandHistory.size()
   */
  public String peekLine(int index) throws IndexOutOfBoundsException {
    try {
      // Get the element at index from commandHistory.
      return commandHistory.get(index);
    } catch (IndexOutOfBoundsException exception) {
      throw exception;
    }
  }

  /**
   * Returns the number of lines currently stored in the user history.
   * 
   * @return int number of lines currently stored in user history
   */
  public int getHistoryNum() {
    return commandHistory.size();
  }

  /**
   * Gets a number of lines from stored user input.
   * 
   * @param numLines number of lines to get
   * @return finalHistory reference to fetched user input
   */
  public String getLines(int numLines) {

    // String to store commands in.
    String finalHistory = "";
    // Variable to store length of commandHistory in.
    int chLength = commandHistory.size();

    // Truncate numLines if it is greater than the length of commandHistory. Note
    // that exception does not need to be caught because of this check.
    if (numLines > chLength) {
      numLines = chLength;
    }
    // If numLines is -1, get complete history.
    if (numLines == -1) {
      for (int i = 0; i < chLength; i++) {
        // Append a line number and the current line to finalHistory.
        finalHistory = finalHistory + (i + 1) + ". " + peekLine(i) + "\n";
      }
    } else {
      // Get numLine number of lines if numLine is positive.
      for (int i = 0; i < numLines; i++) {
        // Append a line number and the current line to finalHistory.
        finalHistory = (chLength - i) + ". " + peekLine(chLength - 1 - i) + "\n"
            + finalHistory;
      }
    }
    return finalHistory;
  }

  /**
   * Returns a string representing the instance of UserInputHistory.
   * 
   * @return string representing instance of UserInputHistory
   */
  public String toString() {
    return ("instance of UserInputHistory");
  }

}
