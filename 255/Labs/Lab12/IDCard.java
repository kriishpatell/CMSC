package Labs.Lab12;

public class IDCard extends Card
{
    private int idNumber;

    public IDCard()
    {
        super("Jane Smith");
        idNumber = 0;
    }

    public IDCard(String name, int idNumber)
    {
        super(name);
        this.idNumber = idNumber;
    }

    public String toString()
    {
        return super.toString() + " ID Number: " + idNumber;
    }
}
