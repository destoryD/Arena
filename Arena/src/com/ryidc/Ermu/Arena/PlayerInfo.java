package com.ryidc.Ermu.Arena;

import org.bukkit.Location;

/**
 * �����Ϣ��
 * @author Er_mu
 *
 */
public class PlayerInfo {
	//�����id ����������ʱʹ��
	private String playerName;
	//ѡ��״̬
	private boolean bool;
	//ѡ����Ϣ
	private Location loc1;
	private Location loc2;
	//������ھ���������������
	private int killNumber;
	//��ǰ���ھ����� ʹ�þ���������
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
