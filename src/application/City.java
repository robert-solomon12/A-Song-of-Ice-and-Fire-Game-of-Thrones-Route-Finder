package application;



public class City {
	public String cityName;
	public double x;
	public double y;

	

	
	public City(String cityName,double x,double y) {
		this.cityName = cityName;
		this.x = x;
		this.y = y;
	}
	


	public String getCityName() {
		return cityName;
	}

	public void setCity(String cityName) {
		this.cityName = cityName;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
