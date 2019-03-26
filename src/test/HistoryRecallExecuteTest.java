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

import command.HistoryRecallExecute;
import command.UrlDownloader;
import filesystem.FileSystem;
import org.junit.Before;
import org.junit.Test;
import shell.CommandExecutor;
import shell.CommandInitializer;
import shell.IoHandler;
import shell.UserInputHistory;

/**
 * Class which tests the HistoryRecallExecute command.
 * 
 * @author liangho2
 */
public class HistoryRecallExecuteTest {

  private HistoryRecallExecute testHistoryRecallExecute;
  private FileSystem mockFs;
  private IoHandler mockIo;
  private CommandTestHelper cth;
  private UserInputHistory uih;
  private UrlDownloader ud;
  private CommandInitializer initializer;
  private CommandExecutor executor;

  /**
   * Creates a mock FileSystem, a mock IoHandler, the command
   * HistoryRecallExecute, a CommandTestHelper, a UserInputHistory (which is
   * then populated for the purposes of testing), a CommandInitialzer, and a
   * CommandExecutor. (An empty instance of UrlDownloader is created only for
   * use in instantiation purposes.)
   */
  @Before
  public void setup() {
    mockFs = new MockFileSystem();
    mockIo = new MockIoHandler(mockFs);
    uih = new UserInputHistory();
    uih.pushLine("command 1");
    uih.pushLine("!2");
    uih.pushLine("invalid command");
    uih.pushLine("al;dkjsaldhasd");
    uih.pushLine("owo whats this");
    executor = new CommandExecutor(mockIo);
    initializer =
        CommandInitializer.createInstance(mockFs, mockIo, uih, executor, ud);
    this.testHistoryRecallExecute =
        new HistoryRecallExecute(mockIo, uih, initializer, executor);
    cth = new CommandTestHelper(mockIo);
  }

  /**
   * Tests execute() with input with an invalid number (0) of arguments; "usage:
   * !NUM\n NUM >= 1" should be returned.
   */
  @Test
  public void testExecuteWith0Arguments() {
    String[] argsArray = {"!"};
    String expected = "usage: " + argsArray[0] + "NUM\n    NUM >= 1";
    String output =
        cth.runCommandAndGetOutput(testHistoryRecallExecute, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with input with an invalid number (2) of arguments; "usage:
   * !NUM\n NUM >= 1" should be returned.
   */
  @Test
  public void testExecuteWith2Arguments() {
    String[] argsArray = {"!", "valid", "invalid"};
    String expected = "usage: " + argsArray[0] + "NUM\n    NUM >= 1";
    String output =
        cth.runCommandAndGetOutput(testHistoryRecallExecute, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with input with an invalid argument (-3); "-3: invalid
   * number\nusage: !NUM\n NUM >= 1" should be returned.
   */
  @Test
  public void testExecuteWithNegativeNumber() {
    String[] argsArray = {"!", "-3"};
    String expected =
        "-3: invalid number\n" + "usage: " + argsArray[0] + "NUM\n    NUM >= 1";
    String output =
        cth.runCommandAndGetOutput(testHistoryRecallExecute, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with input with an invalid argument ("asd"); "\"asd\": not
   * an integer\nusage: !NUM\n NUM >= 1" should be returned.
   */
  @Test
  public void testExecuteWithString() {
    String[] argsArray = {"!", "asd"};
    String expected = "\"asd\": not an integer\n" + "usage: " + argsArray[0]
        + "NUM\n    NUM >= 1";
    String output =
        cth.runCommandAndGetOutput(testHistoryRecallExecute, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with input with an invalid argument (100); "!100: number
   * greater than number of lines in user history" should be returned.
   */
  @Test
  public void testExecuteWith100() {
    String[] argsArray = {"!", "100"};
    String expected =
        "!100: number greater than number of lines in user history";
    String output =
        cth.runCommandAndGetOutput(testHistoryRecallExecute, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with input with a valid argument (1); "!1: operation
   * failed\n command 1: command not found" should be returned.
   */
  @Test
  public void testExecuteWith1() {
    String[] argsArray = {"!", "1"};
    String expected = "!1: operation failed\n    command: command not found";
    String output =
        cth.runCommandAndGetOutput(testHistoryRecallExecute, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with input with a valid argument (5); "!5: operation
   * failed\n owo whats this: command not found" should be returned.
   */
  @Test
  public void testExecuteWith5() {
    String[] argsArray = {"!", "5"};
    String expected = "!5: operation failed\n    owo: command not found";
    String output =
        cth.runCommandAndGetOutput(testHistoryRecallExecute, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with input with a valid recursive argument (!2); "!2:
   * number greater than number of lines in user history" should be returned.
   */
  @Test
  public void testExecuteWith2Recursion() {
    String[] argsArray = {"!", "2"};
    String expected = "!2: number greater than number of lines in user history";
    String output =
        cth.runCommandAndGetOutput(testHistoryRecallExecute, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests HistoryRecallExecute's toString() method.
   */
  @Test
  public void testToString() {
    assertEquals(
        "instance of HistoryRecallExecute with references: " + mockIo + ", "
            + uih + ", " + executor + ", " + initializer,
        testHistoryRecallExecute.toString());
  }

}
