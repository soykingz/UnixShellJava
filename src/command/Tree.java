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
 * This is a command that prints the name of every file in the filesystem and
 * file's name indent at every directory level.
 */
public class Tree extends Command {

  private FileSystem fileSystem;

  /**
   * Constructor for Tree.
   * 
   * @param fs reference to FileSystem instance
   * @param io reference to IoHandler instance
   */
  public Tree(FileSystem fs, IoHandler io) {
    this.fileSystem = fs;
    super.setIo(io);
  }

  @Override
  public void execute(String[] argsArray) {
    if (argsArray.length != 1) {
      this.errMsgToIoHandler("usage: tree");
    } else {
      this.ioHandler.standardOutput(fileSystem.toString());
    }
  }

  @Override
  public String toString() {
    return "a instance of tree command";
  }
}
