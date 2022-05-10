package Labs.Lab11;

public class Book {
    private String title;
    private String author;

    /*********************************************************************************
     * This is the default constructor for the class, which initializes the instance
     * variables title and author to their respect default values of "Test" and null
     *********************************************************************************/
    public Book()
    {
        title = "CMSC256/Test";
        author = null;
    }

    /*********************************************************************************
     * This constructor reassigns the values for the instance variables to the ones
     * that are given in the main method
     *********************************************************************************/
    public Book(String title, String author)
    {
        this.title = title;
        this.author = author;
    }

    /****************************************************
     * This method returns the value of the String title
     ****************************************************/
    public String getTitle()
    {
        return title;
    }

    /********************************************************************************
     * This method reassigns the value of the instance variable title to the one
     * assigned in the main method
     ********************************************************************************/
    public void setTitle(String title)
    {
        this.title = title;
    }

    /****************************************************
     * This method returns the value of the String author
     ****************************************************/
    public String getAuthor()
    {
        return author;
    }

    /********************************************************************************
     * This method reassigns the value of the instance variable author to the one
     * assigned in the main method
     ********************************************************************************/
    public void setAuthor(String author)
    {
        this.author = author;
    }

    /********************************************************************************
     * This method returns the books' information, given the values of title and author
     ********************************************************************************/
    public String toString()
    {
        return "\"" + this.title + "\"" + " by " + this.author;
    }
}
