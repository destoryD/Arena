package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.save.ArSave;

/**
 * ���õȴ�����
 * @author Er_mu
 *
 */
public class SetWaitLocationSubCommand implements ArSubCommand{
	@Override
	public void command(Player p, String[] args) {
		//arena setlocation id
		// TODO Auto-generated method stub
		//�жϳ���
		if(args.length<2) {
			p.sendMessage("��c�����ʽ���� ��ȷ��ʽ:/arena setlocation ������id");
			return;
		}
		//�жϾ������Ƿ����
		if(!Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("��c�þ�����������");
			return;
		}
		ArenaInfo arena = Arena.arenaList.get(args[1]);
		//����
		arena.setWaitLocation(p.getLocation());
		p.sendMessage("��a�ȴ��������óɹ�!");
		//���л��洢
		//ʹ�����л��������һ�α���
		ArSave.save(arena);
	}
	
}
