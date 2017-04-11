/**
 *  NAME: Ruoxin Huang
 *  ID: A99084753
 *  LOGIN: cs12whl
 * */
import java.util.*;
/**
 *  Title: class BSTree
 *  Description: creates a binary search tree using nodes
 *  @author Ruoxin Huang
 *  @version 1.0
 *  @since 02-28-2016
 * */
public class BSTree{
    protected class BSTNode {
        Person person;
        BSTNode leftC;
        BSTNode rightC;
        /** 
         * Constructor to create singleton node link it to its children 
         *
         * @param  lChild   Left child of the node
         * @param  p   the person to be stored in the node
         * @param  rChild   Right child of the node
         */
        public BSTNode(BSTNode lChild, Person p, BSTNode rChild)
        {
            if(p == null){
                throw new NullPointerException();
            }
            person = p;
            leftC = lChild;
            rightC = rChild;
        }

        /** 
         * Getter for the person of a node
         * 
         * @return  the person stored in the node
         */
        public Person getPerson()
        {
            return person;
        }

        /** 
         * Getter for the left child of a node
         * 
         * @return  the left child of the node
         */
        public BSTNode getLChild()
        {
            return leftC;
        }

        /** 
         * Getter for the right child of a node
         * 
         * @return  the right child of the node
         */
        public BSTNode getRChild()
        {
            return rightC;
        }

        /** 
         * Set the left child of the node
         *  
         * @param  p  new left child
         */
        public void setLChild(BSTNode newLChild)
        {
            leftC = newLChild;
        }

        /** 
         * Set the right child of the node
         *  
         * @param  p  new right child
         */
        public void setRChild(BSTNode newRChild)
        {
            rightC = newRChild;
        }
    }

    BSTNode Root = null; //the root node of the binary search tree
    int size=0; // size of the tree
    int leaf=0; // leaves in the tree
    int levelCount = 0; // the leaves in a level
    Person[] persons; // person array for to print

    /** 
     * Get the root of a tree 
     * 
     * @return  the Root of a tree
     */ 
    public BSTNode getRoot()
    {
        return Root;
    }

    /** 
     * Add a node to the correct position in a binary search tree 
     * 
     * @param  name   the name of the person of the node to add
     * @param  key   the key of the person of the node to add
     * @throws IllegalArgumentException
     */ 
    public void insert(String name, int key) throws IllegalArgumentException
    {
        // Check for exception if name is empty or key out of range
        if(name.equals("") || name == null || key<1 || key>200){
            throw new IllegalArgumentException();
        }

        Person p = new Person(name, key); // create a new person with given name and key
        if(Root == null){ // If tree is empty set root to the new node
            Root = new BSTNode(null,p,null);
        }
        else{ // Otherwise find correct position and add to tree
            addHelper(Root,p);
        }
        size++; // plus 1 to size
    }

    /** 
     * Helper method for the insert method to find correct postion in the tree 
     * Recursive method
     * 
     * @param  root   the current node 
     * @param  p   the person to be stored in the new node
     */
    private void addHelper(BSTNode root, Person p) 
    {
        if(p.key<root.person.key){ // If node to add smaller than perent
            if(root.getLChild() == null){ // If no left child
                root.setLChild(new BSTNode(null,p,null));// add the new node to be the left child
            }
            else{ // Otherwise set current node to the left child and do recursion
                addHelper(root.getLChild(),p); 
            }
        }
        else if(p.key>root.person.key){ // If node to add larger than the parent
            if(root.getRChild() == null){ // If no right child
                root.setRChild(new BSTNode(null,p,null)); // add the new node to the right child
            }
            else{ // Otherwise set current node to the right child and do recursion
                addHelper(root.getRChild(),p);
            }
        }
        else{ // If equal, then is a duplicate and set to the right child
            root.setRChild(new BSTNode(null,p,null));
        }
    }

    /** 
     * check if a person is in a binary search tree
     * 
     * @param  key   the key of the person of the node to search
     * @param  name   the name of the person of the node to search
     * @return  true if the person exist false if deos not
     * @throws IllegalArgumentException
     */ 
    public boolean findPerson(int key, String name) throws IllegalArgumentException
    {
        // Check for exception if name is empty or key out of range
        if(name.equals("")||name==null||key<1||key>200){
            throw new IllegalArgumentException();
        }

        // If tree empty then person does not exist
        if(Root == null)
        {
            return false;
        }

        return findHelper(Root, key, name);//us helper method to find the person
    }

    /** 
     * Helper method for the find person method to search at correct postion in the tree 
     * Recursive method
     * 
     * @param  root   the current node 
     * @param  key   the key of the person of the node to search
     * @param  name   the name of the person of the node to search
     * @return  true if the person exist false if deos not
     */
    private boolean findHelper(BSTNode root, int key, String name)
    {
        if(root == null) // If current node is null then reached end of the tree and return false
        {
            return false;
        }

        if(key<root.person.key){ // If key of person to find is less than current node
            return findHelper(root.getLChild(), key, name); // Set the current node to left child and continue to search
        }
        else if(key>root.person.key){// If key of person to find is greater than current node
            return findHelper(root.getRChild(), key, name);// Set the current node to right child and continue to search
        }
        else{ // If we found a equal value key
            if(name.equals(root.person.name)){ // chekc if name is also the same
                return true; // Then we found the person
            }
            else{ // otherwise continue to search and set current node to right child
                return findHelper(root.getRChild(), key, name);
            }
        }
    }

