package Labs.Lab9;

import java.util.Arrays;

public class method {
    public static void main(String[] args)
    {
        int [] nums = {1, 2, 3, 4};
        performAction(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void performAction(int [] data)
    {
        for(int i = 0; i < data.length; i++)
        {
            data[i] = i*5;
        }
    }
}
