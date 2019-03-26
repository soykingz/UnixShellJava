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

import filesystem.FileAlreadyExistException;
import filesystem.FileNotExistException;
import filesystem.FileSystem;
import filesystem.InvalidPathException;
import java.util.HashSet;
import java.util.Set;

public class MockFileSystem implements FileSystem {

  @Override
  public String getCurrentWorkingDir() {
    return ("cwd");
  }

  @Override
  public void createDirectory(String path)
      throws InvalidPathException, FileAlreadyExistException {
    if ("validpath".equals(path)) {
      // Pretend like a directory was successfully created.
      System.out.print("path created\n");
    } else if ("alreadyexists".equals(path)) {
      throw new FileAlreadyExistException();
    } else {
      throw new InvalidPathException();
    }
  }

  @Override
  public void changeWorkingDirectory(String path) throws InvalidPathException {
    if ("validpath".equals(path)) {
      // Pretend like the change was successful.
      System.out.print("success");;
    } else {
      throw new InvalidPathException();
    }
  }

  @Override
  public void addFile(String filecontent, String path)
      throws InvalidPathException, FileAlreadyExistException {
    if ("validpath".equals(path)) {
      // Pretend like the file was added.
      ;
    } else if (path.equals("cwd/file1.txt")) {
      ;
    } else if ("alreadyexists".equals(path)) {
      throw new FileAlreadyExistException();
    } else {
      throw new InvalidPathException();
    }
  }

  @Override
  public void removeFile(String path)
      throws FileNotExistException, InvalidPathException {
    if ("validpathwithfile".equals(path)) {
      // Pretend like the removal was successful.
      ;
    } else if ("validpathwithnofile".equals(path)) {
      throw new FileNotExistException();
    } else {
      throw new InvalidPathException();
    }

  }

  @Override
  public String getFile(String path)
      throws FileNotExistException, InvalidPathException {
    if (path.equals("cwd/file1.txt")) {
      return "valid text content";
    }
    if ("validpathwithfile".equals(path)) {
      // Get mockFile.
      return ("you got a file! nice!");
    } else if ("validpathwithnofile".equals(path)) {
      throw new FileNotExistException();
    } else {
      throw new InvalidPathException();
    }
  }

  @Override
  public Set<String> listDirectoryContent(String path)
      throws InvalidPathException {
    if ("validpath".equals(path)) {
      Set<String> set = new HashSet<>();
      set.add("content1");
      set.add("content2");
      return set;
    } else if ("cwd".equals(path)) {
      Set<String> set = new HashSet<>();
      set.add("cwd1");
      set.add("cwd2");
      return set;
    } else {
      throw new InvalidPathException();
    }
  }

  @Override
  public String tree(String path, String directoryAffix)
      throws InvalidPathException {
    if ("validpath".equals(path)) {
      return "/" + directoryAffix + "\n" + "  validtree\n";
    } else {
      throw new InvalidPathException();
    }
  }

  @Override
  public String toString() {
    try {
      return this.tree("validpath", "");
    } catch (InvalidPathException e) {
      return "FileSystem has error";
    }
  }
}
