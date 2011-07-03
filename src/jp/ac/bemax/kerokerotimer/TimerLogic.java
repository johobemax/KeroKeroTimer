package jp.ac.bemax.kerokerotimer;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;

public class TimerLogic implements Runnable{
	private Timer timer;
	private NumberView[] numberViews;
	private Thread thread;
	private int time;
	private TimerTask task;
	private Handler handler;

	public TimerLogic(NumberView[] nv){
		numberViews = nv;

		timer = new Timer();

		task = new TimerTask(){

			@Override
			public void run() {
				// TODO 自動生成されたメソッド・スタブ
				time --;

				setNumberViews();

				if(time==0){
					timer.cancel();
					for(NumberView n: numberViews){
						n.setChangeOK(true);
					}
				}
			}

		};

		thread = new Thread(this);

		handler = new Handler();
	}

	public void init(){
		int t;
		t = numberViews[0].getNumber() * 3600;
		t += numberViews[1].getNumber() * 600;
		t += numberViews[2].getNumber() * 60;
		t += numberViews[3].getNumber() * 10;
		t += numberViews[4].getNumber();

		time = t;
	}

	public void startTimer(){
		init();
		for(NumberView n: numberViews){
			n.setChangeOK(false);
		}
		thread.start();
	}

	public void setNumberViews(){
		final int tt = time;
		Thread t = new Thread(new Runnable(){
			public void run(){
				handler.post(new Runnable() {
					public void run() {
						// TODO 自動生成されたメソッド・スタブ

						int t = tt;

						numberViews[4].setNumber(t % 10);
						t /= 10;
						numberViews[3].setNumber(t % 6);
						t /= 6;
						numberViews[2].setNumber(t % 10);
						t /= 10;
						numberViews[1].setNumber(t % 6);
						t /= 6;
						numberViews[0].setNumber(t);
					}
				});
			}
		});

		t.start();

	}

	public void run() {
		// TODO 自動生成されたメソッド・スタブ
		timer.schedule(task, 0, 1000);
	}
}
