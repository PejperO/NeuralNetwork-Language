import java.util.*;

public class Main {
    public static void main(String[] args) {
        Util.fileSelector("trainData");
        Map<String, ArrayList<ArrayList<Double>>> trainDataMap = Util.doubleFolderMap;

        ArrayList<Perceptron> perceptronArrayList = new ArrayList<>();
        trainDataMap.forEach((lang, data) -> perceptronArrayList.add(new Perceptron(new ArrayList<>(Collections.nCopies(26, 0.1)), lang)));

        for(int i=0; i< 5; i++){
            perceptronArrayList.forEach(perceptron -> trainDataMap.forEach((lang, data) -> data.forEach((txt) -> {
                ArrayList<Double> tmpIntList = new ArrayList<>(txt);
                tmpIntList.add(-1.0);
                perceptron.teach(tmpIntList, 0.1, lang);
            })));
        }

        /*Util.doubleFolderMap = new HashMap<>();
        Util.fileSelector("testData");
        Map<String, ArrayList<ArrayList<Double>>> testDataMap = Util.doubleFolderMap;

        testDataMap.forEach((lang, value) -> {
            System.out.println(lang+ " test");
            perceptronArrayList.forEach(perceptron -> value.forEach(row -> System.out.println(perceptron.className + " " + perceptron.getOutput(row))));
            System.out.println();
        });*/

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();

        System.out.print("The sentence is written in ");

        double maxActivation = 0.0;
        String mostActivePerceptronClassName = "";

        for (Perceptron perceptron : perceptronArrayList) {
            double activationValue = perceptron.getOutput(Util.convertData(sentence));
            if ((activationValue > maxActivation)) {
                maxActivation = activationValue;
                mostActivePerceptronClassName = perceptron.className;
            }
        }

        System.out.print(mostActivePerceptronClassName);
    }
}