    /** 
     * method to delete a node from the tree
     * 
     * @param  key   the key of the person of the node to delete
     * @param  name   the name of the person of the node to delete
     * @return  the person of the node deleted
     * @throws IllegalArgumentException
     */
    public Person delete(int key, String name)
    {
        // If empty name or key out of bound throw exception
        if(name.equals("")||name.equals(null)||key<1||key>200||!findPerson(key, name)){
            throw new IllegalArgumentException();
        }

        if(FindLevel()<0){
            throw new IllegalArgumentException();
        }
        if(FindLevel()==0){ // If tree only has one node
            Person p = Root.person;
            Root = null; // set Root to null
            return p; // return person of the Root
        }
        else if(FindLevel()==1){ // If tree has levels 0 and 1
            if(Root.person.key<key){ // If key greater than Root, right child becomes null
                Person p = Root.getRChild().person;
                Root.setRChild(null);
                return p;
            }
            else if(Root.person.key>key){ //if key less than Root, left child becomes null
                Person p = Root.getLChild().person;
                Root.setLChild(null);
                return p;
            }
            else{ // if key equal to node
                if(Root.getLChild()!=null) // if have left child, left child becomes root and delete left child
                {
                    Person p = Root.getLChild().person;
                    Root = Root.getLChild();
                    delete(p.key, p.name);
                    return p;
                }
                else{ // if have right child, right child becomes root and delete right child
                    Person p = Root.getRChild().person;
                    Root = Root.getRChild();
                    delete(p.key, p.name);
                    return p;
                }
            }
        }
        else{
            return Root.person;
        }
    }

    /** 
     * Helper method for the delete method to reorganize the tree after deletion
     * Recursive method
     * 
     * @param  parent   the parent node of current node
     * @param  key   the key of the person of the node to delete
     * @param  name   the name of the person of the node to delete
     * @return  the person of the node deleted
     */
    private Person deleteHelper(BSTNode parent, int key, String name)
    {
        if(key<parent.person.key){//if key is less than parent
            BSTNode child = parent.getLChild();//left child is our current node
            if(key<child.person.key){ //if key is less than child
                return deleteHelper(child, key, name);//recurse
            }
            else if(key>child.person.key){ //if key is greater than child
                return deleteHelper(child, key, name);//recurse
            }
            else{ // if key is equal to child
                if(child.getLChild() != null && child.getRChild() != null){ // if child has two children
                    BSTNode min = child.getRChild(); // create a minimue node 
                    while(min.getLChild()!=null) // set min to be the minimum child on right side of the child
                    {
                        min = min.getLChild();
                    }
                    parent.setLChild(min);// set the child to min
                    delete(min.person.key, min.person.name);//delete the min
                }
                else if(child.getLChild() == null && child.getRChild() == null){ // if child has no children parent sets child to null
                    parent.setLChild(null);
                }
                else if(child.getLChild() == null){ // if child has right child only
                    parent.setLChild(child.getRChild()); // parent set child to right child of child
                }
                else{// if child has left child only
                    parent.setLChild(child.getLChild()); // parent set child to left child of child
                }
                return child.person; // return the deleted child
            }
        }
        else if(key>parent.person.key){ //if key is greater than parent, do the same to the right side
            BSTNode child = parent.getRChild();
            if(key<child.person.key){
                return deleteHelper(child, key, name);
            }
            else if(key>child.person.key){
                return deleteHelper(child, key, name);
            }
            else{
                if(child.getLChild() != null && child.getRChild() != null){
                    BSTNode min = child.getRChild();
                    while(min.getLChild()!=null)
                    {
                        min = min.getLChild();
                    }
                    parent.setRChild(min);
                    delete(min.person.key, min.person.name);
                }
                else if(child.getLChild() == null && child.getRChild() == null){
                    parent.setRChild(null);
                }
                else if(child.getLChild() == null){
                    parent.setRChild(child.getRChild());
                }
                else{
                    parent.setRChild(child.getLChild());
                }
                return child.person;
            }
        }
        else{ // If we want to delete the Root (special case)
            BSTNode max = parent.getLChild(); // create max variable
            while(max.getRChild()!=null) // set max to the largest value of the left side of Root
            {
                max = max.getRChild();
            }
            Person p = Root.person;
            Root = max; // set Root to max
            delete(max.person.key, max.person.name);// delete max
            return p;
        }
    }

