package com.ryidc.Ermu.Arena.Command.SubCommand;

import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.save.ArReader;

public class ArReloadSubCommand implements ArSubCommand{
	private Plugin plug;
	public ArReloadSubCommand(Plugin plug) {
		this.plug = plug;
	}
	@Override
	public void command(Player p, String[] args) {
		p.sendMessage("��a���ڹر������Ѿ������ľ�����....");
		//�����Ϸ������
		Arena.gameStartData.clear();
		//����Ϸ������������
		Set<String> keySet = Arena.arenaList.keySet();
		for(String temp : keySet) {
			//ÿ��������������б�
			Map<String,Player> player = Arena.arenaList.get(temp).getPlayerList();
			Set<String> playerKeySet = player.keySet();
			for(String playerName : playerKeySet) {
				//get�����
				Player thisPlayer = player.get(playerName);
				//���䴫�͵�����
				thisPlayer.setOp(true);
				Bukkit.dispatchCommand(thisPlayer, "spawn");
				thisPlayer.setOp(false);
				thisPlayer.sendMessage("��a���ڲ������,�㱻ǿ�����");
				//ȥ�����Ϣ�����ô���һ�ɱ��
				Arena.playerList.get(playerName).setKillNumber(0);
				//���þ�����״̬
				Arena.playerList.get(playerName).setArenaId(null);
			}
		}
		p.sendMessage("��a�ر����,������..");
		
		//���������ļ�
		this.plug.reloadConfig();
		//���ؾ�����
		ArReader.read(Arena.arenaSavePath,true);
		//���ذ������б�
		Arena.whiteCommandList = this.plug.getConfig().getStringList("whiteCommand");
		//��ʾ
		p.sendMessage("��a���سɹ�");
	}

}
