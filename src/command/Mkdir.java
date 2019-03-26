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

import filesystem.FileAlreadyExistException;
import filesystem.FileSystem;
import filesystem.InvalidPathException;
import java.util.Arrays;
import shell.IoHandler;

/**
 * A command that make one or more directory.
 *
 */
public class Mkdir extends Command {

  private FileSystem fileSystem;

  /**
   * Constructor for Mkdir.
   * 
   * @param fs reference to FileSystem instance
   * @param io reference to IoHandler instance
   */
  public Mkdir(FileSystem fs, IoHandler io) {
    this.fileSystem = fs;
    super.setIo(io);
  }

  @Override
  public void execute(String[] argsArray) {
    // Check if args has 2 elements in it (command "cd" and a path ONLY).
    if (argsArray.length == 1) {
      // Send error through IoHandler.
      String err = "usage: " + argsArray[0] + " PATH..."
          + "\n    PATH: path cannot contain spaces";
      super.errMsgToIoHandler(err);
    } else {
      // Get the user input String (remove the "mkdir" string).
      String[] inputString = Arrays.copyOfRange(argsArray, 1, argsArray.length);
      String errorFile = "";
      boolean errorHappened = false;
      // loop through every argument
      for (String cur : inputString) {
        // check for invalid character
        if (cur.matches("^*[^a-zA-Z0-9/]*$")) {
          errorFile = errorFile + cur + ": contains invalid character\n";
          errorHappened = true;
          continue;
        } else if (cur.equals("/")) {
          errorFile = errorFile + cur + ": path cannot be single /\n";
          errorHappened = true;
          continue;
        }
        // create directory
        try {
          this.fileSystem.createDirectory(cur);
        } catch (InvalidPathException e) {
          errorFile = errorFile + cur + ": invalid path\n";
          errorHappened = true;
        } catch (FileAlreadyExistException e) {
          errorFile = errorFile + cur + ": path already exists\n";
          errorHappened = true;
        }
      }
      // check if error happend and send error to iohandler
      if (errorHappened) {
        super.errMsgToIoHandler(errorFile.trim());
      }
    }
  }

  @Override
  public String toString() {
    return "instance of Mkdir";
  }
}