    /** 
     * prints the tree
     * 
     * @param  root  the root in which we print everything under the root in the tree
     * @return  and array of person in the tree
     * @throws NullPointerException
     */ 
    public Person [] printToArray(BSTNode root)
    {
        if(root == null){ // Check for exception if root is null
            throw new NullPointerException();
        }
        ArrayList<Person> temp = new ArrayList<Person>(); //create arraylist for persons so easier to add
        inorderTraverse(root,temp); // add persons into arraylist in order traversal
        persons = new Person[temp.size()];// create array with same size as the arraylist
        for(int i = 0; i < temp.size(); i++){ // copy the arraylist into the array
            persons[i] = temp.get(i);
        }
        return persons; // return the array with persons
    }

    /** 
     * Helper method for the print to array method to print in order traversal
     * Recursive method
     * 
     * @param  root   the current node 
     * @param  temp   the temporary arraylist to store the persons
     */
    private void inorderTraverse(BSTNode root, ArrayList<Person> temp)
    {
        if (root == null){ // end if current node is null, which means end of tree
            return;
        }
        inorderTraverse(root.getLChild(), temp); // first go dwon the left side of the tree
        System.out.println(root.person.name+" "+root.person.key); // print the person`s name and key
        temp.add(root.getPerson()); // add the cureent node to the arraylist
        inorderTraverse(root.getRChild(),temp); // go down the right side of the tree last
    }

    /** 
     * Find the depth of a given node
     * 
     * @param  root   the node to which we determine its depth 
     * @return  the depth of the given node
     */
    public int FindDepth(BSTNode root)
    {
        if(Root == null || root == null){ // if tree is empty or invalid root, return -1
            return -1;
        }
        int counter = FindLevel(); // create counter to be the number of levels in the tree
        // Count the number of level from the given root to the bottom level and use total level to minus it
        while(root.getLChild() != null || root.getRChild() != null) // As long as the root has child
        {
            if(root.getLChild() == null){ //if not left child
                root = root.getRChild(); // set root to its right child
            }
            else{ // otherwise set root to its left child
                root = root.getLChild();
            }
            counter--;  // update counter
        }
        return counter; 
    }

    /** 
     * Find the total numebr of level of a tree
     * 
     * @return  the total number of level of a tree
     */
    private int FindLevel()
    {
        int counter =0; // create counter
        BSTNode temp = Root; // set temporary node to the Root of the tree
        while(temp.getLChild() != null || temp.getRChild() != null){ // if root has any children
            if(temp.getLChild() == null){ // if no left child exist
                temp = temp.getRChild(); // set temp to its right child
            }
            else{ // otherwise set it to its left child
                temp = temp.getLChild();
            }
            counter++; // update the counter
        }
        return counter;
    }

    /** 
     * Find total number of leaves in a tree
     * 
     * @return  the leaves in the tree
     */
    public int leafCount( ){
        leaf=0;//reset leaf to be 0
        if(Root == null){ // if tree is empty then no leaves
            return 0;
        }
        return leafCountHelper(Root); // use helper method to determine the number of leaves
    }

    /** 
     * Helper method for leafCount method to find the number of leaves
     * Recursive method
     * 
     * @param  root   the current node
     * @return  the leaves in the tree
     */
    private int leafCountHelper(BSTNode root){
        if(root.getLChild() == null && root.getRChild() == null){ //if cuttent node is a leaf
            leaf++; // update leaf counter
        }
        else if(root.getLChild() == null ){ // if only has right child
            leafCountHelper(root.getRChild()); //continue the count setting root to its right child
        }
        else if (root.getRChild() == null){// if only has left child
            leafCountHelper(root.getLChild());//continue the count setting root to its left child
        }
        else{ // if both child exist then conitue the count setting root to its left and its right child
            leafCountHelper(root.getLChild());
            leafCountHelper(root.getRChild());
        }
        return leaf; 
    }

    /** 
     * Find the number of nodes at a level
     * 
     * @param  level   the level to find number of nodes
     * @return  the number of nodes in the given level
     */
    public int levelCount(int level)
    {
        levelCount = 0; // reset level count to zero
        // If tree empty, or level does not exist, return -1
        if(Root == null || level < 0||level>FindLevel()){ 
            return -1;
        }
        levelCountHelper(level, Root); // use helper method to count
        return levelCount;
    }

    /** 
     * Helper method of levelCount method to find number of nodes in a level
     * Recursive method
     * 
     * @param  level   the level we count nodes in
     * @param  root   the current node
     */
    private void levelCountHelper(int level, BSTNode root)
    {
        if(level==0){ // If in correct level, update level count
            levelCount++;
        } 
        // If root has two childen, set root to both left child and right child, update level with minus 1
        if(root.getLChild() != null && root.getRChild() != null){
            levelCountHelper(level-1, root.getLChild());
            levelCountHelper(level-1, root.getRChild());
        }
        // If root has only right child, set root to right child, update level with minus 1
        else if(root.getLChild() == null&& root.getRChild() != null){
            levelCountHelper(level-1, root.getRChild());
        }
        // If root has only left child, set root to left child, update level with minus 1
        else if (root.getRChild() == null&& root.getLChild() != null){
            levelCountHelper(level-1, root.getLChild());
        }
        else{ // if root has no children then return
            return;
        }
    }
}