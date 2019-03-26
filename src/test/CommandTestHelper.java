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

package test;

import command.Command;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import shell.IoHandler;

/**
 * JUnit test helper class which gets content output by commands to System.out.
 * 
 * @author liangho
 */
public class CommandTestHelper {

  // Reference to redirect System.out to.
  private ByteArrayOutputStream outRedirect;
  // Variable to store system.out to restore later.
  private PrintStream sysOut;
  private IoHandler mockIo;

  /**
   * Constructor for CommandTestHelper.
   */
  public CommandTestHelper(IoHandler mockIo) {
    outRedirect = new ByteArrayOutputStream();
    sysOut = System.out;
    this.mockIo = mockIo;
  }

  /**
   * Redirects System.out to a ByteArrayOutputStream.
   */
  public void redirectSystemOut() {
    // Redirect System.out to outRedirect.
    System.setOut(new PrintStream(outRedirect));
  }

  /**
   * Returns a string that is the content printed to System.out and restores
   * System.out
   * 
   * @return output string of content printed to System.out
   */
  public String getSystemOutAsString() {
    // Store written content in a string.
    String output = new String(outRedirect.toByteArray());
    // Reset outRedirect to use again.
    outRedirect.reset();

    return output;
  }

  /**
   * Restores System.out.
   */
  public void resetSystemOut() {
    // Restore sysOut.
    System.setOut(sysOut);
  }

  /**
   * Returns a String containing the command output of a command c run with
   * command arguments argsArray.
   * 
   * @param c given Command
   * @param argsArray given array of Strings representing command arguments
   * @return out string containing command's output
   */
  public String runCommandAndGetOutput(Command c, String[] argsArray) {
    // Redirect System.out to capture output of command.
    redirectSystemOut();
    // Run command.
    c.execute(argsArray);
    mockIo.out();
    // Store output in a string.
    String out = getSystemOutAsString();
    resetSystemOut();

    return out;
  }

}
