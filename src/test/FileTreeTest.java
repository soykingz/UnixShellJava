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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import filesystem.FileTree;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class FileTreeTest {

  FileTree<String> f1;

  @Before
  public void setup() {
    f1 = new FileTree<>();
  }

  /**
   * Test set name of a file and get name of a file.
   */
  @Test
  public void testGetPathOfCurrentWorkingNode() {
    assertEquals("/", f1.getCurrentWorkingNode());
  }

  /**
   * Test set name of a file and get name of a file.
   */
  @Test
  public void testAddGetLeafToRoot() {
    try {
      f1.addLeaf("leaf1", "/leaf1");
      assertEquals("leaf1", f1.getLeaf("/leaf1"));
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void testAddNodeThenListNodeContent() {
    try {
      f1.addNode("/dir1");
      f1.addNode("/dir2");
      f1.addNode("/dir3");
      Set<String> expected = new HashSet<>();
      expected.add("dir1");
      expected.add("dir2");
      expected.add("dir3");
      assertEquals(expected, f1.listNodeContent("/"));
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void testChangeCurrentAndGetCurrent() {
    try {
      f1.addNode("/dir1");
      f1.addNode("/dir1/dir2");
      f1.changeWorkingNode("/dir1/dir2");
      assertEquals("/dir1/dir2/", f1.getCurrentWorkingNode());
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void testAddLeafThenGetLeaf() {
    try {
      f1.addLeaf("file1's content", "/file1");
      f1.addNode("/dir1");
      f1.addNode("/dir1/dir2");
      f1.addLeaf("file2's content", "/dir1/dir2/file2");
      assertEquals("file1's content", f1.getLeaf("/file1"));
      assertEquals("file2's content", f1.getLeaf("/dir1/dir2/file2"));
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void testRelativePathAtRoot() {
    try {
      f1.addNode("dir2");
      f1.addLeaf("file1's content", "file1");
      f1.addLeaf("file2's content", "dir2/file2");
      assertEquals("file1's content", f1.getLeaf("file1"));
      assertEquals("file2's content", f1.getLeaf("dir2/file2"));
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void testTraversal() {
    try {
      f1.addNode("/dir1");
      f1.addNode("/dir2");
      f1.addNode("/dir3");
      f1.addNode("/dir2/dirA");
      f1.addNode("/dir2/dirB");
      String expected = "/:\n"
          + " dir3:\n"
          + " dir2:\n"
          + "   dirB:\n"
          + "   dirA:\n"
          + " dir1:\n";
      assertEquals(expected, f1.traversal("/", ":"));
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void testAddNodeWithSamePath() {
    boolean gotException = false;
    try {
      f1.addNode("/dir1");
      f1.addNode("/dir1");
    } catch (Exception e) {
      gotException = true;
    }
    assertTrue(gotException);
  }

  @Test
  public void testAddLeafWithSamePath() {
    boolean gotException = false;
    try {
      f1.addLeaf("some content", "/leaf1");
      f1.addLeaf("some content", "/leaf1");
    } catch (Exception e) {
      gotException = true;
    }
    assertTrue(gotException);
  }

  @Test
  public void testAddLeafOnPathOfNode() {
    boolean gotException = false;
    try {
      f1.addNode("/dir1");
      f1.addLeaf("some content", "/dir1");
    } catch (Exception e) {
      gotException = true;
    }
    assertTrue(gotException);
  }

  @Test
  public void testAddLeafOnNotExistingNode() {
    boolean gotException = false;
    try {
      f1.addLeaf("some content", "/dir1/file1");
    } catch (Exception e) {
      gotException = true;
    }
    assertTrue(gotException);
  }

  @Test
  public void testAddLeafOnPathNextNotExistingNode() {
    boolean gotException = false;
    try {
      f1.addLeaf("some content", "/dir1/dir2");
    } catch (Exception e) {
      gotException = true;
    }
    assertTrue(gotException);
  }

  @Test
  public void testRemoveNode() {
    try {
      f1.addNode("/dir1");
      f1.addNode("/dir2");
      f1.removeNode("/dir1");
      f1.removeNode("/dir2");
      f1.addLeaf("something", "/file1");
      f1.removeNode("/file1");
      Set<String> result = new HashSet<>();
      assertEquals(result, f1.listNodeContent("/"));
    } catch (Exception e) {
      fail();
    }
  }
}
