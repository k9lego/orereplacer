package io.github.kuohsuanlo.orereplacer;

import java.util.Random;

public class OreReplacerUtil {
	private static  Random rand = new Random();
	private static  int[] res = new int[6];
	public static int[] generateRandomPermutation() {
	    for (int i = 0; i < 6; i++) {
	        int d = rand.nextInt(i+1);
	        res[i] = res[d];
	        res[d] = i;
	    }
	    return res;
	}
}
