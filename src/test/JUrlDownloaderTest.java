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

import command.JUrlDownloader;
import command.UrlDownloader;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;


public class JUrlDownloaderTest {
  private UrlDownloader jud;

  @Before
  public void setup() {
    this.jud = new JUrlDownloader();
  }

  @Test
  public void testDownloadTextFromUrl() {
    String expected = "This is\r\na sample\r\ntext file.";
    try {
      String result =
          jud.downloadUrlFile("http://brainjar.com/java/host/test.txt");
      assertEquals(expected, result);
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void testDownloadHtmlFromUrl() {
    String expected =
        "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http:"
            + "//www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\r\n"
            + "<html>\r\n" + "<head>\r\n" + "<title>Test HTML File</title>\r\n"
            + "<meta http-equiv=\"Content-Type\" content=\"text/html;charset"
            + "=utf-8\" />\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n"
            + "<p>This is a very simple HTML file.</p>\r\n" + "\r\n"
            + "</body>\r\n" + "</html>";
    try {
      String result =
          jud.downloadUrlFile("http://brainjar.com/java/host/test.html");
      assertEquals(expected, result);
    } catch (IOException e) {
      fail();
    }
  }
}
