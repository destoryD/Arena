package com.ryidc.Ermu.Arena.Command.SubCommand;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.game.GameStartListener;

/**
 * ���뾺����ָ��
 * @author Er_mu
 *
 */
public class JoinSubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		//arena join arena
		//�ж��Ƿ��о������������Ƿ������Ӧ������
		if(args.length<2) {
			p.sendMessage("��c����������� ��ȷ��ʽ /arena join ������");
			return;
		}
		if(!Arena.arenaList.containsKey(args[1])) {
			//�������˾�����id
			p.sendMessage("��c�˾�����������!");
			return;
		}
		//�ж�ģʽ�Ƿ�Ϊ�Ŷ�
		if(Arena.arenaList.get(args[1]).getMode().equals("team")) {
			//�жϳ������Ƿ�����
			if(Arena.arenaList.get(args[1]).getFirstLocation()==null||Arena.arenaList.get(args[1]).getSecondLocation()==null) {
				p.sendMessage("��c�˾���������δ���,������");
				return;
			}
		}else if(Arena.arenaList.get(args[1]).getMode().equals("single")) {
			if(Arena.arenaList.get(args[1]).getMinPerson()<4) {
				p.sendMessage("��c���˾������4��,������");
				return;
			}
		}
		//�ж������Ƿ���� ������� ��С���� �ȴ�ʱ�� �����ս���� ���� �Լ�������Ϸʱ��
		if(Arena.arenaList.get(args[1]).getMinPerson()<=0||Arena.arenaList.get(args[1]).getTime()<=0||Arena.arenaList.get(args[1]).getMaxPerson()<=0||Arena.arenaList.get(args[1]).getMaxNumber()<=0||Arena.arenaList.get(args[1]).getWaitLocation()==null||Arena.arenaList.get(args[1]).getGameTime()<=0) {
			p.sendMessage("��c�˾���������δ���,������");
			return;
		}
		//�жϴ�����Ƿ��Ѿ������˾�����
		if(Arena.playerList.get(p.getName()).getArenaId()!=null) {
			p.sendMessage("��c���Ѿ���������������,�����˳�");
			return;
		}
		//�жϴ���Ϸ�Ƿ��Ѿ���ʼ
		if(Arena.arenaList.get(args[1]).getStartBool()) {
			p.sendMessage("��a�˾������Ѿ���ʼ~");
			return;
		}
		//�ж��������
		if(Arena.arenaList.get(args[1]).getPlayerList().size()>=Arena.arenaList.get(args[1]).getMaxNumber()) {
			p.sendMessage("��c�˾���������~");
			return;
		}
		//�жϾ�������ս�����Ƿ��д����
		if(Arena.arenaList.get(args[1]).getNumber().containsKey(p.getName())) {
			//�ж���ս����
			if(Arena.arenaList.get(args[1]).getNumber().get(p.getName())>=Arena.arenaList.get(args[1]).getMaxNumber()) {
				p.sendMessage("��c��������ս�����Ѿ�������~");
				return;
			}
		}
		//ȡ��ǰʱ��
		Date d = new Date();
		//תΪ������ �������
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		//�������������� �ж�����
		if(Arena.arenaList.get(args[1]).getWeek()!=0) {	
			//�ж�����
			if(c.get(Calendar.DAY_OF_WEEK)!=Arena.arenaList.get(args[1]).getWeek()) {
				p.sendMessage("��aδ������ʱ��Ŷ~");
				return;
			}	
		}
		if(Arena.arenaList.get(args[1]).getHour()!=0) {
			//�ж�Сʱ�� 24Сʱ
			if(c.get(Calendar.HOUR_OF_DAY)!=Arena.arenaList.get(args[1]).getHour()) {
				p.sendMessage("��aδ������ʱ��Ŷ~");
				return;
			}
		}
		//�ж��Ƿ���Ҫ������Ʒ
		if(Arena.arenaList.get(args[1]).getJoinItem()!=null) {
			//�����Ʒ��
			PlayerInventory pinv = p.getInventory();
			//������Ʒ
			ItemStack joinItem = Arena.arenaList.get(args[1]).getJoinItem();
			//����Ƿ��д���Ʒ
			if(!pinv.contains(joinItem.getType())) {
				p.sendMessage("��e������û�н�����Ʒ!");
				return;
			}else {
				//����id��Ӧ�ĵ�һ����Ʒ
				int playerItemIndex = pinv.first(joinItem.getType());
				ItemStack playerItem = pinv.getItem(playerItemIndex);
				//ȡItemMeta�ж�չʾ����lore
				ItemMeta joinMeta = joinItem.getItemMeta();
				ItemMeta playerMeta = playerItem.getItemMeta();
				List<String> joinLore = joinMeta.getLore();
				String joinDisName = joinMeta.getDisplayName();
				//���ж�����
				if(joinDisName!=null) {
					String playerDisName = playerMeta.getDisplayName();
					//ԭ��Ʒ��չʾ�� ��Ҫ�ж�
					if(playerDisName==null||!playerDisName.equals(joinDisName)) {
						//�����Ʒû������� ��չʾ����ͬ
						p.sendMessage("��e������û�н�����Ʒ!");
						return;
					}
				}
				//���ж�lore
				if(joinLore!=null) {
					//ȡ������Ʒlore
					List<String> playerLore = playerMeta.getLore();
					if(playerLore==null||playerLore.size()!=joinLore.size()) {
						//��Ʒ��lore �򳤶Ȳ�ͬ ֱ�ӹر�
						p.sendMessage("��e������û�н�����Ʒ!");
						return;
					}
					//�ж�����
					for(int i=0;i<joinLore.size();i++) {
						//��Ʒ��ͬʱ loreӦ����һһ��Ӧ�� ��һ����ͬ����һ����Ʒ
						if(!joinLore.get(i).equals(playerLore.get(i))) {
							p.sendMessage("��e������û�н�����Ʒ!");
							return;
						}
					}
				}
				//�жϸ���
				if(playerItem.getAmount()<joinItem.getAmount()) {
					p.sendMessage("��e��Ʒ��������!");
					return;
				}else if(playerItem.getAmount()==joinItem.getAmount()){
					p.sendMessage("��e���ѽ����볡��Ʒ,׼�����!");
					//ɾ��
					pinv.clear(playerItemIndex);
					
				}else if(playerItem.getAmount()>joinItem.getAmount()) {
					p.sendMessage("��e���ѽ����볡��Ʒ,׼�����!");
					//���öѵ�
					playerItem.setAmount(playerItem.getAmount()-joinItem.getAmount());
				}
			}
		}
		//���뾺����
		Arena.arenaList.get(args[1]).getPlayerList().put(p.getName(), p);
		//�޸�״̬
		Arena.playerList.get(p.getName()).setArenaId(args[1]);
		//���͵�����
		p.teleport(Arena.arenaList.get(args[1]).getWaitLocation());
		//�ж�����
		if(Arena.arenaList.get(args[1]).getMinPerson()<=Arena.arenaList.get(args[1]).getPlayerList().size()) {
			GameStartListener.senderMessageToAllPlayer("��a��ҡ�e"+p.getName()+"��a��������Ϸ,���ڵȴ���Ϸ��ʼ", args[1]);
		}else {
			//�򾺼���ȫ����ҷ���һ����Ϣ
			GameStartListener.senderMessageToAllPlayer("��a��ҡ�e"+p.getName()+"��a��������Ϸ,����"+(Arena.arenaList.get(args[1]).getMinPerson()-Arena.arenaList.get(args[1]).getPlayerList().size())+"��", args[1]);
		}
		
		
	}

}
