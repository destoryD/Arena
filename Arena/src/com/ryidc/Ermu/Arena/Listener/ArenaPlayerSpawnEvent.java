package com.ryidc.Ermu.Arena.Listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.Arena.PlayerInfo;
import com.ryidc.Ermu.game.SingleStart;
import com.ryidc.Ermu.game.TeamStart;

public class ArenaPlayerSpawnEvent implements Listener{
	@EventHandler
	public void playerSpawnEvent(PlayerRespawnEvent event) {
		//��ȡ���
		Player p = event.getPlayer();
		//��ȡ�����Ϣ
		PlayerInfo playerInfo = Arena.playerList.get(p.getName());
		//�жϴ�����Ƿ��ھ�������
		if(playerInfo.getArenaId()!=null) {
			//�ھ������� get����Ӧ����������Ϣ
			ArenaInfo ai = Arena.arenaList.get(playerInfo.getArenaId());
			//�ж��Ƿ�ʼ
			if(ai.getStartBool()) {
				//�ж�ģʽ
				if(ai.getMode().equals("team")) {
					//��ȡ����������
					TeamStart ts = (TeamStart) Arena.gameStartData.get(ai.getId());
					//�Ŷ� �����ڶ�Ӧ������
					if(ts.getBlueTeam().containsKey(p.getName())) {
						//����
						event.setRespawnLocation(ts.getBlueTeamSpawn());
					}else {
						//���
						event.setRespawnLocation(ts.getRedTeamSpawn());
					}
					
				}else {
					//���˾���
					//��ȡ����ʱ����
					SingleStart ss = (SingleStart)Arena.gameStartData.get(ai.getId());
					//�����������
					Location loc = ss.randomLocation(ai.getLoc1(), ai.getLoc2());
					//����ҽ����������
					event.setRespawnLocation(loc);
					p.sendMessage("��e�㸴����!");
				}
			}
			
		}
	}
}
