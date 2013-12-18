package com.example.ismygblur;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private int n = 1;

	private GBlurPic mGBlurPic;

	private Bitmap mBitmapIn;
	private Bitmap mBitmapOut;

	private ImageView in;
	private ImageView out;
	private Button add;
	private Button sub;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mGBlurPic = new GBlurPic(this);

		mBitmapIn = loadBitmap(R.drawable.gblur);
		mBitmapOut = Bitmap.createBitmap(mBitmapIn.getWidth(),
				mBitmapIn.getHeight(), mBitmapIn.getConfig());

		in = (ImageView) findViewById(R.id.displayin);
		in.setImageBitmap(mBitmapIn);

		out = (ImageView) findViewById(R.id.displayout);
		out.setImageBitmap(mBitmapIn);

		add = (Button) findViewById(R.id.add);
		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateScript(getResources().getString(R.string.add));
			}
		});

		sub = (Button) findViewById(R.id.sub);
		sub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateScript(getResources().getString(R.string.sub));
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void updateScript(String op) {
		if (op.equals(getResources().getString(R.string.add))) {
			if (n < 25) {
				++n;
			} else {
				Toast.makeText(this, getResources().getString(R.string.range),
						Toast.LENGTH_SHORT).show();
			}
		} else if (op.equals(getResources().getString(R.string.sub))) {
			if (n > 1) {
				--n;
			} else {
				Toast.makeText(this, getResources().getString(R.string.range),
						Toast.LENGTH_SHORT).show();
			}
		}
		mBitmapOut = mGBlurPic.gBlurBitmap(mBitmapIn, n);
		out.setImageBitmap(mBitmapOut);
	}

	private Bitmap loadBitmap(int resource) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		return BitmapFactory.decodeResource(getResources(), resource, options);
	}

}
