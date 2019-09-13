package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.save.ArSave;

/**
 * 设置个人竞技第一物品
 * @author Er_mu
 *
 */
public class SetFirstItemSubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		// TODO Auto-generated method stub
		//判定自变量长度
		if(args.length<2) {
			p.sendMessage("§c指令格式错误 正确格式: /arena setfirstitem 竞技场id");
			return;
		}	
		//查看竞技场是否存在
		if(!Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("§c此竞技场不存在");
			//不存在
			return;
		}
		ArenaInfo ai = Arena.arenaList.get(args[1]);
		//第一物品只可给个人竞技使用
		if(!ai.getMode().equals("single")) {
			p.sendMessage("§c胜利物品只可给个人模式使用");
			return;
		}	
		//取手上物品
		ItemStack is = p.getInventory().getItemInHand();
		if(is.getTypeId()==0) {
			p.sendMessage("§a手上请至少有一个物品");
			return;
		}
		
		ai.setFirstItem(is);
		p.sendMessage("§a成功设置个人第一奖励物品");
		//保存
		ArSave.save(Arena.arenaList.get(args[1]));
	}

}
