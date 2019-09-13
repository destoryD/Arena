package com.ryidc.Ermu.Arena.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.game.GameStartListener;
import com.ryidc.Ermu.game.TeamStart;

/**
 * ����뿪�¼�
 * @author Er_mu
 *
 */
public class ArenaPlayerLeaveEvent implements Listener{
	@EventHandler
	public void PlayerLeaveEvent(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		String id = Arena.playerList.get(p.getName()).getArenaId();
		//�жϴ�����Ƿ��ھ�������
		if(id==null) {
			return;
		}else {
			//�Ϳմ���ҵľ�����״̬
			Arena.playerList.get(p.getName()).setArenaId(null);
			//��������ɾ�������
			Arena.arenaList.get(id).getPlayerList().remove(p.getName());
			//���ô���ҵĻ�ɱ��
			Arena.playerList.get(p.getName()).setKillNumber(0);
			//�жϾ�������ʼ״̬
			if(Arena.arenaList.get(id).getStartBool()) {
				//�Ѿ���ʼ
				//�򾺼���ȫ����ҷ���һ����Ϣ
				GameStartListener.senderMessageToAllPlayer("��a��ҡ�e"+p.getName()+"��a�뿪����Ϸ!", id);
				//�ж���Ϸģʽ
				if(Arena.arenaList.get(id).getMode().equals("team")) {
					//ȡ��Ϸ������
					TeamStart ts = (TeamStart)Arena.gameStartData.get(id);
					if(ts.getBlueTeam().containsKey(p.getName())) {
						//ɾ��
						ts.getBlueTeam().remove(p.getName());
					}else if(ts.getRedTeam().containsKey(p.getName())) {
						ts.getRedTeam().remove(p.getName());
					}
				}
			}
			else {
				//�򾺼���ȫ����ҷ���һ����Ϣ
				GameStartListener.senderMessageToAllPlayer("��a��ҡ�e"+p.getName()+"��a�뿪����Ϸ,����"+(Arena.arenaList.get(id).getMinPerson()-Arena.arenaList.get(id).getPlayerList().size())+"��", id);
				
			}	
		}
	}
}
