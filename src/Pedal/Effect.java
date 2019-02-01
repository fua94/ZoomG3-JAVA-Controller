package Pedal;

public class Effect {
	//VARIABLES
	private int id;
	private boolean state;
	
	//CONSTRUCTOR
	public Effect(boolean state) {
		this.state = state;
	}
	
	public Effect(int id, boolean state) {
		this.id = id;
		this.state = state;
	}

	//GETTERS	
	public int getId() {
		return id;
	}

	public boolean getState() {
		return state;
	}
	
	//SIMULATOR
	public void setId(int i) {
		id = i;
	}
	
	public void setState(boolean s) {
		state = s;
	}
}
