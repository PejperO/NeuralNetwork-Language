import java.io.*;
import java.util.*;

public class Util {
    public static Map<String, ArrayList<ArrayList<Double>>> doubleFolderMap = new HashMap<>();

    public static void fileSelector(String dirPathname){
        File directory = new File(dirPathname);

        if(!directory.isDirectory())
            System.out.println(dirPathname + " is not directory");
        else
            getDataFromDirectory(directory);
    }

    public static void getDataFromDirectory(File directory)  {
        try{
            File[] files = directory.listFiles();

            assert files != null;
            for (File file : files) {
                if(file.isDirectory()){
                    File[] txtFiles = file.listFiles();
                    ArrayList<ArrayList<Integer>> languageData = new ArrayList<>();
                    ArrayList<ArrayList<Double>> doubleLanguageData = new ArrayList<>();

                    assert txtFiles != null;
                    Arrays.stream(txtFiles).forEach(txtFile -> {
                        try {
                            BufferedReader reader = new BufferedReader(new FileReader(txtFile));
                            ArrayList<Integer> valueList = new ArrayList<>(Collections.nCopies(26, 0));
                            ArrayList<Double> doubleValueList = new ArrayList<>(Collections.nCopies(26, 0.0));
                            String line;
                            while ((line = reader.readLine()) != null){
                                for(int i =0; i < line.length(); i++)
                                    switch(line.charAt(i)){
                                        case 'a' -> valueList.set(0, valueList.get(0)+1);
                                        case 'b' -> valueList.set(1, valueList.get(1)+1);
                                        case 'c' -> valueList.set(2, valueList.get(2)+1);
                                        case 'd' -> valueList.set(3, valueList.get(3)+1);
                                        case 'e' -> valueList.set(4, valueList.get(4)+1);
                                        case 'f' -> valueList.set(5, valueList.get(5)+1);
                                        case 'g' -> valueList.set(6, valueList.get(6)+1);
                                        case 'h' -> valueList.set(7, valueList.get(7)+1);
                                        case 'i' -> valueList.set(8, valueList.get(8)+1);
                                        case 'j' -> valueList.set(9, valueList.get(9)+1);
                                        case 'k' -> valueList.set(10, valueList.get(10)+1);
                                        case 'l' -> valueList.set(11, valueList.get(11)+1);
                                        case 'm' -> valueList.set(12, valueList.get(12)+1);
                                        case 'n' -> valueList.set(13, valueList.get(13)+1);
                                        case 'o' -> valueList.set(14, valueList.get(14)+1);
                                        case 'p' -> valueList.set(15, valueList.get(15)+1);
                                        case 'q' -> valueList.set(16, valueList.get(16)+1);
                                        case 'r' -> valueList.set(17, valueList.get(17)+1);
                                        case 's' -> valueList.set(18, valueList.get(18)+1);
                                        case 't' -> valueList.set(19, valueList.get(19)+1);
                                        case 'u' -> valueList.set(20, valueList.get(20)+1);
                                        case 'v' -> valueList.set(21, valueList.get(21)+1);
                                        case 'w' -> valueList.set(22, valueList.get(22)+1);
                                        case 'x' -> valueList.set(23, valueList.get(23)+1);
                                        case 'y' -> valueList.set(24, valueList.get(24)+1);
                                        case 'z' -> valueList.set(25, valueList.get(25)+1);
                                    }
                            }

                            languageData.add(valueList);
                            languageData.forEach(txt -> {
                                int letterCount =0;
                                for(int number : txt) {
                                    letterCount+= number;
                                }
                                for(int i=0; i< txt.size(); i++){
                                    doubleValueList.set(i, (double)txt.get(i)/letterCount);
                                }
                            });
                            doubleLanguageData.add(doubleValueList);

                            doubleFolderMap.put(file.getName(), doubleLanguageData);

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

    public static ArrayList<Double> convertData(String data){
        ArrayList<Integer> valueList = new ArrayList<>(Collections.nCopies(26, 0));
        ArrayList<Double> doubleValueList = new ArrayList<>(Collections.nCopies(26, 0.0));

        for(int i =0; i < data.length(); i++)
            switch(data.charAt(i)){
                case 'a' -> valueList.set(0, valueList.get(0)+1);
                case 'b' -> valueList.set(1, valueList.get(1)+1);
                case 'c' -> valueList.set(2, valueList.get(2)+1);
                case 'd' -> valueList.set(3, valueList.get(3)+1);
                case 'e' -> valueList.set(4, valueList.get(4)+1);
                case 'f' -> valueList.set(5, valueList.get(5)+1);
                case 'g' -> valueList.set(6, valueList.get(6)+1);
                case 'h' -> valueList.set(7, valueList.get(7)+1);
                case 'i' -> valueList.set(8, valueList.get(8)+1);
                case 'j' -> valueList.set(9, valueList.get(9)+1);
                case 'k' -> valueList.set(10, valueList.get(10)+1);
                case 'l' -> valueList.set(11, valueList.get(11)+1);
                case 'm' -> valueList.set(12, valueList.get(12)+1);
                case 'n' -> valueList.set(13, valueList.get(13)+1);
                case 'o' -> valueList.set(14, valueList.get(14)+1);
                case 'p' -> valueList.set(15, valueList.get(15)+1);
                case 'q' -> valueList.set(16, valueList.get(16)+1);
                case 'r' -> valueList.set(17, valueList.get(17)+1);
                case 's' -> valueList.set(18, valueList.get(18)+1);
                case 't' -> valueList.set(19, valueList.get(19)+1);
                case 'u' -> valueList.set(20, valueList.get(20)+1);
                case 'v' -> valueList.set(21, valueList.get(21)+1);
                case 'w' -> valueList.set(22, valueList.get(22)+1);
                case 'x' -> valueList.set(23, valueList.get(23)+1);
                case 'y' -> valueList.set(24, valueList.get(24)+1);
                case 'z' -> valueList.set(25, valueList.get(25)+1);
            }

        int sum = 0;
        for (Integer integer : valueList) {
            sum += integer;
        }

        for (int i = 0; i < doubleValueList.size(); i++) {
            doubleValueList.set(i, (double)valueList.get(i)/sum);
        }

        return doubleValueList;
    }
}
