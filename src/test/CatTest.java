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

import command.Cat;
import filesystem.FileSystem;
import org.junit.Before;
import org.junit.Test;

public class CatTest {

  private Cat testCat;
  private FileSystem mockFs;
  private MockIoHandler mockIo;

  /**
   * Creates a mock FileSystem, a mock IoHandler, the command Mkdir, and a
   * CommandTestHelper.
   */
  @Before
  public void setUp() {
    mockFs = new MockFileSystem();
    mockIo = new MockIoHandler(mockFs);
    this.testCat = new Cat(mockFs, mockIo);
  }

  @Test
  public void testValidPath() {
    String[] argsArray = {"cat", "validpathwithfile"};
    testCat.execute(argsArray);
    assertEquals("you got a file! nice!", mockIo.returnStandardOutput());
    assertEquals("", mockIo.returnStandardError());

  }

  @Test
  public void testInvalidPath() {
    String[] argsArray = {"cat", "validpathwithnofile"};
    testCat.execute(argsArray);
    String expected = "validpathwithnofile: does not exist";
    assertEquals("", mockIo.returnStandardOutput());
    assertEquals(expected, mockIo.returnStandardError());
  }

  @Test
  public void testMultipleFile() {
    String[] argsArray = {"cat", "validpathwithfile", "cwd/file1.txt"};
    testCat.execute(argsArray);
    String expected = "you got a file! nice!\n\nvalid text content";
    assertEquals(expected, mockIo.returnStandardOutput());
    assertEquals("", mockIo.returnStandardError());
  }


}
