package Labs.Lab12;

public class Card
{
    private String name;

    public Card()
    {
        name = "";
    }

    public Card(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return "Card Holder: " + name;
    }
}
