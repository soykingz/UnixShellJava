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
 
import command.Mkdir;
import filesystem.FileSystem;
import org.junit.Before;
import org.junit.Test;
import shell.IoHandler;
 
 
/**
 * Class that tests Mkdir.
 */
public class MkdirTest {
 
  private Mkdir testMkdir;
  private FileSystem mockFs;
  private IoHandler mockIo;
  private CommandTestHelper cth;
 
  /**
   * Creates a mock FileSystem, a mock IoHandler, the command Mkdir, and a
   * CommandTestHelper.
   */
  @Before
  public void setUp() {
    mockFs = new MockFileSystem();
    mockIo = new MockIoHandler(mockFs);
    this.testMkdir = new Mkdir(mockFs, mockIo);
    cth = new CommandTestHelper(mockIo);
  }
 
  /**
   * Tests execute() with no arguments. "usage: mkdir PATH...\n PATH: path
   * cannot contain spaces" should be output.
   */
  @Test
  public void testExecuteWithNoArgs() {
    String[] argsArray = {"mkdir"};
    String expected =
        "usage: mkdir PATH...\n    PATH: path cannot contain spaces";
    String output = cth.runCommandAndGetOutput(testMkdir, argsArray);
    assertEquals(expected, output);
  }
 
  /**
   * Tests execute() with an invalid path. "invalidpath: invalid path" should be
   * output.
   */
  @Test
  public void testExecuteWithInvalidPath() {
    String[] argsArray = {"mkdir", "invalidpath"};
    String expected = "invalidpath: invalid path";
    String output = cth.runCommandAndGetOutput(testMkdir, argsArray);
    assertEquals(expected, output);
  }
 
  /**
   * Tests execute() with a valid path. "path created\n" should be output.
   */
  @Test
  public void testExecuteWithValidPath() {
    String[] argsArray = {"mkdir", "validpath"};
    String expected = "path created\n";
    String output = cth.runCommandAndGetOutput(testMkdir, argsArray);
    assertEquals(expected, output);
  }
 
  /**
   * Tests execute() with a path that contains invalid characters. "$#%^:
   * contains invalid character" should be output.
   */
  @Test
  public void testExecutePathWithInvalidChars() {
    String[] argsArray = {"mkdir", "$#%^"};
    String expected = "$#%^: contains invalid character";
    String output = cth.runCommandAndGetOutput(testMkdir, argsArray);
    assertEquals(expected, output);
  }
 
  /**
   * Tests execute() with the special root path. "/: path cannot be single /"
   * should be output.
   */
  @Test
  public void testExecuteWithSingleSlash() {
    String[] argsArray = {"mkdir", "/"};
    String expected = "/: path cannot be single /";
    String output = cth.runCommandAndGetOutput(testMkdir, argsArray);
    assertEquals(expected, output);
  }
 
  /**
   * Tests execute() with a path that already exists. "alreadyexists: path
   * already exists" should be output.
   */
  @Test
  public void testExecutePathAlreadyExists() {
    String[] argsArray = {"mkdir", "alreadyexists"};
    String expected = "alreadyexists: path already exists";
    String output = cth.runCommandAndGetOutput(testMkdir, argsArray);
    assertEquals(expected, output);
  }
 
  /**
   * Tests execute() with two valid paths. "path created\n path created\n"
   * should be output.
   */
  @Test
  public void testExecuteTwoValidPaths() {
    String[] argsArray = {"mkdir", "validpath", "validpath"};
    String expected = "path created\npath created\n";
    String output = cth.runCommandAndGetOutput(testMkdir, argsArray);
    assertEquals(expected, output);
  }
 
  /**
   * Tests execute() with a valid path and an invalid path. "path
   * created\ninvalidpath: invalid path"should be output.
   */
  @Test
  public void testExecuteOneValidOneInvalidPath() {
    String[] argsArray = {"mkdir", "validpath", "invalidpath"};
    String expected = "path created\ninvalidpath: invalid path";
    String output = cth.runCommandAndGetOutput(testMkdir, argsArray);
    assertEquals(expected, output);
  }
 
  /**
   * Tests execute() with two invalid paths. "invalidpath: invalid
   * pathinvalidpath: invalid path" should be output.
   */
  @Test
  public void testExecutePathWithTwoInvalidPaths() {
    String[] argsArray = {"mkdir", "invalidpath", "invalidpath"};
    String expected = "invalidpath: invalid path\ninvalidpath: invalid path";
    String output = cth.runCommandAndGetOutput(testMkdir, argsArray);
    assertEquals(expected, output);
  }
 
  /**
   * Tests execute() with a valid path, an invalid path, a path with invalid
   * characters, a / path, and a path that already exists. "path
   * created\ninvalidpath: invalid path\n@w@: invalid path\n/: path cannot be
   * single /\nalreadyexists: path already exists" should be output.
   */
  @Test
  public void testExecuteValidInvalidInvalidCharsSingleSlashAlreadyExistsPaths() {
    String[] argsArray =
        {"mkdir", "validpath", "invalidpath", "@w@", "/", "alreadyexists"};
    String expected = "path created\n" + "invalidpath: invalid path\n"
        + "@w@: invalid path\n" + "/: path cannot be single /\n"
        + "alreadyexists: path already exists";
    String output = cth.runCommandAndGetOutput(testMkdir, argsArray);
    assertEquals(expected, output);
  }
}
