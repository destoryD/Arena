package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.save.ArSave;

public class SetFirstLocationSubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		// TODO Auto-generated method stub
		//arena setFirstLocation ������
		if(args.length<2) {
			p.sendMessage("��c��ʽ���� ��ȷ��ʽ arena setFirstLocation ������");
			return;
		}
		//�жϾ������Ƿ����
		if(!Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("��c�þ�����������");
			return;
		}		
		//�жϾ���������
		if(!Arena.arenaList.get(args[1]).getMode().equals("team")) {
			p.sendMessage("��c���������ͱ���Ϊ�ŶӾ����ſ���������");
			return;
		}
		p.sendMessage("��a���óɹ�");
		//���õ�һ������
		Arena.arenaList.get(args[1]).setFirstLocation(p.getLocation());
		//����
		ArSave.save(Arena.arenaList.get(args[1]));
		
	}

}
