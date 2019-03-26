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
import command.JUrlDownloader;
import command.UrlDownloader;
import filesystem.FileSystem;
import java.util.Scanner;

/**
 * Class that runs the main loop of Assignment 2A's shell.
 * 
 * @author liangho2
 */
public class Shell {

  private FileSystem fileSystem;
  private IoHandler ioHandler;

  /**
   * Constructor for Shell.
   * 
   * @param fs reference to FileSystem instance
   * @param io reference to IoHandler instance
   */
  public Shell(FileSystem fs, IoHandler io) {
    this.fileSystem = fs;
    this.ioHandler = io;
  }

  /**
   * Runs a shell loop which repeatedly waits for user input and executes valid
   * input.
   */
  public void run() {
    // Create a scanner to read user input.
    Scanner scan = new Scanner(System.in);
    // String to store line of user input.
    String input = "";
    // String to store arguments of user input separated by whitespace.
    String[] arguments;
    // Create a UserHistory instance to store user input.
    UserInputHistory history = new UserInputHistory();
    // Create a UrlDownloader for use with CommandInitializer.
    UrlDownloader urlDown = new JUrlDownloader();
    // Create a CommandExecutor to start commands after they have been
    // validated.
    CommandExecutor commandExecutor = new CommandExecutor(ioHandler);
    // Create a CommandInitializer instance to validate command inputs.
    CommandInitializer commandInitializer = CommandInitializer.createInstance(
        fileSystem, ioHandler, history, commandExecutor, urlDown);
    // Create a Command to store a validated command to sent to CommandExecutor.
    Command command;

    while (true) {
      // Print prompt.
      System.out.printf("/# ");
      // Take a line of user input.
      input = scan.nextLine();
      // Send current line of user input to UserInputHistory.
      history.pushLine(input);
      // Parse input.
      arguments = UserInputParser.parse(input);

      // Immediately exit if that keyword is found.
      if ("exit".equals(arguments[0])) {
        break;
      }
      // Check if input is all whitespace (do nothing if so).
      if (arguments.length > 0 && !(arguments[0].equals(""))) {
        try {
          // Validate command.
          command = commandInitializer.validate(arguments[0]);
          // Execute command.
          commandExecutor.runOnInput(command, arguments);
        } catch (CommandInstantiationException exception) {
          // This shouldn't happen, but if it does, print appropriate error
          // message.
          System.out.printf("%s: failed to initialize command\n", arguments[0]);
        } catch (InvalidCommandException exception1) {
          // Print out relevant error if command is invalid.
          System.out.printf("%s: command not found\n", arguments[0]);
        }
      }
    }
    scan.close();
  }

  /**
   * Returns a string representing the instance of Shell.
   * 
   * @return string representing instance of Shell
   */
  public String toString() {
    return ("instance of Shell");
  }

}
