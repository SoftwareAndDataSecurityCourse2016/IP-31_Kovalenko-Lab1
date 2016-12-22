import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Decryptor {
    private Map<String, Float> monogramStandard;
    private Map<String, Float> bigramStandard;
    private Map<String, Float> trigramStandard;
    private Map<String, Float> quadgramStandard;

    public Decryptor() {
        this.monogramStandard = load_gram(1);
        this.bigramStandard = load_gram(2);
        this.trigramStandard = load_gram(3);
        this.quadgramStandard = load_gram(4);
    }

    public static Map<String, Float> load_gram(int n) {
        FileReader fileReader = null;
        Map<String, Float> map = new LinkedHashMap<String, Float>();
        try {
            switch (n) {
                case 1:
                    fileReader = new FileReader("D:\\Sec_t\\grams\\monograms.txt");
                    break;
                case 2:
                    fileReader = new FileReader("D:\\Sec_t\\grams\\bigrams.txt");
                    break;
                case 3:
                    fileReader = new FileReader("D:\\Sec_t\\grams\\trigrams.txt");
                    break;
                case 4:
                    fileReader = new FileReader("D:\\Sec_t\\grams\\quadgrams.txt");
                    break;
                default:
                    fileReader = null;
                    break;
            }

            BufferedReader buffer = new BufferedReader(fileReader);
            String str;

            long summ = 0;
            while ((str = buffer.readLine()) != null) {
                map.put(str.split(" ")[0], new Float(str.split(" ")[1]));
                summ += Integer.parseInt(str.split(" ")[1]);
            }

            for (Map.Entry<String, Float> entry : map.entrySet()) {
                entry.setValue((float) Math.log10(entry.getValue() / summ));
            }

            buffer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    protected float computeQuality(String text, int n) {
        Map<String, Float> standard;
        switch (n) {
            case 1:
                standard = this.monogramStandard;
                break;
            case 2:
                standard = this.bigramStandard;
                break;
            case 3:
                standard = this.trigramStandard;
                break;
            case 4:
                standard = this.quadgramStandard;
                break;
            default:
                standard = null;
                break;
        }

        float currentQuality = 0f;
        for (int i = 0; i < text.length() - n + 1; i++) {
            if (standard.containsKey(text.substring(i, i + n)))
                currentQuality += standard.get(text.substring(i, i + n));
        }
        return currentQuality;
    }

    protected abstract String decrypt();
    protected String decrypt(String encryptedText, Map<String, String> currentKey) {
        String currDecriptedText = new String("");
        for (int i = 0; i < encryptedText.length(); i++) {
            currDecriptedText += currentKey.get(Character.toString(encryptedText.charAt(i)));
        }
        return currDecriptedText;
    }
}

