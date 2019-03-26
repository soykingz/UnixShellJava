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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import filesystem.Node;
import filesystem.NodeNoContentException;
import org.junit.Before;
import org.junit.Test;


public class NodeTest {
  private Node<String> node1;
  private Node<String> leaf1;
  
  @Before
  public void setup() {
    this.node1 = new Node<>(true);
    this.leaf1 = new Node<>(false);
  }

  @Test
  public void testSetGetContentLeaf() {
    leaf1.setContent("hi");
    try {
      assertEquals("hi", leaf1.getContent());
    } catch (NodeNoContentException e) {
      fail();
    }
  }

  @Test
  public void testSetGetNode() {
    Node<String> newNode = new Node<>(false);
    newNode.setName("node1");
    newNode.setContent("cc");
    node1.addReference("1", newNode);
    try {
      Node<String> result = node1.getReference("1");
      assertEquals("node1", result.getName());
      assertEquals("cc", result.getContent());
    } catch (Exception e) {
      fail();
    }

  }
}
