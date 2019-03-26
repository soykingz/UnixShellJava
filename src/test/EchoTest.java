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

import command.Echo;
import filesystem.FileSystem;
import org.junit.Before;
import org.junit.Test;
import shell.IoHandler;

/**
 * Class which tests the Echo command.
 * 
 * @author liangho2
 */
public class EchoTest {

  private Echo testEcho;
  private FileSystem mockFs;
  private IoHandler mockIo;
  private CommandTestHelper cth;

  /**
   * Creates a mock FileSystem, a mock IoHandler, the command Echo, and a
   * CommandTestHelper.
   */
  @Before
  public void setup() {
    mockFs = new MockFileSystem();
    mockIo = new MockIoHandler(mockFs);
    this.testEcho = new Echo(mockIo);
    cth = new CommandTestHelper(mockIo);
  }

  /**
   * Tests execute() with input with no quotes; "usage: echo \"STRING\" [> FILE]
   * [>> FILE]" + "\n \"STRING\": input string cannot contain double quotes" +
   * "\n FILE: file names cannot contain spaces" should be output.
   */
  @Test
  public void testExecuteWithNoQuotesInput() {
    String[] argsArray = {"echo", "missing", "quotes"};
    String expected =
        "usage: " + argsArray[0] + " \"STRING\" [> FILE] [>> FILE]"
            + "\n    \"STRING\": input string cannot contain double quotes"
            + "\n    FILE: file names cannot contain spaces";
    String output = cth.runCommandAndGetOutput(testEcho, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with input with one quote; "usage: echo \"STRING\" [> FILE]
   * [>> FILE]" + "\n \"STRING\": input string cannot contain double quotes" +
   * "\n FILE: file names cannot contain spaces" should be output.
   */
  @Test
  public void testExecuteWithOneQuoteInput() {
    String[] argsArray = {"echo", "\"one", "quote"};
    String expected =
        "usage: " + argsArray[0] + " \"STRING\" [> FILE] [>> FILE]"
            + "\n    \"STRING\": input string cannot contain double quotes"
            + "\n    FILE: file names cannot contain spaces";
    String output = cth.runCommandAndGetOutput(testEcho, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with input with too many (3) quotes; "usage: echo
   * \"STRING\" [> FILE] [>> FILE]" + "\n \"STRING\": input string cannot
   * contain double quotes" + "\n FILE: file names cannot contain spaces" should
   * be output.
   */
  @Test
  public void testExecuteWith3QuotesInput() {
    String[] argsArray = {"echo", "\"toomany\"", "quotes\""};
    String expected =
        "usage: " + argsArray[0] + " \"STRING\" [> FILE] [>> FILE]"
            + "\n    \"STRING\": input string cannot contain double quotes"
            + "\n    FILE: file names cannot contain spaces";
    String output = cth.runCommandAndGetOutput(testEcho, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with input with quotes in wrong position; "usage: echo
   * \"STRING\" [> FILE] [>> FILE]" + "\n \"STRING\": input string cannot
   * contain double quotes" + "\n FILE: file names cannot contain spaces" should
   * be output.
   */
  @Test
  public void testExecuteWithWrongQuotePositionInput() {
    String[] argsArray = {"echo", "wrong\"quote", "posi\"tion"};
    String expected =
        "usage: " + argsArray[0] + " \"STRING\" [> FILE] [>> FILE]"
            + "\n    \"STRING\": input string cannot contain double quotes"
            + "\n    FILE: file names cannot contain spaces";
    String output = cth.runCommandAndGetOutput(testEcho, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with one input which is valid; "onevalid" should be output.
   */
  @Test
  public void testExecuteWithOneValidInputt() {
    String[] argsArray = {"echo", "\"onevalid\""};
    String expected = "onevalid";
    String output = cth.runCommandAndGetOutput(testEcho, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with multiple (3) valid input; "thr ee3 valid" should be
   * output.
   */
  @Test
  public void testExecuteWithMultipleValidInput() {
    String[] argsArray = {"echo", "\"thr", "ee3", "valid\""};
    String expected = "thr ee3 valid";
    String output = cth.runCommandAndGetOutput(testEcho, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests Echo's toString() method.
   */
  @Test
  public void testToString() {
    assertEquals("instance of Echo with references: " + mockIo,
        testEcho.toString());
  }

}
