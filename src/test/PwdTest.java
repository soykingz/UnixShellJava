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

import command.Pwd;
import filesystem.FileSystem;
import org.junit.Before;
import org.junit.Test;
import shell.IoHandler;

/**
 * Class which tests the Pwd command.
 * 
 * @author liangho2
 */
public class PwdTest {

  private Pwd testPwd;
  private FileSystem mockFs;
  private IoHandler mockIo;
  private CommandTestHelper cth;

  /**
   * Creates a mock FileSystem, a mock IoHandler, the command Pwd, and a
   * CommandTestHelper.
   */
  @Before
  public void setup() {
    mockFs = new MockFileSystem();
    mockIo = new MockIoHandler(mockFs);
    this.testPwd = new Pwd(mockFs, mockIo);
    cth = new CommandTestHelper(mockIo);
  }

  /**
   * Tests execute() with an argument; "usage: pwd" should be output.
   */
  @Test
  public void testExecuteWithArgument() {
    String[] argsArray = {"pwd", "argument"};
    String expected = "usage: pwd";
    String output = cth.runCommandAndGetOutput(testPwd, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with no arguments; "cwd" should be output.
   */
  @Test
  public void testExecute0Args() {
    String[] argsArray = {"pwd"};
    String expected = "cwd";
    String output = cth.runCommandAndGetOutput(testPwd, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests Pwd's toString() method.
   */
  @Test
  public void testToString() {
    assertEquals("instance of Pwd with references: " + mockFs + ", " + mockIo,
        testPwd.toString());
  }

}
