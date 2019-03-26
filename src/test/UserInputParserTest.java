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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import shell.UserInputParser;

/**
 * Class which tests UserInputParser.
 * 
 * @author liangho2
 */
public class UserInputParserTest {

  private UserInputParser testUip;

  /**
   * Creates an instance of UserInputParser (for use in testing toString() only.
   */
  @Before
  public void setUp() {
    testUip = new UserInputParser();
  }

  /**
   * Tests parse() with a string with no spaces as input; { "string" } should be
   * returned.
   */
  @Test
  public void testParseWithStringWithNoSpaces() {
    String[] expected = {"string"};
    assertArrayEquals(expected, UserInputParser.parse("string"));
  }

  /**
   * Tests parse() with an empty string, { "" } should be returned.
   */
  @Test
  public void testParseWithEmptyString() {
    String[] expected = {""};
    assertArrayEquals(expected, UserInputParser.parse(""));
  }

  /**
   * Tests parse() with a string with spaces as input; { "string", "with",
   * "spaces" } should be returned.
   */
  @Test
  public void testParseWithStringWithSpaces() {
    String[] expected = {"string", "with", "spaces"};
    assertArrayEquals(expected, UserInputParser.parse("string with spaces"));
  }

  /**
   * Tests parse() with a string containing only the recall symbol, { "!" }
   * should be returned.
   */
  @Test
  public void testParseWithRecallSymbolOnly() {
    String[] expected = {"!"};
    assertArrayEquals(expected, UserInputParser.parse("!"));
  }

  /**
   * Tests parse() with a string containing the recall symbol and spaces, { "!",
   * "num", "invalid", "arguments" } should be returned.
   */
  @Test
  public void testParseWithRecallSymbolAndStringWithSpaces() {
    String[] expected = {"!", "num", "invalid", "arguments"};
    assertArrayEquals(expected,
        UserInputParser.parse("!num invalid arguments"));
  }

  /**
   * Tests UserInputParser's toString() method.
   */
  @Test
  public void testToString() {
    assertEquals("instance of UserInputParser", testUip.toString());
  }

}
