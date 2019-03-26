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

package shell;

/**
 * Class that validates if the redirection portion of a command's arguments is
 * valid.
 * 
 * @author liangho2
 */
public class RedirectValidator {

  /**
   * Checks if there is only one file to redirect to.
   * 
   * @param redirectArgs array of strings representing redirection portion of a
   *        command's arguments
   * @return int 0 representing valid; 1 representing not valid
   */
  public static int validate(String[] redirectArgs) {
    // Redirection is only valid for 1 file.
    if (redirectArgs.length != 2) {
      return 1;
    } else {
      return 0;
    }
  }

  /**
   * Returns a string representing the instance of RedirectValidator.
   * 
   * @return string representing instance of RedirectValidator
   */
  public String toString() {
    return ("instance of RedirectValidator");
  }

}
