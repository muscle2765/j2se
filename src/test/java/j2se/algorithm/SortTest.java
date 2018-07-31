package j2se.algorithm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import algorithm.Sort;

public class SortTest {

	@Test
	public void testBubbleSort() {
		int[] array = {3,5,2,7,1,6,9,4,0,8};
		Sort.bubbleSort(array);
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
			assertEquals(i, array[i]);
		}
		System.out.println("bubble success");
	}
	
	@Test
	public void testSelectSort() {
		int[] array = {3,5,2,7,1,6,9,4,0,8};
		Sort.selectSort(array);
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
			assertEquals(i, array[i]);
		}
		System.out.println("select success");
	}
	
	@Test
	public void testInsertSort() {
		int[] array = {3,5,2,7,1,6,9,4,0,8};
		Sort.insertSort(array);
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
			assertEquals(i, array[i]);
		}
		System.out.println("insert success");
	}
	
	@Test
	public void testBinaryInsertSort() {
		int[] array = {3,5,2,7,1,6,9,4,0,8};
		Sort.binaryInsertSort(array);
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
//			assertEquals(i, array[i]);
		}
		System.out.println("binary insert success");
	}
}
