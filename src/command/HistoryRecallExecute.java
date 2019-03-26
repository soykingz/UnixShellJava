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

import command.HistoryRecallExecute;
import shell.CommandExecutor;
import shell.CommandInitializer;
import shell.CommandInstantiationException;
import shell.InvalidCommandException;
import shell.IoHandler;
import shell.UserInputHistory;
import shell.UserInputParser;

/**
 * Command which allows the user to recall a line of user history and execute
 * it.
 * 
 * @author liangho2
 */
public class HistoryRecallExecute extends Command {

  private UserInputHistory savedInput;
  private CommandInitializer initializer;
  private CommandExecutor executor;

  /**
   * Constructor for HistoryRecallExecute.
   * 
   * @param history reference to UserInputHistory instance
   */
  public HistoryRecallExecute(IoHandler io, UserInputHistory history,
      CommandInitializer ci, CommandExecutor ex) {
    super.setIo(io);
    this.savedInput = history;
    this.initializer = ci;
    this.executor = ex;
  }

  @Override
  public void execute(String[] argsArray) {
    // Usage message.
    String err = "usage: " + argsArray[0] + "NUM\n    NUM >= 1";
    // Check if more than one argument is given; if so, send an error.
    if (argsArray.length != 2) {
      super.errMsgToIoHandler(err);
      // Remove !NUM stored in history.
      savedInput.popLine();
    } else {
      try {
        // Convert the argument to a int to be used with savedInput.
        int historyNum = Integer.parseInt(argsArray[1]);
        // If conversion is successful but number is < 1, send error.
        if (historyNum < 1) {
          err = historyNum + ": invalid number\n" + err;
          // Remove !NUM stored in history.
          savedInput.popLine();
          super.errMsgToIoHandler(err);
        } else if (historyNum > savedInput.getHistoryNum()) {
          // Send error if historyNum is greater than the number of lines
          // stored in the history. Note that IndexOutOfBoundsException does not need
          // to be caught because of this check.
          err = "!" + historyNum
              + ": number greater than number of lines in user history";
          // Remove !NUM stored in history.
          savedInput.popLine();
          super.errMsgToIoHandler(err);
        } else {
          // If number is valid, remove the !num stored in history and replace
          // it with the actual command that !num refers to.
          String userInput = savedInput.peekLine(historyNum - 1);
          savedInput.popLine();
          savedInput.pushLine(userInput);
          // Attempt to run the recalled line.
          runRecalledLine(userInput, argsArray[1]);
        }
      } catch (NumberFormatException exception) {
        // If an error occurs converting string to a number send an error.
        err = "\"" + argsArray[1] + "\"" + ": not an integer\n" + err;
        // Remove !NUM from history.
        savedInput.popLine();
        super.errMsgToIoHandler(err);
      }
    }
  }

  /**
   * Attempts to run recalled user input from the user's history.
   * 
   * @param userInput string representing user input
   * @param recallNum string representing the number given in !NUM command
   */
  private void runRecalledLine(String userInput, String recallNum) {
    // Parse line for use in re-execution.
    String[] commandArgs = UserInputParser.parse(userInput);
    try {
      // Validate command.
      Command command = initializer.validate(commandArgs[0]);
      // Make sure not to get stuck in an infinite loop if !NUM is the command
      // returned.
      if (command instanceof HistoryRecallExecute) {
        String err = "!" + commandArgs[1]
            + ": number greater than number of lines in user history";
        // Don't save !NUM to history.
        savedInput.popLine();
        super.errMsgToIoHandler(err);
      } else {
        // Directly run the command.
        executor.runOnInput(command, commandArgs);
      }
    } catch (CommandInstantiationException exception) {
      // This shouldn't happen, but if it does, send appropriate error.
      String err = "!" + recallNum + ": operation failed\n    " + commandArgs[0]
          + ": failed to initialize command";
      super.errMsgToIoHandler(err);
    } catch (InvalidCommandException exception1) {
      // Send relevant error if command is invalid.
      String err = "!" + recallNum + ": operation failed\n    " + commandArgs[0]
          + ": command not found";
      super.errMsgToIoHandler(err);
    }
  }

  @Override
  public String toString() {
    return ("instance of HistoryRecallExecute with references: " + ioHandler
        + ", " + savedInput + ", " + executor + ", " + initializer);
  }

}
