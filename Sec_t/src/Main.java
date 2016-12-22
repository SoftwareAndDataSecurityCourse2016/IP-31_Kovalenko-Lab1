
public class Main {

    public static void main(String[] args) {

        String task1 = "EFFPQLMEHEMVPCPYFLMVHQLUHYTCETHQEKLPVMMVHQLUWEOLFPQLIVDLWLULMVHQLUCYAUHUYDUEOSQYATFFVSMUVOVWEPLPQVSPCHLYGETDYUVPQOGUYOOYWYETHQEKLPVMYWLSASVWDEWCPLSPYGDYYFWLSSYGGVPCYAEULMYOGYUPEKTLBVPQCYAOECASLVWFLRYGMYVWMVFLWMLNESVSNVLREOVWEPVYWSPEPVSPVMETPLSPSYUBQEPLILUOLPQYFCYAGLLTBVTTSQYBPQLKLSPULSATPPUCPYPQVWNYGBQEPBVTTQEHHLWVGPQLNLCMYWPEVWSSEOLMQEUEMPLUSEPFVGGLULWPHYSVPVYWSBVTTCYAUHUYDUEOSPVTTKLQEILMYUULMPTC";
        System.out.println(task1);
        Decryptor decryptor1 = new Alphabet(task1);
        System.out.println(decryptor1.decrypt());
        System.out.println("2-nd task");
        String task2 = "EDVRLESZMLARCTGBICANLEJYRYCGRDGDTODWELHFEBWRMCUGTHWPWTZCSNWSWEVGRTZCGIHFIRLCBTKFIRWFESLUINLWWIPGRDWNINVCRTJYRDGKPYUFSSWLQOFMELHFEBWRMCKSFSLGXULGSNHYXTWPRSXMVESALLWRXEJDVOECRGDGWHSJTHSZITARMSUJIAJRLALWSUUYRNGKSRWPILQMRTZCWAECWIENPEJMYTALIOXEYEKQMNYRLECCCBQCBHSSWTATISWYVCZULIUFCOMNVOTYFLQSWEVRSDWAMPZCVTZGWPSPEGJYTHOGPLLFIIFBIXGDGOALGIVCRCWQXIDJAOJIESSQYGYCWTAMRYGSGAFRVYLMHINGHELFIMWQWAYCMNHYVTKZCTZCRUEZIRGDGHSPECLCVSALEKWWENVYTPDWJRWOYEFACAFYPYKGWTGCECZMJTZCQCSLCOMDMNVYAAQRSUKCLIYFIRGPHEJDVEISINUWWTSRMSLGGSOGXHLFMSLWTEGDGIHFIRLFINWVXPSPEGJYTHUMRTSGRSKMQEHPITLWMNLCVEKRMNYGRFGPQALGSNSZSULQSMWYHDARMOFYPRWUERV";
        System.out.println(task2);
        Decryptor decryptor2 = new Vign(task2);
        System.out.println(decryptor2.decrypt());
    }

}