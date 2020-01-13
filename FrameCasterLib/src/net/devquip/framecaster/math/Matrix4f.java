package net.devquip.framecaster.math;

import java.nio.FloatBuffer;

import org.lwjgl.util.vector.Matrix;

public class Matrix4f {
	float[][] m;
	
	private static float m00;

	private static float m01;

	private static float m02;

	private static float m03;

	private static float m10;

	private static float m11;

	private static float m12;

	private static float m13;

	private static float m20;

	private static float m21;

	private static float m22;

	private static float m23;

	private static float m30;

	private static float m31;

	private static float m32;

	private static float m33;
	
	public Matrix4f() {
		m = new float[4][4];
	}
	
	public Matrix4f identity() {
		m[0][0] = 1;		m[0][1] = 0;		m[0][2] = 0;		m[0][4] = 0;
		m[1][0] = 0;		m[1][1] = 1;		m[1][2] = 0;		m[1][4] = 0;
		m[2][0] = 0;		m[2][1] = 0;		m[2][2] = 1;		m[2][4] = 0;
		m[3][0] = 0;		m[3][1] = 0;		m[3][2] = 0;		m[3][4] = 1;
		
		return this;
	}
	
	public Matrix4f translate(float x, float y, float z) {
		m[0][0] = 1;		m[0][1] = 0;		m[0][2] = 0;		m[0][3] = x;
		m[1][0] = 0;		m[1][1] = 1;		m[1][2] = 0;		m[1][3] = y;
		m[2][0] = 0;		m[2][1] = 0;		m[2][2] = 1;		m[2][3] = z;
		m[3][0] = 0;		m[3][1] = 0;		m[3][2] = 0;		m[3][3] = 1;
		
		return this;
	}
	public Matrix4f rotation(float x, float y, float z) {
		
		Matrix4f rx = new Matrix4f();
		Matrix4f ry = new Matrix4f();
		Matrix4f rz = new Matrix4f();
		
		x = (float) Math.toRadians(x);
		y = (float) Math.toRadians(y);
		z = (float) Math.toRadians(z);
		
		rx.m[0][0] = 1;		rx.m[0][1] = 0;		rx.m[0][2] = 0;		rx.m[0][3] = 0;
		rx.m[1][0] = 0;		rx.m[1][1] = (float)Math.cos(x);		rx.m[1][2] = -(float)Math.sin(x);		rx.m[1][3] = 0;
		rx.m[2][0] = 0;		rx.m[2][1] = (float)Math.sin(x);		rx.m[2][2] = (float)Math.cos(x);		rx.m[2][3] = 0;
		rx.m[3][0] = 0;		rx.m[3][1] = 0;		rx.m[3][2] = 0;		rx.m[3][3] = 1;
		
		ry.m[0][0] = (float)Math.cos(y);		ry.m[0][1] = 0;		ry.m[0][2] = -(float)Math.sin(y);		ry.m[0][3] = 0;
		ry.m[1][0] = 0;		ry.m[1][1] = 1;		ry.m[1][2] = 0;		ry.m[1][3] = 0;
		ry.m[2][0] = (float)Math.sin(y);		ry.m[2][1] = 0;		ry.m[2][2] = (float)Math.cos(y);		ry.m[2][3] = 0;
		ry.m[3][0] = 0;		ry.m[3][1] = 0;		ry.m[3][2] = 0;		ry.m[3][3] = 1;
		
		rz.m[0][0] = (float)Math.cos(z);		rz.m[0][1] = -(float)Math.sin(z);		rz.m[0][2] = 0;		rz.m[0][3] = 0;
		rz.m[1][0] = (float)Math.sin(z);		rz.m[1][1] = (float)Math.cos(z);		rz.m[1][2] = 0;		rz.m[1][3] = 0;
		rz.m[2][0] = 0;		rz.m[2][1] = 0;		rz.m[2][2] = 1;		rz.m[2][3] = 0;
		rz.m[3][0] = 0;		rz.m[3][1] = 0;		rz.m[3][2] = 0;		rz.m[3][3] = 1;
		
		m = rz.mul(ry.mul(rx)).getM();
		
		return this;
	}
	
	public Matrix4f scale(float x, float y, float z) {
		m[0][0] = x;		m[0][1] = 0;		m[0][2] = 0;		m[0][3] = x;
		m[1][0] = 0;		m[1][1] = y;		m[1][2] = 0;		m[1][3] = y;
		m[2][0] = 0;		m[2][1] = 0;		m[2][2] = z;		m[2][3] = z;
		m[3][0] = 0;		m[3][1] = 0;		m[3][2] = 0;		m[3][3] = 1;
		
		return this;
	}
	
