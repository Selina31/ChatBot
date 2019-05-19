
import java.util.*;
import java.io.*;

public class Chatbot {
	private static String filename = "./WARC201709_wid.txt";

	private static ArrayList<Integer> readCorpus() {
		ArrayList<Integer> corpus = new ArrayList<Integer>();
		try {
			File f = new File(filename);
			Scanner sc = new Scanner(f);
			while (sc.hasNext()) {
				if (sc.hasNextInt()) {
					int i = sc.nextInt();
					corpus.add(i);
				} else {
					sc.next();
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("File Not Found.");
		}
		return corpus;
	}

	static public void main(String[] args) {
		ArrayList<Integer> corpus = readCorpus();
		int flag = Integer.valueOf(args[0]);

		if (flag == 100) {
			int w = Integer.valueOf(args[1]);
			int count = 0;
			// TODO count occurrence of w
			for (int key : corpus) {
				if (key == w) {
					count++;
				}
			}
			System.out.println(count);
			System.out.println(String.format("%.7f", count / (double) corpus.size()));
		} else if (flag == 200) {
			int n1 = Integer.valueOf(args[1]);
			int n2 = Integer.valueOf(args[2]);
			// TODO generate
			//a hashmap collect to collect keys
			HashMap<Integer, Integer> collect = new HashMap<>();
			List<Double[]> list = new ArrayList<Double[]>();
			double r = (double) n1 / (double) n2;
			for (int key : corpus) {
				if (!collect.containsKey(key)) {
					collect.put(key, 1);
				} else {
					collect.put(key, collect.get(key) + 1);
				}
			}
			double currLeft = 0;
			for (int i = 0; i < collect.size(); i++) {
				//a set of (i, l, r) where r = li + p
				Double set[] = { (double) i, currLeft, currLeft + collect.get(i) / (double) corpus.size() };
				list.add(set);
				currLeft += collect.get(i) / (double) corpus.size();
			}

			if (r == 0) {
				System.out.println((int) r);
				System.out.println(String.format("%.7f", r));
				System.out.println(String.format("%.7f", list.get(0)[2]));
			}
			//output
			for (Double temp[] : list) {
				if (r <= temp[2] && r > temp[1]) {
					System.out.println(temp[0].intValue());
					System.out.println(String.format("%.7f", temp[1]));
					System.out.println(String.format("%.7f", temp[2]));
				}
			}
		} else if (flag == 300) {
			int h = Integer.valueOf(args[1]);
			int w = Integer.valueOf(args[2]);
			int count = 0;
			ArrayList<Integer> words_after_h = new ArrayList<Integer>();
			// TODO
			for (int i = 0; i < corpus.size() - 1; i++) {
				//if h and w matches at position i and i+1, increase count
				if (corpus.get(i) == h && corpus.get(i + 1) == w) {
					count++;
					words_after_h.add(corpus.get(i + 1));
				} else if (corpus.get(i) == h) {
					words_after_h.add(corpus.get(i + 1));
				}
			}
			if (words_after_h.size() == 0) {
				System.out.println("undefined");
			} else {
				System.out.println(count);
				System.out.println(words_after_h.size());
				System.out.println(String.format("%.7f", count / (double) words_after_h.size()));
			}
		} else if (flag == 400) {
			int n1 = Integer.valueOf(args[1]);
			int n2 = Integer.valueOf(args[2]);
			int h = Integer.valueOf(args[3]);
			// TODO
			double currLeft = 0;
			double r = (double) n1 / (double) n2;
			HashMap<Integer, Integer> collect = new HashMap<>();
			List<Double[]> list = new ArrayList<Double[]>();

			ArrayList<Integer> wordAfterH = new ArrayList<Integer>();
			for (int i = 0; i < corpus.size() - 1; i++) {
				if (corpus.get(i) == h) {
					wordAfterH.add(corpus.get(i + 1));
				}
			}
			for (int i : wordAfterH) {
				if (!collect.containsKey(i)) {
					collect.put(i, 1);
				} else {
					collect.put(i, collect.get(i) + 1);
				}
			}
			Collections.sort(wordAfterH);
			Map<Integer, Integer> map = new TreeMap<Integer, Integer>(collect); // sort
			for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
				int next = entry.getKey();
				Double set[] = { (double) next, currLeft,
						currLeft + (double) entry.getValue() / (double) wordAfterH.size() };
				list.add(set);
				currLeft += (double) entry.getValue() / (double) wordAfterH.size();
			}
			if (r == 0) {
				System.out.println(list.get(0)[2].intValue());
				System.out.println(String.format("%.7f", r));
				System.out.println(String.format("%.7f", list.get(0)[2]));
			}
			for (Double temp[] : list) {
				if (r <= temp[2] && r > temp[1]) {
					System.out.println(temp[0].intValue());
					System.out.println(String.format("%.7f", temp[1]));
					System.out.println(String.format("%.7f", temp[2]));
				}
			}

		} else if (flag == 500) {
			int h1 = Integer.valueOf(args[1]);
			int h2 = Integer.valueOf(args[2]);
			int w = Integer.valueOf(args[3]);
			int count = 0;
			ArrayList<Integer> wordAfterH1H2 = new ArrayList<Integer>();
			// TODO
			for (int i = 0; i < corpus.size() - 2; i++) {
				if (corpus.get(i) == h1 && corpus.get(i + 1) == h2 && corpus.get(i + 2) == w) {
					count++;
					wordAfterH1H2.add(corpus.get(i + 2));
				} else if (corpus.get(i) == h1 && corpus.get(i + 1) == h2) {
					wordAfterH1H2.add(corpus.get(i + 2));
				}
			}
			// output
			System.out.println(count);
			System.out.println(wordAfterH1H2.size());
			if (wordAfterH1H2.size() == 0)
				System.out.println("undefined");
			else
				System.out.println(String.format("%.7f", count / (double) wordAfterH1H2.size()));
		} else if (flag == 600) {
			int n1 = Integer.valueOf(args[1]);
			int n2 = Integer.valueOf(args[2]);
			int h1 = Integer.valueOf(args[3]);
			int h2 = Integer.valueOf(args[4]);
			// TODO
			double r = (double) n1 / (double) n2;
			Map<Integer, Integer> collect = new HashMap<>();
			List<Double[]> list = new ArrayList<Double[]>();
			ArrayList<Integer> wordAfterH1H2 = new ArrayList<Integer>();
			double ctr = 0;
			for (int i = 0; i < corpus.size() - 2; i++) {
				if (corpus.get(i) == h1 && corpus.get(i + 1) == h2) {
					wordAfterH1H2.add(corpus.get(i + 2));
				}
			}
			for (int i : wordAfterH1H2)
				if (!collect.containsKey(i))
					collect.put(i, 1);
				else
					collect.put(i, collect.get(i) + 1);
			Collections.sort(wordAfterH1H2);
			Map<Integer, Integer> map = new TreeMap<Integer, Integer>(collect);
			if (wordAfterH1H2.size() == 0)
				System.out.println("undefined");
			else {
				for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
					int next = entry.getKey();
					Double temp[] = { (double) next, ctr,
							ctr + (double) entry.getValue() / (double) wordAfterH1H2.size() }; // add to list
					list.add(temp);
					ctr += (double) entry.getValue() / (double) wordAfterH1H2.size();
				}
				if (r == 0) {
					System.out.println(list.get(0)[0].intValue());
					System.out.println(String.format("%.7f", r));
					System.out.println(String.format("%.7f", list.get(0)[2]));
				}
				for (Double temp[] : list) {
					if (r <= temp[2] && r > temp[1]) {
						System.out.println(temp[0].intValue());
						System.out.println(String.format("%.7f", temp[1]));
						System.out.println(String.format("%.7f", temp[2]));
					}
				}
			}
		} else if (flag == 700) {
			int seed = Integer.valueOf(args[1]);
			int t = Integer.valueOf(args[2]);
			int h1 = 0, h2 = 0;

			Random rng = new Random();
			if (seed != -1)
				rng.setSeed(seed);

			if (t == 0) {
				// TODO Generate first word using r
				double r = rng.nextDouble();
				double ctr = 0;
				Map<Integer, Integer> collect = new HashMap<>();
				List<Double[]> list = new ArrayList<Double[]>();
				for (int key : corpus)
					if (!collect.containsKey(key))
						collect.put(key, 1);
					else
						collect.put(key, collect.get(key) + 1);
				for (int i = 0; i < collect.size(); i++) {
					Double set[] = { (double) i, ctr, ctr + (double) collect.get(i) / (double) corpus.size() }; // add
																												// to
																												// list
					list.add(set);
					ctr += (double) collect.get(i) / (double) corpus.size();
				}
				if (r == 0)
					h1 = list.get(0)[0].intValue();
				for (Double temp[] : list)
					if (r <= temp[2] && r > temp[1])
						h1 = temp[0].intValue();
				System.out.println(h1);
				if (h1 == 9 || h1 == 10 || h1 == 12) {
					return;
				}
				// TODO Generate second word using r
				r = rng.nextDouble();
				ctr = 0;
				List<Double[]> list2 = new ArrayList<Double[]>();
				Map<Integer, Integer> collect2 = new HashMap<>();
				ArrayList<Integer> wordAfterH1 = new ArrayList<Integer>();
				for (int i = 0; i < corpus.size() - 1; i++)
					if (corpus.get(i) == h1)
						wordAfterH1.add(corpus.get(i + 1));
				for (int w : wordAfterH1)
					if (!collect2.containsKey(w))
						collect2.put(w, 1);
					else
						collect2.put(w, collect2.get(w) + 1);
				Collections.sort(wordAfterH1);
				Map<Integer, Integer> map = new TreeMap<Integer, Integer>(collect2);
				for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
					int next = entry.getKey();
					Double set[] = { (double) next, ctr,
							ctr + (double) entry.getValue() / (double) wordAfterH1.size() }; // add to list
					list2.add(set);
					ctr += (double) entry.getValue() / (double) wordAfterH1.size();
				}
				if (r == 0)
					h2 = list2.get(0)[2].intValue();
				for (Double temp[] : list2)
					if (r <= temp[2] && r > temp[1])
						h2 = temp[0].intValue();
				System.out.println(h2);
			} else if (t == 1) {
				h1 = Integer.valueOf(args[3]);
				// TODO Generate second word using r
				double r = rng.nextDouble();
				double ctr = 0;
				Map<Integer, Integer> collect = new HashMap<>();
				List<Double[]> list = new ArrayList<Double[]>();
				ArrayList<Integer> wordAfterH1 = new ArrayList<Integer>();
				for (int i = 0; i < corpus.size() - 1; i++)
					if (corpus.get(i) == h1)
						wordAfterH1.add(corpus.get(i + 1));
				for (int w : wordAfterH1)
					if (!collect.containsKey(w))
						collect.put(w, 1);
					else
						collect.put(w, collect.get(w) + 1);
				Collections.sort(wordAfterH1);
				Map<Integer, Integer> map = new TreeMap<Integer, Integer>(collect);
				for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
					int next = entry.getKey();
					Double temp[] = { (double) next, ctr,
							ctr + (double) entry.getValue() / (double) wordAfterH1.size() }; // add to list
					list.add(temp);
					ctr += (double) entry.getValue() / (double) wordAfterH1.size();
				}
				if (r == 0)
					h2 = list.get(0)[2].intValue();
				for (Double temp[] : list)
					if (r <= temp[2] && r > temp[1])
						h2 = temp[0].intValue();
				System.out.println(h2);
			} else if (t == 2) {
				h1 = Integer.valueOf(args[3]);
				h2 = Integer.valueOf(args[4]);
			}

