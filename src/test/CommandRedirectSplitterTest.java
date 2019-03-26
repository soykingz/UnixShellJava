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
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import shell.CommandRedirectSplitter;
import shell.RedirectSymbolNotPresentException;

/**
 * Class which tests CommandRedirectSplitter.
 * 
 * @author liangho2
 */
public class CommandRedirectSplitterTest {

  private CommandRedirectSplitter crs;

  /**
   * Creates an instance of CommandRedirectSplitter (only used for toString()
   * testing).
   */
  @Before
  public void setUp() {
    crs = new CommandRedirectSplitter();
  }

  /**
   * Tests split() where no redirection symbol is present;
   * RedirectSymbolNotPresentException should be thrown.
   */
  @Test
  public void testSplitWithNoRedirectSymbols() {
    String[] args = {"no", "redirect", "symbol"};
    try {
      CommandRedirectSplitter.split(args);
      fail("no RedirectSymbolNotPresentException thrown");
    } catch (RedirectSymbolNotPresentException e) {
      // Correct exception thrown.
      return;
    }
  }

  /**
   * Tests split() where a ">" redirect symbol is found; an array with arrays {
   * "command", "commandargs" } and { ">", "OUT" } should be returned.
   */
  @Test
  public void testSplitWithOverwriteRedirectSymbols() {
    String[] args = {"command", "commandargs", ">", "OUT"};
    String[] commandstuff = {"command", "commandargs"};
    String[] redir = {">", "OUT"};
    try {
      String[][] tuple = CommandRedirectSplitter.split(args);
      assertArrayEquals(commandstuff, tuple[0]);
      assertArrayEquals(redir, tuple[1]);
    } catch (RedirectSymbolNotPresentException e) {
      fail("RedirectSymbolNotPresentException thrown");
    }
  }

  /**
   * Tests split() where a ">>" redirect symbol is found; an array with arrays {
   * "command", "commandargs" } and { ">>", "OUT" } should be returned.
   */
  @Test
  public void testSplitWithAppendRedirectSymbols() {
    String[] args = {"command", "commandargs", ">>", "OUT"};
    String[] commandstuff = {"command", "commandargs"};
    String[] redir = {">>", "OUT"};
    try {
      String[][] tuple = CommandRedirectSplitter.split(args);
      assertArrayEquals(commandstuff, tuple[0]);
      assertArrayEquals(redir, tuple[1]);
    } catch (RedirectSymbolNotPresentException e) {
      fail("RedirectSymbolNotPresentException thrown");
    }
  }

  /**
   * Tests CommandRedirectSplitter's toString() method (not used in normal
   * program operation).
   */
  @Test
  public void testToString() {
    assertEquals("instance of CommandRedirectSplitter", crs.toString());
  }

}
