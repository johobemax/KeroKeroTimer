package jp.ac.bemax.kerokerotimer;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

/**
 * デジタル数字パネルのクラス
 * @author Masaaki Horikawa
 */
public class NumberView extends ImageView implements OnTouchListener{
	private int number;
	private int[] nums;
	private int base;
	private boolean changeable;

	/**
	 * コンストラクタ.
	 * @param context 	このViewを実装するコンテンツ
	 * @param attrs		main_layout.xmlから読み込まれる属性
	 */
	public NumberView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// main_Layout.xmlから、base属性値を読み込んで代入する
		base = attrs.getAttributeIntValue(null, "base", 10);

		// リスナー設定
		this.setOnTouchListener(this);

		// 初期値は0
		number = 0;

		// 0-9までのImageを読み込む
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

		// Imageをnumberの値で更新
		setImageResource(nums[number]);

		// パネルをタッチで、値の変更が可能
		changeable = true;
	}

	/**
	 * 現在のnumberを返す.
	 * @return 現在のパネルの数値
	 */
	public int getNumber(){
		return number;
	}

	/**
	 * パネルの表示値を変更する.
	 * @param _number 変更するパネルの数値
	 */
	public void setNumber(int _number){
		number = _number;
		setImageResource(nums[number]);
	}

	/** パネルの値を変更できるかどうかのフラグを設定する.
	 * @param _changeable 変更可能ならtrue
	 */
	public void setChangeOK(boolean _changeable){
		changeable = _changeable;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(changeable){
			switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:
				Rect rect = new Rect();
				getFocusedRect(rect);
				if(event.getY() < rect.centerY()){
					number = (number + base - 1) % base;
				}else{
					number = (number + 1) % base;
				}
				setImageResource(nums[number]);
				break;
			default:

			}
		}
		return false;
	}
}
