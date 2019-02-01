package Pedal;

public class Patch {
	
	//VARIABLES
	private String name;
	private int number;
	private Effect[] effects;
	
	//CONSTRUCTORES
	public Patch(int number) {
		this.number = number;
		effects = new Effect[6];
	}
	
	public Patch(int number, Effect[] effects) {
		this.number = number;
		this.effects = effects;
	}
	
	public Patch(String name, int number, Effect[] effects) {
		this.name = name;
		this.number = number;
		this.effects = effects;
	}

	//GETTERS
	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public Effect getEffects(int index) {
		return effects[index];
	}
	
	//SETTERS
	public void setState(int i, boolean s){
		effects[i] = new Effect(s);
	}
	
	public void setName(String n){
		name = n;
	}
}
