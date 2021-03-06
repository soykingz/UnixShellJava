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

import filesystem.FileNotExistException;
import filesystem.FileSystem;
import filesystem.InvalidPathException;
import java.util.Arrays;
import shell.IoHandler;

/**
 * Command which output the content of file at given path.
 */
public class Cat extends Command {

  private FileSystem fileSystem;

  /**
   * Constructor for Cat.
   * 
   * @param fs reference to FileSystem instance
   * @param io reference to IoHandler instance
   */
  public Cat(FileSystem fs, IoHandler io) {
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
      // Get the user input String (remove the "cat" string).
      String[] inputString = Arrays.copyOfRange(argsArray, 1, argsArray.length);
      String errorFiles = "";
      String result = "";
      boolean errorHappened = false;
      for (String cur : inputString) {
        // get the content of every path
        try {
          this.fileSystem.getFile(cur);
          result = result + this.fileSystem.getFile(cur) + "\n" + "\n";
        } catch (FileNotExistException | InvalidPathException e) {
          errorFiles = errorFiles + cur + ": does not exist\n";
          errorHappened = true;
        }
      }
      if (errorHappened) {
        super.errMsgToIoHandler(errorFiles.trim());
      }
      if (!"".equals(result)) {
        this.ioHandler.standardOutput(result.trim());
      }
    }
  }

  @Override
  public String toString() {
    return "instance of Cat Command";
  }
}
