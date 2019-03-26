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

import command.Ls;
import filesystem.FileSystem;
import org.junit.Before;
import org.junit.Test;
import shell.IoHandler;

/**
 * Class which tests the Ls command.
 * 
 * @author liangho2
 */
public class LsTest {

  private Ls testLs;
  private FileSystem mockFs;
  private IoHandler mockIo;
  private CommandTestHelper cth;

  /**
   * Creates a mock FileSystem, a mock IoHandler, the command Ls, and a
   * CommandTestHelper.
   */
  @Before
  public void setup() {
    mockFs = new MockFileSystem();
    mockIo = new MockIoHandler(mockFs);
    this.testLs = new Ls(mockFs, mockIo);
    cth = new CommandTestHelper(mockIo);
  }

  /**
   * Tests execute() with an invalid path; "invalidpath: path does not exist"
   * should be output.
   */
  @Test
  public void testExecuteWithInvalidPath() {
    String[] argsArray = {"ls", "invalidpath"};
    String expected = "invalidpath: path does not exist";
    String output = cth.runCommandAndGetOutput(testLs, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with 2 invalid paths; "invalidpath: path does not exist\n
   * invalidpath2: path does not exist" should be output.
   */
  @Test
  public void testExecuteWith2InvalidPaths() {
    String[] argsArray = {"ls", "invalidpath", "invalidpath2"};
    String expected =
        "invalidpath: path does not exist\ninvalidpath2: path does not exist";
    String output = cth.runCommandAndGetOutput(testLs, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with no arguments; "cwd:\ncwd2\ncwd1" should be output.
   */
  @Test
  public void testExecuteWithNoArguments() {
    String[] argsArray = {"ls"};
    String expected = "cwd:\ncwd2\ncwd1";
    String output = cth.runCommandAndGetOutput(testLs, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with a valid path; "validpath:\ncontent2\ncontent1" should
   * be output.
   */
  @Test
  public void testExecuteWithValidPath() {
    String[] argsArray = {"ls", "validpath"};
    String expected = "validpath:\ncontent2\ncontent1";
    String output = cth.runCommandAndGetOutput(testLs, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with a valid path and invalid path;
   * "validpath:\ncontent2\ncontent1invalidpath: path does not exist" should be
   * output.
   */
  @Test
  public void testExecuteWithValidAndInvalidPath() {
    String[] argsArray = {"ls", "validpath", "invalidpath"};
    String expected =
        "validpath:\ncontent2\ncontent1invalidpath: path does not exist";
    String output = cth.runCommandAndGetOutput(testLs, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with a valid path and invalid path with recursion active;
   * "/:\n validtreeinvalidpath: path does not exist" should be output.
   */
  @Test
  public void testExecuteWithValidAndInvalidPathRecursion() {
    String[] argsArray = {"ls", "-R", "validpath", "invalidpath"};
    String expected = "/:\n  validtreeinvalidpath: path does not exist";
    String output = cth.runCommandAndGetOutput(testLs, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests Ls's toString() method.
   */
  @Test
  public void testToString() {
    assertEquals("instance of Ls with references: " + mockFs + ", " + mockIo,
        testLs.toString());
  }
}
