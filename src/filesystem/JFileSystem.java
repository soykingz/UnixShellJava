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

package filesystem;

import java.util.Set;

/**
 * A FileSystem to manage directories and files. "/" is the path of the root
 * Directory. If file file1 is in FileSystem and has path
 * /dir1/dir2/.../dirN/file1, then the root directory contains dir1 directory,
 * and dir1 directory contains dir2 directory, and so on, and dirN contains file
 * file1.
 */
public class JFileSystem implements FileSystem {
  // a static reference to a FileSystem
  private static FileTree<String> refToFT = null;

  /**
   * FileSystem is created with an root directory. Private Constructor for
   * FileSystem because of Singleton Design.
   */
  private JFileSystem() {}

  /**
   * Create a FileSystem if FileSystem was never created, else always return a
   * reference to a FileSystem that was created.
   * 
   * @return
   */
  public static JFileSystem createFileSystem() {
    if (refToFT == null) {
      // construct a new tree if havent
      refToFT = new FileTree<String>();
    }
    return new JFileSystem();
  }

  @Override
  public String getCurrentWorkingDir() {
    return refToFT.getCurrentWorkingNode();
  }

  @Override
  public void createDirectory(String path)
      throws InvalidPathException, FileAlreadyExistException {
    try {
      refToFT.addNode(path);
    } catch (NodeAlreadyExistException e) {
      throw new FileAlreadyExistException();
    }
  }

  @Override
  public void changeWorkingDirectory(String path) throws InvalidPathException {
    refToFT.changeWorkingNode(path);
  }

  @Override
  public void addFile(String fileContent, String path)
      throws InvalidPathException, FileAlreadyExistException {
    try {
      refToFT.addLeaf(fileContent, path);
    } catch (NodeAlreadyExistException e) {
      throw new FileAlreadyExistException();
    }
  }


  @Override
  public void removeFile(String path)
      throws FileNotExistException, InvalidPathException {
    try {
      refToFT.removeNode(path);
    } catch (NodeNotExistException e) {
      throw new FileNotExistException();
    }
  }


  @Override
  public String getFile(String path)
      throws InvalidPathException, FileNotExistException {
    try {
      return refToFT.getLeaf(path);
    } catch (NodeNotExistException e) {
      throw new FileNotExistException();
    } catch (NodeNoContentException e) {
      return "";
    }
  }

  @Override
  public Set<String> listDirectoryContent(String path)
      throws InvalidPathException {
    return refToFT.listNodeContent(path);
  }

  /**
   * Traverse of FileSystem and display the content of this FileSystem as a
   * tree.
   */
  public String tree(String path, String directoryAffix)
      throws InvalidPathException {
    return refToFT.traversal(path, directoryAffix);
  }

  @Override
  public String toString() {
    try {
      return this.tree("/", "");
    } catch (InvalidPathException e) {
      return "FileSystem has error";
    }

  }
}
