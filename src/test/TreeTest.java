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

import command.Tree;
import filesystem.FileSystem;
import org.junit.Before;
import org.junit.Test;

public class TreeTest {

  private Tree testTree;
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
    this.testTree = new Tree(mockFs, mockIo);
  }

  @Test
  public void testTree() {
    String[] argsArray = {"tree"};
    testTree.execute(argsArray);
    String expected = "/\n  validtree\n";
    assertEquals(expected, mockIo.returnStandardOutput());
  }
}