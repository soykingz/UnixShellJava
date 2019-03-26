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

package driver;

import filesystem.FileSystem;
import filesystem.JFileSystem;
import shell.IoHandler;
import shell.JIoHandler;
import shell.Shell;

/**
 * Driver for Assignment 2A (shell program).
 * 
 * @author liangho2
 */
public class JShell {

  /**
   * Creates instances of FileSystem, IoHandler, and Shell to run the assignment
   * shell.
   * 
   * @param args not applicable to this program
   */
  public static void main(String[] args) {
    // Initialize file system.
    FileSystem fileSystem = JFileSystem.createFileSystem();
    // Initialize IOHandler.
    IoHandler io = new JIoHandler(fileSystem);
    // Initialize Shell.
    Shell shell = new Shell(fileSystem, io);
    // Run shell.
    shell.run();
  }

  /**
   * Returns a string representing the JShell object.
   * 
   * @return string representing JShell object
   */
  public String toString() {
    return ("instance of JShell");
  }
}
