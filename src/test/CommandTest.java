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
import command.Command;
import filesystem.FileSystem;
import org.junit.Before;
import org.junit.Test;
import shell.IoHandler;

/**
 * Class which tests the abstract class Command's implemented methods.
 * 
 * @author liangho2
 */
public class CommandTest {

  private Command testCommand;
  private FileSystem mockFs;
  private IoHandler mockIo;
  private CommandTestHelper cth;

  /**
   * Creates a mock FileSystem, a mock IoHandler, the command Cd (to test its
   * superclass Command's methods), and a CommandTestHelper.
   */
  @Before
  public void setUp() {
    mockFs = new MockFileSystem();
    mockIo = new MockIoHandler(mockFs);
    this.testCommand = new Cd(mockFs, mockIo);
    cth = new CommandTestHelper(mockIo);
  }

  /**
   * Test errMsgToIoHandler(); it should output "testerr".
   */
  @Test
  public void testErrMsgToIoHandler() {
    cth.redirectSystemOut();
    testCommand.errMsgToIoHandler("testerr");
    mockIo.out();
    // Store output in a string.
    String out = cth.getSystemOutAsString();
    cth.resetSystemOut();
    assertEquals("testerr", out);
  }

}
