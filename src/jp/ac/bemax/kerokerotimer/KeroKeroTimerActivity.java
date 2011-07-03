package jp.ac.bemax.kerokerotimer;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceView;

import android.widget.ImageView;

public class KeroKeroTimerActivity extends Activity {
    /** Called when the activity is first created. */
	private TimerLogic logic;
	private NumberView[] numberViews;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        numberViews = new NumberView[5];

        numberViews[0] = (NumberView)findViewById(R.id.hour1);
        numberViews[1] = (NumberView)findViewById(R.id.min1);
        numberViews[2] = (NumberView)findViewById(R.id.min2);
        numberViews[3] = (NumberView)findViewById(R.id.sec1);
        numberViews[4] = (NumberView)findViewById(R.id.sec2);

        numberViews[0].setMax(2);
        numberViews[1].setMax(6);
        numberViews[2].setMax(10);
        numberViews[3].setMax(6);
        numberViews[4].setMax(10);

        SurfaceView sw = (SurfaceView)findViewById(R.id.surfaceView);

        logic = new TimerLogic(numberViews);


    }

	@Override
	protected void onStart() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStart();

		Thread t = new Thread(){
			public void run(){
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				logic.startTimer();
			}
		};

		t.start();
	}

}