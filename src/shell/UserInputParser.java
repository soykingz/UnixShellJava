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
 * Class that parses user input (a string).
 * 
 * @author liangho2
 */
public class UserInputParser {

  // Store the symbol for the special command which allows the user to directly
  // call a command from their command history and recall it.
  private static final String HISTORY_RECALL_SYMBOL = "!";

  /**
   * Parses given user input (a string) and returns an array of strings which
   * represents the parsed string.
   * 
   * @param input String representing user input
   * @return parsed String represented parsed user input
   */
  public static String[] parse(String input) {
    String[] parsed;
    // Separate input by any whitespace. If the recall symbol is found,
    // separate that from the rest of the input.
    if (input.indexOf(HISTORY_RECALL_SYMBOL) == 0) {
      parsed = input.trim().split("\\s+|\\!");
      // If parsed has length 0 that means the only thing present is the recall
      // symbol; overwrite parsed with a new array with space for that symbol
      // only if that is the case.
      if (parsed.length == 0) {
        parsed = new String[1];
      }
      // Re-add the recall symbol as it was deleted by the split.
      parsed[0] = HISTORY_RECALL_SYMBOL;
    } else {
      parsed = input.trim().split("\\s+");
    }
    return parsed;
  }

  @Override
  public String toString() {
    return ("instance of UserInputParser");
  }

}
