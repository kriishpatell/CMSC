package Labs.Lab12;

import java.sql.Driver;

public class DriversLicense extends IDCard
{
    private int expirationYear;
    private Month expirationMonth;

    public DriversLicense()
    {
        super();
        expirationYear = 1969;
        expirationMonth = Month.JANUARY;
    }

    public DriversLicense(String name, int idNumber, int expirationYear, Month expirationMonth)
    {
        super(name, idNumber);
        this.expirationYear = expirationYear;
        this.expirationMonth = expirationMonth;
    }

    public String toString()
    {
        return super.toString() + " Expiration Month & Year: " + expirationMonth + " " + expirationYear;
    }
}
