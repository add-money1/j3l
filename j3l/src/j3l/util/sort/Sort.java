package j3l.util.sort;

import java.util.ArrayList;

public final class Sort {
	
	public static void main(String[] args) {
		String[] s = new String[] {"fsd", "asd", "csdb", "csda", "a", "as", "f", "fs", "win1", "win2", "win10", "win11"};
		s = Sort.alphabetizeArray(s);
		for(int a=0,n=s.length;a<n;a++) {
			System.out.println(s[a]);
		}
	}
	
	/*
	 * 
	 * ordnet noch nicht komplett richtig (siehe Sort.main())
	 * 
	 * 
	*/
	
	public static <T> T[] alphabetizeArray(T[] array) {
		ArrayList<T> copy = new ArrayList<>();
		
		for(int a=0,n=array.length;a<n;a++) {
			copy.add(array[a]);
		}
		
		return Sort.alphabetizeArrayList(copy).toArray(array);
	}

	
	/**
	 * <p>sorts the elements of the ArrayList alphabetically - uses toString()</p>
	 * <p>note: not case sensitive</p>
	 *
	 * @param list the list
	 * @param T any type
	 * @return the ArrayList list passed by the parameter
	 */
	public static <T> ArrayList<T> alphabetizeArrayList(ArrayList<T> list) {
		
		list.sort((T t1, T t2) -> {
			
			String name_1 = t1.toString();
			String name_2 = t2.toString();
			
			if(name_1 == null || name_1.equals("")) {
				return -1;
			}
			else {
				if (name_2 == null  || name_2.equals("")) {
					return 1;
				}
				else {
					name_1 = name_1.toLowerCase();
					name_2 = name_2.toLowerCase();
					char c1, c2;
					int shorter = (name_1.length() < name_2.length())? name_1.length() : name_2.length();
				
					for(int a=0;a<shorter;a++) {
						c1 = name_1.charAt(a);
						c2 = name_2.charAt(a);
						
						if(c1 == c2) {
							if(a + 1 < shorter) {
								continue;
							}
							else {
								return (name_1.length() < name_2.length())? -1 : 1;
							}
						}
						else {
							return (int)(c1) - (int)(c2);
						}
					}
					
					return 0;
				}
			}
		});
		
		return list;
	}
	
}
