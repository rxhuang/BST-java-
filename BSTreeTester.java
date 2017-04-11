/*
 * Name: Chen Liu
 * ID: A92008330
 * Login: cs12wip
 * Date: Feb 26, 2016
 *
 * Title: class BSTreeTester
 * Description: A JUnit tester for BSTree class
 */
 

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit tester for BSTree class
 * @author Chen Liu
 * @version 1.0
 * @since Feb 26, 2016
 */
public class BSTreeTester {
  
  private BSTree empty;
  private BSTree root;
  private BSTree leftSided;
  private BSTree rightSided;
  private BSTree full;

  /**
   * Set up the test with initializing the trees
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    
    //initialize empty tree
    empty = new BSTree();
    
    //initialize tree with only root
    root = new BSTree();
    root.insert("Root", 1);
    
    //initialize tree that has only a chain to left
    leftSided = new BSTree();
    leftSided.insert("50", 50);
    leftSided.insert("40", 40);
    leftSided.insert("30", 30);
    leftSided.insert("20", 20);
    leftSided.insert("10", 10);
    
    //initialize tree that has only a chain to right
    rightSided = new BSTree();
    rightSided.insert("10", 10);
    rightSided.insert("20", 20);
    rightSided.insert("30", 30);
    rightSided.insert("40", 40);
    rightSided.insert("50", 50);
    
    //initialize a full BSTree
    full = new BSTree();
    full.insert("16", 16);
    full.insert("8", 8);
    full.insert("24", 24);
    full.insert("4", 4);
    full.insert("12", 12);
    full.insert("20", 20);
    full.insert("28", 28);
    full.insert("2", 2);
    full.insert("6", 6);
    full.insert("10", 10);
    full.insert("14", 14);
    full.insert("18", 18);
    full.insert("22", 22);
    full.insert("26", 26);
    full.insert("30", 30);
    full.insert("1", 1);
    full.insert("3", 3);
    full.insert("5", 5);
    full.insert("7", 7);
    full.insert("9", 9);
    full.insert("11", 11);
    full.insert("13", 13);
    full.insert("15", 15);
    full.insert("17", 17);
    full.insert("19", 19);
    full.insert("21", 21);
    full.insert("23", 23);
    full.insert("25", 25);
    full.insert("27", 27);
    full.insert("29", 29);
    full.insert("31", 31);
  }

  /**
   * Test method for {@link hw7.BSTree#getRoot()}.
   */
  @Test
  public void testGetRoot() {
    
    //test the name of the roots of each tree
    assertEquals("Root of empty tree:", null, empty.getRoot());
    assertEquals("Root of root tree:", "Root", root.getRoot().getPerson().getName());
    assertEquals("Root of leftSided tree:", "50", leftSided.getRoot().getPerson().getName());
    assertEquals("Root of rightSided tree:", "10", rightSided.getRoot().getPerson().getName());
    assertEquals("Root of full tree:", "16", full.getRoot().getPerson().getName());
    
    //test the key of the roots of each tree
    assertEquals("Root of empty tree:", null, empty.getRoot());
    assertEquals("Root of root tree:", 1, root.getRoot().getPerson().getKey());
    assertEquals("Root of leftSided tree:", 50, leftSided.getRoot().getPerson().getKey());
    assertEquals("Root of rightSided tree:", 10, rightSided.getRoot().getPerson().getKey());
    assertEquals("Root of full tree:", 16, full.getRoot().getPerson().getKey());
  }

