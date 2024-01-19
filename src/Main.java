import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        //region array a
        Array myArrayA = new Array(7);
        myArrayA.add(1.5);
        myArrayA.add(2.0);
        myArrayA.add(2.6);
        myArrayA.add(2.9);
        myArrayA.add(3.0);
        myArrayA.add(3.4);
        myArrayA.add(3.6);
        //endregion
        //region array b
        Array myArrayB = new Array(7);
        myArrayB.add(1.0);
        myArrayB.add(2.2);
        myArrayB.add(3.4);
        myArrayB.add(3.9);
        myArrayB.add(4.4);
        myArrayB.add(6.4);
        myArrayB.add(6.6);
        //endregion
        //region searching and timing
        System.out.println("Linear search for array a");
        long startTime = System.nanoTime();
        System.out.println(myArrayA.linearSearch(3.4));
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Took " + totalTime + " nanoseconds");
        System.out.println("Binary search for array a");
        startTime = System.nanoTime();
        endTime = System.nanoTime();
        System.out.println("It took "+ myArrayA.binarySearch(3.4) + " steps");
        totalTime = endTime - startTime;
        System.out.println("Took " + totalTime + " nanoseconds");
        System.out.println("Linear search for array b");
        startTime = System.nanoTime();

       System.out.println(myArrayB.linearSearch(3.4));
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Took " + totalTime + " nanoseconds");
        System.out.println("Binary search for array b");
        startTime = System.nanoTime();
        System.out.println("It took "+ myArrayB.binarySearch(3.4) + " steps");
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Took " + totalTime + " nanoseconds");
        //endregion
    }
}
//i am using the same array that i made for the homework 1
class Array{
    private int size;
    private int lastIndex;
    private Double[] dataArray;
    private int currentRightMostItem = 0;
    private boolean hasAdded = false;


    public Array(int startSize){
        this.size = startSize;
        this.lastIndex = startSize - 1;
        this.dataArray = new Double[startSize];

    }
    public void add(double data){
        //add data by storing in a temp array which is a clone of the current array and creating a new array that is one size bigger than the size
        //this is very inefficient, but I wasn't sure how to do it otherwise.
        if (currentRightMostItem + 1 > lastIndex){
            Double[] tempArray = dataArray.clone();
            Double[] newArray = new Double[size + 1];
            for(int i = 0; i < size; i++){
                newArray[i] = tempArray[i];
            }
            newArray[size] = data;
            size ++;
            currentRightMostItem = size;
            lastIndex = size-1;
            //finally set the instance array to the new array
            dataArray = newArray;

        }
        else {
            if(hasAdded){
                dataArray[currentRightMostItem + 1] = data;
                currentRightMostItem ++;
            }
            else{
                dataArray[currentRightMostItem] = data;
                hasAdded = true;
            }
        }

    }
    public void add(double data,int index) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException();// i think this is the correct exception
        } else {
            if (currentRightMostItem + 1 > lastIndex) {
                Double[] newArray = Arrays.copyOf(dataArray,dataArray.length + 1);
                for (int i = size - 1; i >= index; i--) {
                    newArray[i + 1] = newArray[i];
                }
                newArray[index] = data;
                size++;
                currentRightMostItem = size;
                lastIndex = size - 1;
                //finally set the instance array to the new array
                dataArray = newArray;

            }
            else{
                Double[] reference = Arrays.copyOf(dataArray,dataArray.length);
                for (int i = size - 1; i > index;i--){
                    reference[i + 1] = reference[i];
                }
                reference[index] = data;
                dataArray = reference;
                currentRightMostItem ++;
            }
        }
    }
    public void resize(int newSize){
        Double[] newArray = Arrays.copyOf(dataArray,newSize);
        dataArray = newArray;
    }


    public int getSize() {
        return size;
    }
    public void printArray(){
        for (Object o : dataArray){
            System.out.println(o);
        }
    }
    public void set(double data,int index){
        dataArray[index] = data;
    }
    public Object get(int index){
        return dataArray[index];
    }
    public void remove(int index){
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        else {
            Double[] tempArray = Arrays.copyOf(dataArray,dataArray.length);
            Double[] newArray = new Double[dataArray.length - 1];

            for (int i = 0; i < index; i++){
                newArray[i] = tempArray[i];
            }
            for (int i = index; i < size-1; i++){
                newArray[i] = tempArray[i+1];
            }
            size--;
            currentRightMostItem--;
            dataArray = newArray;
        }

    }
    public int linearSearch(double target){
        int count = 0;
        int i = 0;
        while (dataArray[i] != target){
            i++;
            count++;
        }
        return count;
    }
    public int binarySearch(double target){
        int lowIndex = 0;
        int highIndex = dataArray.length - 1;
        int midIndex;
        int count = 1;
        while (lowIndex <= highIndex){
            midIndex = (lowIndex + highIndex)/2;
            if (dataArray[midIndex] == target){
                return count;
            } else if (dataArray[midIndex] > target) {
                highIndex = midIndex - 1;
                count++;
            } else if (dataArray[midIndex] < target) {
                lowIndex = midIndex +1;
                count++;
            }
        }
        return -1;

    }

}
