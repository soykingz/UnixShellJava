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

import java.util.Arrays;
import shell.IoHandler;

/**
 * Command which allows the user to display input strings.
 * 
 * @author liangho2
 */
public class Echo extends CommandWithOutput {

  /**
   * Constructor for Echo.
   * 
   * @param io reference to IoHandler instance
   */
  public Echo(IoHandler io) {
    super.setIo(io);
  }

  @Override
  public void execute(String[] argsArray) {
    // Get the user input String (remove the "echo" string).
    String[] inputString = Arrays.copyOfRange(argsArray, 1, argsArray.length);
    // Recombine input string in case there are spaces in user input.
    String recombined = String.join(" ", inputString);
    // Check if input string has 1 leading double quote and one trailing double
    // quote ONLY.
    if ((recombined.indexOf('"') != 0)
        || (recombined.indexOf('"', 1) != (recombined.length() - 1))) {
      // Send error through ioHandler.
      String err = "usage: " + argsArray[0] + " \"STRING\" [> FILE] [>> FILE]"
          + "\n    \"STRING\": input string cannot contain double quotes"
          + "\n    FILE: file names cannot contain spaces";
      super.errMsgToIoHandler(err);
    } else {
      // Trim the leading and trailing double quotes if string is valid.
      recombined = recombined.substring(1, (recombined.length() - 1));
      // Send recombined string through a file to ioHandler.
      super.outputToIoHandler(recombined);
    }

  }

  @Override
  public String toString() {
    return ("instance of Echo with references: " + ioHandler);
  }

}
