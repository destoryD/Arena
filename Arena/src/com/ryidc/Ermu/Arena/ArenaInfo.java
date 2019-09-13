package com.ryidc.Ermu.Arena;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ArenaInfo{
	//id
	private String id;
	//ģʽ
	private String mode;
	//�˾����������ս����
	private int maxNumber;
	//��ǰ�����������ս����
	private Map<String,Integer> number;
	//��ǰ��������� �����Բ��������л�
	private Map<String,Player> playerList;
	//�������
	private int minPerson;
	//�������
	private int maxPerson;
	//�˾�������Ҫ�ȴ�ʱ��
	private int time;
	//�˾�������ǰ�ȴ�ʱ��
	private int waitSeconds;
	//����״̬
	private boolean startBool;
	//�ȴ���������
	private Location waitLocation;
	//������
	private Location firstLocation;
	private Location secondLocation;
	//�˾���������
	private Location loc1;
	private Location loc2;
	//�˾���������Ϸʱ��
	private int gameTime;
	//��ʤ��Ʒ
	private ItemStack winneritem;
	//ʤ���߽�����Ʒ
	private ItemStack winItem;
	private int winMoney;
	//ʧ���߽�����
	private ItemStack loseItem;
	private int loseMoney;
	//���˾���1~3����Ʒ
	private ItemStack firstItem;
	private ItemStack secondItem;
	private ItemStack thirdItem;
	//����ʱ�� ����Ϊ1 �Դ�����
	private int week;
	//���㿪�� 1~24
	private int hour;
	//������Ʒ
	private ItemStack joinItem;
	public ArenaInfo() {
		//��ʼ����ǰ����б�
		this.playerList = new HashMap<>();
		//��ʼ����С���������
		this.minPerson = 2;
		this.maxPerson = this.minPerson*2;
	}
	/**
	 * ʹ��ָ���һ���µľ���������ʱ
	 * �������id ģʽ �� ��������
	 * @param id ������id
	 * @param mode ������ģʽ
	 * @param loc1 ����������1
	 * @param loc2 ����������2
	 * ʹ�öԽ������洢������
	 * Ĭ����С����2 ���Ϊ��С������2��
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
		//����Ӧ�ȴ�ʱ��ʱ�� ��ǰ�ȴ�ʱ��һ������
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
