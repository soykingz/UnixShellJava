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

import driver.JShell;
import org.junit.Before;
import org.junit.Test;

/**
 * Class which tests JShell.
 * 
 * @author liangho2
 */
public class JShellTest {

  private JShell testJs;

  /**
   * Create an instance of JShell.
   */
  @Before
  public void setUp() {
    testJs = new JShell();
  }

  /**
   * Test JShell's toString() method.
   */
  @Test
  public void testToString() {
    assertEquals("instance of JShell", testJs.toString());
  }

}
