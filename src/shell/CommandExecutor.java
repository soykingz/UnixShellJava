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

import command.Command;

/**
 * Class which starts Command operation.
 * 
 * @author liangho2
 *
 */
public class CommandExecutor {

  private IoHandler ioHandler;

  /**
   * Constructor for CommandExecutor.
   * 
   * @param io reference to IoHandler instance
   */
  public CommandExecutor(IoHandler io) {
    this.ioHandler = io;
  }

  /**
   * Starts a valid given command's operation.
   * 
   * </p>
   * Redirects command operation as necessary if a redirect symbol is present in
   * the input command arguments.
   * 
   * @param arguments array of strings representing a command and its arguments
   */
  public void runOnInput(Command command, String[] arguments) {

    // Create a tuple to store CommandRedirectSplitter output.
    String[][] argsSplitByRedirect;
    // Variable to store redirect validation status.
    int redirectStatus;

    // If there is a redirection, redirect as necessary. Otherwise, run
    // command with user input list of arguments.
    try {
      argsSplitByRedirect = CommandRedirectSplitter.split(arguments);
      command.execute(argsSplitByRedirect[0]);
      // Validate redirection for a single file ONLY.
      redirectStatus = RedirectValidator.validate(argsSplitByRedirect[1]);
      if (redirectStatus == 1) {
        System.out.printf("%s: redirection valid for one file only\n",
            argsSplitByRedirect[1][0]);
      } else {
        ioHandler.reDirect(argsSplitByRedirect[1][0],
            argsSplitByRedirect[1][1]);
        // Send results through ioHandler.
        ioHandler.out();
      }
    } catch (RedirectSymbolNotPresentException e) {
      command.execute(arguments);
      // Send results through ioHandler.
      ioHandler.out();
    }

  }

  /**
   * Returns a string representing the instance of CommandExecutor.
   * 
   * @return string representing the instance of CommandExecutor
   */
  public String toString() {
    return ("instance of CommandExecutor");
  }

}
