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

import command.History;
import filesystem.FileSystem;
import org.junit.Before;
import org.junit.Test;
import shell.IoHandler;
import shell.UserInputHistory;

/**
 * Class which tests the History command.
 * 
 * @author liangho2
 */
public class HistoryTest {

  private History testHistory;
  private FileSystem mockFs;
  private IoHandler mockIo;
  private CommandTestHelper cth;
  private UserInputHistory uih;

  /**
   * Creates a mock FileSystem, a mock IoHandler, the command History, a
   * CommandTestHelper, and a UserInputHistory (which is then populated for the
   * purposes of testing).
   */
  @Before
  public void setup() {
    mockFs = new MockFileSystem();
    mockIo = new MockIoHandler(mockFs);
    uih = new UserInputHistory();
    uih.pushLine("command 1");
    uih.pushLine("command 2");
    uih.pushLine("invalid command");
    uih.pushLine("al;dkjsaldhasd");
    uih.pushLine("owo whats this");
    this.testHistory = new History(mockIo, uih);
    cth = new CommandTestHelper(mockIo);
  }

  /**
   * Tests execute() with input with an invalid number (2) of arguments; "usage:
   * history [NUM]\n NUM: must be >= 0" should be returned.
   */
  @Test
  public void testExecuteWith2Arguments() {
    String[] argsArray = {"history", "valid", "invalid"};
    String expected =
        "usage: " + argsArray[0] + " [NUM]\n    NUM: must be >= 0";
    String output = cth.runCommandAndGetOutput(testHistory, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with input with an invalid argument (-3); "usage: history
   * [NUM]\n NUM: must be >= 0" should be returned.
   */
  @Test
  public void testExecuteWithNegativeNumber() {
    String[] argsArray = {"history", "-3"};
    String expected =
        "usage: " + argsArray[0] + " [NUM]\n    NUM: must be >= 0";
    String output = cth.runCommandAndGetOutput(testHistory, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with no arguments; "1. command 1\n2. command 2\n3. invalid
   * command\n4. al;dkjsaldhasd\n5. owo whats this" should be returned.
   */
  @Test
  public void testExecuteWithNoArgs() {
    String[] argsArray = {"history"};
    String expected =
        "1. command 1\n2. command 2\n3. invalid command\n4. al;dkjsaldhasd\n5. "
            + "owo whats this";
    String output = cth.runCommandAndGetOutput(testHistory, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with 0 as the argument; "" should be returned.
   */
  @Test
  public void testExecuteWith0() {
    String[] argsArray = {"history", "0"};
    String expected = "";
    String output = cth.runCommandAndGetOutput(testHistory, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with 3 as the argument; "3. invalid command\n4.
   * al;dkjsaldhasd\n5. owo whats this" should be returned.
   */
  @Test
  public void testExecuteWith3() {
    String[] argsArray = {"history", "3"};
    String expected =
        "3. invalid command\n4. al;dkjsaldhasd\n5. owo whats this";
    String output = cth.runCommandAndGetOutput(testHistory, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with 10 as the argument; "1. command 1\n2. command 2\n3.
   * invalid command\n4. al;dkjsaldhasd\n5. owo whats this" should be returned.
   */
  @Test
  public void testExecuteWith10() {
    String[] argsArray = {"history", "10"};
    String expected =
        "1. command 1\n2. command 2\n3. invalid command\n4. al;dkjsaldhasd\n5. "
            + "owo whats this";
    String output = cth.runCommandAndGetOutput(testHistory, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests History's toString() method.
   */
  @Test
  public void testToString() {
    assertEquals("instance of History with references: " + mockIo + ", " + uih,
        testHistory.toString());
  }

}
