package com.example.ismygblur;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;

public class GBlurPic {

	private Bitmap mBitmap;

	private RenderScript mRS;
	private Allocation mInAllocation;
	private Allocation mOutAllocation;
	private ScriptIntrinsicBlur mBlur;

	public GBlurPic(Context context) {
		super();
		this.mRS = RenderScript.create(context);
		this.mBlur = ScriptIntrinsicBlur.create(mRS, Element.U8_4(mRS));
	}

	public Bitmap gBlurBitmap(Bitmap bitmap, float radius) {
		if (mBitmap != null) {
			mBitmap.recycle();
			mBitmap = null;
		}
		mBitmap = bitmap.copy(bitmap.getConfig(), true);

		mInAllocation = null;
		mOutAllocation = null;
		mInAllocation = Allocation.createFromBitmap(mRS, mBitmap,
				Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);
		mOutAllocation = Allocation.createTyped(mRS, mInAllocation.getType());

		mBlur.setRadius(radius);
		mBlur.setInput(mInAllocation);
		mBlur.forEach(mOutAllocation);

		mOutAllocation.copyTo(mBitmap);

		return mBitmap;
	}

}
