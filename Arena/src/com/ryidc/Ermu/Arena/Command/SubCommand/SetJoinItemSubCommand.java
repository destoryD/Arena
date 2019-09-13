package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.save.ArSave;

/**
 * 将手中物品设置为进入物品
 * @author Er_mu
 *
 */
public class SetJoinItemSubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		//判断自变量
		//正确格式 /arena setjoinitem 竞技场id [null]
		if(args.length<2) {
			p.sendMessage("§c指令格式错误 正确格式/arena setjoinitem 竞技场id");
			return;
		}
		//查看竞技场是否存在
		if(!Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("§c此竞技场不存在");
			//不存在
		}
		//判断有没有输入第三个参数
		if(args.length>=3) {
			//判断是否为null
			if(args[2].equals("null")) {
				//删除物品
				Arena.arenaList.get(args[1]).setJoinItem(null);
				p.sendMessage("§a进入物品删除成功!");
				//保存
				ArSave.save(Arena.arenaList.get(args[1]));
				return;
			}else {
				p.sendMessage("§c参数输入错误");
				return;
			}
		}
		//取手上物品
		ItemStack is = p.getInventory().getItemInHand();
		if(is.getTypeId()==0) {
			p.sendMessage("§a手上请至少有一个物品");
			return;
		}
		//设置物品
		Arena.arenaList.get(args[1]).setJoinItem(is);
		p.sendMessage("§a进入物品设置成功!");
		//保存
		ArSave.save(Arena.arenaList.get(args[1]));
		
	}
	
}
