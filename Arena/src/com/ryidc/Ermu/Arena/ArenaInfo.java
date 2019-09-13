package com.ryidc.Ermu.Arena;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ArenaInfo{
	//id
	private String id;
	//模式
	private String mode;
	//此竞技场最大挑战次数
	private int maxNumber;
	//当前竞技场玩家挑战次数
	private Map<String,Integer> number;
	//当前竞技场玩家 此属性不进行序列化
	private Map<String,Player> playerList;
	//最低人数
	private int minPerson;
	//最大人数
	private int maxPerson;
	//此竞技场需要等待时间
	private int time;
	//此竞技场当前等待时间
	private int waitSeconds;
	//开启状态
	private boolean startBool;
	//等待大厅坐标
	private Location waitLocation;
	//出生点
	private Location firstLocation;
	private Location secondLocation;
	//此竞技场坐标
	private Location loc1;
	private Location loc2;
	//此竞技场的游戏时间
	private int gameTime;
	//获胜物品
	private ItemStack winneritem;
	//胜利者奖励物品
	private ItemStack winItem;
	private int winMoney;
	//失败者奖励物
	private ItemStack loseItem;
	private int loseMoney;
	//个人竞技1~3名物品
	private ItemStack firstItem;
	private ItemStack secondItem;
	private ItemStack thirdItem;
	//开放时间 周日为1 以此类推
	private int week;
	//几点开放 1~24
	private int hour;
	//进入物品
	private ItemStack joinItem;
	public ArenaInfo() {
		//初始化当前玩家列表
		this.playerList = new HashMap<>();
		//初始化最小和最大人数
		this.minPerson = 2;
		this.maxPerson = this.minPerson*2;
	}
	/**
	 * 使用指令构造一个新的竞技场对象时
	 * 必须包含id 模式 及 俩个坐标
	 * @param id 竞技场id
	 * @param mode 竞技场模式
	 * @param loc1 竞技场坐标1
	 * @param loc2 竞技场坐标2
	 * 使用对角线来存储竞技场
	 * 默认最小人数2 最大为最小人数的2倍
	 * 
	 */
	public ArenaInfo(String id,String mode,Location loc1,Location loc2) {
		this.number = new HashMap<>();
		this.id = id;
		this.mode = mode;
		this.playerList = new HashMap<>();
		this.number = new HashMap<>();
		this.minPerson = 2;
		this.maxPerson = this.minPerson*2;
		this.loc1 = loc1;
		this.loc2 = loc2;
	}
	public Map<String, Player> getPlayerList() {
		return playerList;
	}
	public void setPlayerList(Map<String, Player> playerList) {
		this.playerList = playerList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public Map<String, Integer> getNumber() {
		return number;
	}
	public void setNumber(Map<String, Integer> number) {
		this.number = number;
	}
	public int getMinPerson() {
		return minPerson;
	}
	public void setMinPerson(int minPerson) {
		this.minPerson = minPerson;
	}
	public int getMaxPerson() {
		return maxPerson;
	}
	public void setMaxPerson(int maxPerson) {
		this.maxPerson = maxPerson;
	}
	public int getWaitSeconds() {
		return waitSeconds;
	}
	public void setWaitSeconds(int waitSeconds) {
		this.waitSeconds = waitSeconds;
	}
	public boolean getStartBool() {
		return this.startBool;
	}
	public void setStartBool(boolean startBool) {
		this.startBool = startBool;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
		//设置应等待时间时把 当前等待时间一起重置
		this.waitSeconds = time;
	}
	public int getMaxNumber() {
		return this.maxNumber;
	}
	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}
	public void setWaitLocation(Location waitLocation) {
		this.waitLocation = waitLocation;
	}
	public Location getWaitLocation() {
		return this.waitLocation;
	}
	public void setLoc1(Location loc1) {
		this.loc1 = loc1;
	}
	public Location getLoc1() {
		return this.loc1;
	}
	public void setLoc2(Location loc2) {
		this.loc2 = loc2;
	}
	public Location getLoc2() {
		return this.loc2;
	}
	public Location getFirstLocation() {
		return firstLocation;
	}
	public void setFirstLocation(Location firstLocation) {
		this.firstLocation = firstLocation;
	}
	public Location getSecondLocation() {
		return secondLocation;
	}
	public void setSecondLocation(Location secondLocation) {
		this.secondLocation = secondLocation;
	}
	public void setGameTime(int gameTime) {
		this.gameTime = gameTime;
	}
	public int getGameTime() {
		return this.gameTime;
	}
	public ItemStack getWinItem() {
		return winItem;
	}
	public void setWinItem(ItemStack winItem) {
		this.winItem = winItem;
	}
	public ItemStack getWinnerItem() {
		return this.winneritem;
	}
	public void setWinnerItem(ItemStack WinnerItem) {
		this.winneritem=WinnerItem;
	}
	public int getWinMoney() {
		return winMoney;
	}
	public void setWinMoney(int winMoney) {
		this.winMoney = winMoney;
	}
	public ItemStack getLoseItem() {
		return loseItem;
	}
	public void setLoseItem(ItemStack loseItem) {
		this.loseItem = loseItem;
	}
	public int getLoseMoney() {
		return loseMoney;
	}
	public void setLoseMoney(int loseMoney) {
		this.loseMoney = loseMoney;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public ItemStack getJoinItem() {
		return joinItem;
	}
	public void setJoinItem(ItemStack joinItem) {
		this.joinItem = joinItem;
	}
	public ItemStack getFirstItem() {
		return firstItem;
	}
	public void setFirstItem(ItemStack firstItem) {
		this.firstItem = firstItem;
	}
	public ItemStack getSecondItem() {
		return secondItem;
	}
	public void setSecondItem(ItemStack secondItem) {
		this.secondItem = secondItem;
	}
	public ItemStack getThirdItem() {
		return thirdItem;
	}
	public void setThirdItem(ItemStack thirdItem) {
		this.thirdItem = thirdItem;
	}
	
	
	
}
