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

import shell.IoHandler;

/**
 * Command is the abstract parent class for every command in Assignment 2A,
 * which runs commands and handles output direction.
 * 
 * @author liangho2
 */
public abstract class Command {

  // Instantiate an ioHandler for use in every command.
  protected IoHandler ioHandler;

  /**
   * Sets ioHandler, which Command makes use of.
   * 
   * @param io reference to IoHandler instance
   */
  public void setIo(IoHandler io) {
    this.ioHandler = io;
  }

  /**
   * Executes command, operating on an argument list.
   * 
   * @param argsArray input argument array to command
   */
  public abstract void execute(String[] argsArray);

  /**
   * Sends an error string through IoHandler to be output.
   * 
   * @param err string describing error
   */
  public void errMsgToIoHandler(String err) {
    ioHandler.standardError(err);
  }

  /**
   * Returns a string representation of the current command.
   * 
   * @return string representing the current command
   */
  public abstract String toString();
}
