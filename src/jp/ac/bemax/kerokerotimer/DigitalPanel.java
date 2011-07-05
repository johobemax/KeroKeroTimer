package jp.ac.bemax.kerokerotimer;

import android.os.Handler;
import android.view.View;

class DigitalPanel {
	private View view;
	private Handler handler;

	NumberView[] panels;

	/**
	 *  コンストラクタ
	 *  @param _view
	 *  @param _timerLogic
	 */
	public DigitalPanel(View _view) {
		view = _view;
		handler = new Handler();

		panels = new NumberView[5];
		panels[0] = (NumberView)view.findViewById(R.id.hour1);
        panels[1] = (NumberView)view.findViewById(R.id.min1);
        panels[2] = (NumberView)view.findViewById(R.id.min2);
        panels[3] = (NumberView)view.findViewById(R.id.sec1);
        panels[4] = (NumberView)view.findViewById(R.id.sec2);
	}

	/** パネルを全て0に初期化 */
	void initPanels(){

	}

	/** 指定された時間でパネル表示をする */
	void setPanels(int time){
		final int tt = time;
		Thread t = new Thread(new Runnable(){
			public void run(){
				handler.post(new Runnable() {
					public void run() {
						// TODO 自動生成されたメソッド・スタブ

						int time = tt;

						panels[4].setNumber(time % 10);
						time /= 10;
						panels[3].setNumber(time % 6);
						time /= 6;
						panels[2].setNumber(time % 10);
						time /= 10;
						panels[1].setNumber(time % 6);
						time /= 6;
						panels[0].setNumber(time);
					}
				});
			}
		});

		t.start();
	}

	/**
	 * 数字パネルから読み取れるtimeを返す
	 * @return 数字パネルから読み取れるtime
	 */
	int getTime(){
		int time;

		time = panels[0].getNumber() * 3600;
		time += panels[1].getNumber() * 600;
		time += panels[2].getNumber() * 60;
		time += panels[3].getNumber() * 10;
		time += panels[4].getNumber();

		return time;
	}
}
