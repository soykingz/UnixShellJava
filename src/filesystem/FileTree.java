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
 * This is a Tree Containing nodes and leafs nodes containing references to
 * other nodes or leafs leafs contain T. "/" is the path of the root Node. leaf1
 * is in Tree and has path /node1/node2/.../nodeN/leaf1 iff the root Node
 * contains node1 Node, and node1 Node contains node2 Node, and so on, and nodeN
 * contains leaf1 leaf Node
 *
 */
public class FileTree<T> {
  public Node<T> currentWorkingNode;
  public Node<T> root;

  /**
   * Constructor instantiate a root node.
   * 
   */
  public FileTree() {
    this.root = new Node<>(true);
    this.root.addReference(".", this.root);
    this.root.setName("/");
    this.currentWorkingNode = this.root;
  }

  /**
   * If path is ...dirN/dirM, create a node dirM in the dirN node, Note :
   * addNode only creates one node at a time. If path is dir1/dir2, and dir1
   * doesn't exist, addNode will not created them.
   * 
   * @param path path of the node
   * @throws InvalidPathException if the given path does not exist
   */
  public void addNode(String path)
      throws InvalidPathException, NodeAlreadyExistException {
    // split the parent out of path
    String[] splitted = this.cleanPath(path);
    String parentPath = splitted[0];
    String name = splitted[1];
    // get parent node
    Node<T> parentNode = this.getNode(parentPath);
    if (parentNode.containsNode(name)) {
      throw new NodeAlreadyExistException();
    }
    // create a node
    Node<T> newNode = new Node<>(true);
    newNode.setName(name);
    // added in newNode "." which is a reference to itself
    newNode.addReference(".", newNode);
    // add the new Node side parent
    parentNode.addReference(name, newNode);
    // set the new Node's parent to parent
    newNode.addReference("..", parentNode);
  }

  /**
   * If path is dir1/.../dirN, and node has dir1/.../dirN then return dirN. If
   * any of dir1/.../dirN in the path doesn't exist or exist as a Node without
   * reference to other node(leaf), throw InvalidPathException. Path cannot
   * start with "/", and cannot end with "/"
   * 
   * @param nodePath is a path of the node to get
   * @return the node at path
   * @throws InvalidPathException if the given path does not exist
   * @throws NodeNotExistException if the given path is not a node
   */
  private Node<T> getSubNode(Node<T> node, String nodePath)
      throws InvalidPathException, NodeNotExistException {
    // check if node is node containing reference
    if (!(node.hasReference())) {
      throw new InvalidPathException();
    }
    // split the names
    String[] nodePaths = nodePath.split("/");
    // every name in the fileNames has to be a directory
    int index = 0;
    int total = nodePaths.length;
    boolean error = false;
    Node<T> curNode = node;
    while (index < total && !(error)) {
      // check if there is a file with name same as fileNames[index]
      Node<T> subNode = curNode.getReference(nodePaths[index]);
      // if we did find a file with name we still have to check
      // that its an directory
      if (!subNode.hasReference()) {
        throw new InvalidPathException();
      }
      // if we did find a directory, move in the directory we found
      // and increment index to get the next file name
      curNode = curNode.getReference(nodePaths[index]);
      index++;
    }
    return curNode;
  }

  /**
   * Return the Nodes at path, path can be absolute or relative. Path can be
   * /dir1/.../dirN or dir1/.../dirN. Return null if its not found.
   * 
   * @param path path of the node
   * @return the node at path
   * @throws InvalidPathException if the given path does not exist
   */
  private Node<T> getNode(String path) throws InvalidPathException {
    Node<T> result;
    // if the path is a absolute path
    if (path.equals("/")) {
      result = this.root;
    } else if (path.equals("")) {
      result = this.currentWorkingNode;
    } else {
      // if the path is a absolute path
      try {
        if (path.indexOf("/") == 0) {
          result = getSubNode(this.root, path.substring(1));
          // if path is a relative path
        } else {
          result = getSubNode(this.currentWorkingNode, path);
        }
      } catch (NodeNotExistException e) {
        throw new InvalidPathException();
      }
    }
    return result;
  }

  /**
   * Change the current working Node in this file system.
   * 
   * @param path path of the node
   * @throws InvalidPathException if the given path does not exist
   */
  public void changeWorkingNode(String path) throws InvalidPathException {
    Node<T> dest = this.getNode(path);
    this.currentWorkingNode = dest;
  }

  /**
   * Return the current working node in this tree.
   * 
   * @return the path of current working node
   */
  public String getCurrentWorkingNode() {
    return this.getPath(this.currentWorkingNode);
  }

  /**
   * List the content of node at path.
   * 
   * @return a set and every element in the set is a node in the path node
   * @throws InvalidPathException if path is not a valid node
   */
  public Set<String> listNodeContent(String path) throws InvalidPathException {
    Node<T> node = this.getNode(path);
    Set<String> result = node.listReference();
    result.remove(".");
    result.remove("..");
    return result;
  }

  /**
   * Get content of node at path.
   * 
   * @param path is a path to the node to get. if path is dir1/.../dirN/file:
   * @throws InvalidPathException if the given path does not exist
   * @throws FileNotExistException if dirN doesn't contain file
   */
  public T getLeaf(String path) throws NodeNotExistException,
      InvalidPathException, NodeNoContentException {
    // split the parent out of path
    String[] splitted = this.cleanPath(path);
    String parentPath = splitted[0];
    String name = splitted[1];
    // find the parent directory
    Node<T> parent = this.getNode(parentPath);
    // get fileName file if the file exist
    Node<T> leaf = parent.getReference(name);
    return leaf.getContent();
  }

