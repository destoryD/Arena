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
	//���
	private Map<String,Player> redTeam;
	//����
	private Map<String,Player> blueTeam;
	//��ӳ�����
	private Location redTeamSpawn;
	//���ӳ�����
	private Location blueTeamSpawn;
	//��ɱ��
	private int redKillNumber;
	private int blueKillNumber;
	//��ʤ��Ʒ
	private ItemStack WinnerItem;
	//��ʤ����
	private Map<String,Player> WinTeam;
	//ʧ�ܶ���
	private Map<String,Player> LoseTeam;
	private int gameTime;
	public BossStart(String id) {
		this.id = id;
		//��������Ա
		this.redTeam = new HashMap<>();
		this.blueTeam = new HashMap<>();
		//��ӳ�����
		this.redTeamSpawn = Arena.arenaList.get(this.id).getFirstLocation();
		//���ӳ�����
		this.blueTeamSpawn = Arena.arenaList.get(this.id).getSecondLocation();
		//���洢�ķ���תΪ��
		this.gameTime = Arena.arenaList.get(this.id).getGameTime()*60;
	}
	@Override
	public void runGame() {
		// TODO Auto-generated method stub
		//����ҽ����������
		this.shuffle(this.getPlayerList(this.id));
		//�ֱ���� Ȼ����
		Set<String> redKeySet = this.redTeam.keySet();
		Set<String> blueKeySet = this.blueTeam.keySet();
		for(String temp : redKeySet) {
			this.redTeam.get(temp).sendMessage("��c��Ϸ��ʼ,����������~");
			//����
			this.redTeam.get(temp).teleport(this.redTeamSpawn);
		}
		for(String temp : blueKeySet) {
			this.blueTeam.get(temp).sendMessage("��c��Ϸ��ʼ,����������~");
			//����
			this.blueTeam.get(temp).teleport(this.blueTeamSpawn);
		}
		
		
	}
	@Override
	public void stopGame() {
		// TODO Auto-generated method stub
		ArenaInfo ai = Arena.arenaList.get(this.id);
		//��������״̬����Ϊfalse
		ai.setStartBool(false);
		//���ȴ�ʱ������
		ai.setWaitSeconds(ai.getTime());
		//������Ʒ ����������
		Set<String> keySet = ai.getPlayerList().keySet();
		//��������������Ҿ�����״̬��ֵΪnull
		for(String temp : keySet) {
			//��ȡ�����������Ϣ ���侺������Ϣ�ÿ�
			Arena.playerList.get(temp).setArenaId(null);
			//��ÿһλ��Ҷ����͵�����
			//��Ϊess spawnû��Ȩ�޾���һ���ӳ� ������ʱ���*Ȩ��
			ai.getPlayerList().get(temp).setOp(true);
			//���䴫�͵�����
			Bukkit.dispatchCommand(ai.getPlayerList().get(temp), "spawn");
			ai.getPlayerList().get(temp).setOp(false);
			getPlayerList(id).get(temp).sendMessage("��a��Ϸ�Ѿ�����,�����������!");
		}
		this.WinTeam=this.redTeam;
		//���� ��ʤ���������Ʒ�ͽ��
		Set<String> winKeySet = WinTeam.keySet();
		for(String temp : winKeySet) {
			//�ж���Ʒ�Ƿ�Ϊnull
			if(ai.getWinItem()!=null) {
				WinTeam.get(temp).getInventory().addItem(ai.getWinItem());
			}	
			//������
			Arena.economy.depositPlayer(temp, ai.getWinMoney());
			//��ս����+1
			if(!ai.getNumber().containsKey(temp)) {
				//����
				ai.getNumber().put(temp, 1);
			}else {
				//����
				ai.getNumber().put(temp, ai.getNumber().get(temp)+1);
			}
		}
		//����������ɾ���˶���
		Arena.gameStartData.remove(this.id);
		//��վ���������б�
		ai.getPlayerList().clear();
		//����
		ArSave.save(ai);
		
	}
	
	/**
	 * Bossս������һ������
	 * @param playerList ����б�
	 */
	private void shuffle(Map<String,Player> playerList) {
		Set<String> keySet = playerList.keySet();
		for(String temp : keySet) {
			//������
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