	public Matrix4f getProjection(float fov, float ar, float zNear, float zFar) {
		float FOV = (float) Math.tan(Math.toRadians(fov / 2));
		float r = zNear - zFar;
		
		m[0][0] = 1.0f / (FOV * ar);		m[0][1] = 0;		m[0][2] = 0;		m[0][3] = 0;
		m[1][0] = 0;						m[1][1] = 1.0f / FOV;m[1][2] = 0;		m[1][3] = 0;
		m[2][0] = 0;						m[2][1] = 0;		m[2][2] = (-zNear - zFar) / r;		m[2][3] = 2 * zFar * zNear / r;
		m[3][0] = 0;						m[3][1] = 0;		m[3][2] = 1;		m[3][3] = 0;
		
		return this;
	}
	
	public Matrix4f mul(Matrix4f v) {
		Matrix4f r = new Matrix4f();
		for(int x = 0; x < 4; x++) {
			for(int y = 0; y < 4; y++) {
				r.setM(x, y, getM(x, 0) * v.getM(0, y) +
							 getM(x, 1) * v.getM(1, y) +
							 getM(x, 2) * v.getM(2, y) +
						     getM(x, 3) * v.getM(3, y));
			}
		}
		
		return r;
	}
	
	public float[][] getM() {
		return m;
	}

	public float getM(int x, int y) {
		return m[x][y];
	}

	public void setM(int x, int y, float v) {
		m[x][y] = v;
	}

	public float determinant() {
		return 0;
	}
	
	private static float determinant3x3(float t00, float t01, float t02,
				     float t10, float t11, float t12,
				     float t20, float t21, float t22)
	{
		return   t00 * (t11 * t22 - t12 * t21)
		       + t01 * (t12 * t20 - t10 * t22)
		       + t02 * (t10 * t21 - t11 * t20);
	}

	@SuppressWarnings("static-access")
	public Matrix invert(Matrix4f src, Matrix4f dest) {
		float determinant = src.determinant();

		if (determinant != 0) {
			/*
			 * m00 m01 m02 m03
			 * m10 m11 m12 m13
			 * m20 m21 m22 m23
			 * m30 m31 m32 m33
			 */
			if (dest == null)
				dest = new Matrix4f();
			float determinant_inv = 1f/determinant;

			// first row
			float t00 =  determinant3x3(src.m11, src.m12, src.m13, src.m21, src.m22, src.m23, src.m31, src.m32, src.m33);
			float t01 = -determinant3x3(src.m10, src.m12, src.m13, src.m20, src.m22, src.m23, src.m30, src.m32, src.m33);
			float t02 =  determinant3x3(src.m10, src.m11, src.m13, src.m20, src.m21, src.m23, src.m30, src.m31, src.m33);
			float t03 = -determinant3x3(src.m10, src.m11, src.m12, src.m20, src.m21, src.m22, src.m30, src.m31, src.m32);
			// second row
			float t10 = -determinant3x3(src.m01, src.m02, src.m03, src.m21, src.m22, src.m23, src.m31, src.m32, src.m33);
			float t11 =  determinant3x3(src.m00, src.m02, src.m03, src.m20, src.m22, src.m23, src.m30, src.m32, src.m33);
			float t12 = -determinant3x3(src.m00, src.m01, src.m03, src.m20, src.m21, src.m23, src.m30, src.m31, src.m33);
			float t13 =  determinant3x3(src.m00, src.m01, src.m02, src.m20, src.m21, src.m22, src.m30, src.m31, src.m32);
			// third row
			float t20 =  determinant3x3(src.m01, src.m02, src.m03, src.m11, src.m12, src.m13, src.m31, src.m32, src.m33);
			float t21 = -determinant3x3(src.m00, src.m02, src.m03, src.m10, src.m12, src.m13, src.m30, src.m32, src.m33);
			float t22 =  determinant3x3(src.m00, src.m01, src.m03, src.m10, src.m11, src.m13, src.m30, src.m31, src.m33);
			float t23 = -determinant3x3(src.m00, src.m01, src.m02, src.m10, src.m11, src.m12, src.m30, src.m31, src.m32);
			// fourth row
			float t30 = -determinant3x3(src.m01, src.m02, src.m03, src.m11, src.m12, src.m13, src.m21, src.m22, src.m23);
			float t31 =  determinant3x3(src.m00, src.m02, src.m03, src.m10, src.m12, src.m13, src.m20, src.m22, src.m23);
			float t32 = -determinant3x3(src.m00, src.m01, src.m03, src.m10, src.m11, src.m13, src.m20, src.m21, src.m23);
			float t33 =  determinant3x3(src.m00, src.m01, src.m02, src.m10, src.m11, src.m12, src.m20, src.m21, src.m22);

			// transpose and divide by the determinant
			dest.m00 = t00*determinant_inv;
			dest.m11 = t11*determinant_inv;
			dest.m22 = t22*determinant_inv;
			dest.m33 = t33*determinant_inv;
			dest.m01 = t10*determinant_inv;
			dest.m10 = t01*determinant_inv;
			dest.m20 = t02*determinant_inv;
			dest.m02 = t20*determinant_inv;
			dest.m12 = t21*determinant_inv;
			dest.m21 = t12*determinant_inv;
			dest.m03 = t30*determinant_inv;
			dest.m30 = t03*determinant_inv;
			dest.m13 = t31*determinant_inv;
			dest.m31 = t13*determinant_inv;
			dest.m32 = t23*determinant_inv;
			dest.m23 = t32*determinant_inv;
			return null;
		} else
			return null;
	}
	
