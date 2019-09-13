package com.ryidc.Ermu.game;

import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;

/**
 * 对游戏的状态进行监听
 * 并进行相关操作
 * @author Er_mu
 *
 */
public class GameStartListener extends BukkitRunnable{
	GameHandle ts;
	public GameStartListener() {
	}
	@Override
	public void run() {
		//遍历全部竞技场
		Set<String> keySet = Arena.arenaList.keySet();
		//遍历
		for(String tempId : keySet) {
			ArenaInfo temp = Arena.arenaList.get(tempId);
			//先判断玩家数量
			if(temp.getPlayerList().size()>=temp.getMinPerson()) {
				//判断开始状态
				if(!temp.getStartBool()) {
					if(temp.getWaitSeconds()==temp.getTime()||temp.getWaitSeconds()==temp.getTime()/2||temp.getWaitSeconds()==15||temp.getWaitSeconds()<=5) {
						//向此竞技场内全部玩家发送消息
						senderMessageToAllPlayer("§a距离游戏开始还有§e"+temp.getWaitSeconds()+"s!",tempId);
					}	
					if(temp.getWaitSeconds()==0) {
						senderMessageToAllPlayer("§a游戏开始!", tempId);
						//开始游戏 
						temp.setStartBool(true);
						//运行对应模式的start
						if(temp.getMode().equals("team")) {
							GameHandle ts = new TeamStart(tempId);
							//新建对应对象 丢入容器中
							Arena.gameStartData.put(tempId, ts);
							//调用run方法
							ts.runGame();
						}else if(temp.getMode().equals("single")) {
							GameHandle ts = new SingleStart(tempId);
							//新建对应对象 丢入容器中
							Arena.gameStartData.put(tempId, ts);
							//调用run方法
							ts.runGame();
						}else if(temp.getMode().equals("guard")) {
							GameHandle ts = new SingleStart(tempId);
							//新建对应对象 丢入容器中
							Arena.gameStartData.put(tempId, ts);
							//调用run方法
							ts.runGame();
						}
						else if(temp.getMode().equals("boss")) {
							GameHandle ts = new SingleStart(tempId);
							//新建对应对象 丢入容器中
							Arena.gameStartData.put(tempId, ts);
							//调用run方法
							ts.runGame();
						}
					}
					//未开始则减少时间 每次减少1s 定时器使用1s
					temp.setWaitSeconds(temp.getWaitSeconds()-1);
				}
			}else if(temp.getPlayerList().size()<temp.getMinPerson()&&temp.getWaitSeconds()!=temp.getTime()&&!temp.getStartBool()) {
				//玩家人数不足并且时间不等于最大等待时间 并且游戏未开始 重置时间
				senderMessageToAllPlayer("§a由于玩家不足,重新开始等待!",tempId);
				temp.setWaitSeconds(temp.getTime());
			}
			//获取已经开始的比赛
			if(temp.getStartBool()) {
				//获得守卫战或者是boss战
				if(temp.getMode().equals("guard")||temp.getMode().equals("boss")) {
					for(Entry<String, Player> entry : temp.getPlayerList().entrySet()) {
						Player p=entry.getValue();
						Inventory inv=p.getInventory();
						for(ItemStack is:inv.getContents()) {
							if(is.equals(temp.getWinnerItem())) {
								ts.stopGame();
							}
						}
					}
				}
			}
		}
		
	}
	/**
	 * 给指定竞技场全部玩家发送消息
	 * @param 消息
	 * @param 竞技场id
	 */
	public static void senderMessageToAllPlayer(String mess,String id) {
		Set<String> keySet = Arena.arenaList.get(id).getPlayerList().keySet();
		for(String temp : keySet) {
			Player p = Arena.arenaList.get(id).getPlayerList().get(temp);
			p.sendMessage(mess);
		}
		
	}
	
}
