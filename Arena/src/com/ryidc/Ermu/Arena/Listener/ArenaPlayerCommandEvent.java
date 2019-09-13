package com.ryidc.Ermu.Arena.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.ryidc.Ermu.Arena.Arena;

/**
 * �������ִ��ָ��
 * @author Er_mu
 *
 */
public class ArenaPlayerCommandEvent implements Listener{
	@EventHandler
	public void playerCommandEvent(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		//�����op ֱ������
		if(p.hasPermission("*")) {
			return;
		}
		//�ж�����Ƿ��ھ�����
		if(Arena.playerList.get(p.getName()).getArenaId()!=null) {
			//ȡ��ҵ�ǰִ��ָ�� �жϰ��������Ƿ��д�
			if(!Arena.whiteCommandList.contains(e.getMessage())) {
				//ȡ��
				e.setCancelled(true);
				p.sendMessage("��c�������ڽ�ִֹ��ָ��!");
			}
		}
	}
}
