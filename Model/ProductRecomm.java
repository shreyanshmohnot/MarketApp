package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductRecomm {
	// Create an HashMap to store key value pairs corresponding to transaction key
	// and associated values.
	HashMap<String, List<String>> hmap = null;
	// value stores the list of all the values in the hashmap
	List<String> value = null;
	// powerSets contains the list of the combination of pairs of values
	List<String> powerSets;
	// uniqueList stores all unique values of elements
	List<String> uniqueList = null;
	// supportList stores the list of values that are greater than the minimum
	// support
	List<String> supportList = null;
	int support, confidence;

	public ProductRecomm() {
		uniqueList = new ArrayList<String>();
		hmap = new HashMap<String, List<String>>();
		value = new ArrayList<String>();
		supportList = new ArrayList<String>();
	}

	// toMap function adds the values from the transaction map into a HashMap.
	// the function also calls the unique values function and calls other functions.
	public void toMap(String fileName, int supp, int conf) {
		support = supp;
		confidence = conf;
		String line = null;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);
			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				String[] parts1 = line.split("\\t");
				String[] parts2 = parts1[1].split(";");
				List<String> hmapValue = Arrays.asList(parts2);
				value.addAll(hmapValue);
				if (parts1.length >= 2) {
					String key = parts1[0];
					// List will have ";" of parts1[1]
					hmap.put(key, hmapValue);
				} else {
					System.out.println("ignoring line: " + line);
				}
			}
			bufferedReader.close();
			uniqueValuesFunction(value);
			int r = 10;
			int n_s = uniqueList.size();
			for (int j = 1; j <= r; j++) {
				printCombination(uniqueList, n_s, j, false);
			}
			int n_c = supportList.size();
			printCombination(supportList, n_c, 2, true);
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
	}

	// takes as input a String list of all the values present in the transaction
	// file and calculates the unique elements.
	public void uniqueValuesFunction(List<String> value) {
		Set<String> uniqueValues = new HashSet<String>(value);
		for (String s : uniqueValues) {
			uniqueList.add(s);
		}
	}

	// this function calculates the combination of the values
	public void combinationUtil(List<String> arr, String data[], int start, int end, int index, int r,
			boolean call_conf) {
		if (index == r) {
			String data_append = "";
			for (int j = 0; j < r; j++) {
				if (!call_conf) {
					data_append = data_append + data[j] + " ";
				} else {
					data_append = data_append + data[j] + ";";
				}
			}
			if (!call_conf) {
				// if call confidence is false, call calculateSupport function
				if (calculateSupport(data_append)) {
					supportList.add(data_append);
					// System.out.println(data_append);
				}
			} else {
				// else if call_conf is true, call calcuateConfidence function
				if (calculateConfidence(data_append)) {
					data_append = data_append.replace(";", "->");
					String data_cut = data_append.substring(0, data_append.length() - 2);
					System.out.println(data_cut);
				}
			}
			return;
		}
		for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
			data[index] = arr.get(i);
			combinationUtil(arr, data, i + 1, end, index + 1, r, call_conf);
		}
	}

	public void printCombination(List<String> arr, int n, int r, boolean call_conf) {
		// A temporary array to store all combination one by one
		String data[] = new String[r];
		// Print all combination using temporary array 'data[]'
		combinationUtil(arr, data, 0, n - 1, 0, r, call_conf);
	}

	// calculates the support of the value pairs, and returns true if the
	// combination has support greater or equal to minimum support
	public boolean calculateSupport(String combi_result) {
		boolean insert = false;
		int occuranceItemSet = 0;
		List<String> combi_result_list = Arrays.asList(combi_result.split(" "));
		int tcount = hmap.size();
		int suppCount = 0;
		for (String key : hmap.keySet()) {
			boolean itemPresent = true;
			List<String> keyValue = hmap.get(key);
			for (String check : combi_result_list) {
				if (itemPresent == true) {
					itemPresent = keyValue.contains(check.trim());
				}
			}
			if (itemPresent == true) {
				occuranceItemSet++;
			}
		}
		if (occuranceItemSet > 0) {
			suppCount = (occuranceItemSet * 100) / tcount;
			if (suppCount >= support) {
				insert = true;
			}
		}
		return insert;
	}

	// calculates the confidence of the value pairs that have support >= minimum
	// support, and returns true if the combination has support greater or equal to
	// minimum confidence
	public boolean calculateConfidence(String sup_list) {
		boolean insert = false;
		// occuranceLeft keeps track of number of occurances of value on the left side
		// of the itemset
		int occuranceLeft = 0;
		// occuranceLeft keeps track of number of occurances of value on the left, and
		// right side of the itemset apprearing together
		int occuranceAll = 0;
		// calc_conf is the value of computed confidence
		int calc_conf = 0;
		String[] left_right_array = sup_list.split(";");
		String left = left_right_array[0];
		String right = left_right_array[1];
		List<String> left_list = Arrays.asList(left.split(" "));
		List<String> right_list = Arrays.asList(right.split(" "));
		// Check weather left items exist
		for (String key : hmap.keySet()) {
			boolean leftpresent = true;
			boolean rightpresent = true;
			List<String> keyValue = hmap.get(key);
			for (String check : left_list) {
				if (leftpresent == true) {
					leftpresent = keyValue.contains(check.trim());
				}
			}
			if (leftpresent == true) {
				occuranceLeft++;
				for (String checkright : right_list) {
					if (rightpresent == true) {
						rightpresent = keyValue.contains(checkright.trim());
					}
				}
				if (rightpresent == true) {
					occuranceAll++;
				}
			}
		}
		if (occuranceLeft > 0) {
			// return true if itemset has confidence > minimum confidence
			calc_conf = (occuranceAll) * 100 / (occuranceLeft);
		}
		if (calc_conf > confidence) {
			return true;
		} else {
			return false;
		}
	}
}