import java.util.ArrayList;

public class Perceptron {
    private ArrayList<Double> weights;
    private final String className;

    public String getClassName() {
        return className;
    }

    public Perceptron (ArrayList<Double> weights, double threshold, String className){
        this.weights = weights;
        this.weights.add(threshold);
        this.className=className;
    }

    public Double getOutput(ArrayList<Double> input){
        double sum = 0.0;

        for(int i=0; i<input.size(); i++){
            sum+= weights.get(i)*input.get(i);
        }

        if(sum >= -1 && sum <= 1)
            return sum;
        else {
            if (sum < 0)
                return -1.0;
            if (sum > 0)
                return 1.0;
        }

        return sum;
    }

    public void teach(ArrayList<Double> input, Double constant, String correctLanguage){
        ArrayList<Double> newWeights = new ArrayList<>();

        if(this.className.equals(correctLanguage)) {
            for (int i = 0; i < weights.size() ; i++)
                newWeights.add(weights.get(i) + (1 - this.getOutput(input)) * constant * input.get(i));
        }else{
            for (int i = 0; i < weights.size() ; i++)
                newWeights.add(weights.get(i) + (-1 - this.getOutput(input)) * constant * input.get(i));
        }
        this.weights = newWeights;
    }

    public String toString(){
        StringBuilder tmp = new StringBuilder("Wagi: ");
        for(int i = 0; i< weights.size()-1; i++)
            tmp.append(weights.get(i)).append(" ");
        tmp.append("Próg: ").append(weights.get(weights.size() - 1));
        return tmp.toString();
    }


}