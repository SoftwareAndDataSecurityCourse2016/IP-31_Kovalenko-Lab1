import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Alphabet extends Decryptor {
    private String text;

    private float quality;
    private Map<String, String> key = new LinkedHashMap<String, String>() {
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
        }
    };

    private List<String> alphabet = new ArrayList<String>() {

        private static final long serialVersionUID = 1L;

        {
            add("A");
            add("B");
            add("C");
            add("D");
            add("E");
            add("F");
            add("G");
            add("H");
            add("I");
            add("J");
            add("K");
            add("L");
            add("M");
            add("N");
            add("O");
            add("P");
            add("Q");
            add("R");
            add("S");
            add("T");
            add("U");
            add("V");
            add("W");
            add("X");
            add("Y");
            add("Z");
        }
    };


    public Alphabet(String text) {
        this.quality = -99999;
        this.text = text;
    }

    @Override
    public String decrypt() {
        String decryptedText = "";
        int i = 0;
        while (i < 1000) {
            Map<String, String> currentKey = this.swap_key();
            String current = super.decrypt(text, currentKey);
            float currentQuality = super.computeQuality(current, 3);
            if (currentQuality > this.quality) {
                this.quality = currentQuality;
                this.key = currentKey;
                decryptedText = current;
                i = 0;
            }
            i++;
        }
        return decryptedText;
    }


    private Map<String, String> swap_key() {
        Map<String, String> current = new LinkedHashMap<String, String>(this.key);
        int pos1 = (int) (Math.random() * 26);
        int pos2 = (int) (Math.random() * 26);

        String a = current.get(alphabet.get(pos2));
        String b = current.get(alphabet.get(pos1));

        current.put(alphabet.get(pos1), a);
        current.put(alphabet.get(pos2), b);
        return current;
    }

}
