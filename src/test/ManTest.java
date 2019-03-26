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

import command.Man;
import filesystem.FileSystem;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.Before;
import org.junit.Test;
import shell.IoHandler;

/**
 * Class which tests the Man command.
 * 
 * @author liangho2
 */
public class ManTest {

  private Man testMan;
  private FileSystem mockFs;
  private IoHandler mockIo;
  private CommandTestHelper cth;

  /**
   * Creates a mock FileSystem, a mock IoHandler, the command Man (using
   * reflection), and a CommandTestHelper.
   */
  @Before
  public void setup() {
    mockFs = new MockFileSystem();
    mockIo = new MockIoHandler(mockFs);
    // Use reflection to access Man's constructor to be able to instantiate a
    // clean instance of Man for every test.
    try {
      Constructor<Man> manCon =
          Man.class.getDeclaredConstructor(IoHandler.class);
      manCon.setAccessible(true);
      testMan = manCon.newInstance(mockIo);
    } catch (NoSuchMethodException | SecurityException | InstantiationException
        | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException e) {
      // This should never happen.
      System.out.println("Error using reflection to access Man's constructor");
    }
    cth = new CommandTestHelper(mockIo);
  }

  /**
   * Tests execute() with an invalid number (3) of arguments; "usage: man CMD"
   * should be output.
   */
  @Test
  public void testExecuteWith3Args() {
    String[] argsArray = {"man", "too", "many", "args"};
    String expected = "usage: man CMD";
    String output = cth.runCommandAndGetOutput(testMan, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with an invalid command (anime); "anime: invalid command"
   * should be output.
   */
  @Test
  public void testExecuteWithInvalidCommand() {
    String[] argsArray = {"man", "anime"};
    String expected = "anime: invalid command";
    String output = cth.runCommandAndGetOutput(testMan, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests execute() with an valid command (pwd); "NAME\n pwd - print current
   * working directory\nSYNOPSIS\n pwd\nDESCRIPTION\n Prints the full path of
   * the current working directory." should be output.
   */
  @Test
  public void testExecuteWithValidCommand() {
    String[] argsArray = {"man", "pwd"};
    String expected =
        "NAME\n    pwd - print current working directory\nSYNOPSIS\n    "
            + "pwd\nDESCRIPTION\n    Prints the full path of the current "
            + "working directory.";
    String output = cth.runCommandAndGetOutput(testMan, argsArray);
    assertEquals(expected, output);
  }

  /**
   * Tests Man's toString() method.
   */
  @Test
  public void testToString() {
    assertEquals("instance of Man with references: " + mockIo,
        testMan.toString());
  }

}
