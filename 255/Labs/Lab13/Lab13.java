package Labs.Lab13;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Lab13
{
    public static void processFile(File inputFile, File outputFile) throws FileNotFoundException
    {
        Team team;
        Team minTeam = null;
        Team maxTeam = null;
        double totalShots = 0;
        int numTeams = 0;
        String line;

        Scanner fileScan = new Scanner(inputFile);
        PrintWriter writer = new PrintWriter(outputFile);

        while(fileScan.hasNextLine())
        {
            line = fileScan.nextLine();
            String[] fields = line.split(",");

            team = new Team(fields[0],Integer.parseInt(fields[1]), Double.parseDouble(fields[2]));

            if(minTeam == null || minTeam.getNumGoals() > team.getNumGoals())
            {
                minTeam = team;
            }

            if(maxTeam == null || maxTeam.getNumGoals() < team.getNumGoals())
            {
                maxTeam = team;
            }

            numTeams++;
            totalShots += team.getNumShots();
        }

        fileScan.close();

        if(numTeams > 0)
        {
            writer.println("Maximum goals Scored: " + maxTeam.getName() + " " + maxTeam.getNumGoals());
            writer.println("Minimum goals Scored: " + minTeam.getName() + " " + minTeam.getNumGoals());
            writer.printf("Average shots per game: %.3f", (totalShots/numTeams));
        }
        writer.close();
    }

    public static void main(String[] args)
    {
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);

        try
        {
            processFile(inputFile, outputFile);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Unable to open file(s)");
        }
    }
}
