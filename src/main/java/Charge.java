public class Charge {
	String type;
	float daily;
	boolean weekday;
	boolean weekend;
	boolean holiday;
	public Charge(){
		type="";
		daily=0;
		weekday=true;
		weekend=false;
		holiday=false;
	}
	public Charge(String tp, float dly, boolean wkdy,boolean wknd, boolean holi){
		type = tp;
		daily= dly;
		weekday = wkdy;
		weekend = wknd;
		holiday = holi;
	}
	public String getType(){
	return type;
	}
	public float getDaily(){
		return daily;
	}
	public boolean getWeekday(){
		return weekday;
	}
	public boolean getWeekend(){
		return weekend;
	}
	public boolean getHoliday(){
		return holiday;
	}
}

