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

import filesystem.FileSystem;
import shell.IoHandler;

public class MockIoHandler extends IoHandler {

  private String out = "";
  private String err = "";

  /**
   * Constructor for a mock IoHandler for use in testing.
   * 
   * @param fs reference to mock filesystem
   */
  protected MockIoHandler(FileSystem fs) {
    super(fs);
  }

  @Override
  public void standardOutput(String out) {
    this.out = out;
  }

  @Override
  public void standardError(String err) {
    this.err = err;
  }

  @Override
  public void out() {
    System.out.print(this.out);
    System.out.print(this.err);
    out = "";
    err = "";
  }

  public String returnStandardOutput() {
    return this.out;
  }

  public String returnStandardError() {
    return this.err;
  }

  @Override
  public void reDirect(String redirectSymbol, String filePath) {
    System.out.print("Redirected to " + filePath);
    out = "";
  }

  @Override
  public String toString() {
    return ("IoHandler instance");
  }
}
