package org.artoolkit.ar.samples.ARSimple;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import org.artoolkit.ar.base.rendering.RenderUtils;

import android.opengl.GLES10;

public class Pyramid
{
	
	private FloatBuffer vb;
	private FloatBuffer cb;
	private ByteBuffer ib;

	private float[] vp = //(x,y,z)
		{
				-20.0f, -20.0f, -20.0f, //0
				20.0f, -20.0f, -20.0f, //1
				20.0f, -20.0f, 20.0f, //2
				-20.0f, -20.0f, 20.0f, //3
				0.0f, 20.0f, 0.0f //4 pointe
		};
	private float[] colors = 
		{
				0.0f, 0.0f, 1.0f, 1.0f, //bleu
				0.0f, 1.0f, 0.0f, 1.0f, //vert
				0.0f, 0.0f, 1.0f, 1.0f, //bleu
				0.0f, 1.0f, 0.0f, 1.0f, //vert
				1.0f, 0.0f, 0.0f, 1.0f //rouge
		};
	private byte[] ind = //indices
		{
				1, 4, 2, //droite
				4, 0, 3, //gauche
				2, 4, 3, //face
				0, 4, 1	 //dos
		};

	public Pyramid()
	{
		ByteBuffer vbb = ByteBuffer.allocateDirect(vp.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		vb = vbb.asFloatBuffer();
		vb.put(vp);
		vb.position(0);
		
		ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		cb = cbb.asFloatBuffer();
		cb.put(colors);
		cb.position(0);
		
		ib = ByteBuffer.allocateDirect(ind.length);
		ib.put(ind);
		ib.position(0);
	}
	public void draw(GL10 gles)
	{
		gles.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gles.glVertexPointer(3, GL10.GL_FLOAT, 0, vb);
		gles.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gles.glColorPointer(4, GL10.GL_FLOAT, 0, cb);
		
		gles.glDrawElements(GL10.GL_TRIANGLES, ind.length, GL10.GL_UNSIGNED_BYTE, ib);
		
		gles.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gles.glDisableClientState(GL10.GL_COLOR_ARRAY);
	}
	
}