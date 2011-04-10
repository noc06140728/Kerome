package net.hardwave.kerome;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import android.content.res.Resources;

public class MailGenerator {
	private int minites;
	private Resources res;
	private Random rand;

	public MailGenerator(Resources res) {
		this.res = res;
		this.rand = new Random();
	}
	
	public void setTime(int minites) {
		this.minites = minites;
	}
	public String generateSubject() {
		return null;
	}

	public String generateBody() {
		StringBuffer str = new StringBuffer();
		if (isGoHomeSoon()) {
			str.append(randomChoise(res.getStringArray(R.array.body_go_home)));
			if (isLater()) {
				str.append(randomChoise(res.getStringArray(R.array.body_go_home_later)));
			}
		} else {
			str.append(randomChoise(res.getStringArray(R.array.body_on_job1)));
			//str.append(embededTime(randomChoise(res.getStringArray(R.array.body_on_job2))));
			str.append(getTimeMessage(minites));
			str.append(randomChoise(res.getStringArray(R.array.body_on_job3)));
			str.append(randomChoise(res.getStringArray(R.array.body_on_job4)));
			str.append(randomChoise(res.getStringArray(R.array.body_on_job5)));
			str.append(randomChoise(res.getStringArray(R.array.body_on_job6)));
		}
		return str.toString();
	}
	private Object getTimeMessage(int minutes) {
		if (minutes < 60) {
			return "あと" + minutes / 10 * 10 + "分くらいで、";
		} else {
			Calendar now = new GregorianCalendar();
			now.add(Calendar.MINUTE, minutes);
			now.add(Calendar.MINUTE, -now.get(Calendar.MINUTE) % 10);
			
			SimpleDateFormat format;
			if (now.get(Calendar.MINUTE) == 0) {
				format = new SimpleDateFormat("H時頃");
			} else if (now.get(Calendar.MINUTE) == 30) {
				format = new SimpleDateFormat("H時半頃");
			} else {
				format = new SimpleDateFormat("H:mm");
			}
			return format.format(now.getTime()) + "までには、";
		}
	}

	private Object embededTime(String str) {
		Calendar now = new GregorianCalendar();
		now.add(Calendar.MINUTE, minites);
		now.add(Calendar.MINUTE, -now.get(Calendar.MINUTE) % 10);
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		String s = format.format(now.getTime());
		return str.replace("HHMM", s).replace("MM", Integer.toString(minites));
	}

	private String randomChoise(String[] stringArray) {
		return stringArray[rand.nextInt(stringArray.length)];
	}

	private boolean isLater() {
		// TODO 遅い時間帯判定を実装（現在時刻の値で判定）
		return false;
	}
	private boolean isGoHomeSoon() {
		// TODO 帰るメールかどうかの判定を実装（timeフィールドの値で判定）
		return false;
	}
}
