package com.example.demo;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;

 class Result {

    /*
     * Complete the 'solve' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     *  3. INTEGER_ARRAY h
     *  4. 2D_INTEGER_ARRAY rounds
     */

    public static List<Integer> solve(int n, int m, List<Integer> h, List<List<Integer>> rounds) {
    	List<Integer> result = new ArrayList<>();
    	for(int i=0 ; i <m ;i++)
    	{
    		result.add(getResult(h,rounds.get(i)));
    	}
    	return result;

    }

	private static int getResult(List<Integer> height, List<Integer> inputNumbers) {
			int result = -1;
		 int l = inputNumbers.get(0);
		 int r = inputNumbers.get(1);
		 int x = inputNumbers.get(2);
		 List<Integer>  actualList = new ArrayList<Integer>();
		 for(int i=l-1;i<r; i++)
		 {
			 actualList.add(height.get(i));
		 }
		 Collections.sort(actualList);
		
		for(int i=0; i < actualList.size() -1; i++)
		 {
			if(i > 0 && actualList.get(i-1) == actualList.get(i) && actualList.get(i) == actualList.get(i+1))
				continue;
			 int height1 = actualList.get(i);
			 int height2= actualList.get(i +1);
			 if(powerOfPlayers(height1, height2) >=x)
			 {
				 result = height2;
				 break;
			 }
		 }
		 return result;
		
	}
	
	private static int powerOfPlayers(int height1,int height2)
	{
		return 4 * (height1 + height2);
	}

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/milindtechnopreneur/Technopreneur/out.txt"));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> h = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<List<Integer>> rounds = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                rounds.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> answer = Result.solve(n, m, h, rounds);

        bufferedWriter.write(
            answer.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
