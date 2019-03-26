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

package command;

import shell.IoHandler;
import shell.UserInputHistory;

/**
 * Command which allows the user to see their input (command) history.
 * 
 * @author liangho2
 */
public class History extends CommandWithOutput {

  private UserInputHistory savedInput;

  /**
   * Constructor for History.
   * 
   * @param io reference to IoHandler instance
   * @param history reference to UserInputHistory instance
   */
  public History(IoHandler io, UserInputHistory history) {
    super.setIo(io);
    this.savedInput = history;
  }

  @Override
  public void execute(String[] argsArray) {

    // Error message.
    String err = "usage: " + argsArray[0] + " [NUM]\n    NUM: must be >= 0";
    // String to store return of UserInputHistory.getLines.
    String historyReturn = "";

    // Check if history has more than 1 argument.
    if (argsArray.length > 2) {
      super.errMsgToIoHandler(err);
    } else {
      if (argsArray.length == 1) {
        // Check if history has no arguments.
        historyReturn = savedInput.getLines(-1);
      } else if (Integer.parseInt(argsArray[1]) < 0) {
        // Check if second argument is a negative number.
        super.errMsgToIoHandler(err);
      } else {
        // Get the expected number of lines of user input history.
        historyReturn = savedInput.getLines(Integer.parseInt(argsArray[1]));
      }
      // Only send results through ioHandler if historyReturn is not empty.
      if (!"".equals(historyReturn.trim())) {
        super.outputToIoHandler(historyReturn.trim());
      }
    }
  }

  @Override
  public String toString() {
    return ("instance of History with references: " + ioHandler + ", "
        + savedInput);
  }

}
