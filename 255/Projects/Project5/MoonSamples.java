/****************************************************************************
 * Moon Samples
 ****************************************************************************
 * Determines the best areas to support life on Mars given elements present
 * and how much of each value exists
 ****************************************************************************
 * Krish Patel
 * 10/26/2021
 * CMSC 255 Section 004
 ****************************************************************************/

package Labs.Project5;

import java.util.Scanner;

public class MoonSamples
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        String elementString = sc.nextLine();
        String samplesString = sc.nextLine();

        String[] elements = getElements(elementString);
        double[][] samples = getSamples(samplesString);
        int[] life = searchForLife(samples);

        System.out.print("The samples that contain life are: ");
        for(int i=0;i<life.length;i++){
            System.out.print(life[i]+" ");
        }
        System.out.println();
        System.out.println("The highest elements for sample 1 are " + searchHighestElements(samples, elements, 1));
        System.out.println("The sample with the highest value of the element water is " + searchHighestSample(samples, elements, "water"));
    }

    /***************************************************************
     * Returns the string array of the inputted element string,
     * creating a new index for array based on division by commas
     ***************************************************************/
    public static String[] getElements(String inputElementString)
    {
        String[] elements = inputElementString.split(",");
        return elements;
    }

    /****************************************************************************
     * Returns the 2D array for samples given the inputted string, creating
     * a new row for the array at each "<>" and new index (column) at each comma
     ****************************************************************************/
    public static double[][] getSamples(String inputSampleString)
    {
        String[] rows = inputSampleString.split("<>");
        double[][] samples = new double[rows.length][];

        for(int i = 0; i < rows.length; i++)
        {
            String[] values = rows[i].split(",");

            double a = Double.parseDouble(values[0]);
            double b = Double.parseDouble(values[1]);
            double c = Double.parseDouble(values[2]);
            double d = Double.parseDouble(values[3]);
            double e = Double.parseDouble(values[4]);
            double f = Double.parseDouble(values[5]);

            double[] value = {a, b, c, d, e, f};

            samples[i] = value;
        }
        return samples;
    }

    /****************************************************************************************
     *  Given the inputted double array of samples, evaluates a value for each row/sample,
     *  and determines whether that value is greater than the criteria for life value (350).
     *  Returns the indexes of the array which pass the criteria for life value
    *****************************************************************************************/
    public static int[] searchForLife(double [][] samples)
    {
        double [] criteriaValues = new double[samples.length];
        int count = 0;
        for(int i = 0; i < samples.length; i++)
        {
            double value = (8 * samples[i][0]) + (2 * samples[i][1]) + (4 * samples[i][3]) + samples[i][4] + (5 * samples[i][5]);
            criteriaValues[i] = value;
            if(value >= 350)
            {
                count++;
            }
        }
        int[] result = new int[count];
        int k = 0;
        for(int i = 0; i < criteriaValues.length; i++)
        {
            if(criteriaValues[i] >= 350)
            {
                result[k++] = (i+1);
            }
        }
        return result;
    }

    /***************************************************************
     * Searches the inputted arrays for each row, and returns the
     * corresponding greatest two values of element of that sample
     ***************************************************************/
    public static String searchHighestElements(double[][] samples, String[] elements, int sampleNum)
    {
        int highest = 0;
        int index1 = -1, index2 = -1;
        int secondHighest = 0;
        for (int i = 0; i < samples[sampleNum - 1].length; i++) {
            if (samples[sampleNum-1][i] > samples[sampleNum-1][highest]) {
                secondHighest = highest;
                highest = i;
            }
            else if(samples[sampleNum-1][i] > samples[sampleNum-1][secondHighest]){
                secondHighest=i;
            }
        }
        return elements[highest] + " and " + elements[secondHighest];
    }

    /********************************************************************
     * Searches the index of the array given the element, and searches
     * each sample for the highest value of that element and returns it
     ********************************************************************/
    public static int searchHighestSample(double[][] samples, String[] elements, String element)
    {
        double max = -1;
        int highIndex = 0;
        int index= 1;
        int value=0;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].equals(element)) {
                index=i;
            }
        }

        for (int i = 0; i < samples.length; i++) {
            if (samples[i][index]>samples[highIndex][index]) {
                highIndex=i;
            }
        }
        highIndex++;
        return highIndex;
    }
}