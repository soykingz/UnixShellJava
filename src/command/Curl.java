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

import command.UrlDownloader;
import filesystem.FileAlreadyExistException;
import filesystem.FileSystem;
import filesystem.InvalidPathException;
import java.io.IOException;
import shell.IoHandler;

/**
 * A Command that gets the content of a plain text or html file at given url.
 * 
 * @author Soykingz
 *
 */
public class Curl extends CommandWithOutput {
  private FileSystem fileSystem;
  private UrlDownloader ud;

  /**
   * Constructor for Curl.
   * 
   * @param io reference to IoHandler instance
   */
  public Curl(FileSystem fs, IoHandler io, UrlDownloader ud) {
    super.setIo(io);
    this.ud = ud;
    this.fileSystem = fs;
  }

  @Override
  public void execute(String[] argsArray) {
    // Check if args has 2 elements in it (command "cd" and a path ONLY).
    if (argsArray.length != 2) {
      // Send error through IoHandler.
      String err = "usage: " + argsArray[0] + " PATH"
          + "\n    PATH: path cannot contain spaces";
      super.errMsgToIoHandler(err);
    } else {
      // check using regex that we get html and text only
      if (argsArray[1].endsWith(".txt") || argsArray[1].endsWith(".html")) {
        int last = argsArray[1].lastIndexOf("/");
        // check if we have at least one "/"
        if (last == -1) {
          super.errMsgToIoHandler("invalid url");
        } else {
          // seperate file name from the url path
          String fileName = argsArray[1].substring(last);
          try {
            // download the url
            String result = this.ud.downloadUrlFile(argsArray[1]);
            // add the file into file system
            this.fileSystem.addFile(result,
                fileSystem.getCurrentWorkingDir() + fileName);
          } catch (IOException e) {
            super.errMsgToIoHandler("invalid url");
          } catch (FileAlreadyExistException | InvalidPathException e) {
            super.errMsgToIoHandler(fileName + ": FileAlreadyExist");
          }
        }
      } else {
        super.errMsgToIoHandler("curl only works with plain text, html");
      }
    }
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return null;
  }

}
