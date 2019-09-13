package com.ryidc.Ermu.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.Arena.PlayerInfo;
import com.ryidc.Ermu.save.ArSave;

public class SingleStart extends GameHandle{
	private String id;
	//最终排名
	private Player firstPlayer;
	private Player secondPlayer;
	private Player thirdPlayer;
	//游戏时间
	private int gameTime;
	public SingleStart(String id) {
		this.id = id;
		//将存储的分数转为秒
		this.gameTime = Arena.arenaList.get(this.id).getGameTime()*60;
	}
	@Override
	public void runGame() {
		//玩家列表
		Map<String,Player> pL = this.getPlayerList(this.id);
		//此竞技场信息
		ArenaInfo ai = Arena.arenaList.get(this.id);
		Set<String> keySet = pL.keySet();
		//遍历玩家列表中的所有 玩家 随机生成出生点
		for(String p : keySet) {
			//发送消息
			pL.get(p).sendMessage("§a游戏开始,进入重生点!");
			pL.get(p).teleport(this.randomLocation(ai.getLoc1(), ai.getLoc2()));
		}
		
	}
	@Override
	public void stopGame() {
		// TODO Auto-generated method stub
		//将竞技场状态设置为false
		Arena.arenaList.get(this.id).setStartBool(false);;
		//等待时间重置
		Arena.arenaList.get(this.id).setWaitSeconds(Arena.arenaList.get(this.id).getTime());
		//将竞技场各个玩家的杀人数汇总
		Map<String,PlayerInfo> pi = new HashMap<>();
		Set<String> keySet = this.getPlayerList(this.id).keySet();
		for(String temp : keySet) {
			pi.put(temp, Arena.playerList.get(temp));
		}
		//排序结果 使用list 方便排序
		List<Map.Entry<String,PlayerInfo>> list = new ArrayList<>();
		//获取Entry
		Set<Map.Entry<String, PlayerInfo>> entrySet = pi.entrySet();
		list.addAll(entrySet);
		//对list进行排序
		Collections.sort(list, new Comparator<Map.Entry<String, PlayerInfo>>() {
			@Override
			public int compare(Entry<String, PlayerInfo> o1, Entry<String, PlayerInfo> o2) {
				return o1.getValue().getKillNumber()-o2.getValue().getKillNumber();
			}
		});
		//因为是从小到大的 故倒序容器
		Collections.reverse(list);
		//最终结果
		List<String> result = new ArrayList<>();
		//遍历将全部玩家名放入字符串容器中
		for(Map.Entry<String,PlayerInfo> entry : list) {
			result.add(entry.getKey());
		}
		//取 第一 第二 第三
		this.firstPlayer = this.getPlayerList(this.id).get(result.get(0));
		this.secondPlayer =this.getPlayerList(this.id).get(result.get(1));
		this.thirdPlayer = this.getPlayerList(this.id).get(result.get(2));
		//对其发放奖励
		if(Arena.arenaList.get(this.id).getFirstItem()!=null) {
			//发送物品
			this.firstPlayer.getInventory().addItem(Arena.arenaList.get(this.id).getFirstItem());
		}
		//第二
		if(Arena.arenaList.get(this.id).getSecondItem()!=null) {
			//发送物品
			this.secondPlayer.getInventory().addItem(Arena.arenaList.get(this.id).getSecondItem());
		}
		//第三
		if(Arena.arenaList.get(this.id).getThirdItem()!=null) {
			//发送物品
			this.thirdPlayer.getInventory().addItem(Arena.arenaList.get(this.id).getThirdItem());
		}
		Arena.economy.depositPlayer(this.firstPlayer.getName(),Arena.arenaList.get(this.id).getWinMoney());
		this.getPlayerList(id).get(this.firstPlayer.getName()).sendMessage("§a本次获得金币"+Arena.arenaList.get(id).getWinMoney());
		Arena.economy.depositPlayer(this.secondPlayer.getName(),Arena.arenaList.get(this.id).getWinMoney());
		this.getPlayerList(id).get(this.secondPlayer.getName()).sendMessage("§a本次获得金币"+Arena.arenaList.get(id).getWinMoney());
		Arena.economy.depositPlayer(this.thirdPlayer.getName(),Arena.arenaList.get(this.id).getWinMoney());
		this.getPlayerList(id).get(this.thirdPlayer.getName()).sendMessage("§a本次获得金币"+Arena.arenaList.get(id).getWinMoney());
		//对其他全部玩家进行操作
		for(String temp : keySet) {
			//获取到所有玩家信息 将其竞技场信息置空
			Arena.playerList.get(temp).setArenaId(null);
			//将每一位玩家都传送到主城
			//因为ess spawn没有权限具有一定延迟 所以临时添加*权限
			this.getPlayerList(this.id).get(temp).setOp(true);
			//传送至主城
			Bukkit.dispatchCommand(this.getPlayerList(this.id).get(temp), "spawn");
			//下掉
			this.getPlayerList(this.id).get(temp).setOp(false);
			this.getPlayerList(id).get(temp).sendMessage("§a游戏已经结束,奖励结算完毕!");
			this.getPlayerList(id).get(temp).sendMessage("§a本次排名:"+(result.indexOf(temp)+1));
			//增加挑战次数
			if(!Arena.arenaList.get(this.id).getNumber().containsKey(temp)) {
				//放入
				Arena.arenaList.get(this.id).getNumber().put(temp, 1);
			}else {
				//包含
				Arena.arenaList.get(this.id).getNumber().put(temp, Arena.arenaList.get(this.id).getNumber().get(temp)+1);
			}
			//给予失败方金币 因为第一,第二,第三的物品已经给予过了 所以直接跳过
			if(!temp.equals(this.firstPlayer.getName())&&!temp.equals(this.secondPlayer.getName())&&!temp.equals(this.thirdPlayer.getName())) {
				//给予金币
				this.getPlayerList(id).get(temp).sendMessage("§a本次获得金币"+Arena.arenaList.get(id).getLoseMoney());
			}
		}
		//运行中数据删除此对象
		Arena.gameStartData.remove(this.id);
		//清空竞技场玩家列表
		Arena.arenaList.get(this.id).getPlayerList().clear();
		//保存
		ArSave.save(Arena.arenaList.get(this.id));
		
	}
	public Player getFirstPlayer() {
		return firstPlayer;
	}
	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	public Player getSecondPlayer() {
		return secondPlayer;
	}
	public void setSecondPlayer(Player secondPlayer) {
		this.secondPlayer = secondPlayer;
	}
	public Player getThirdPlayer() {
		return thirdPlayer;
	}
	public void setThirdPlayer(Player thirdPlayer) {
		this.thirdPlayer = thirdPlayer;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getGameTime() {
		return gameTime;
	}
	public void setGameTime(int getTime) {
		this.gameTime = getTime;
	}

}
