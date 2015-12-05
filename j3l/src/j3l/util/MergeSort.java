package j3l.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.10.13_0
 * @author Johannes B. Latzel
 */
public class MergeSort {
	
	public static <T> void sort(Comparator<T> comp, T[] input) {
		
		ArrayList<T> array_list = new ArrayList<>(input.length);
		Collections.addAll(array_list, input);
		sort(comp, array_list);
		
		for(int a=0,n=input.length;a<n;a++) {
			input[a] = array_list.get(a);
		}
		
	}

	public static <T> void sort(Comparator<T> comp, ArrayList<T> input) {
		int n = input.size();
		int n_half = n/2;
		if(n_half == 0) {
			return;
		}
		int dummy = 0;
		ArrayList<T> part_one = new ArrayList<>(n_half);
		for(dummy=0;dummy<n_half;dummy++) {
			part_one.add(dummy, input.get(dummy));
		}
		ArrayList<T> part_two = new ArrayList<>(n_half + (n % 2));
		int max_count_two = n_half + (n % 2);
		for(dummy=0;dummy<max_count_two;dummy++) {
			part_two.add(dummy, input.get(dummy + n_half));
		}
		sort(comp, part_one);
		sort(comp, part_two);
		int count_one = 0;
		int count_two = 0;
		for(dummy=0;dummy<n;dummy++) {
			if(count_one == n_half) {
				input.set(dummy, part_two.get(count_two));
				count_two++;
			}
			else {
				if(count_two == max_count_two) {
					input.set(dummy, part_one.get(count_one));
					count_one++;
				}
				else {
					if(comp.compare(part_one.get(count_one),part_two.get(count_two)) < 0) {
						input.set(dummy, part_one.get(count_one));
						count_one++;
					}
					else {
						input.set(dummy, part_two.get(count_two));
						count_two++;
					}
				}
			}
		}
	}
	
	public static void sort(double[] input) {
		int n = input.length;
		int n_half = n/2;
		if(n_half == 0) {
			return;
		}
		int dummy = 0;
		double[] part_one = new double[n_half];
		for(dummy=0;dummy<n_half;dummy++) {
			part_one[dummy] = input[dummy];
		}
		double[] part_two = new double[n_half + (n % 2)];
		int max_count_two = part_two.length;
		for(dummy=0;dummy<max_count_two;dummy++) {
			part_two[dummy] = input[dummy+n_half];	
		}
		sort(part_one);
		sort(part_two);
		int count_one = 0;
		int count_two = 0;
		for(dummy=0;dummy<n;dummy++) {
			if(count_one == n_half) {
				input[dummy] = part_two[count_two];
				count_two++;
			}
			else {
				if(count_two == max_count_two) {
					input[dummy] = part_one[count_one];
					count_one++;
				}
				else {
					if(part_one[count_one] < part_two[count_two]) {
						input[dummy] = part_one[count_one];
						count_one++;
					}
					else {
						input[dummy] = part_two[count_two];
						count_two++;
					}
				}
			}
		}
	}
	
	public static void sort(long[] input) {
		int n = input.length;
		int n_half = n/2;
		if(n_half == 0) {
			return;
		}
		int dummy = 0;
		long[] part_one = new long[n_half];
		for(dummy=0;dummy<n_half;dummy++) {
			part_one[dummy] = input[dummy];
		}
		long[] part_two = new long[n_half + (n % 2)];
		int max_count_two = part_two.length;
		for(dummy=0;dummy<max_count_two;dummy++) {
			part_two[dummy] = input[dummy+n_half];	
		}
		sort(part_one);
		sort(part_two);
		int count_one = 0;
		int count_two = 0;
		for(dummy=0;dummy<n;dummy++) {
			if(count_one == n_half) {
				input[dummy] = part_two[count_two];
				count_two++;
			}
			else {
				if(count_two == max_count_two) {
					input[dummy] = part_one[count_one];
					count_one++;
				}
				else {
					if(part_one[count_one] < part_two[count_two]) {
						input[dummy] = part_one[count_one];
						count_one++;
					}
					else {
						input[dummy] = part_two[count_two];
						count_two++;
					}
				}
			}
		}
	}
	
}
