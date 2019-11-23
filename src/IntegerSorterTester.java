/*Author: Danyang Wang
 * Class: ICS4U
 * Instructor: Mr Radulovich
 * Assignment name: Comparing Sorting Algorithms Assignment
 * Date: November 6th, 2019
 * Description: This class tests the sort methods from IntegerSorter class, and records the runtime
 */

public class IntegerSorterTester {

	public static void main(String[] args) {
		
	/* General Procedures to Test the Program:
		1) use setList(int[] list) method to copy the elements from the parameter into the main array, 
		OR use readFile(String File) method to read the 2power txt files and store them into the 
		main array 
		2) call the sort(int type) method to test the corresponding sort methods 
	*/
			long startTime, endTime;
			IntegerSorter s = new IntegerSorter();
		
		    s.readFile("2power7.txt");
		    
			startTime = System.nanoTime();
			s.sort(3);
			endTime = System.nanoTime();
			
			//print runtime in seconds
			System.out.println("Time: "+(endTime-startTime)/1e9);
		    //System.out.println(s.checkSorted());
			//System.out.println(s.toString());
		
	}

}

