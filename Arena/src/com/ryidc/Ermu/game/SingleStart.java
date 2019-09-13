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
	//��������
	private Player firstPlayer;
	private Player secondPlayer;
	private Player thirdPlayer;
	//��Ϸʱ��
	private int gameTime;
	public SingleStart(String id) {
		this.id = id;
		//���洢�ķ���תΪ��
		this.gameTime = Arena.arenaList.get(this.id).getGameTime()*60;
	}
	@Override
	public void runGame() {
		//����б�
		Map<String,Player> pL = this.getPlayerList(this.id);
		//�˾�������Ϣ
		ArenaInfo ai = Arena.arenaList.get(this.id);
		Set<String> keySet = pL.keySet();
		//��������б��е����� ��� ������ɳ�����
		for(String p : keySet) {
			//������Ϣ
			pL.get(p).sendMessage("��a��Ϸ��ʼ,����������!");
			pL.get(p).teleport(this.randomLocation(ai.getLoc1(), ai.getLoc2()));
		}
		
	}
	@Override
	public void stopGame() {
		// TODO Auto-generated method stub
		//��������״̬����Ϊfalse
		Arena.arenaList.get(this.id).setStartBool(false);;
		//�ȴ�ʱ������
		Arena.arenaList.get(this.id).setWaitSeconds(Arena.arenaList.get(this.id).getTime());
		//��������������ҵ�ɱ��������
		Map<String,PlayerInfo> pi = new HashMap<>();
		Set<String> keySet = this.getPlayerList(this.id).keySet();
		for(String temp : keySet) {
			pi.put(temp, Arena.playerList.get(temp));
		}
		//������ ʹ��list ��������
		List<Map.Entry<String,PlayerInfo>> list = new ArrayList<>();
		//��ȡEntry
		Set<Map.Entry<String, PlayerInfo>> entrySet = pi.entrySet();
		list.addAll(entrySet);
		//��list��������
		Collections.sort(list, new Comparator<Map.Entry<String, PlayerInfo>>() {
			@Override
			public int compare(Entry<String, PlayerInfo> o1, Entry<String, PlayerInfo> o2) {
				return o1.getValue().getKillNumber()-o2.getValue().getKillNumber();
			}
		});
		//��Ϊ�Ǵ�С����� �ʵ�������
		Collections.reverse(list);
		//���ս��
		List<String> result = new ArrayList<>();
		//������ȫ������������ַ���������
		for(Map.Entry<String,PlayerInfo> entry : list) {
			result.add(entry.getKey());
		}
		//ȡ ��һ �ڶ� ����
		this.firstPlayer = this.getPlayerList(this.id).get(result.get(0));
		this.secondPlayer =this.getPlayerList(this.id).get(result.get(1));
		this.thirdPlayer = this.getPlayerList(this.id).get(result.get(2));
		//���䷢�Ž���
		if(Arena.arenaList.get(this.id).getFirstItem()!=null) {
			//������Ʒ
			this.firstPlayer.getInventory().addItem(Arena.arenaList.get(this.id).getFirstItem());
		}
		//�ڶ�
		if(Arena.arenaList.get(this.id).getSecondItem()!=null) {
			//������Ʒ
			this.secondPlayer.getInventory().addItem(Arena.arenaList.get(this.id).getSecondItem());
		}
		//����
		if(Arena.arenaList.get(this.id).getThirdItem()!=null) {
			//������Ʒ
			this.thirdPlayer.getInventory().addItem(Arena.arenaList.get(this.id).getThirdItem());
		}
		Arena.economy.depositPlayer(this.firstPlayer.getName(),Arena.arenaList.get(this.id).getWinMoney());
		this.getPlayerList(id).get(this.firstPlayer.getName()).sendMessage("��a���λ�ý��"+Arena.arenaList.get(id).getWinMoney());
		Arena.economy.depositPlayer(this.secondPlayer.getName(),Arena.arenaList.get(this.id).getWinMoney());
		this.getPlayerList(id).get(this.secondPlayer.getName()).sendMessage("��a���λ�ý��"+Arena.arenaList.get(id).getWinMoney());
		Arena.economy.depositPlayer(this.thirdPlayer.getName(),Arena.arenaList.get(this.id).getWinMoney());
		this.getPlayerList(id).get(this.thirdPlayer.getName()).sendMessage("��a���λ�ý��"+Arena.arenaList.get(id).getWinMoney());
		//������ȫ����ҽ��в���
		for(String temp : keySet) {
			//��ȡ�����������Ϣ ���侺������Ϣ�ÿ�
			Arena.playerList.get(temp).setArenaId(null);
			//��ÿһλ��Ҷ����͵�����
			//��Ϊess spawnû��Ȩ�޾���һ���ӳ� ������ʱ���*Ȩ��
			this.getPlayerList(this.id).get(temp).setOp(true);
			//����������
			Bukkit.dispatchCommand(this.getPlayerList(this.id).get(temp), "spawn");
			//�µ�
			this.getPlayerList(this.id).get(temp).setOp(false);
			this.getPlayerList(id).get(temp).sendMessage("��a��Ϸ�Ѿ�����,�����������!");
			this.getPlayerList(id).get(temp).sendMessage("��a��������:"+(result.indexOf(temp)+1));
			//������ս����
			if(!Arena.arenaList.get(this.id).getNumber().containsKey(temp)) {
				//����
				Arena.arenaList.get(this.id).getNumber().put(temp, 1);
			}else {
				//����
				Arena.arenaList.get(this.id).getNumber().put(temp, Arena.arenaList.get(this.id).getNumber().get(temp)+1);
			}
			//����ʧ�ܷ���� ��Ϊ��һ,�ڶ�,��������Ʒ�Ѿ�������� ����ֱ������
			if(!temp.equals(this.firstPlayer.getName())&&!temp.equals(this.secondPlayer.getName())&&!temp.equals(this.thirdPlayer.getName())) {
				//������
				this.getPlayerList(id).get(temp).sendMessage("��a���λ�ý��"+Arena.arenaList.get(id).getLoseMoney());
			}
		}
		//����������ɾ���˶���
		Arena.gameStartData.remove(this.id);
		//��վ���������б�
		Arena.arenaList.get(this.id).getPlayerList().clear();
		//����
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
