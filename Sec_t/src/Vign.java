import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Vign extends Decryptor {
    private String text;
    private int keyWordLength;
    private String keyWord = "";

    private Map<String, Vign_Node> variantGroup = new LinkedHashMap<>();


    private final Map<String, String> key = new LinkedHashMap<String, String>() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        {
            put("A", "A");
            put("B", "B");
            put("C", "C");
            put("D", "D");
            put("E", "E");
            put("F", "F");
            put("G", "G");
            put("H", "H");
            put("I", "I");
            put("J", "J");
            put("K", "K");
            put("L", "L");
            put("M", "M");
            put("N", "N");
            put("O", "O");
            put("P", "P");
            put("Q", "Q");
            put("R", "R");
            put("S", "S");
            put("T", "T");
            put("U", "U");
            put("V", "V");
            put("W", "W");
            put("X", "X");
            put("Y", "Y");
            put("Z", "Z");
        }};

    private final List<String> alphabet = new ArrayList<String>() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        {
            add("A"); add("B");	add("C"); add("D"); add("E"); add("F");
            add("G"); add("H"); add("I"); add("J"); add("K"); add("L");
            add("M"); add("N"); add("O"); add("P"); add("Q"); add("R");
            add("S"); add("T"); add("U"); add("V"); add("W"); add("X");
            add("Y"); add("Z");
        }};

    public Vign(String text) {
        this.text = text;
        this.keyWordLength = 4;
    }

    @Override
    public String decrypt() {
        this.computVariantGroup();
        this.computeKeyWord();
        return this.getStringFromGroup(computBestVariant());
    }

    private void computVariantGroup() {
        for (int i = 0; i < keyWordLength; i++) {
            String groupLetter = this.getGroupFromString(text, i);
            ArrayList<Vign_Node> variantList = new ArrayList<>();

            for (int j = 0; j < 26; j++) {
                Vign_Node variantNode = new Vign_Node();
                Map<String, String> currentKey = this.changeKey(j);

                variantNode.setVariant(super.decrypt(groupLetter, currentKey));
                variantNode.setQuality(super.computeQuality(variantNode.getVariant(), 1));
                variantNode.setShift(j);
                variantList.add(variantNode);
            }
            Collections.sort(variantList);
            variantGroup.put(groupLetter, variantList.get(0));
        }
    }

    private String[] computBestVariant() {
        String[] bestVariant = new String[keyWordLength];
        int ii = 0;
        for (Map.Entry<String, Vign_Node> entry: variantGroup.entrySet()) {
            bestVariant[ii] = entry.getValue().getVariant();
            ii++;
        }
        return bestVariant;
    }

    private String getGroupFromString(String text, int groupIndex) {
        String res = "";
        for (int i = 0; i < text.length(); i++)
            if (i % keyWordLength  == groupIndex)
                res += text.charAt(i);
        return res;
    }

    private String getStringFromGroup(String[] arr) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            result += arr[i % arr.length].charAt(i / arr.length);
        }
        return result;
    }

    /**
     * change key according to shift
     */
    private Map<String, String> changeKey(int shift) {
        Map<String, String> currentKey = new LinkedHashMap<String, String>(this.key);
        int index = 0;
        for (Map.Entry<String, String> entry: currentKey.entrySet()) {
            //entry.setValue(alphabet.get((index + shift) % 26));
            entry.setValue(alphabet.get(((index - shift + alphabet.size())) % alphabet.size()) );
            index ++;
        }
        return currentKey;
    }

    public Map<String, Integer> computeNGramFrequency(String text, int n) {
        Map<String, Integer> nGramFrequency = new LinkedHashMap<String, Integer>();;
        for (int i = 0; i < text.length() - n + 1; i++) {
            String currSubstr = text.substring(i, i + n);
            if (nGramFrequency.containsKey(currSubstr))
                nGramFrequency.put(currSubstr, nGramFrequency.get(currSubstr) + 1);
            else nGramFrequency.put(currSubstr, 1);
        }
        return sortMapByValue(nGramFrequency);
    }

    public void printFrequency(Map<String, Integer> nGramFrequency, String text) {
        for (Map.Entry<String, Integer> entry: nGramFrequency.entrySet()) {
            System.out.print(entry.getKey() + ": " + entry.getValue() + "  ");
            int index = text.indexOf(entry.getKey());

            while (index >= 0) {
                System.out.print((index + 1) + " ");
                index = text.indexOf(entry.getKey(), index + 1);
            }
            System.out.println();
        }
    }

    private Map<String, Integer> sortMapByValue(Map<String, Integer> unsortMap) {
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1,   Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    private void computeKeyWord() {
        for (Map.Entry<String, Vign_Node> entry: this.variantGroup.entrySet()) {
            this.keyWord += alphabet.get(entry.getValue().getShift());
        }
    }

}

