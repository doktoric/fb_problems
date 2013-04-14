
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Hello world!
 * 
 */
public class DnaProblem {

	public final List<String> END_CONDON = new ArrayList<String>() {
		{
			add("TAA");
			add("TAG");
			add("TGA");
		}
	};
	public static final String BEGIN_CONDON = "ATG";
	public static final String CONDON_SEPARATOR = "// Codons";
	public static final String DNA_SEPARATOR = "// DNA";
	public static final String EXIT_CODE = "X";

	public static void main(String[] args) {
		DnaProblem solution = new DnaProblem();
		try {
			String solvedString = solution
					.solve("/com/acme/doktoric/facebook/condons_testcases/input00.txt");
			System.out.println(solvedString);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DnaProblem() {
	}

	private String solve(String inputFile) throws FileNotFoundException {
		String solution = "";
		InputStream inputStream = getClass().getResourceAsStream(inputFile);
		List<String> rows = readFile(inputStream);
		Map<String, String> condons = getCondomsFromFile(rows);
		String dna = getDnaFromFile(rows);
		List<String> codes = getDecodedList(dna);
		System.out.println(codes);
		solution = decodeCodes(codes, condons);
		return solution;
	}

	private String decodeCodes(List<String> codes, Map<String, String> condons) {
		String solution = "";
		for (String code : codes) {
			String key = condons.get(code);
			if (!key.equals(EXIT_CODE)) {
				solution += key;
			}
		}
		return solution;
	}

	private List<String> getDecodedList(String dna) {
		List<String> elements = new ArrayList<String>();
		String tempDna = dna;
		int beginDna = dna.indexOf(BEGIN_CONDON);
		tempDna = tempDna.substring(beginDna, tempDna.length());
		for (int i = 0; i + 3 < tempDna.length(); i += 3) {
			String condon = tempDna.substring(i, i + 3);
			elements.add(condon);
			if (END_CONDON.contains(condon)) {
				elements.add(condon);
				beginDna = tempDna.indexOf(BEGIN_CONDON, i);
				if (beginDna != -1) {
					tempDna = tempDna.substring(beginDna, tempDna.length());
					i = -3;
				} else {
					i = tempDna.length();
				}
			}
		}
		return elements;

	}

	private List<String> readFile(InputStream file) {
		List<String> rows = new ArrayList<String>();
		Scanner scanner = new Scanner(file);
		String s = "";
		while (scanner.hasNextLine()) {
			s = scanner.nextLine();
			rows.add(s);
		}
		scanner.close();
		return rows;
	}

	private String getDnaFromFile(List<String> rows) {
		int dnaIndex = rows.indexOf(DNA_SEPARATOR);
		return rows.get(dnaIndex + 1);
	}

	private Map<String, String> getCondomsFromFile(List<String> rows) {
		Map<String, String> condons = new HashMap<String, String>();
		for (int i = 0; i < rows.size(); i++) {
			String row = rows.get(i);
			if (row.equals(CONDON_SEPARATOR))
				continue;
			else if (row.equals(DNA_SEPARATOR)) {
				i = rows.size();
			} else {
				getCondonFromRow(row, condons);
			}
		}
		return condons;

	}

	private void getCondonFromRow(String row, Map<String, String> condons) {
		String[] text = row.split(" ");
		condons.put(text[0], text[1]);
	}

}
