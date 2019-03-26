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

package command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * A JUrlDownloader that download file from url.
 *
 */
public class JUrlDownloader implements UrlDownloader {

  /**
   * Get the file at url, and return the content as a String.
   * 
   * @param url the url of the file
   * @return the content of file at url
   * @throws IOException if Get url file failed
   */
  public String downloadUrlFile(String url) throws IOException {
    URL urlFile = new URL(url);
    // read text returned by server
    BufferedReader in =
        new BufferedReader(new InputStreamReader(urlFile.openStream()));
    String result = "";
    int i;
    while ((i = in.read()) != -1) {
      String cur = String.valueOf((char) i);
      result = result + cur;
    }
    in.close();
    return result.trim();
  }
}
