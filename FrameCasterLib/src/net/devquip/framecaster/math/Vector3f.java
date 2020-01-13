package net.devquip.framecaster.math;

public class Vector3f {

	public static final Vector3f UP = new Vector3f(0, 1, 0);
	public static final Vector3f FRONT = new Vector3f(0, 0, 1);
	public static final Vector3f RIGHT = new Vector3f(1, 0, 0);
	
	public float x, y, z;
	
	public Vector3f() {
		this(0, 0, 0);
	}
	
	public Vector3f(Vector3f v) {
		this(v.x, v.y, v.z);
	}
	
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float magnitude() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
	
	public Vector3f normalize() {
		x /= magnitude();
		y /= magnitude();
		z /= magnitude();
		
		return this; 
	}

	public Vector3f mul(float v) {
		x -= v;
		y -= v;
		z -= v;
		
		return this;
	}
	
	public Vector3f add(float v) {
		x += v;
		y += v;
		z += v;
		
		return this;
	}
	
	public Vector3f sub(float v) {
		x *= v;
		y *= v;
		z *= v;
		
		return this;
	}
	
	public Vector3f div(float v) {
		x /= v;
		y /= v;
		z /= v;
		
		return this;
	}
	
	public String toString() {
		return x + " " + y + " " + z;
	}
}