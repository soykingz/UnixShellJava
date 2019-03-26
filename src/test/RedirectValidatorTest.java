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

import org.junit.Before;
import org.junit.Test;
import shell.RedirectValidator;

/**
 * Class which tests RedirectValidator.
 * 
 * @author liangho2
 */
public class RedirectValidatorTest {

  private RedirectValidator testRv;

  /**
   * Creates an instance of RedirectValidator (only used for toString()
   * testing).
   */
  @Before
  public void setUp() {
    testRv = new RedirectValidator();
  }

  /**
   * Tests validate() with an invalid input of length 3; 1 should be returned.
   */
  @Test
  public void testValidateWithInvalidInputLength3() {
    String[] redirectArgs = {"too", "many", "args"};
    assertEquals(1, RedirectValidator.validate(redirectArgs));
  }

  /**
   * Tests validate() with a valid input of length 2; 0 should be returned.
   */
  @Test
  public void testValidateWithValidInputLenght2() {
    String[] redirectArgs = {"just", "right"};
    assertEquals(0, RedirectValidator.validate(redirectArgs));
  }

  /**
   * Tests RedirectValidator's toString() method (not used in normal program
   * operation).
   */
  @Test
  public void testToString() {
    assertEquals("instance of RedirectValidator", testRv.toString());
  }

}
