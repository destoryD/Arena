package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.save.ArSave;

public class CreateSubCommand implements ArSubCommand{
	@Override
	public void command(Player p, String[] args) {
		if(args.length<3) {
			p.sendMessage("§c请正确输入命令: 格式/Arena create ID 模式");
			return;
		}
		//判断是否有选点数据
		if(Arena.playerList.get(p.getName()).getLoc1()==null||Arena.playerList.get(p.getName()).getLoc2()==null) {
			p.sendMessage("§c请先选中两个点");
			//为空
			return;
		}
		//判断ID是否存在
		if(Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("§c该ID已经存在");
			return;
		}
		//判断是否有此类型
		if(!Arena.ARENA_LIST.contains(args[2])) {
			p.sendMessage("§c该模式不存在");
			return;
		}
		ArenaInfo arInfo = new ArenaInfo(args[1],args[2],Arena.playerList.get(p.getName()).getLoc1(),Arena.playerList.get(p.getName()).getLoc2());
		//创建
		Arena.arenaList.put(args[1], arInfo);
		p.sendMessage("§a创建成功");
		//取消掉选点状态
		Arena.playerList.get(p.getName()).setBool(false);
		ArSave.save(arInfo);
	}

}