	@SuppressWarnings("static-access")
	public static Matrix4f load(Matrix4f src, Matrix4f dest) {
		if (dest == null)
			dest = new Matrix4f();
		dest.m00 = src.m00;
		dest.m01 = src.m01;
		dest.m02 = src.m02;
		dest.m03 = src.m03;
		dest.m10 = src.m10;
		dest.m11 = src.m11;
		dest.m12 = src.m12;
		dest.m13 = src.m13;
		dest.m20 = src.m20;
		dest.m21 = src.m21;
		dest.m22 = src.m22;
		dest.m23 = src.m23;
		dest.m30 = src.m30;
		dest.m31 = src.m31;
		dest.m32 = src.m32;
		dest.m33 = src.m33;

		return dest;
	}
	
	public static Matrix loadTranspose(FloatBuffer buf) {
		m00 = buf.get();
		m10 = buf.get();
		m20 = buf.get();
		m30 = buf.get();
		m01 = buf.get();
		m11 = buf.get();
		m21 = buf.get();
		m31 = buf.get();
		m02 = buf.get();
		m12 = buf.get();
		m22 = buf.get();
		m32 = buf.get();
		m03 = buf.get();
		m13 = buf.get();
		m23 = buf.get();
		m33 = buf.get();

		return null;
	}
	
	@SuppressWarnings("static-access")
	public static Matrix negate(Matrix4f src, Matrix4f dest) {
		if (dest == null)
			dest = new Matrix4f();

		dest.m00 = -src.m00;
		dest.m01 = -src.m01;
		dest.m02 = -src.m02;
		dest.m03 = -src.m03;
		dest.m10 = -src.m10;
		dest.m11 = -src.m11;
		dest.m12 = -src.m12;
		dest.m13 = -src.m13;
		dest.m20 = -src.m20;
		dest.m21 = -src.m21;
		dest.m22 = -src.m22;
		dest.m23 = -src.m23;
		dest.m30 = -src.m30;
		dest.m31 = -src.m31;
		dest.m32 = -src.m32;
		dest.m33 = -src.m33;

		return null;
	}

	public static Matrix setIdentity() {
		return null;
	}
	
	@SuppressWarnings("static-access")
	public static Matrix4f setZero(Matrix4f m) {
		m.m00 = 0.0f;
		m.m01 = 0.0f;
		m.m02 = 0.0f;
		m.m03 = 0.0f;
		m.m10 = 0.0f;
		m.m11 = 0.0f;
		m.m12 = 0.0f;
		m.m13 = 0.0f;
		m.m20 = 0.0f;
		m.m21 = 0.0f;
		m.m22 = 0.0f;
		m.m23 = 0.0f;
		m.m30 = 0.0f;
		m.m31 = 0.0f;
		m.m32 = 0.0f;
		m.m33 = 0.0f;

		return m;
	}
	
	public static Matrix store(FloatBuffer buf) {
		buf.put(m00);
		buf.put(m01);
		buf.put(m02);
		buf.put(m03);
		buf.put(m10);
		buf.put(m11);
		buf.put(m12);
		buf.put(m13);
		buf.put(m20);
		buf.put(m21);
		buf.put(m22);
		buf.put(m23);
		buf.put(m30);
		buf.put(m31);
		buf.put(m32);
		buf.put(m33);
		return null;
	}
	
	public static Matrix storeTranspose(FloatBuffer buf) {
		buf.put(m00);
		buf.put(m10);
		buf.put(m20);
		buf.put(m30);
		buf.put(m01);
		buf.put(m11);
		buf.put(m21);
		buf.put(m31);
		buf.put(m02);
		buf.put(m12);
		buf.put(m22);
		buf.put(m32);
		buf.put(m03);
		buf.put(m13);
		buf.put(m23);
		buf.put(m33);
		return null;
	}
	
	public static Matrix transpose() {
		return transpose();
	}
	
	public static Matrix load(Matrix4f src) {
		return load(src);
	}
	
	public static Matrix setZero() {
		return null;
	}
}