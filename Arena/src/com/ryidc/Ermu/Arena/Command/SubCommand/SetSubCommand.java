package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.ryidc.Ermu.Arena.Arena;

/**
 * 拿到选点工具 并开始选点
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
		//给予物品
		int id = this.plug.getConfig().getInt("selectId");
		//获取玩家物品容器
		Inventory inv = p.getInventory();
		//判断是否有空位
		if(inv.firstEmpty()!=-1) {
			//设置状态
			Arena.playerList.get(p.getName()).setBool(true);
			//给予物品
			inv.addItem(new ItemStack(id));
			p.sendMessage("§a[Area]: 已经进入选点状态");
		}
		else {
			p.sendMessage("§c[Area]: 背包空位不足,请至少有一个空位");
		}
	}
	
}
