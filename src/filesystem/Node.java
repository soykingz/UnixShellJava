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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Node contains content Node if hasReference, else Node contains reference to
 * other Nodes.
 * 
 */
public class Node<T> {
  public boolean hasReference;
  public T content;
  public Map<String, Node<T>> nodes;
  public String name = "";
  public String path = "";

  /**
   * Create a Node, if hasRefs, create a List of reference to other Node.
   */
  public Node(boolean hasReference) {
    this.hasReference = hasReference;
    nodes = new HashMap<>();
  }

  public boolean hasReference() {
    return this.hasReference;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getPath() {
    return this.path;
  }

  public void setContent(T data) {
    this.content = data;
  }

  /**
   * Return the content of this Node.
   * 
   * @return the content of this Node
   * @throws NodeNoContentException if this node does not have content
   */
  public T getContent() throws NodeNoContentException {
    if (this.content == null) {
      throw new NodeNoContentException();
    } else {
      return this.content;
    }
  }

  /**
   * Return at reference of node nodeName iff node nodeName is in this node.
   * 
   * @param nodeName is the name of the node
   * @return a reference to node nodeName
   * @throws NodeNotExistException if this node doesn't contain ref to nodeName
   */
  public Node<T> getReference(String nodeName) throws NodeNotExistException {
    if (this.containsNode(nodeName)) {
      return this.nodes.get(nodeName);
    } else {
      throw new NodeNotExistException();
    }
  }

  /**
   * Return true iff this node contains node nodeName.
   * 
   * @param nodeName the name of node to check
   * @return a boolean indicating if this node contains node nodeName
   */
  public boolean containsNode(String nodeName) {
    if (this.nodes.containsKey(nodeName)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * List all the nodes thats in this node.
   * 
   * @return a Set of String where every element is the name of a node in this
   *         node
   */
  public Set<String> listReference() {
    return this.nodes.keySet();
  }

  /**
   * Added a node n to this node.
   * 
   * @param name name of node n
   * @param n a node to be added
   */
  public void addReference(String name, Node<T> n) {
    if (this.hasReference) {
      this.nodes.put(name, n);
    }
  }

  /**
   * Remove a node from this node.
   * 
   * @param name the name of node to be removed
   * @throws NodeNotExistException if this node doesn't contain name node
   */
  public void removeReference(String name) throws NodeNotExistException {
    if (this.containsNode(name)) {
      this.nodes.remove(name);
    } else {
      throw new NodeNotExistException();
    }
  }

  /**
   * Return the String representation of content of this file or directory.
   */
  @Override
  public String toString() {
    String result;
    if (this.hasReference) {
      result = "Reference to other Node" + this.nodes.values().toString();
    } else {
      result = "contents" + this.content.toString();
    }
    return this.getName() + ": instance of a node with" + result;
  }

  /**
   * Return true iff the other File is logically equivalent to this File.
   */
  @Override
  public boolean equals(Object other) {
    boolean result = false;
    // if the other File is also a File
    if (other instanceof Node<?>) {
      try {
        // if they are both file or both directory
        if (this.hasReference() == (((Node<?>) other).hasReference())) {
          // if they are both node with reference
          if (this.hasReference()) {
            if (this.nodes.equals(((Node<?>) other).nodes)) {
              result = true;
            }
          } else {
            // if they both file check file content equal
            if (this.getContent().equals(((Node<?>) other).getContent())) {
              result = true;
            }
          }
        }
        // if this File is empty or other File is empty, can't equality check
      } catch (Exception e) {
        result = false;
      }
    }
    return result;
  }
}
