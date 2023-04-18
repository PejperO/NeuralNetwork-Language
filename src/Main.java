import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        //loading train data
        Util.getDataFromDirectory(new File("trainData"));
        Map<String, ArrayList<ArrayList<Double>>> trainDataMap = Util.languageToVectorsMap;

        ArrayList<Perceptron> perceptrons = new ArrayList<>();
        trainDataMap.forEach((lang, data) -> {
            perceptrons.add(new Perceptron(new ArrayList<>(Collections.nCopies(26, 0.1)), 0.2, lang));
        });

        //training
        for(int i=0; i< 5000; i++){
            perceptrons.forEach(perceptron -> trainDataMap.forEach((lang, data) -> data.forEach((txt) -> {
                ArrayList<Double> tmpIntList = new ArrayList<>(txt);
                tmpIntList.add(-1.0);
                perceptron.teach(tmpIntList, 0.1, lang);
            })));
        }

        //loading test data
        Util.languageToVectorsMap = new HashMap<>();
        Util.getDataFromDirectory(new File("testData"));
        Map<String, ArrayList<ArrayList<Double>>> testDataMap = Util.languageToVectorsMap;

        //testing
        for (int i = 0; i < testDataMap.size(); i++) {
            System.out.println((new Locale( testDataMap.keySet().toArray()[i].toString()).getDisplayLanguage(new Locale("en")) + " test:"));
            ArrayList<ArrayList<Double>> languageVectors = (ArrayList<ArrayList<Double>>) testDataMap.values().toArray()[i];
            for (int j = 0; j < languageVectors.size(); j++) {
                String maxActivatedPerceptronClassName = predict(perceptrons, languageVectors.get(j));
                System.out.println(j + ". " + (testDataMap.keySet().toArray()[i].equals(maxActivatedPerceptronClassName) ? "correct" : "incorrect"));
            }
            System.out.println();
        }


        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a sentence: ");
            String sentence = scanner.nextLine();
            System.out.print("Your sentence is written in " +  (new Locale(predict(perceptrons, Util.convertSentenceToVector(sentence)) )).getDisplayLanguage(new Locale("en")) + "\n\n");
        }

    }

    private static String predict(ArrayList<Perceptron> perceptrons, ArrayList<Double> input) {
        double maxActivation = 0.0;
        String mostActivePerceptronClassName = "";
        for (Perceptron perceptron : perceptrons) {
            double activationValue = perceptron.getOutput(input);
            if ((activationValue > maxActivation)) {
                maxActivation = activationValue;
                mostActivePerceptronClassName = perceptron.getClassName();
            }
        }
        return mostActivePerceptronClassName;
    }


}