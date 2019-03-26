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

import java.util.Arrays;

/**
 * Class which separates a command's arguments from its redirection portion, if
 * present.
 * 
 * @author liangho2
 */
public class CommandRedirectSplitter {

  /**
   * Splits a command's arguments by a redirection symbol, if found.
   * 
   * @param args array of strings that are input command arguments
   * @return tuple reference to split arguments of two arrays of strings
   * @throws RedirectSymbolNotPresentException thrown if no redirect symbol is
   *         found
   */
  public static String[][] split(String[] args)
      throws RedirectSymbolNotPresentException {

    // Create a tuple to store the command args and redirect args.
    String[][] tuple = new String[2][];

    for (int i = 0; i < args.length; i++) {
      // If a redirect symbol is found, split the input args.
      if (">".equals(args[i]) || ">>".equals(args[i])) {
        String[] commandArgs = Arrays.copyOfRange(args, 0, i);
        String[] redirectArgs = Arrays.copyOfRange(args, i, args.length);
        tuple[0] = commandArgs;
        tuple[1] = redirectArgs;
        // Break immediately after finishing processing.
        break;
      } else if (i == (args.length - 1)) {
        // If all arguments have been iterated through and no redirect symbol
        // has been found, throw an exception.
        throw new RedirectSymbolNotPresentException();
      }
    }
    // Return tuple if it is populated.
    return tuple;
  }

  /**
   * Returns a string representing the instance of CommandRedirectSplitter.
   * 
   * @return string representing instance of CommandRedirectSplitter
   */
  public String toString() {
    return ("instance of CommandRedirectSplitter");
  }

}
