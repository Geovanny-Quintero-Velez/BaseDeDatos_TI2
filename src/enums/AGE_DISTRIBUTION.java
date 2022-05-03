package enums;

public enum AGE_DISTRIBUTION {
	_0_TO_14_(0, 14, 0.1862), _15_TO_24(15, 24, 0.1312), _25_TO_54_(25, 54, 0.3929), _55_TO_64_(55, 64, 0.1294), _65_TO_MORE_(65, 80, 0.1603);
	
	private int min;
	private int max;
	private double distribution;
	
	AGE_DISTRIBUTION(int min, int max, double distribution){
		this.min = min;
		this.max = max;
		this.distribution = distribution;
	}
	
	public int getMin() { return min; }
	public int getMax() { return max; }
	public double getDistribution() { return distribution; }
}
