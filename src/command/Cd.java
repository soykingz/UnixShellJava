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

import filesystem.FileSystem;
import filesystem.InvalidPathException;
import shell.IoHandler;

/**
 * Command which allows the user to change into a directory.
 * 
 * @author liangho2
 */
public class Cd extends Command {

  private FileSystem fileSystem;

  /**
   * Constructor for Cd.
   * 
   * @param fs reference to FileSystem instance
   * @param io reference to IoHandler instance
   */
  public Cd(FileSystem fs, IoHandler io) {
    this.fileSystem = fs;
    super.setIo(io);
  }

  @Override
  public void execute(String[] argsArray) {
    // Check if args has 2 elements in it (command "cd" and a path ONLY).
    if (argsArray.length != 2) {
      // Send error through IoHandler.
      String err = "usage: " + argsArray[0] + " PATH"
          + "\n    PATH: path cannot contain spaces";
      super.errMsgToIoHandler(err);
    } else {
      // Call file system's change directory method.
      try {
        this.fileSystem.changeWorkingDirectory(argsArray[1]);
      } catch (InvalidPathException exception) {
        // Check if exception was thrown; send error through IoHandler if so.
        String err = argsArray[1] + ": path does not exist";
        super.errMsgToIoHandler(err);
      }
    }

  }

  @Override
  public String toString() {
    return ("instance of Cd with references: " + fileSystem + ", " + ioHandler);
  }

}
