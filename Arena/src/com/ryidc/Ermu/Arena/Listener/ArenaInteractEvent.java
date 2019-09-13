package com.ryidc.Ermu.Arena.Listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.ryidc.Ermu.Arena.Arena;

/**
 * ����¼��ļ��� �����
 * @author Er_mu
 *
 */
public class ArenaInteractEvent implements Listener{
	private Plugin plug;
	public ArenaInteractEvent(Plugin plug) {
		this.plug = plug;
	}
	@EventHandler
	public void playerClick(PlayerInteractEvent e) {
		if(e.getItem()==null) {
			return;
		}
		//�鿴������Ƿ�����Ϸ�� �жϽ���ʱ�����Ƿ���ĩӰ���� �ǵĻ�ȡ�����¼�
		if(Arena.playerList.get(e.getPlayer().getName()).getArenaId()!=null) {
			//ֻ��û��Ȩ�޵��˻��ܵ�����
			if(!e.getPlayer().hasPermission("*")) {
				//�ж���Ʒ
				if(e.getItem().getType().equals(Material.ENDER_PEARL)) {
					//������Ϣ
					e.getPlayer().sendMessage("��c��ֹʹ��ĩӰ����");
					//ȡ���¼�
					e.setCancelled(true);
				}
			}	
		}
		//�жϽ�����ʽ
		if(e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			//�������������ƷΪ�ջ��������
			if(e.getItem()==null||e.getClickedBlock()==null) {
				return;
			}
			//�����
			if(this.saveLocation(e.getPlayer(), e.getItem(), e.getClickedBlock().getLocation(), "left")) {
				//�����ɹ�ȡ��ʱ��
				e.setCancelled(true);
			}
			
		}
		else if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			//�һ�����������ƷΪ�ջ��������
			if(e.getItem()==null||e.getClickedBlock()==null) {
				return;
			}
			//�����
			if(this.saveLocation(e.getPlayer(), e.getItem(), e.getClickedBlock().getLocation(), "right")) {
				e.setCancelled(true);
			}
		}
	}
	/**
	 * �жϲ������
	 * @return ѡ��״̬
	 */
	private boolean saveLocation(Player p,ItemStack item,Location loc,String mode) {
		//�жϵ�ǰѡ��״̬
		if(Arena.playerList.get(p.getName()).getBool()) {
			//�ж���Ʒ
			if(item.getTypeId()==this.plug.getConfig().getInt("selectId")) {
				//���������ļ�����Ʒ
				//ѡ��
				if(mode.equals("left")) {
					Arena.playerList.get(p.getName()).setLoc1(loc);
					p.sendMessage("��a��ѡ����X: "+loc.getX()+" Y: "+loc.getY()+" Z: "+loc.getZ());
					return true;
				}else {
					Arena.playerList.get(p.getName()).setLoc2(loc);
					p.sendMessage("��a��ѡ����X: "+loc.getX()+" Y: "+loc.getY()+" Z: "+loc.getZ());
					return true;
				}
			}
			else{
				p.sendMessage("��c��ʹ��ָ����Ʒ����ѡ��,�����˳�����������/Arena cancel");
			}
		}
		return false;
		
	}
	
}
