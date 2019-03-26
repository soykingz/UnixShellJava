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
import shell.IoHandler;

/**
 * Command which allows the user to print the current working directory.
 * 
 * @author liangho2
 */
public class Pwd extends CommandWithOutput {

  private FileSystem fileSystem;

  /**
   * Constructor for Pwd.
   * 
   * @param fs reference to FileSystem instance
   * @param io reference to Iohandler instance
   */
  public Pwd(FileSystem fs, IoHandler io) {
    this.fileSystem = fs;
    super.setIo(io);
  }

  @Override
  public void execute(String[] argsArray) {
    // If pwd has any arguments, send an error.
    if (argsArray.length != 1) {
      String err = "usage: " + argsArray[0];
      super.errMsgToIoHandler(err);
    } else {
      // Call getCurrentWorkingDir from file system and getPath from directory.
      String path = fileSystem.getCurrentWorkingDir();
      // Send path through ioHandler.
      super.outputToIoHandler(path);
    }
  }

  @Override
  public String toString() {
    return ("instance of Pwd with references: " + fileSystem + ", "
        + ioHandler);
  }

}
