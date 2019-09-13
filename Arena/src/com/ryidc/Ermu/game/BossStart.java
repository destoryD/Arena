package com.ryidc.Ermu.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.save.ArSave;

public class BossStart extends GameHandle{
	private String id;
	//红队
	private Map<String,Player> redTeam;
	//蓝队
	private Map<String,Player> blueTeam;
	//红队出生点
	private Location redTeamSpawn;
	//蓝队出生点
	private Location blueTeamSpawn;
	//击杀数
	private int redKillNumber;
	private int blueKillNumber;
	//获胜物品
	private ItemStack WinnerItem;
	//获胜队伍
	private Map<String,Player> WinTeam;
	//失败队伍
	private Map<String,Player> LoseTeam;
	private int gameTime;
	public BossStart(String id) {
		this.id = id;
		//各队伍人员
		this.redTeam = new HashMap<>();
		this.blueTeam = new HashMap<>();
		//红队出生点
		this.redTeamSpawn = Arena.arenaList.get(this.id).getFirstLocation();
		//蓝队出生点
		this.blueTeamSpawn = Arena.arenaList.get(this.id).getSecondLocation();
		//将存储的分数转为秒
		this.gameTime = Arena.arenaList.get(this.id).getGameTime()*60;
	}
	@Override
	public void runGame() {
		// TODO Auto-generated method stub
		//将玩家进行随机分配
		this.shuffle(this.getPlayerList(this.id));
		//分别遍历 然后传送
		Set<String> redKeySet = this.redTeam.keySet();
		Set<String> blueKeySet = this.blueTeam.keySet();
		for(String temp : redKeySet) {
			this.redTeam.get(temp).sendMessage("§c游戏开始,进入重生点~");
			//传送
			this.redTeam.get(temp).teleport(this.redTeamSpawn);
		}
		for(String temp : blueKeySet) {
			this.blueTeam.get(temp).sendMessage("§c游戏开始,进入重生点~");
			//传送
			this.blueTeam.get(temp).teleport(this.blueTeamSpawn);
		}
		
		
	}
	@Override
	public void stopGame() {
		// TODO Auto-generated method stub
		ArenaInfo ai = Arena.arenaList.get(this.id);
		//将竞技场状态设置为false
		ai.setStartBool(false);
		//将等待时间重置
		ai.setWaitSeconds(ai.getTime());
		//结算物品 并传送主城
		Set<String> keySet = ai.getPlayerList().keySet();
		//将容器内所有玩家竞技场状态赋值为null
		for(String temp : keySet) {
			//获取到所有玩家信息 将其竞技场信息置空
			Arena.playerList.get(temp).setArenaId(null);
			//将每一位玩家都传送到主城
			//因为ess spawn没有权限具有一定延迟 所以临时添加*权限
			ai.getPlayerList().get(temp).setOp(true);
			//将其传送到主城
			Bukkit.dispatchCommand(ai.getPlayerList().get(temp), "spawn");
			ai.getPlayerList().get(temp).setOp(false);
			getPlayerList(id).get(temp).sendMessage("§a游戏已经结束,奖励结算完毕!");
		}
		this.WinTeam=this.redTeam;
		//遍历 获胜队伍给予物品和金币
		Set<String> winKeySet = WinTeam.keySet();
		for(String temp : winKeySet) {
			//判断物品是否为null
			if(ai.getWinItem()!=null) {
				WinTeam.get(temp).getInventory().addItem(ai.getWinItem());
			}	
			//给予金币
			Arena.economy.depositPlayer(temp, ai.getWinMoney());
			//挑战次数+1
			if(!ai.getNumber().containsKey(temp)) {
				//放入
				ai.getNumber().put(temp, 1);
			}else {
				//包含
				ai.getNumber().put(temp, ai.getNumber().get(temp)+1);
			}
		}
		//运行中数据删除此对象
		Arena.gameStartData.remove(this.id);
		//清空竞技场玩家列表
		ai.getPlayerList().clear();
		//保存
		ArSave.save(ai);
		
	}
	
	/**
	 * Boss战仅设置一个队伍
	 * @param playerList 玩家列表
	 */
	private void shuffle(Map<String,Player> playerList) {
		Set<String> keySet = playerList.keySet();
		for(String temp : keySet) {
			//放入红队
			redTeam.put(temp, playerList.get(temp));
		}
	}
	public Map<String, Player> getRedTeam() {
		return redTeam;
	}
	public void setRedTeam(Map<String, Player> redTeam) {
		this.redTeam = redTeam;
	}
	public Map<String, Player> getBlueTeam() {
		return blueTeam;
	}
	public void setBlueTeam(Map<String, Player> blueTeam) {
		this.blueTeam = blueTeam;
	}
	public int getRedKillNumber() {
		return this.redKillNumber;
	}
	public void setRedKillNumber(int redKillNumber) {
		this.redKillNumber = redKillNumber;
	}
	public int getBlueKillNumber() {
		return this.blueKillNumber;
	}
	public void setBlueKillNumber(int blueKillNumber) {
		this.blueKillNumber = blueKillNumber;
	}
	public int getGameTime() {
		return gameTime;
	}
	public void setGameTime(int gameTime) {
		this.gameTime = gameTime;
	}
	public Location getRedTeamSpawn() {
		return redTeamSpawn;
	}
	public void setRedTeamSpawn(Location redTeamSpawn) {
		this.redTeamSpawn = redTeamSpawn;
	}
	public Location getBlueTeamSpawn() {
		return blueTeamSpawn;
	}
	public void setBlueTeamSpawn(Location blueTeamSpawn) {
		this.blueTeamSpawn = blueTeamSpawn;
	}
	
	

}
