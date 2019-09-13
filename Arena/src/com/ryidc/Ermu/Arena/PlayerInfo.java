package com.ryidc.Ermu.Arena;

import org.bukkit.Location;

/**
 * 玩家信息类
 * @author Er_mu
 *
 */
public class PlayerInfo {
	//此玩家id 方便在排序时使用
	private String playerName;
	//选点状态
	private boolean bool;
	//选点信息
	private Location loc1;
	private Location loc2;
	//此玩家在竞技场内死亡次数
	private int killNumber;
	//当前所在竞技场 使用竞技场名字
	private String ArenaId;
	public void setBool(boolean bool) {
		this.bool = bool;
	}
	public boolean getBool() {
		return this.bool;
	}
	public Location getLoc1() {
		return loc1;
	}
	public void setLoc1(Location loc1) {
		this.loc1 = loc1;
	}
	public Location getLoc2() {
		return loc2;
	}
	public void setLoc2(Location loc2) {
		this.loc2 = loc2;
	}
	public String getArenaId() {
		return ArenaId;
	}
	public void setArenaId(String arenaId) {
		ArenaId = arenaId;
	}
	public void setKillNumber(int deathNumber) {
		this.killNumber = deathNumber;
	}
	public int getKillNumber() {
		return this.killNumber;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
}
