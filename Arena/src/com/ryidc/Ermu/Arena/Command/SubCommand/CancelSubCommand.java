package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;

/**
 * ȡ������״̬
 * @author Er_mu
 *
 */
public class CancelSubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		//ȡ��������ҵ�ѡ��״̬
		Arena.playerList.get(p.getName()).setBool(false);
		p.sendMessage("��cȡ���ɹ�");
	}
	
}
