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
public interface FileSystem {

  /**
   * Return the current working directory in the FileSystem.
   * 
   * @return the path of current working Directory
   */
  public String getCurrentWorkingDir();

  /**
   * If path is ...dirN/dirM, create a directory dirM in the dirN directory,
   * else if path is dirN, create dirN in the current working directory. Note :
   * createDirectory only creates one directory at a time. If path is dir1/dir2,
   * and dir1 doesn't exist, createDirectory will not created them.
   * 
   * @param path path of the directory
   * @throws InvalidPathException if the given path does not exist
   * @throws FileAlreadyExistException if given path already exist a file
   */
  public void createDirectory(String path)
      throws InvalidPathException, FileAlreadyExistException;

  /**
   * Change the current working directory in this file system.
   * 
   * @param path path of the directory
   * @throws InvalidPathException if the given path does not exist
   */
  public void changeWorkingDirectory(String path) throws InvalidPathException;

  /**
   * List the content of directory at path.
   * 
   * @return a set and every element in the set is a File in the Directory
   * @throws InvalidPathException if path is not a valid Directory
   */
  public Set<String> listDirectoryContent(String path)
      throws InvalidPathException;

  /**
   * If path is .../file1, insert file with fileContent and with name file1 at
   * .../ directory.
   * 
   * @param fileContent content of file
   * @param path the path of file1 after insertion
   * @throws InvalidPathException if the given path does not exist
   * @throws FileAlreadyExistException if given path already exist a file
   */
  public void addFile(String fileContent, String path)
      throws InvalidPathException, FileAlreadyExistException;

  /**
   * Remove file at path.
   * 
   * @param path is the path of file to remove. if path is dir1/.../dirN/file:
   * @throws InvalidPathException if the given path does not exist
   * @throws FileNotExistException if dirN doesn't contain file
   */
  public void removeFile(String path)
      throws FileNotExistException, InvalidPathException;

  /**
   * Get content of file at path.
   * 
   * @param path is a path to the directory of file to delete. if path is
   *        dir1/.../dirN/file:
   * @throws InvalidPathException if the given path does not exist
   * @throws FileNotExistException if dirN doesn't contain file
   */
  public String getFile(String path)
      throws FileNotExistException, InvalidPathException;

  /**
   * Traverse of FileSystem and display the content of this FileSystem as a
   * tree.
   */
  public String tree(String path, String directoryAffix)
      throws InvalidPathException;
}