  /**
   * Test method for {@link hw7.BSTree#insert(java.lang.String, int)}.
   */
  @Test
  public void testInsert() {
    
    /*test normal insertion for full done in setup*/
    
    //check the printed array
    Person[] fullActual = full.printToArray(full.getRoot());
    Person[] fullExpect = new Person[31];
    for(int i = 0; i < fullExpect.length; i++) {
      fullExpect[i] = new Person(new Integer(i + 1).toString(), i + 1);
    }
    for (int i = 0; i < fullActual.length; i++) {
      assertEquals(fullExpect[i].getName(), fullActual[i].getName());
    }
    for (int i = 0; i < fullActual.length; i++) {
      assertEquals(fullExpect[i].getKey(), fullActual[i].getKey());
    }
    
    //check leaf
    assertEquals(16, full.leafCount());
    
    //check levels
    for(int i = 0; i < 5; i++) {
      assertEquals((int)Math.pow(2, i), full.levelCount(i));
    }
    
    
    /*test duplicate key*/
    
    //insert duplicate keys
    for(int i = 1; i <= 5; i++) {
      leftSided.insert("new" + i * 10, i * 10);
    }
    
    //check the printed array
    Person[] leftActual = leftSided.printToArray(leftSided.getRoot());
    Person[] leftExpect = {
        new Person("10", 10),
        new Person("new10", 10),
        new Person("20", 20),
        new Person("new20", 20),
        new Person("30", 30),
        new Person("new30", 30),
        new Person("40", 40),
        new Person("new40", 40),
        new Person("50", 50),
        new Person("new50", 50),
    };
    for (int i = 0; i < leftActual.length; i++) {
      assertEquals(leftExpect[i].getName(), leftActual[i].getName());
    }
    for (int i = 0; i < leftActual.length; i++) {
      assertEquals(leftExpect[i].getKey(), leftActual[i].getKey());
    }
       
    //check leaf
    assertEquals(5, leftSided.leafCount());
    
    //check levels
    assertEquals("Check level 0", 1, leftSided.levelCount(0));
    for(int i = 1; i < 4; i++) {
      //assertEquals(2, leftSided.levelCount(i));
    }
   assertEquals("Check level 5", 1, leftSided.levelCount(5));
    
    //check the deepest leaf's position (should be a right child)
    assertEquals(null, leftSided.getRoot().getLChild().getLChild().
        getLChild().getLChild().getLChild());
    assertEquals("new10", leftSided.getRoot().getLChild().getLChild().
        getLChild().getLChild().getRChild().getPerson().getName());
    assertEquals(10, leftSided.getRoot().getLChild().getLChild().
        getLChild().getLChild().getRChild().getPerson().getKey());

    
    /*test exceptions*/
    
    try {
      empty.insert("anything", 0);
      fail("should throw an exception");
    } catch (IllegalArgumentException e) {
      //normal
    }

    try {
      empty.insert("anything", 201);
      fail("should throw an exception");
    } catch (IllegalArgumentException e) {
      //normal
    }

    try {
      empty.insert("", 1);
      fail("should throw an exception");
    } catch (IllegalArgumentException e) {
      //normal
    }
    /*
    try {
      empty.insert(null, 1);
      fail("should throw an exception");
    } catch (IllegalArgumentException e) {
      //normal
    }*/
  }

  /**
   * Test method for {@link hw7.BSTree#findPerson(int, java.lang.String)}.
   */
  @Test
  public void testFindPerson() {

    /*check purely find*/
    
    //test empty
    assertFalse(empty.findPerson(1, "anything"));
    
    //test root
    assertTrue(root.findPerson(1, "Root"));
    assertFalse("Check case sensitivity", root.findPerson(1, "root"));
    assertFalse("Check key", root.findPerson(2, "Root"));
    
    //test leftSided 
    for(int i = 1; i <= 5; i++) {
      assertTrue(leftSided.findPerson(i* 10, new Integer(i * 10).toString()));
    }
    
    //test rightSided
    for(int i = 1; i <= 5; i++) {
      assertTrue(rightSided.findPerson(i* 10, new Integer(i * 10).toString()));
    }
    
    //test full
    for(int i = 1; i <= 31; i++) {
      assertTrue(full.findPerson(i, new Integer(i).toString()));
    }
    
    
    /*check exceptions*/
    
    try {
      empty.findPerson(0, "anything");
      fail("should throw an exception");
    } catch (IllegalArgumentException e) {
      //normal
    }

    try {
      empty.findPerson(201, "anything");
      fail("should throw an exception");
    } catch (IllegalArgumentException e) {
      //normal
    }

    try {
      empty.findPerson(1, "");
      fail("should throw an exception");
    } catch (IllegalArgumentException e) {
      //normal
    }
    /*
    try {
      empty.findPerson(1, null);
      fail("should throw an exception");
    } catch (IllegalArgumentException e) {
      //normal
    }*/
  }

