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

import java.util.HashMap;
import java.util.Map;
import shell.IoHandler;

/**
 * Command which displays documentation for commands.
 * 
 * @author liangho2
 */
public class Man extends CommandWithOutput {

  // Dict to store man outputs.
  private final Map<String, String> commandInfo = new HashMap<>();
  // Singleton man instance, as commandInfo will never change and only one is
  // ever needed.
  private static Man manInstance = null;

  /**
   * Constructor for Man, which populates a HashMap with commands and their
   * corresponding documentation.
   * 
   * @param io reference to IoHandler instance
   */
  private Man(IoHandler io) {
    // Populate commandInfo. (Please note that the great number of lines is
    // only due to extensive documentation.)
    commandInfo.put("exit",
        "NAME\n    exit - exit the program\nSYNOPSIS\n    exit\nDESCRIPTION\n"
            + "    Exits this shell program when called.\n    "
            + "Exit will occur regardless of any command arguments input.");
    commandInfo.put("mkdir",
        "NAME\n    mkdir - make directories\nSYNOPSIS\n    "
            + "mkdir DIR...\nDESCRIPTION\n    Creates directory DIR or "
            + "multiple directories if they do not already exist.\n    "
            + "DIR may be relative to the current directory or a full path.");
    commandInfo.put("cd",
        "NAME\n    cd - change current working directory\nSYNOPSIS\n    "
            + "cd DIR\nDESCRIPTION\n    Changes current working directory to "
            + "DIR if it exists.\n    DIR can be relative to the current "
            + "directory or a full path.");
    commandInfo.put("ls",
        "NAME\n    ls - print contents of directories\nSYNOPSIS\n    "
            + "ls [-R] [PATH...]\nDESCRIPTION\n    Prints the contents of each "
            + "PATH specified.\n    If no PATH is given, prints the contents of"
            + "the current directory.\n    If [-R] is present, "
            + "subdirectory contents will be recursively listed.");
    commandInfo.put("pwd",
        "NAME\n    pwd - print current working directory\nSYNOPSIS\n    "
            + "pwd\nDESCRIPTION\n    Prints the full path of the current "
            + "working directory.");
    commandInfo.put("pushd",
        "NAME\n    pushd - save current working directory and change to new "
            + "directory\nSYNOPSIS\n    pushd DIR\nDESCRIPTION\n    "
            + "Pushes the current working directory and changes into DIR if it "
            + "exists.\n    Saved directory can be returned to using popd.");
    commandInfo.put("popd",
        "NAME\n    popd - change to saved directory\nSYNOPSIS\n    "
            + "popd\nDESCRIPTION\n    Pops a saved directory (from pushd) and "
            + "changes to it.");
    commandInfo.put("history",
        "NAME\n    history - show previous entered user input\nSYNOPSIS\n    "
            + "history [NUM]\nDESCRIPTION\n    Prints out recent user input, "
            + "numbered such that the highest numbered line is the most recent "
            + "input.\n    Output is truncated to NUM number of lines.\n    "
            + "Special commands below will not be included in history:\n        "
            + "!NUM");
    commandInfo.put("cat",
        "NAME\n    cat - display content of files\nSYNOPSIS\n    "
            + "cat FILE1 [FILE2...]\nDESCRIPTION\n    Displays the contents of "
            + "input FILE(s).");
    commandInfo.put("echo",
        "NAME\n    echo - display input strings back at user and/or redirects "
            + "input strings to a file\nSYNOPSIS\n    "
            + "echo \"STRING\" [> OUTFILE] [>> OUTFILE]\nDESCRIPTION\n    "
            + "Prints STRING if no redirect symbol (> or >>) is present.\n    "
            + "If > is present, STRING will be written to OUTFILE and any "
            + "content will be overwritten (OUTFILE will be created if it does "
            + "not exist).\n    If >> is present, STRING will be appended to "
            + "OUTFILE.");
    commandInfo.put("find",
        "NAME\n    find - search for files or directories\nSYNOPSIS\n    "
            + "find PATH... -type [f|d] -name EXPRESSION\nDESCRIPTION\n    "
            + "Searches PATH for files and/or directories by name.\n    "
            + "-type f searches for files; -type d searches for directories."
            + "\n    -name matches search target by exact name"
            + " \"EXPRESSION\".");
    commandInfo.put("tree",
        "NAME\n    tree - display human readable representation of the entire "
            + "file system of this program\nSYNOPSIS\n    "
            + "tree\nDESCRIPTION\n    Displays a tree representation of the "
            + "file system of this program.");
    commandInfo.put("man",
        "NAME\n    man - manual for commands of this program\nSYNOPSIS\n    "
            + "man CMD\nDESCRIPTION\n    "
            + "Displays documentation for command CMD.");
    commandInfo.put("\">\"",
        "NAME\n    > - redirect command output to a file (overwrite)"
            + "\nSYNOPSIS\n    CMD [> OUTFILE]\nDESCRIPTION\n    CMD's output will "
            + "be written to OUTFILE and any content present in OUTFILE will be "
            + "overwritten (OUTFILE will be created if it does not exist).\n    "
            + "OUTFILE will not be created if CMD has no output.\n    Any errors "
            + "generated will not be redirected to OUTFILE.");
    commandInfo.put("\">>\"",
        "NAME\n    >> - redirect command output to a file (append)\nSYNOPSIS\n"
            + "    CMD [>> OUTFILE]\nDESCRIPTION\n    CMD's output will be appended"
            + " to OUTFILE (OUTFILE will be created if it does not exist).\n    "
            + "OUTFILE will not be appended to or created if CMD has no output.\n"
            + "    Any errors generated will not be redirected to OUTFILE.");
    commandInfo.put("redirect",
        "NAME\n    > - redirect command output to a file\n    >> - redirect command"
            + " output to a file (append)\nSYNOPSIS\n    "
            + "CMD [> OUTFILE] [>> OUTFILE]\nDESCRIPTION\n    CMD's output will be"
            + " redirected to OUTFILE (OUTFILE will be created if it does not"
            + " exist).\n    man \">\" or man \">>\" for more information.");
    commandInfo.put("mv",
        "NAME\n    mv - move an item to a new path\nSYNOPSIS\n    mv OLDPATH"
            + " NEWPATH\nDESCRIPTION\n    Moves item located at OLDPATH to NEWPATH."
            + "\n    OLDPATH and NEWPATH may be relative paths or full paths.\n"
            + "    If NEWPATH is a directory, move OLDPATH into that directory.");
    commandInfo.put("cp",
        "NAME\n    cp - copy an item to a new path\nSYNOPSIS\n    cp OLDPATH"
            + " NEWPATH\nDESCRIPTION\n    Copies item located at OLDPATH to NEWPATH."
            + "\n    OLDPATH and NEWPATH may be relative paths or full paths.\n"
            + "    If NEWPATH is a directory, copy OLDPATH into that directory.\n"
            + "    If OLDPATH is a directory, recursively copy that directory to"
            + " NEWPATH.");
    commandInfo.put("curl",
        "NAME\n    curl - retrieve the file located at an URL and add it to the"
            + " current working directory\nSYNOPSIS\n    curl URL\nDESCRIPTION\n"
            + "    Retrieves the file located at URL and adds it to the current"
            + " working directory.\n    URL is a Uniform Resource Locator referring"
            + " to a web address.\n    File located at URL must be a valid plaintext"
            + " file.");
    commandInfo.put("!",
        "NAME\n    ! - recall and execute a line of user input from the user history"
            + "\nSYNOPSIS\n    !NUM\nDESCRIPTION\n    Recalls a line of user input"
            + " in the user history at position NUM and executes it.\n    NUM is the"
            + " number preceding a line of user input as displayed by the history"
            + " command.\n    NUM >= 1\n    man history for more information about"
            + " user history.");
    commandInfo.put("grep",
        "NAME\n    grep - find lines in a file or files under directories that match"
            + " a given expression\nSYNOPSIS\n    grep [-R] REGEX PATH...\n"
            + "DESCRIPTION\n    Matches information at PATH that contain REGEX.\n"
            + "    If PATH is a file, and [-R] is not supplied, any lines in PATH"
            + " containing REGEX will be displayed.\n        If PATH is a file, [-R]"
            + " should NOT be supplied.\n    If PATH is a directory, and [-R] is"
            + " supplied, the path to every file found (recursively) in PATH that"
            + " contains lines matching REGEX will be displayed, with relevant"
            + " matching lines also displayed.\n        If PATH is a directory, [-R]"
            + " MUST be supplied.\n    REGEX is a regular expression.");
    super.setIo(io);
  }

  /**
   * Creates a singleton instance of Man (factory method).
   * 
   * @param io reference to IoHandler instance
   * @return manInstance reference to singleton Man instance
   */
  public static Man createInstance(IoHandler io) {
    // Only create instance of Man if one does not already exist.
    if (manInstance == null) {
      manInstance = new Man(io);
    }
    return manInstance;
  }

  @Override
  public void execute(String[] argsArray) {
    // Send error through ioHandler if arguments are invalid.
    if (argsArray.length != 2) {
      String err = "usage: " + argsArray[0] + " CMD";
      super.errMsgToIoHandler(err);
    } else {
      // Check if CMD is a valid one.
      if (commandInfo.containsKey(argsArray[1])) {
        // If valid, send documentation through ioHandler.
        super.outputToIoHandler(commandInfo.get(argsArray[1].trim()));
      } else {
        // If command is invalid, send error through ioHandler.
        String err = argsArray[1] + ": invalid command";
        super.errMsgToIoHandler(err);
      }
    }

  }

  @Override
  public String toString() {
    return ("instance of Man with references: " + ioHandler);
  }

}