			while (h2 != 9 && h2 != 10 && h2 != 12) {
				double r = rng.nextDouble();
				int w = 0;
				// TODO Generate new word using h1,h2
				Map<Integer, Integer> collect = new HashMap<>();
				List<Double[]> list = new ArrayList<Double[]>();
				ArrayList<Integer> wordAfterH1H2 = new ArrayList<Integer>();
				double ctr = 0;
				for (int i = 0; i < corpus.size() - 2; i++) {
					if (corpus.get(i) == h1 && corpus.get(i + 1) == h2) {
						wordAfterH1H2.add(corpus.get(i + 2));
					}
				}
				for (int k : wordAfterH1H2)
					if (!collect.containsKey(k))
						collect.put(k, 1);
					else
						collect.put(k, collect.get(k) + 1);
				Collections.sort(wordAfterH1H2);
				Map<Integer, Integer> map = new TreeMap<Integer, Integer>(collect);
				if (wordAfterH1H2.size() == 0) {
					ArrayList<Integer> wordAfterH2 = new ArrayList<Integer>();
					ctr = 0;
					for (int i = 0; i < corpus.size() - 1; i++)
						if (corpus.get(i) == h2)
							wordAfterH2.add(corpus.get(i + 1));
					for (int k : wordAfterH2)
						if (!collect.containsKey(k))
							collect.put(k, 1);
						else
							collect.put(w, collect.get(k) + 1);
					Collections.sort(wordAfterH2);
					Map<Integer, Integer> list2 = new TreeMap<Integer, Integer>(collect);
					for (Map.Entry<Integer, Integer> entry : list2.entrySet()) {
						int next = entry.getKey();
						Double temp[] = { (double) next, ctr,
								ctr + (double) entry.getValue() / (double) wordAfterH2.size() }; // add to list
						list.add(temp);
						ctr += (double) entry.getValue() / (double) wordAfterH2.size();
					}
					if (r == 0)
						h2 = list.get(0)[2].intValue();
					for (Double temp[] : list)
						if (r <= temp[2] && r > temp[1])
							h2 = temp[0].intValue();
				} else {
					for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
						int next_w = entry.getKey();
						Double temp[] = { (double) next_w, ctr,
								ctr + (double) entry.getValue() / (double) wordAfterH1H2.size() }; // add to list
						list.add(temp);
						ctr += (double) entry.getValue() / (double) wordAfterH1H2.size();
					}
					if (r == 0)
						w = list.get(0)[0].intValue();
					for (Double temp[] : list)
						if (r <= temp[2] && r > temp[1])
							w = temp[0].intValue();
				}
				System.out.println(w);
				h1 = h2;
				h2 = w;
			}
		}

		return;
	}
}
