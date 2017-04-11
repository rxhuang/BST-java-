/**
 *  NAME: Ruoxin Huang
 *  ID: A99084753
 *  LOGIN: cs12whl
 * */
/**
 *  Title: class Person
 *  Description: creates a person object
 *  @author Ruoxin Huang
 *  @version 1.0
 *  @since 02-28-2016
 * */
public class Person 
{
    String name;
    int key;
    /**
     * Constructor for person class
     * 
     * @param  name   the name of the person
     * @param  key   the key of the person
     */
    public Person(String name, int key)
    {
        this.name = name;
        this.key = key;
    }

    /**
     * Setter method for name of person
     * 
     * @param  name   the new name to be set to
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Getter method for name of person
     * 
     * @return  the name of the person
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Getter method for key of person
     * 
     * @return  the key of the person
     */
    public int getKey()
    {
        return key;
    }
}
