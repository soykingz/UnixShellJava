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

import command.Cd;
import filesystem.FileSystem;
import org.junit.Before;
import org.junit.Test;
import shell.IoHandler;

/**
 * Class which tests the Cd command.
 * 
 * @author liangho2
 */
public class CdTest {

  private Cd testCd;
  private FileSystem mockFs;
  private IoHandler mockIo;
  private CommandTestHelper cth;

  /**
   * Creates a mock FileSystem, a mock IoHandler, the command Cd, and a
   * CommandTestHelper.
   */
  @Before
  public void setup() {
    mockFs = new MockFileSystem();
    mockIo = new MockIoHandler(mockFs);
    this.testCd = new Cd(mockFs, mockIo);
    cth = new CommandTestHelper(mockIo);
  }

  /**
   * Tests execute() with an invalid number of command arguments; "usage: cd
   * PATH" + "\n PATH: path cannot contain spaces" should be output.
   */
  @Test
  public void testExecuteWithInvalidNumberOfArgs() {
    String[] argsArray = {"cd", "arg1", "arg2"};
    String expected =
        "usage: cd PATH" + "\n    PATH: path cannot contain spaces";
    String output = cth.runCommandAndGetOutput(testCd, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with a valid path; "success" should be output.
   */
  @Test
  public void testExecuteWithValidPath() {
    String[] argsArray = {"cd", "validpath"};
    String expected = "success";
    String output = cth.runCommandAndGetOutput(testCd, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with an invalid path; "invalidpath: path does not exist"
   * should be output.
   */
  @Test
  public void testExecuteWithInvalidPath() {
    String[] argsArray = {"cd", "invalidpath"};
    String expected = "invalidpath: path does not exist";
    String output = cth.runCommandAndGetOutput(testCd, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests Cd's toString() method.
   */
  @Test
  public void testToString() {
    assertEquals("instance of Cd with references: " + mockFs + ", " + mockIo,
        testCd.toString());
  }

}
