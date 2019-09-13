package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.ryidc.Ermu.Arena.Arena;

/**
 * �õ�ѡ�㹤�� ����ʼѡ��
 * @author Er_mu
 *
 */
public class SetSubCommand implements ArSubCommand{
	private Plugin plug;
	public SetSubCommand(Plugin plug) {
		this.plug = plug;
	}

	@Override
	public void command(Player p, String[] args) {
		//������Ʒ
		int id = this.plug.getConfig().getInt("selectId");
		//��ȡ�����Ʒ����
		Inventory inv = p.getInventory();
		//�ж��Ƿ��п�λ
		if(inv.firstEmpty()!=-1) {
			//����״̬
			Arena.playerList.get(p.getName()).setBool(true);
			//������Ʒ
			inv.addItem(new ItemStack(id));
			p.sendMessage("��a[Area]: �Ѿ�����ѡ��״̬");
		}
		else {
			p.sendMessage("��c[Area]: ������λ����,��������һ����λ");
		}
	}
	
}
