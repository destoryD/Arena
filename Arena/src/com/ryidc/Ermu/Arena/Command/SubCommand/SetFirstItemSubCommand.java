package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.save.ArSave;

/**
 * ���ø��˾�����һ��Ʒ
 * @author Er_mu
 *
 */
public class SetFirstItemSubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		// TODO Auto-generated method stub
		//�ж��Ա�������
		if(args.length<2) {
			p.sendMessage("��cָ���ʽ���� ��ȷ��ʽ: /arena setfirstitem ������id");
			return;
		}	
		//�鿴�������Ƿ����
		if(!Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("��c�˾�����������");
			//������
			return;
		}
		ArenaInfo ai = Arena.arenaList.get(args[1]);
		//��һ��Ʒֻ�ɸ����˾���ʹ��
		if(!ai.getMode().equals("single")) {
			p.sendMessage("��cʤ����Ʒֻ�ɸ�����ģʽʹ��");
			return;
		}	
		//ȡ������Ʒ
		ItemStack is = p.getInventory().getItemInHand();
		if(is.getTypeId()==0) {
			p.sendMessage("��a������������һ����Ʒ");
			return;
		}
		
		ai.setFirstItem(is);
		p.sendMessage("��a�ɹ����ø��˵�һ������Ʒ");
		//����
		ArSave.save(Arena.arenaList.get(args[1]));
	}

}
