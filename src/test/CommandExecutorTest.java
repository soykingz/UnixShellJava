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

import static org.junit.Assert.assertEquals;

import command.Command;
import command.Echo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shell.CommandExecutor;

/**
 * Class which tests CommandExecutor.
 * 
 * @author liangho2
 */
public class CommandExecutorTest {

  private CommandExecutor ce;
  private MockFileSystem mockFs;
  private MockIoHandler mockIo;
  private Command echo;
  private CommandTestHelper cth;

  /**
   * Creates a MockFileSystem, a MockIoHandler, and an instance of
   * CommandExecutor, along with a CommandTestHelper, and redirects System.out
   * for use in testing.
   */
  @Before
  public void setUp() {
    mockFs = new MockFileSystem();
    mockIo = new MockIoHandler(mockFs);
    ce = new CommandExecutor(mockIo);
    // Valid Echo command for use with testing.
    echo = new Echo(mockIo);
    cth = new CommandTestHelper(mockIo);
    // Redirect System.out for use in testing.
    cth.redirectSystemOut();
  }

  /**
   * Resets System.out.
   */
  @After
  public void tearDown() {
    cth.resetSystemOut();
  }

  /**
   * Tests runOnInput() with a ">" redirection symbol with multiple OUTFILES;
   * should output ">: redirection valid for one file only\n".
   */
  @Test
  public void testRunOnInputWithMultipleOverwriteRedireciton() {
    String[] arguments = {"echo", "\"hello\"", ">", "OUTFILE1", "OUTFILE2"};
    String expected = ">: redirection valid for one file only\n";
    ce.runOnInput(echo, arguments);
    String output = cth.getSystemOutAsString();
    assertEquals(expected, output);
  }

  /**
   * Tests runOnInput() with a ">>" redirection symbol with multiple OUTFILES;
   * should output ">>: redirection valid for one file only\n".
   */
  @Test
  public void testRunOnInputWithMultipleAppendRedireciton() {
    String[] arguments = {"echo", "\"hello\"", ">>", "OUTFILE1", "OUTFILE2"};
    String expected = ">>: redirection valid for one file only\n";
    ce.runOnInput(echo, arguments);
    String output = cth.getSystemOutAsString();
    assertEquals(expected, output);
  }

  /**
   * Tests runOnInput() with a ">" redirection symbol with a single OUTFILE;
   * should output "Redirected to OUTFILE1".
   */
  @Test
  public void testRunOnInputWithValidRedirection() {
    String[] arguments = {"echo", "\"hello\"", ">", "OUTFILE1"};
    String expected = "Redirected to OUTFILE1";
    ce.runOnInput(echo, arguments);
    String output = cth.getSystemOutAsString();
    assertEquals(expected, output);
  }

  /**
   * Tests runOnInput() with no redirection symbol; should output "hello".
   */
  @Test
  public void testRunOnInputWithNoRedirection() {
    String[] arguments = {"echo", "\"hello\""};
    String expected = "hello";
    ce.runOnInput(echo, arguments);
    String output = cth.getSystemOutAsString();
    assertEquals(expected, output);
  }

  /**
   * Tests CommandExecutor's toString() method.
   */
  @Test
  public void testToString() {
    assertEquals("instance of CommandExecutor", ce.toString());
  }

}
