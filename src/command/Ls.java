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

import filesystem.FileSystem;
import filesystem.InvalidPathException;
import java.util.ArrayList;
import java.util.Set;
import shell.IoHandler;

/**
 * Command which allows the user to (recursively) list the contents of directories.
 * 
 * @author liangho2
 */
public class Ls extends CommandWithOutput {

  private FileSystem fileSystem;

  /**
   * Constructor for Ls.
   * 
   * @param fs reference to FileSystem instance
   * @param io reference to IoHandler instance
   */
  public Ls(FileSystem fs, IoHandler io) {
    this.fileSystem = fs;
    super.setIo(io);
  }

  @Override
  public void execute(String[] argsArray) {

    // Create a master paths array to store all path strings in.
    ArrayList<String> pathList = new ArrayList<>();
    // String to store master result of ls execution.
    String lsResult = "";
    // String to store master error (since multiple given paths can error).
    String masterErr = "";
    // Flag to store whether subdirectories should be recursively listed.
    boolean recursionFlag = false;

    // Add each given path to pathsList.
    for (String path : argsArray) {
      // Turn on recursion flag if it is found.
      if ("-R".equals(path)) {
        recursionFlag = true;
      } else if (!"ls".equals(path)) {
        // Ignore the command string itself.
        pathList.add(path);
      }
    }
    // If no paths are given, add only the current working directory to
    // pathList.
    if (argsArray.length == 1 || (argsArray.length == 2 && recursionFlag)) {
      pathList.add(fileSystem.getCurrentWorkingDir());
    }

    // Iterate through each path.
    for (String path : pathList) {
      // Store the current directory ls.
      String[] currDirLsResult = getPathLsResult(path, recursionFlag);
      // Add ls results to master result.
      lsResult = lsResult + currDirLsResult[0];
      // Add any errors to master err.
      masterErr = masterErr + currDirLsResult[1];
    }

    // Send any errors at this point through Iohandler.
    if (!"".equals(masterErr.trim())) {
      super.errMsgToIoHandler(masterErr.trim());
    }
    // Send current directory listing through Iohandler if it is not empty.
    if (!"".equals(lsResult.trim())) {
      super.outputToIoHandler(lsResult.trim());
    }
  }

  /**
   * Gets contents of the Directory at the given path if it exists (recursively
   * if necessary) and converts that information into ls format.
   * 
   * <p>Returns an array of two string with the first being the Directory contents
   * and the second being any error messages.
   * 
   * @param path String representing a path in the file system
   * @param recursiveListing Boolean option to indicate recursive operation
   * @return lsResultAndErr Array of two strings
   */
  private String[] getPathLsResult(String path, boolean recursiveListing) {
    // Set to store contents of current path.
    Set<String> currDirContents;
    // String to store final ls result.
    String contentLsFormat = "";
    String err = "";
    // Results array, where the 0th element is the ls result and the 1st
    // element is an error string.
    String[] lsResultAndErr = new String[2];

    try {
      // Don't do recursion if it's not required.
      if (!recursiveListing) {
        currDirContents = fileSystem.listDirectoryContent(path);
        contentLsFormat = contentLsFormat + path + ":\n";
        for (String content : currDirContents) {
          // Ignore . and .. since they are special symbols.
          if (!content.equals(".") && !content.equals("..")) {
            // Just concatenate content to the result if recursion is not used.
            contentLsFormat = contentLsFormat + content + "\n";
          }
        }
      } else {
        // Call filesystem's tree() method.
        contentLsFormat = fileSystem.tree(path, ":");
      }
      // Add an extra newline after directory listings, as specified.
      contentLsFormat += "\n";
    } catch (InvalidPathException exception) {
      // Add to error if path is not a file.
      err = err + path + ": path does not exist\n";
    }
    // Add results to results array.
    lsResultAndErr[0] = contentLsFormat;
    lsResultAndErr[1] = err;
    return lsResultAndErr;
  }

  @Override
  public String toString() {
    return ("instance of Ls with references: " + fileSystem + ", " + ioHandler);
  }

}