  /**
   * If path is .../file1, insert node with content and with name file1 at .../
   * node.
   * 
   * @param content is content of node
   * @param path the path of file1 after insertion
   * @throws InvalidPathException if the given path does not exist
   * @throws NodeAlreadyExistException if given path already exist a node
   */
  public void addLeaf(T content, String path)
      throws InvalidPathException, NodeAlreadyExistException {
    // split the parent out of path
    String[] splitted = this.cleanPath(path);
    String parentPath = splitted[0];
    String name = splitted[1];
    // find the parent directory
    Node<T> parentNode = this.getNode(parentPath);
    if (parentNode.containsNode(name)) {
      throw new NodeAlreadyExistException();
    }
    // create a leaf node
    Node<T> leaf = new Node<>(false);
    leaf.setContent(content);
    // add leaf node into parent
    parentNode.addReference(name, leaf);
  }

  /**
   * Remove node at path.
   * 
   * @param path is the path of node to remove. if path is dir1/.../dirN/file:
   * @throws InvalidPathException if the given path does not exist
   * @throws FileNotExistException if dirN doesn't contain file
   */
  public void removeNode(String path)
      throws NodeNotExistException, InvalidPathException {
    // split the parent out of path
    String[] splitted = this.cleanPath(path);
    String parentPath = splitted[0];
    String name = splitted[1];
    // find the parent directory
    Node<T> parent = this.getNode(parentPath);
    parent.removeReference(name);
  }

  /**
   * Return the path of node.
   * 
   * @param node to get its path
   * @return the path as a string
   */
  private String getPath(Node<T> node) {
    String result = this.getPathHelper(node);
    // remove the extra "/" in the head
    // and add an extra "/" in the end
    if (result.charAt(0) == '/') {
      result = result.substring(1);
    }
    return result + "/";
  }

  /**
   * Recursive part of getPath.
   * 
   * @return the path of this Directory
   */
  private String getPathHelper(Node<T> node) {
    // if this directory has a parent directory
    try {
      return this.getPathHelper(node.getReference("..")) + "/" + node.getName();
    } catch (NodeNotExistException e) {
      return node.getName();
    }
  }

  /**
   * Return a String thats the traversal of tree root at nodePath. if the node
   * "\" contains two nodes as "A", "B", "C" and "A" contains "A1" and "A2",
   * then traversal("\", ":", 0) will return the following string \: A: A1 A2 B:
   * C:
   * 
   * @param nodeAffix the the affix to be added when displaying node
   * @return a String thats the traversal of tree root at nodePath
   * @throws InvalidPathException if nodePath doesn't exist
   */
  public String traversal(String nodePath, String nodeAffix)
      throws InvalidPathException {
    // get the node at nodePath
    Node<T> node = this.getNode(nodePath);
    try {
      return traversal_helper(node, nodeAffix, 0);
    } catch (NodeNotExistException e) {
      throw new InvalidPathException();
    }
  }

  /**
   * Recursive part of traversal.
   */
  private String traversal_helper(Node<T> node, String nodeAffix,
      int indentation) throws NodeNotExistException {
    String result = "";
    String indent = "";
    for (int i = 0; i < indentation; i++) {
      indent = indent + " ";
    }
    if (node.hasReference()) {
      result = result + indent + node.getName() + nodeAffix + "\n";
    } else {
      result = result + indent + node.getName() + "\n";
    }
    if (node.hasReference()) {
      // add every node name in the directory to result
      Set<String> set = node.listReference();
      for (String cur : set) {
        Node<T> sub = node.getReference(cur);
        // ignore the . and .. present in every directory
        if (!(cur.equals(".") || cur.equals(".."))) {
          result = result + indent
              + traversal_helper(sub, nodeAffix, indentation + 1);
        }
      }
    }
    return result;
  }

  /**
   * Return a String thats the traversal of this tree.
   */
  @Override
  public String toString() {
    try {
      return this.traversal("/", "");
    } catch (InvalidPathException e) {
      return "tree error";
    }
  }

  /**
   * Split out the Node name from its path If path is "/path/name/" then return
   * ["/path", "name"]. If path is "/name/" then return ["/", "name"].
   * 
   * @param path string path
   * @return
   */
  private String[] cleanPath(String path) {
    String[] result = new String[2];
    // remove all tailing "/"
    if (path.charAt(path.length() - 1) == '/') {
      path = path.substring(0, path.length() - 1);
    }
    // check if there is still "/" in the path after removing the tailing "/"
    int last = path.lastIndexOf("/");
    // if we can't find a "/" in the path name
    // then the entire path name is a directory name
    // and the path has to be the current working directory
    if (last == -1) {
      result[0] = "";
      result[1] = path;
      // if the last index of "/" is the first index
    } else if (last == 0) {
      result[0] = "/";
      result[1] = path.substring(1);
    } else {
      // remove
      String pathReduced = path.substring(0, last + 1);
      // pathReduced == "...fileN/" iff path is "...fileN/fileM",
      // so fileN is the parent path.
      // the "/" in the end has to be removed for getDirectory
      String slashRemoved = pathReduced.substring(0, pathReduced.length() - 1);
      result[0] = slashRemoved;
      result[1] = path.substring(last + 1);
    }
    return result;
  }

}

