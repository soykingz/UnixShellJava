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

import command.Curl;
import command.UrlDownloader;
import filesystem.FileSystem;
import org.junit.Before;
import org.junit.Test;

public class CurlTest {

  private Curl curl;
  private FileSystem mockFs;
  private MockIoHandler mockIo;
  private UrlDownloader mockUd;

  /**
   * Creates a mock FileSystem, a mock IoHandler, the command Cd, and a
   * CommandTestHelper.
   */
  @Before
  public void setup() {
    mockFs = new MockFileSystem();
    mockIo = new MockIoHandler(mockFs);
    mockUd = new MockUrlDownloader();
    this.curl = new Curl(mockFs, mockIo, mockUd);
  }

  @Test
  public void testGetTextFromUrl() {
    String[] argsArray = {"curl", "/file1.txt"};
    String expected = "valid text content";
    curl.execute(argsArray);
    try {
      String resultPath = mockFs.getCurrentWorkingDir() + "/file1.txt";
      assertEquals(expected, mockFs.getFile(resultPath));
    } catch (Exception e) {
      fail();
    }
    assertEquals("", mockIo.returnStandardError());
  }

  @Test
  public void testInvalidFormattedUrl() {
    String[] argsArray = {"curl", "invalid"};
    String expected = "curl only works with plain text, html";
    curl.execute(argsArray);
    assertEquals(expected, mockIo.returnStandardError());
    assertEquals("", mockIo.returnStandardOutput());
  }

  @Test
  public void testInvalidUrl() {
    String[] argsArray = {"curl", "/invalid.txt"};
    String expected = "invalid url";
    curl.execute(argsArray);
    assertEquals(expected, mockIo.returnStandardError());
    assertEquals("", mockIo.returnStandardOutput());
  }
}
