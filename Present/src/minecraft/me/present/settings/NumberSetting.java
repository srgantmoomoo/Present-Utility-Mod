package me.present.settings;

public class NumberSetting extends Setting {
	
	public double value, minimun, maximum, increment;

	public NumberSetting(String name, double value, double minimun, double maximum, double increment) {
		this.name = name;
		this.value = value;
		this.minimun = minimun;
		this.maximum = maximum;
		this.increment = increment;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		double precision = 1 / increment;
		this.value = Math.round(Math.max(minimun, Math.min(maximum, value)) * precision) / precision;
	}
	
	public void increment(boolean positive) {
		setValue(getValue() + (positive ? 1 : -1) * increment);
	}

	public double getMinimun() {
		return minimun;
	}

	public void setMinimun(double minimun) {
		this.minimun = minimun;
	}

	public double getMaximum() {
		return maximum;
	}

	public void setMaximum(double maximum) {
		this.maximum = maximum;
	}

	public double getIncrement() {
		return increment;
	}

	public void setIncrement(double increment) {
		this.increment = increment;
	}

}
