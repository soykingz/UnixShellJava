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

import org.junit.Before;
import org.junit.Test;
import shell.UserInputHistory;

/**
 * Class which tests UserInputHistory.
 * 
 * @author liangho2
 */
public class UserInputHistoryTest {

  private UserInputHistory testUih;

  /**
   * Creates an instance of UserInputHistory.
   */
  @Before
  public void setUp() {
    testUih = new UserInputHistory();
  }

  /**
   * Tests pushLine() on an empty user history; getLines should return "1. line
   * 1\n".
   */
  @Test
  public void testPushLineOnEmptyHistory() {
    testUih.pushLine("line 1");
    assertEquals("1. line 1\n", testUih.getLines(-1));
  }

  /**
   * Tests pushLine() on a populated user history; getLines should return "1.
   * line 1\n2. line 2\n".
   */
  @Test
  public void testPushLineOnPopulatedHistory() {
    testUih.pushLine("line 1");
    testUih.pushLine("line 2");
    assertEquals("1. line 1\n2. line 2\n", testUih.getLines(-1));
  }

  /**
   * Tests popLine() on an empty user history; IndexOutOfBoundsException should
   * be thrown.
   */
  @Test
  public void testPopLineOnEmptyHistory() {
    try {
      testUih.popLine();
      fail("no IndexOutOfBoundsException thrown");
    } catch (IndexOutOfBoundsException e) {
      // Correct exception thrown.
      return;
    }
  }

  /**
   * Tests popLine() on a populated user history; "line" should be returned.
   */
  @Test
  public void testPopLineOnPopulatedHistory() {
    testUih.pushLine("line");
    try {
      assertEquals("line", testUih.popLine());
    } catch (IndexOutOfBoundsException e) {
      fail("IndexOutOfBoundsException thrown");
    }
  }

  /**
   * Tests peekLine() on an empty user history; IndexOutOfBoundsException should
   * be thrown.
   */
  @Test
  public void testPeekLineOnEmptyHistory() {
    try {
      testUih.peekLine(0);
      fail("no IndexOutOfBoundsException thrown");
    } catch (IndexOutOfBoundsException e) {
      // Correct exception thrown.
      return;
    }
  }

  /**
   * Tests peekLine() on a populated user history with index being in bounds;
   * "line" should be returned.
   */
  @Test
  public void testPeekLineOnPopulatedHistoryWithValidIndex() {
    testUih.pushLine("line");
    try {
      assertEquals("line", testUih.peekLine(0));
    } catch (IndexOutOfBoundsException e) {
      fail("IndexOutOfBoundsException thrown");
    }
  }

  /**
   * Tests peekLine() on a populated user history with index being out of
   * bounds; IndexOutOfBoundsException should be thrown.
   */
  @Test
  public void testPeekLineOnPopulatedHistoryWithInvalidIndex() {
    testUih.pushLine("line");
    try {
      testUih.peekLine(1);
      fail("no IndexOutOfBoundsException thrown");
    } catch (IndexOutOfBoundsException e) {
      // Correct exception thrown.
      return;
    }
  }

  /**
   * Tests getHistoryNum(); 0 should be returned.
   */
  @Test
  public void testGetHistoryNum() {
    assertEquals(0, testUih.getHistoryNum());
  }

  /**
   * Tests getLines(100) on an empty history; "" should be returned.
   */
  @Test
  public void testGetLinesOneHundredOnEmptyHistory() {
    assertEquals("", testUih.getLines(-1));
  }

  /**
   * Tests getLines(-1) on an empty history; "" should be returned.
   */
  @Test
  public void testGetLinesNegativeOneOnEmptyHistory() {
    assertEquals("", testUih.getLines(-1));
  }

  /**
   * Tests getLines(100) on a populated history; "1. line 1\n2. line 2\n" should
   * be returned.
   */
  @Test
  public void testGetLinesOneHundredOnPopulatedHistory() {
    testUih.pushLine("line 1");
    testUih.pushLine("line 2");
    assertEquals("1. line 1\n2. line 2\n", testUih.getLines(100));
  }

  /**
   * Tests getLines(-1) on a populated history; "1. line 1\n2. line2\n" should
   * be returned.
   */
  @Test
  public void testGetLinesNegativeOneOnPopulatedHistory() {
    testUih.pushLine("line 1");
    testUih.pushLine("line 2");
    assertEquals("1. line 1\n2. line 2\n", testUih.getLines(-1));
  }

  /**
   * Tests getLines(2) on a populated history with 3 lines; "2. line 2\n3.
   * line3\n" should be returned.
   */
  @Test
  public void testGetLinesTwoOnPopulatedHistoryWithThreeLines() {
    testUih.pushLine("line 1");
    testUih.pushLine("line 2");
    testUih.pushLine("line 3");
    assertEquals("2. line 2\n3. line 3\n", testUih.getLines(2));
  }

  /**
   * Tests getLines(0) on a populated history; "" should be returned.
   */
  @Test
  public void testGetLinesZeroOnPopulatedHistory() {
    testUih.pushLine("line 1");
    testUih.pushLine("line 2");
    assertEquals("", testUih.getLines(0));
  }

  /**
   * Tests UserInputHistory's toString() method.
   */
  @Test
  public void testToString() {
    assertEquals("instance of UserInputHistory", testUih.toString());
  }

}
