package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.save.ArSave;

public class SetWinnerItemSubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		// TODO Auto-generated method stub
		// arena setwinitem 竞技场id
		//判定自变量长度
		if(args.length<2) {
			p.sendMessage("§c指令格式错误 正确格式: /arena setwinneritem 竞技场id");
			return;
		}
		//查看竞技场是否存在
		if(!Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("§c此竞技场不存在");
			//不存在
			return;
		}
		//设置
		ArenaInfo ai = Arena.arenaList.get(args[1]);
		//胜利物品只可给Boss战或者守卫战使用
		if(!ai.getMode().equals("boss")||!ai.getMode().equals("guard")) {
			p.sendMessage("§c胜利物品只可给boss战或保卫战使用");
			return;
		}
		//取手上物品
		ItemStack is = p.getInventory().getItemInHand();
		if(is.getTypeId()==0) {
			p.sendMessage("§a手上请至少有一个物品");
			return;
		}
		ai.setWinnerItem(is);
		p.sendMessage("§a成功设置胜利方获胜物品");
		//保存
		ArSave.save(Arena.arenaList.get(args[1]));
	}

}
