package unit3_df;

public class WeightRanges1 {
	double height = 10;
	double weight = 10;

	public static double getBMI(WeightRanges1 nomalWeight) {
		return nomalWeight.weight / (nomalWeight.height * 0.01) / (nomalWeight.height * 0.01);
	}

	public static double getHealthWeight(WeightRanges1 nomalWeight) {
		return (nomalWeight.height * 0.01) * (nomalWeight.height * 0.01) * 22;
	}

	public static void main(String[] args) {
		WeightRanges1 nomalWeight = new WeightRanges1();
		nomalWeight.height = 160;
		nomalWeight.weight = 50;
		System.out.println("Your BMI is " + WeightRanges1.getBMI(nomalWeight));
		System.out.println("Your ideal weight is " + WeightRanges1.getHealthWeight(nomalWeight));
		
		nomalWeight.height = 150;
		nomalWeight.weight = 40;
		System.out.println("Your BMI is " + WeightRanges1.getBMI(nomalWeight));
		System.out.println("Your ideal weight is " + WeightRanges1.getHealthWeight(nomalWeight));
	}
}
