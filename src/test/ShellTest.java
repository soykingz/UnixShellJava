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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shell.Shell;

/**
 * Class which tests Shell.
 * 
 * @author liangho2
 */
public class ShellTest {

  private MockFileSystem mockFs;
  private MockIoHandler mockIo;
  private Shell testShell;
  private InputStream simulatedUserInput;
  private CommandTestHelper cth;
  private InputStream stdin = System.in;

  /**
   * Creates instances of MockFileSystem and MockIoHandler and creates a Shell
   * instance to test. Also creates a CommandTestHelper to redirect System.out.
   */
  @Before
  public void setUp() {
    mockFs = new MockFileSystem();
    mockIo = new MockIoHandler(mockFs);
    testShell = new Shell(mockFs, mockIo);
    cth = new CommandTestHelper(mockIo);
    cth.redirectSystemOut();
  }

  /**
   * Resets System.out and System.in
   */
  @After
  public void tearDown() {
    cth.resetSystemOut();
    System.setIn(stdin);
  }

  /**
   * Tests run() with empty simulated user input. "/# " should be output (only
   * the prompt).
   */
  @Test
  public void testRunWithNoInput() {
    simulatedUserInput = new ByteArrayInputStream("".getBytes());
    System.setIn(simulatedUserInput);
    // Run shell on simulated user input for as long as there is simulated
    // input.
    try {
      testShell.run();
    } catch (NoSuchElementException e) {
      // No more simulated user input, so continue to check test results.
      ;
    }
    String output = cth.getSystemOutAsString();
    assertEquals("/# ", output);
  }

  /**
   * Tests run() with populated simulated user input (an invalid command). "/#
   * invalid: command not found\n/# " should be output (prompt, error, prompt
   * again).
   */
  @Test
  public void testRunWithCommandInput() {
    simulatedUserInput = new ByteArrayInputStream("invalid\n".getBytes());
    System.setIn(simulatedUserInput);
    // Run shell on simulated user input for as long as there is simulated
    // input.
    try {
      testShell.run();
    } catch (NoSuchElementException e) {
      // No more simulated user input, so continue to check test results.
      ;
    }
    String output = cth.getSystemOutAsString();
    assertEquals("/# invalid: command not found\n/# ", output);
  }

  /**
   * Tests run() with "exit" keyword. "/# " should be output (prompt, immediate
   * exit so nothing else is output).
   */
  @Test
  public void testRunWithExitKeyword() {
    simulatedUserInput = new ByteArrayInputStream("exit\n".getBytes());
    System.setIn(simulatedUserInput);
    // Run shell on simulated user input for as long as there is simulated
    // input.
    try {
      testShell.run();
    } catch (NoSuchElementException e) {
      // No more simulated user input, so continue to check test results.
      ;
    }
    String output = cth.getSystemOutAsString();
    assertEquals("/# ", output);
  }

  /**
   * Tests Shell's toString() method.
   */
  @Test
  public void testToString() {
    assertEquals("instance of Shell", testShell.toString());
  }

}
