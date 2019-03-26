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

import filesystem.FileAlreadyExistException;
import filesystem.FileNotExistException;
import filesystem.FileSystem;
import filesystem.InvalidPathException;

/**
 * A JIoHandler class that handles command output, error output, and command
 * output redirection.
 */
public class JIoHandler extends IoHandler {

  public JIoHandler(FileSystem fs) {
    super(fs);
  }

  private String stdOutput = "";
  private String stdError = "";

  @Override
  public void standardOutput(String strIn) {
    stdOutput = strIn;
  }

  @Override
  public void standardError(String strErr) {
    stdError = strErr;
  }

  @Override
  public void out() {
    if (!"".equals(stdError)) {
      System.out.println(stdError);
      // Reset stdError.
      stdError = "";
    }
    if (!"".equals(stdOutput)) {
      System.out.println(stdOutput);
      // Reset stdOutput.
      stdOutput = "";
    }
  }

  @Override
  public void reDirect(String redirectSymbol, String filePath) {
    // Check if the redirect symbol is for appending.
    if (">>".equals(redirectSymbol)) {
      // Try to get an existing file's content.
      try {
        String fileContent = fs.getFile(filePath);
        // Append output to original contents.
        fileContent = fileContent + "\n" + stdOutput;
        // Remove file and readd it with updated content.
        fs.removeFile(filePath);
        fs.addFile(fileContent, filePath);
      } catch (FileNotExistException | InvalidPathException e) {
        // Catch both exceptions here and recheck for InvalidPathException while
        // creating a new file to reduce number of lines in code.
        // If file doesn't exist try to create it.
        createFile(stdOutput, filePath);
      } catch (FileAlreadyExistException e) {
        // This will not happen since >> appends.
        setOrAppendToErr(filePath, ": path already exists");
      }
    } else if (">".equals(redirectSymbol)) {
      try {
        // Check if file exists by attempting to get its contents.
        fs.getFile(filePath);
        // If the above succeeds, remove and readd the file with overwritten
        // contents.
        fs.removeFile(filePath);
        fs.addFile(stdOutput, filePath);
      } catch (FileNotExistException | InvalidPathException e) {
        // If file doesn't exist try to create it.
        createFile(stdOutput, filePath);
      } catch (FileAlreadyExistException e) {
        // This will not happen since the file was removed above.
        setOrAppendToErr(filePath, ": path already exists");
      }
    }
    // Clear stdOutput as it has been redirected.
    stdOutput = "";
  }

  /**
   * Creates a file with given string content at a valid path.
   *
   * @param content string representing content to be put in file
   * @param filePath string representing path that file will be located at
   */
  private void createFile(String content, String filePath) {
    try {
      // Try to create the file at filePath.
      fs.addFile(stdOutput, filePath);
    } catch (InvalidPathException e1) {
      // Add to error if path is invalid.
      if ("".equals(stdError)) {
        stdError = filePath + ": invalid path";
      } else {
        // Append to error if necessary.
        stdError = stdError + "\n" + filePath + ": invalid path";
      }
    } catch (FileAlreadyExistException e) {
      setOrAppendToErr(filePath, ": path already exists");
    }
  }

  /**
   * Sets or appends to stdError depending on its state.
   *
   * @param filePath string representing object causing error
   * @param err string representing error message
   */
  private void setOrAppendToErr(String filePath, String err) {
    if ("".equals(this.stdError)) {
      this.stdError = filePath + err;
    } else {
      // Append to error if there is already an error message.
      this.stdError = this.stdError + "\n" + filePath + err;
    }
  }
}
