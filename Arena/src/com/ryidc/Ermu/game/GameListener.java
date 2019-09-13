package com.ryidc.Ermu.game;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import org.bukkit.scheduler.BukkitRunnable;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.save.ArSave;

/**
 * 监视各个开始后游戏的游戏状态
 * 并根据状态做出操作
 * 每当此游戏出现人数不足 时间到达时 关闭此游戏
 * @author Er_mu
 *
 */
public class GameListener extends BukkitRunnable{
	//计数器
	private int number = 0;
	
	@Override
	public void run() {
		this.resetNumber();
		if(this.number==30) {
			this.number = 0;
		}	
		//遍历所有的已开始游戏数据
		Set<String> keySet = Arena.gameStartData.keySet();
		for(String temp : keySet) {
			//增加计数器
			this.number++;	
			//取模式
			String mode = Arena.arenaList.get(temp).getMode();
			//判断模式
			if(mode.equals("team")) {		
				//团队竞技 转为对应对象
				TeamStart ts = (TeamStart) Arena.gameStartData.get(temp);
				//每次减少时间1s
				ts.setGameTime(ts.getGameTime()-1);
				//判断计数器是否完成了一个轮训
				if(this.number==1) {
					//全体发送消息
					GameStartListener.senderMessageToAllPlayer("§c距离游戏结束还有"+ts.getGameTime()+"s", temp);
				}
				//判断时间
				if(ts.getGameTime()==0) {
					//结束
					ts.stopGame();
					//跳出当前
					continue;
				}
				//其中一边人数等于0
				if(ts.getBlueTeam().size()==0||ts.getRedTeam().size()==0) {
					GameStartListener.senderMessageToAllPlayer("§a由于人数不足,游戏已经强制结束,已经将奖励发送到获胜一方", temp);
					//结束
					ts.stopGame();
					continue;
				}
			}else {
				//个人竞技
				//取游戏中数据
				SingleStart ss = (SingleStart) Arena.gameStartData.get(temp);
				//每次减少1s
				ss.setGameTime(ss.getGameTime()-1);
				if(this.number==1) {
					//全体发送消息
					GameStartListener.senderMessageToAllPlayer("§c距离游戏结束还有"+ss.getGameTime()+"s", temp);
				}
				//判断时间
				if(ss.getGameTime()==0) {
					//结束
					ss.stopGame();
					continue;
				}
				//判断人数
				if(Arena.arenaList.get(temp).getPlayerList().size()<=3) {
					//结束
					ss.stopGame();
					continue;
				}
			}
		}
	}
	private void resetNumber() {
		//获取当前时间
		Date d = new Date();
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		//时间
		int hour = c.get(Calendar.HOUR_OF_DAY);
		if(hour==23) {
			Set<String> keySet = Arena.arenaList.keySet();
			//23点重置
			for(String temp : keySet) {
				Arena.arenaList.get(temp).getNumber().clear();
				//保存
				ArSave.save(Arena.arenaList.get(temp));
			}
		}
		
	}
	
}
