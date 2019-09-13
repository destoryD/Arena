package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.save.ArSave;

public class SetLoseItemSubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		// TODO Auto-generated method stub
		// arena setwinitem ������id
		//�ж��Ա�������
		if(args.length<2) {
			p.sendMessage("��cָ���ʽ���� ��ȷ��ʽ: /arena setwinitem ������id");
			return;
		}
		//�鿴�������Ƿ����
		if(!Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("��c�˾�����������");
			//������
			return;
		}
		//ʤ����Ʒֻ�ɸ��ŶӾ���ʹ��
		//����
		ArenaInfo ai = Arena.arenaList.get(args[1]);
		if(!ai.getMode().equals("team")) {
			p.sendMessage("��cʤ����Ʒֻ�ɸ��Ŷ�ģʽʹ��");
			return;
		}		
		//ȡ������Ʒ
		ItemStack is = p.getInventory().getItemInHand();
		if(is.getTypeId()==0) {
			p.sendMessage("��a������������һ����Ʒ");
			return;
		}
		
		ai.setLoseItem(is);
		p.sendMessage("��a�ɹ�����ʧ�ܷ�������Ʒ");
		//����
		ArSave.save(Arena.arenaList.get(args[1]));
	}

}