  /**
   * Test method for {@link hw7.BSTree#printToArray(hw7.BSTree.BSTNode)}.
   */
  @Test
  public void testPrintToArray() {
    
    /*Check to print the whole tree*/
    
    //root tree
    Person[] rootActual = root.printToArray(root.getRoot());
    for (int i = 0; i < rootActual.length; i++) {
      assertEquals("Root", rootActual[i].getName());
      assertEquals(1, rootActual[i].getKey());
    }
    
    //full
    Person[] fullActual = full.printToArray(full.getRoot());
    for (int i = 0; i < fullActual.length; i++) {
      assertEquals(new Integer(i + 1).toString(), fullActual[i].getName());
      assertEquals(i + 1, fullActual[i].getKey());
    }
    
    //make tweak
    
    //insert 11 to 19
    for(int i = 11; i < 20; i++) {
      leftSided.insert(new Integer(i).toString(), i);
    }
    
    //check tweak
    Person[] tweakActual = leftSided.printToArray(leftSided.getRoot());
    for (int i = 0; i <= 10; i++) {
      assertEquals(new Integer(i + 10).toString(), tweakActual[i].getName());
      assertEquals(i + 10, tweakActual[i].getKey());
    }
    for (int i = 11; i <= 13; i++) {
      assertEquals(new Integer((i - 8) * 10).toString(), 
          tweakActual[i].getName());
      assertEquals((i - 8) * 10, tweakActual[i].getKey());
    }
    
    
    /*Check to print a subtree*/
    
    //in full
    Person[] fullPartial = 
        full.printToArray(full.getRoot().getLChild().getRChild());
    for (int i = 0; i < fullPartial.length; i++) {
      assertEquals(new Integer(i + 9).toString(), fullPartial[i].getName());
      assertEquals(i + 9, fullPartial[i].getKey());
    }
    
    //in tweak
    Person[] tweakPartial1 = 
        leftSided.printToArray(leftSided.getRoot().getLChild().getLChild());
    for (int i = 0; i <= 10; i++) {
      assertEquals(new Integer(i + 10).toString(), tweakPartial1[i].getName());
      assertEquals(i + 10, tweakPartial1[i].getKey());
    }
    for (int i = 11; i <= 11; i++) {
      assertEquals(new Integer((i - 8) * 10).toString(), 
          tweakPartial1[i].getName());
      assertEquals((i - 8) * 10, tweakPartial1[i].getKey());
    }
    Person[] tweakPartial2 = 
        leftSided.printToArray(leftSided.getRoot().
            getLChild().getLChild().getLChild().getLChild().getRChild());
    for (int i = 0; i <= 8; i++) {
      assertEquals(new Integer(i + 11).toString(), tweakPartial2[i].getName());
      assertEquals(i + 11, tweakPartial2[i].getKey());
    }
    
    /*Check exceptions*/
    /*
    try {
      empty.printToArray(empty.getRoot());
      fail("should throw an exception");
    } catch (NullPointerException e) {
      //normal
    }
    
    try {
      root.printToArray(full.getRoot());
      fail("should throw an exception");
    } catch (NullPointerException e) {
      //normal
    }*/
  }

  /**
   * Test method for {@link hw7.BSTree#delete(int, java.lang.String)}.
   */
  @Test
  public void testDelete() {
    
    /*test deletion and return values*/
    
    //root
    Person rootDeleted = root.delete(1, "Root");
    assertEquals("Root", rootDeleted.getName());
    assertEquals(1, rootDeleted.getKey());
    assertEquals(null, root.getRoot());
    assertFalse(root.findPerson(1, "Root"));
    
    //leftSided
    Person[] leftDeleted = new Person[5];
    for(int i = 0; i < 5; i++) {
      leftDeleted[i] = leftSided.delete((i + 1) * 10, new Integer((i + 1) * 10).toString());
    }
    for(int i = 0; i < 5; i++) {
      assertEquals(new Integer((i + 1) * 10).toString(), leftDeleted[i].getName());
      assertEquals((i + 1) * 10, leftDeleted[i].getKey());
    }
    assertEquals(null, leftSided.getRoot());
    for(int i = 0; i < 5; i++) {
      assertFalse(leftSided.findPerson((i + 1) * 10, new Integer((i + 1) * 10).toString()));
    }
    
    //rightSided
    Person[] rightDeleted = new Person[5];
    for(int i = 0; i < 5; i++) {
      rightDeleted[i] = rightSided.delete((i + 1) * 10, new Integer((i + 1) * 10).toString());
    }
    for(int i = 0; i < 5; i++) {
      assertEquals(new Integer((i + 1) * 10).toString(), rightDeleted[i].getName());
      assertEquals((i + 1) * 10, rightDeleted[i].getKey());
    }
    assertEquals(null, rightSided.getRoot());
    for(int i = 0; i < 5; i++) {
      assertFalse(rightSided.findPerson((i + 1) * 10, new Integer((i + 1) * 10).toString()));
    }
    
    //full
    Person[] fullDeleted = new Person[31];
    for(int i = 0; i < 31; i++) {
      fullDeleted[i] = full.delete((i + 1), new Integer(i + 1).toString());
    }
    for(int i = 0; i < 31; i++) {
      assertEquals(new Integer(i + 1).toString(), fullDeleted[i].getName());
      assertEquals((i + 1), fullDeleted[i].getKey());
    }
    assertEquals(null, full.getRoot());
    for(int i = 0; i < 31; i++) {
      assertFalse(full.findPerson(i + 1, new Integer(i + 1).toString()));
    }
    
    
    /*test exceptions*/
    
    try {
      full.delete(0, "anything");
      fail("should throw an exception");
    } catch (IllegalArgumentException e) {
      //normal
    }

    try {
      full.delete(201, "anything");
      fail("should throw an exception");
    } catch (IllegalArgumentException e) {
      //normal
    }

    try {
      full.delete(1, "");
      fail("should throw an exception");
    } catch (IllegalArgumentException e) {
      //normal
    }

    try {
      full.delete(1, null);
      fail("should throw an exception");
    } catch (IllegalArgumentException e) {
      //normal
    }
    
    try {
      empty.delete(1, "1");
      fail("should throw an exception");
    } catch (IllegalArgumentException e) {
      //normal
    }
    
    try {
      full.delete(200, "1");
      fail("should throw an exception");
    } catch (IllegalArgumentException e) {
      //normal
    }
    
    try {
      full.delete(1, "200");
      fail("should throw an exception");
    } catch (IllegalArgumentException e) {
      //normal
    }
  }

