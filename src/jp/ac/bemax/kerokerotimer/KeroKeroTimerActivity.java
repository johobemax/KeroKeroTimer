package jp.ac.bemax.kerokerotimer;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceView;

/**
 *
 * @author Masaaki Horikawa
 *
 */
public class KeroKeroTimerActivity extends Activity {
    /**
     * KeroKeroTimerが起動されたとき、最初に呼び出されるクラス.
     */

	private TimerLogic timerLogic;
	private DigitalPanel digitalPanel;
	private SurfaceView analogView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // main_layout.xmlを反映させる
        setContentView(R.layout.main_layout);

        // digitalPanelを初期化
        digitalPanel = new DigitalPanel(findViewById(R.id.digital_panel));

        // analogPanelを初期化
        analogView = (SurfaceView)findViewById(R.id.analogView);

        // タイマーを初期化
        timerLogic = new TimerLogic(digitalPanel);

        PlayButton playButton = new PlayButton(timerLogic, findViewById(R.id.play_button));
    }

	@Override
	protected void onStart() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStart();

	}

}