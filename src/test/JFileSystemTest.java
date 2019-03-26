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

import filesystem.FileSystem;
import filesystem.JFileSystem;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JFileSystemTest {
  private FileSystem fs;

  /**
   * Creates a singleton instance of JFileSystem.
   */
  @Before
  public void setup() {
    fs = JFileSystem.createFileSystem();
  }

  /**
   * Removes the current JFileSystem so a fresh one can be used in the next
   * test.
   */
  @After
  public void tearDown() {
    try {
      // Use reflection to access JFileSystem's private refToFT.
      Field ref = fs.getClass().getDeclaredField("refToFT");
      ref.setAccessible(true);
      // Set it to null so the garbage collector eats it and a new JFileSystem
      // can be created for the next test.
      ref.set(fs, null);
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      // This should not happen.
      fail("error while using reflection to access fs");
    }
  }

  /**
   * FileSystem should be initialized with one empty root directory. and the
   * name of the root directory should be "/".
   * 
   */
  @Test
  public void testRootDirOfEmptyFileSystem() {
    assertEquals("/", fs.getCurrentWorkingDir());
  }

  /**
   * Test set name of a file and get name of a file.
   */
  @Test
  public void testAddGetFileToRoot() {
    try {
      fs.addFile("file1", "/file1");
      assertEquals("file1", fs.getFile("/file1"));
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void testAddDirectoryThenListDirectoryContent() {
    try {
      fs.createDirectory("/dir1");
      fs.createDirectory("/dir2");
      fs.createDirectory("/dir3");
      Set<String> expected = new HashSet<>();
      expected.add("dir1");
      expected.add("dir2");
      expected.add("dir3");
      assertEquals(expected, fs.listDirectoryContent("/"));
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void testChangeCurrentAndGetCurrentDirectory() {
    try {
      fs.createDirectory("/dir1");
      fs.createDirectory("/dir1/dir2");
      fs.changeWorkingDirectory("/dir1/dir2");
      assertEquals("/dir1/dir2/", fs.getCurrentWorkingDir());
    } catch (Exception e) {
      fail();
    }
  }

}
