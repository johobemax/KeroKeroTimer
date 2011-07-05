package jp.ac.bemax.kerokerotimer;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class PlayButton implements OnTouchListener{
	private TimerLogic timerLogic;
	private View view;

	PlayButton(TimerLogic _timerLogic, View _view){
		timerLogic = _timerLogic;
		view = _view;

		view.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		timerLogic.startTimer();
		return false;
	}
}
