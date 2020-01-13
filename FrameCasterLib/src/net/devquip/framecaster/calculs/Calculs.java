package net.devquip.framecaster.calculs;

public class Calculs {
	
	public static void calculPlus(int var, int var1) {
		System.out.println(var + var1);
	}
	
	public static void calculPlus3(int var, int var1, int var2) {
		System.out.println(var + var1 + var2);
	}
	
	public static void calculDiv(int var, int var1) {
		System.out.println(var / var1);
	}
	
	public static void calculDiv3(int var, int var1, int var2) {
		System.out.println(var / var1 / var2);
	}
	
	public static void calculMul(int var, int var1) {
		System.out.println(var * var1);
	}
	
	public static void calculMul3(int var, int var1, int var2) {
		System.out.println(var * var1 * var2);
	}
	
	public static void calculSquare(int var) {
		System.out.println(Math.sqrt(var));
	}
	
	public static void sayPI() {
		System.out.println(" PI = "+Math.PI);
	}
	
	public static void randomNumber() {
		System.out.println(+Math.random());
	}
}