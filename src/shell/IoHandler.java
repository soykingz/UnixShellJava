//**********************************************************
//Assignment2:

//Student1:
//UTORID user_name: liangho2
//UT Student #:1002408085
//Author: Hong Xuan David Liang
//
//Student2:
//UTORID user_name:jinze1
//UT Student #:1003155863
//Author: Ze Jin
//
//Student3:
//UTORID user_name: seyedayd
//UT Student #: 1002471201
//Author: Aydin Baradaran-Seyed
//
//Student4:
//UTORID user_name: vezocluc
//UT Student #: 1002403749
//Author: Luca Vezoc
//
//
//Honor Code: I pledge that this program represents my own
//program code and that I have coded on my own. I received
//help from no one in designing and debugging my program.
//I have also read the plagiarism section in the course info
//sheet of CSC B07 and understand the consequences.
//*********************************************************

package shell;

import filesystem.FileSystem;

public abstract class IoHandler {
  protected FileSystem fs;

  /**
   * Set the FileSystem that this IoHandler can output to.
   * 
   * @param fs is a FileSystem
   */
  protected IoHandler(FileSystem fs) {
    this.fs = fs;
  }

  /**
   * Sets the standard output in Iohandler to an input string.
   *
   * @param fileContent input string
   */
  public abstract void standardOutput(String fileContent);

  /**
   * Sets the standard error in Iohandler to an input error string.
   *
   * @param fileContent input error string
   */
  public abstract void standardError(String fileContent);

  /**
   * Outputs contents of stdOuput and stdErr to System.out, if they are not
   * empty.
   */
  public abstract void out();

  /**
   * Redirects a command's output depending on a redirection symbol and path
   * given; the redirect will either overwrite to a file or append to a file.
   *
   * @param redirectSymbol string representing a redirect symbol
   * @param filePath string representing a file path
   */
  public abstract void reDirect(String redirectSymbol, String filePath);
}
