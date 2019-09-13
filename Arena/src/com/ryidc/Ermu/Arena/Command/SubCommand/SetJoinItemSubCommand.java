package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.save.ArSave;

/**
 * ��������Ʒ����Ϊ������Ʒ
 * @author Er_mu
 *
 */
public class SetJoinItemSubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		//�ж��Ա���
		//��ȷ��ʽ /arena setjoinitem ������id [null]
		if(args.length<2) {
			p.sendMessage("��cָ���ʽ���� ��ȷ��ʽ/arena setjoinitem ������id");
			return;
		}
		//�鿴�������Ƿ����
		if(!Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("��c�˾�����������");
			//������
		}
		//�ж���û���������������
		if(args.length>=3) {
			//�ж��Ƿ�Ϊnull
			if(args[2].equals("null")) {
				//ɾ����Ʒ
				Arena.arenaList.get(args[1]).setJoinItem(null);
				p.sendMessage("��a������Ʒɾ���ɹ�!");
				//����
				ArSave.save(Arena.arenaList.get(args[1]));
				return;
			}else {
				p.sendMessage("��c�����������");
				return;
			}
		}
		//ȡ������Ʒ
		ItemStack is = p.getInventory().getItemInHand();
		if(is.getTypeId()==0) {
			p.sendMessage("��a������������һ����Ʒ");
			return;
		}
		//������Ʒ
		Arena.arenaList.get(args[1]).setJoinItem(is);
		p.sendMessage("��a������Ʒ���óɹ�!");
		//����
		ArSave.save(Arena.arenaList.get(args[1]));
		
	}
	
}
