package algorithm;

public class Sort {
	
	public static int[] bubbleSort(int[] array){
		
		for(int i=array.length-1;i>=0;i--){
			
			for(int j=0;j<i;j++){
				
				if(array[j]>array[j+1]){
					int temp = array[j];
					array[j]=array[j+1];
					array[j+1]=temp;
				}
			}
		}
		
		return array;
	}
	
	public static int[] selectSort(int[] array){
		
		for(int i=0;i<array.length;i++){
			for(int j=i+1;j<array.length;j++){
				if(array[i]>array[j]){
					int temp = array[j];
					array[j]=array[i];
					array[i]=temp;
				}
			}
		}
		
		return array;
	}
	
	public static int[] insertSort(int[] array){
		
		for(int i=0;i<array.length;i++){
			int key = i;
			while( key>0 && array[key]<array[key-1]){
				int temp = array[key];
				array[key] = array[key-1];
				array[key-1] = temp;
				key--;
			}
		}
		
		return array;
	}
	
	public static int[] binaryInsertSort(int[] array){		
		
		for(int i=1;i<array.length;i++){
			int key = array[i];
			int high = i-1;
			int low = 0;		
			
			while(low<=high){
				int middle = (low+high)/2;
				if(key<array[middle]){
					high = middle-1;
				}else{
					low = middle+1;
				}
			}
			
			for(int j=i-1;j>high;j--){
				array[j+1]=array[j];
			}
			array[high+1]=key;
		}
		
		return array;
	}	
}
