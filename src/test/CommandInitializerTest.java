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
import static org.junit.Assert.fail;

import command.Command;
import command.Man;
import command.UrlDownloader;
import filesystem.FileSystem;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import shell.CommandExecutor;
import shell.CommandInitializer;
import shell.CommandInstantiationException;
import shell.InvalidCommandException;
import shell.IoHandler;
import shell.UserInputHistory;

/**
 * Class which tests CommandInitializer.
 * 
 * @author liangho2
 */
public class CommandInitializerTest {

  private CommandInitializer ci;
  private CommandExecutor ce;
  private UserInputHistory uih;
  private MockFileSystem mockFs;
  private MockIoHandler mockIo;
  private UrlDownloader ud;

  /**
   * Creates a MockFileSystem, a MockIoHandler, and an instance of
   * CommandInitializer (using reflection), and redirects System.out for use in
   * testing.
   * </p>
   * Also creates empty instances of UserInputHistory, CommandExecutor, and
   * UrlDownloader for instantiation purposes, but those are not used for the
   * purposes of testing.
   */
  @Before
  public void setUp() {
    mockFs = new MockFileSystem();
    mockIo = new MockIoHandler(mockFs);
    // Use reflection to access CommandInitializer's constructor to be able to
    // instantiate a clean instance for every test.
    try {
      Constructor<CommandInitializer> ciCon =
          CommandInitializer.class.getDeclaredConstructor(FileSystem.class,
              IoHandler.class, UserInputHistory.class, CommandExecutor.class,
              UrlDownloader.class);
      ciCon.setAccessible(true);
      ci = ciCon.newInstance(mockFs, mockIo, uih, ce, ud);
    } catch (NoSuchMethodException | SecurityException | InstantiationException
        | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException e) {
      // This should never happen.
      System.out.println("Error using reflection to access Man's constructor");
    }
  }

  /**
   * Tests validate() with an invalid command string representation;
   * InvalidCommandException should be thrown.
   */
  @Test
  public void testValidateWithInvalidCommandString() {
    String command = "invalid";
    try {
      ci.validate(command);
      fail("no InvalidCommandException thrown");
    } catch (CommandInstantiationException e) {
      fail("no InvalidCommandException thrown");
    } catch (InvalidCommandException e) {
      // Correct exception caught.
      return;
    }
  }

  /**
   * Tests validate() with an valid command string ("man"); a Command object
   * (Man) should be returned.
   */
  @Test
  public void testValidateWithValidCommandString() {
    String command = "man";
    try {
      Command output = ci.validate(command);
      // Check if output is an instance of Man (which is a Command).
      assertEquals(true, output instanceof Man);
    } catch (CommandInstantiationException e) {
      fail("CommandInstantationException thrown");
    } catch (InvalidCommandException e) {
      fail("InvalidCommandException thrown");
    }
  }

  /**
   * Tests validate() with CommandInitializer's command dictionary containing an
   * error; CommandInstantiationException should be thrown.
   */
  @Test
  public void testValidateWithErrorInCommandDictionary() {
    String command = "uwu";
    Map<String, List<Object>> errorDict = new HashMap<>();
    // Erroneous key/value to insert into internal command dictionary.
    errorDict.put("uwu",
        Arrays.asList("command.uwu", CommandInitializer.class));
    // Mess up the internal command dictionary, accessing it using reflection.
    try {
      // Get the commandDict field of CommandInitializer.
      Field commandDict = ci.getClass().getDeclaredField("commandDict");
      // Make it accessible since it is private.
      commandDict.setAccessible(true);
      // Add an erroneous key/value to commandDict.
      commandDict.set(ci, errorDict);
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e1) {
      // This should not happen.
      fail("error while trying to access internal command dictionary");
    }
    try {
      ci.validate(command);
      fail("no CommandInstantiationException thrown");
    } catch (CommandInstantiationException e) {
      // Correct exception.
      return;
    } catch (InvalidCommandException e) {
      fail("InvalidCommandException thrown");
    }
  }

  /**
   * Tests CommandInitializer's toString() method.
   */
  @Test
  public void testToString() {
    assertEquals("singleton instance of CommandInitializer", ci.toString());
  }

}
