package Labs.Lab1;

/** 
  Determines if three doubles can be sides of triangle. 
*/
public class Triangle  {

   /** Length of side 1. */
   private double sideA;

   /** Length of side 2. */
   private double sideB;

   /** Length of side 3. */
   private double sideC;

   /**
    * Creates a Triangle.
    *
    * @param aIn length of side 1.
    * @param bIn length of side 2.
    * @param cIn length of side 3.
    */
   public Triangle(double aIn, double bIn, double cIn) {
      
      if (aIn <= 0 || bIn <= 0 || cIn <= 0) {
         throw new IllegalArgumentException("Sides: " + aIn + " " + bIn + " " + cIn
            + " -- do not contain values that add up to a triangle");       // add an explanation why this exception is being thrown.
      }
     
      if ((aIn >= bIn + cIn) || (bIn >= aIn + cIn) || (cIn >= aIn + bIn)) {
         throw new IllegalArgumentException("Sides: "
            + aIn + " " + bIn + " " + cIn
            + " -- 2 shorter sides of a triangles must be less than or equal to the longest side");      // add an explanation why this exception is being thrown.
      }
     
      sideA = aIn;
      sideB = bIn;
      sideC = cIn;
   }

   /**
    *  Classifies a triangle based on the lengths of the three sides.
    *  The classifications are either: "equilateral", "scalene", "isosceles", 
    *  or "not a triangle". The returned string must match one of these.
    * 
    * @return the triangle classification.
    */
   public String classify() {
      String result = "";
      if (sideA == sideB && sideB == sideC) {
         result = "equilateral";
      } else if (sideA != sideB && sideB != sideC && sideA != sideC) {
         result = "scalene";
      } else if (sideA == sideB || sideB == sideC || sideC == sideA) {
         result = "isosceles";
      } else {
         result = "not a triangle";
      }
      return result;
   }
}
