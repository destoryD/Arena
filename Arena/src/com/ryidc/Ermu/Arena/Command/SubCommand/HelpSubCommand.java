package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;


/**
 * ��ʾ��
 * @author Er_mu
 *
 */
public class HelpSubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		p.sendMessage("��a��ʾ��ʱ����д��,���ĵ�");
	}

}
