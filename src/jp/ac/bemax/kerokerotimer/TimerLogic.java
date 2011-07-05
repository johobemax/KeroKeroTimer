package jp.ac.bemax.kerokerotimer;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;

enum Condition {STOP, PLAY, PAUSE};

/**
 *
 * @author Masaaki Horikawa
 */
public class TimerLogic implements Runnable{
	/**
	 * タイマーの本体クラス.
	 */

	private Timer timer;
	private DigitalPanel digitalPanel;
	private Thread thread;
	private int time;
	private TimerTask task;
	private Handler handler;

	/** タイマーの状態 */
	Condition condition;

	/**
	 * コンストラクタ
	 * @param _digitalPanel デジタルパネル
	 */
	public TimerLogic(DigitalPanel _digitalPanel){
		digitalPanel = _digitalPanel;

		condition = Condition.STOP;

		timer = new Timer();
		task = new TimerTask(){

			@Override
			public void run() {
				time --;

				digitalPanel.setPanels(time);

				if(time==0){
					timer.cancel();
					for(NumberView n: digitalPanel.panels){
						n.setChangeOK(true);
					}
				}
			}

		};

		handler = new Handler();

	}

	/**
	 * デジタルパネルの初期化
	 */
	public void init(){
		time = digitalPanel.getTime();
		thread = new Thread(this);
	}

	/**
	 * タイマーをスタートさせる
	 */
	public void startTimer(){
		init();
		for(NumberView n: digitalPanel.panels){
			n.setChangeOK(false);
		}
		thread.start();
	}

	@Override
	public void run() {
		timer.schedule(task, 1000, 1000);
	}
}
