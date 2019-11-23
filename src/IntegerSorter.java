/*Author: Danyang Wang
 * Class: ICS4U
 * Instructor: Mr Radulovich
 * Assignment name: Comparing Sorting Algorithms Assignment
 * Date: November 6th, 2019
 * Description: This class includes the 3 sort methods, method for reading from a file, and completes
 * the Sorter interface.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IntegerSorter implements Sorter{
	
	/* General Procedures to Test the Program:
	1) use setList(int[] list) method to copy the elements from the parameter into the main array, 
	OR use readFile(String File) method to read the 2power txt files and store them into the 
	main array 
	2) call the sort(int type) method to test the corresponding sort methods 
    */
	
	
	private int[] main_array;	/* updated when using setList(int []) method or readFile(String file)
	method. This is the main array that stores all the elements */ 
	
	private int numOfElements;	// stores the number of elements in the file/list
	
	/* reads the numbers from the 2power txt files and store them in an array, the parameter is
	the file name */
	public void readFile(String file) {
		File input = new File(file);
		numOfElements = 0;
		int i = 0;
		try {
			Scanner scan = new Scanner(input);
			
			// counts the number of integers within the file
			while (scan.hasNextInt()) {
				scan.nextInt();
				numOfElements++;
			}
			
			scan.close();
			Scanner scanner = new Scanner(input);
			
			// create an array corresponding to the number of integers in the file
			main_array = new int[numOfElements];
			
			// reads the integers within the file and store them in the array
			while (scanner.hasNextInt()) {
				main_array[i] = scanner.nextInt();
				i++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("Invalid File");
			e.printStackTrace();
		}
	}
	
	// takes two index as parameter and swap the values of the two index inside the array
	public void swap(int a, int b) {
		int temp = main_array[a];
		main_array[a] = main_array[b]; 
		main_array[b] = temp;
	}
	
	// implements method 1
	/* compares two adjacent numbers, and swapping them if the first number is larger than  
	the second number */
	public void sort_method1() {
		for (int j=main_array.length-1;j>=0;j--) {
			for (int i=1;i<=j;i++) {
				if (main_array[i-1] > main_array[i]) {
					swap(i-1,i);
				}
			
			}
		}
	}
	
	//implements method 2
	/* start from the beginning of the list, and move down one element at a time. Swap the elements,
	 if the second element is smaller */
	public void sort_method2() {	
		for (int j=0;j<main_array.length;j++) {
			for (int i=j+1;i<main_array.length;i++) {
				if (main_array[j] > main_array[i]) {
					swap(j, i);
				}
			}
		}
	}
	
	//implements method 3
	/* Start by splitting the array to a bunch of single element arrays, then combine two arrays
	 * at a time, by selecting the smaller element in the two arrays each time
	 */
	public void sort_method3(int low, int high) {
		if (low < high) {
			int mid = (low + high) / 2;
			
			// using recursion to split the array
			sort_method3(low, mid);
			sort_method3(mid+1, high);
			
			// combining the split arrays
			combineArr(low,high);
		}
	}
	
	// Takes the leftmost index and the rightmost index, and combine two arrays 
	public void combineArr(int left, int right) {
		
		// the middle index
		int mid = (left+right)/2;
		
		// size of the two arrays
	    int size1 = mid - left + 1; 
	    int size2 =  right - mid; 
	  
	    // create temporary arrays to store the elements
	    int[] L = new int[size1];
	    int[] R = new int[size2]; 
	  
	    // copy data to the temporary arrays
	    for (int i = 0; i < size1; i++) {
	        L[i] = main_array[left + i]; 
	    }
	    for (int j = 0; j < size2; j++) {
	        R[j] = main_array[mid + 1+ j]; 
	    }
	    
	  
	    int i = 0; // tracks index of first temporary array (int [] L)
	    int j = 0; // tracks index of second temporary array (int [] R)
	    int k = left; // index of the main array 
	    
	    // merges the temporary arrays back into the main array 
	    while (i < size1 && j < size2) 
	    { 
	        if (L[i] <= R[j]) 
	        { 
	            main_array[k] = L[i]; 
	            i++; 
	        } 
	        else
	        { 
	            main_array[k] = R[j]; 
	            j++; 
	        } 
	        k++; 
	    } 
	  
	    // copy the remaining elements of L[]
	    while (i < size1) 
	    { 
	        main_array[k] = L[i]; 
	        i++; 
	        k++; 
	    } 
	  
	    // copy the remaining elements of R[]
	    while (j < size2) 
	    { 
	        main_array[k] = R[j]; 
	        j++; 
	        k++; 
	    } 
	}
	
	// checks if the array is correctly sorted by returning a boolean
	public boolean checkSorted() {
		for (int i=1;i<main_array.length;i++) {
			if (main_array[i] < main_array[i-1]) {
				return false;
			}
		}
		return true;
	}

	/* takes an integer list as parameter, and sets the array in this class to contain the same
       elements as the list passed in as parameter */
    @Override
    public void setList(int[] list){
    	numOfElements = list.length;
        this.main_array = list;
    }

    // returns the integer array in this class 
    @Override
    public int[] getList(){
        return this.main_array;
    }

    // takes an integer as input, and selects the corresponding sort method based on the input
    @Override
    public void sort(int type){
        if (type == 1) sort_method1();
        if (type == 2) sort_method2();
        if (type == 3) sort_method3(0, main_array.length-1);
    }

    // prints out all elements inside the array as a string
    @Override
    public String toString(){
        String ans = "";
        for (int i = 0; i<main_array.length;i++) {
        	ans += main_array[i] + ", ";
        }
        return ans;
    }
}
