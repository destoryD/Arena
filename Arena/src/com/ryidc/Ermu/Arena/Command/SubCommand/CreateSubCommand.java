package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.save.ArSave;

public class CreateSubCommand implements ArSubCommand{
	@Override
	public void command(Player p, String[] args) {
		if(args.length<3) {
			p.sendMessage("��c����ȷ��������: ��ʽ/Arena create ID ģʽ");
			return;
		}
		//�ж��Ƿ���ѡ������
		if(Arena.playerList.get(p.getName()).getLoc1()==null||Arena.playerList.get(p.getName()).getLoc2()==null) {
			p.sendMessage("��c����ѡ��������");
			//Ϊ��
			return;
		}
		//�ж�ID�Ƿ����
		if(Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("��c��ID�Ѿ�����");
			return;
		}
		//�ж��Ƿ��д�����
		if(!Arena.ARENA_LIST.contains(args[2])) {
			p.sendMessage("��c��ģʽ������");
			return;
		}
		ArenaInfo arInfo = new ArenaInfo(args[1],args[2],Arena.playerList.get(p.getName()).getLoc1(),Arena.playerList.get(p.getName()).getLoc2());
		//����
		Arena.arenaList.put(args[1], arInfo);
		p.sendMessage("��a�����ɹ�");
		//ȡ����ѡ��״̬
		Arena.playerList.get(p.getName()).setBool(false);
		ArSave.save(arInfo);
	}

}
