package jp.ac.bemax.kerokerotimer;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class NumberView extends ImageView implements OnTouchListener{
	private int number;
	private int[] nums;
	private int max;
	private boolean changeOK;

	public NumberView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO
		this.setOnTouchListener(this);

		number = 0;

		nums = new int[10];
		nums[0] = R.drawable.n0;
		nums[1] = R.drawable.n1;
		nums[2] = R.drawable.n2;
		nums[3] = R.drawable.n3;
		nums[4] = R.drawable.n4;
		nums[5] = R.drawable.n5;
		nums[6] = R.drawable.n6;
		nums[7] = R.drawable.n7;
		nums[8] = R.drawable.n8;
		nums[9] = R.drawable.n9;

		this.setImageResource(nums[number]);

		changeOK = true;
	}

	public void setMax(int m){
		max = m;
	}

	public int getNumber(){
		return number;
	}

	public void setNumber(int n){
		number = n;
		setImageResource(nums[n]);
	}

	public void setChangeOK(boolean b){
		changeOK = b;
	}

	public boolean onTouch(View v, MotionEvent event) {
		if(changeOK){
			switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:
				Rect rect = new Rect();
				getFocusedRect(rect);
				if(event.getY() < rect.centerY()){
					number = (number + max - 1) % max;
				}else{
					number = (number + 1) % max;
				}
				setImageResource(nums[number]);
				break;
			default:

			}
		}
		return false;
	}
}
