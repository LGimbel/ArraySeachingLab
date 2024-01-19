public class TimingTesting {
    public static void main(String[] args) {
        Array myArrayB = new Array(7);
        myArrayB.add(1.0);
        myArrayB.add(2.2);
        myArrayB.add(3.4);
        myArrayB.add(3.9);
        myArrayB.add(4.4);
        myArrayB.add(6.4);
        myArrayB.add(6.6);
        long maxTime = 0;
        long minTime = 1000000000;
        long allTime = 0;
        long averageTime;
        long endTime;
        long startTime;
        long thisTime;
        int iterations = 0;
        int goal = 10000;
        for(int i = 0; i < goal; i++){
             startTime = System.nanoTime();
            myArrayB.linearSearch(3.4);
            endTime = System.nanoTime();
            thisTime = endTime - startTime;
            maxTime = Math.max(thisTime, maxTime);
            minTime = Math.min(thisTime, minTime);
            allTime += thisTime;
            iterations++;
        }
        averageTime = allTime/iterations;
        System.out.println("Average time: " + averageTime);
        System.out.println("Max time: " + maxTime);
        System.out.println("Min time: " + minTime);

    }

}

