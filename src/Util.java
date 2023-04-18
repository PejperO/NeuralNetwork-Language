import java.io.*;
import java.util.*;

public class Util {
    public static Map<String, ArrayList<ArrayList<Double>>> languageToVectorsMap = new HashMap<>();

    public static void getDataFromDirectory(File directory)  {

        if(!directory.isDirectory()){
            throw new RuntimeException(directory + "is not a directory.");
        }

        try{
            File[] files = directory.listFiles();

            assert files != null;
            for (File file : files) {
                if(file.isDirectory()){
                    File[] txtFiles = file.listFiles();
                    ArrayList<ArrayList<Integer>> languageLetterCountVectors = new ArrayList<>();
                    ArrayList<ArrayList<Double>> languageLetterFrequencyVectors = new ArrayList<>();

                    assert txtFiles != null;
                    Arrays.stream(txtFiles).forEach(txtFile -> {
                        try {
                            BufferedReader reader = new BufferedReader(new FileReader(txtFile));
                            ArrayList<Integer> letterCountVector = new ArrayList<>(Collections.nCopies(26, 0));
                            ArrayList<Double> letterFrequencyVector = new ArrayList<>(Collections.nCopies(26, 0.0));
                            String line;
                            while ((line = reader.readLine()) != null){
                                countLetters(line, letterCountVector);
                            }

                            languageLetterCountVectors.add(letterCountVector);
                            languageLetterCountVectors.forEach(txt -> {
                                int letterCount =0;
                                for(int number : txt) {
                                    letterCount+= number;
                                }
                                for(int i=0; i< txt.size(); i++){
                                    letterFrequencyVector.set(i, (double)txt.get(i)/letterCount);
                                }
                            });
                            languageLetterFrequencyVectors.add(letterFrequencyVector);

                            languageToVectorsMap.put(file.getName(), languageLetterFrequencyVectors);

                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Double> convertSentenceToVector(String data){
        ArrayList<Integer> letterCountVector = new ArrayList<>(Collections.nCopies(26, 0));
        ArrayList<Double> letterFrequencyVector = new ArrayList<>(Collections.nCopies(26, 0.0));

        countLetters(data, letterCountVector);

        int sum = 0;
        for (Integer integer : letterCountVector) {
            sum += integer;
        }

        for (int i = 0; i < letterFrequencyVector.size(); i++) {
            letterFrequencyVector.set(i, (double)letterCountVector.get(i)/sum);
        }

        return letterFrequencyVector;

    }

    private static void countLetters(String sentence, ArrayList<Integer> lettersCountVector) {
        for(int i =0; i < sentence.length(); i++)
            switch(sentence.charAt(i)){
                case 'a' -> lettersCountVector.set(0, lettersCountVector.get(0)+1);
                case 'b' -> lettersCountVector.set(1, lettersCountVector.get(1)+1);
                case 'c' -> lettersCountVector.set(2, lettersCountVector.get(2)+1);
                case 'd' -> lettersCountVector.set(3, lettersCountVector.get(3)+1);
                case 'e' -> lettersCountVector.set(4, lettersCountVector.get(4)+1);
                case 'f' -> lettersCountVector.set(5, lettersCountVector.get(5)+1);
                case 'g' -> lettersCountVector.set(6, lettersCountVector.get(6)+1);
                case 'h' -> lettersCountVector.set(7, lettersCountVector.get(7)+1);
                case 'i' -> lettersCountVector.set(8, lettersCountVector.get(8)+1);
                case 'j' -> lettersCountVector.set(9, lettersCountVector.get(9)+1);
                case 'k' -> lettersCountVector.set(10, lettersCountVector.get(10)+1);
                case 'l' -> lettersCountVector.set(11, lettersCountVector.get(11)+1);
                case 'm' -> lettersCountVector.set(12, lettersCountVector.get(12)+1);
                case 'n' -> lettersCountVector.set(13, lettersCountVector.get(13)+1);
                case 'o' -> lettersCountVector.set(14, lettersCountVector.get(14)+1);
                case 'p' -> lettersCountVector.set(15, lettersCountVector.get(15)+1);
                case 'q' -> lettersCountVector.set(16, lettersCountVector.get(16)+1);
                case 'r' -> lettersCountVector.set(17, lettersCountVector.get(17)+1);
                case 's' -> lettersCountVector.set(18, lettersCountVector.get(18)+1);
                case 't' -> lettersCountVector.set(19, lettersCountVector.get(19)+1);
                case 'u' -> lettersCountVector.set(20, lettersCountVector.get(20)+1);
                case 'v' -> lettersCountVector.set(21, lettersCountVector.get(21)+1);
                case 'w' -> lettersCountVector.set(22, lettersCountVector.get(22)+1);
                case 'x' -> lettersCountVector.set(23, lettersCountVector.get(23)+1);
                case 'y' -> lettersCountVector.set(24, lettersCountVector.get(24)+1);
                case 'z' -> lettersCountVector.set(25, lettersCountVector.get(25)+1);
            }
    }
}