  /**
   * Test method for {@link hw7.BSTree#FindDepth(hw7.BSTree.BSTNode)}.
   */
  @Test
  public void testFindDepth() {
    
    //empty
    assertEquals(-1, empty.FindDepth(null));
    assertEquals(-1, empty.FindDepth(root.getRoot()));
    
    //root
    assertEquals(-1, root.FindDepth(null));
    //assertEquals(-1, root.FindDepth(full.getRoot()));
    assertEquals(0, root.FindDepth(root.getRoot()));
    
    //leftSided
    assertEquals(0, leftSided.FindDepth(leftSided.getRoot()));
    assertEquals(1, leftSided.FindDepth(leftSided.getRoot().getLChild()));
    assertEquals(2, leftSided.FindDepth(leftSided.getRoot().
        getLChild().getLChild()));
    assertEquals(3, leftSided.FindDepth(leftSided.getRoot().
        getLChild().getLChild().getLChild()));
    assertEquals(4, leftSided.FindDepth(leftSided.getRoot().
        getLChild().getLChild().getLChild().getLChild()));
    
    //rightSided
    assertEquals(0, rightSided.FindDepth(rightSided.getRoot()));
    assertEquals(1, rightSided.FindDepth(rightSided.getRoot().getRChild()));
    assertEquals(2, rightSided.FindDepth(rightSided.getRoot().
        getRChild().getRChild()));
    assertEquals(3, rightSided.FindDepth(rightSided.getRoot().
        getRChild().getRChild().getRChild()));
    assertEquals(4, rightSided.FindDepth(rightSided.getRoot().
        getRChild().getRChild().getRChild().getRChild()));
    
    //full
    assertEquals(0, full.FindDepth(full.getRoot()));
    assertEquals(1, full.FindDepth(full.getRoot().getLChild()));
    assertEquals(2, full.FindDepth(full.getRoot().
        getRChild().getLChild()));
    assertEquals(3, full.FindDepth(full.getRoot().
        getLChild().getRChild().getLChild()));
    assertEquals(4, full.FindDepth(full.getRoot().
        getRChild().getLChild().getRChild().getLChild()));
  }

  /**
   * Test method for {@link hw7.BSTree#leafCount()}.
   */
  @Test
  public void testLeafCount() {
    
    /*normal*/
    
    //empty
    assertEquals(0, empty.leafCount());
    
    //root
    assertEquals(1, root.leafCount());
    
    //leftSided
    assertEquals(1, leftSided.leafCount());
    
    //rightSided
    assertEquals(1, rightSided.leafCount());
    
    //full
    assertEquals(16, full.leafCount());
    
    
    /*tweak*/
    
    //insert 11 to 19 and check
    for(int i = 11; i < 20; i++) {
      leftSided.insert(new Integer(i).toString(), i);
      assertEquals(1, leftSided.leafCount());
    }    
  }

  /**
   * Test method for {@link hw7.BSTree#levelCount(int)}.
   */
  @Test
  public void testLevelCount() {
    
    //empty
    assertEquals(-1, empty.levelCount(-1));
    assertEquals(-1, empty.levelCount(0));
    assertEquals(-1, empty.levelCount(1));
    
    //root
    assertEquals(-1, root.levelCount(-1));
    assertEquals(1, root.levelCount(0));
    assertEquals(-1, root.levelCount(1));
    
    //leftSided
    assertEquals(-1, leftSided.levelCount(-1));
    for(int i = 0; i < 5; i++) {
      assertEquals(1, leftSided.levelCount(i));
    }
    assertEquals(-1, leftSided.levelCount(5));
    
    //rightSided
    assertEquals(-1, rightSided.levelCount(-1));
    for(int i = 0; i < 5; i++) {
      assertEquals(1, rightSided.levelCount(i));
    }
    assertEquals(-1, rightSided.levelCount(5));
    
    //full
    assertEquals(-1, full.levelCount(-1));
    for(int i = 0; i < 5; i++) {
      assertEquals((int)Math.pow(2, i), full.levelCount(i));
    }
    assertEquals(-1, full.levelCount(5));
    
  }

}
