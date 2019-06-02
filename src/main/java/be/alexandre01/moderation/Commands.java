package be.alexandre01.moderation;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Commands implements CommandExecutor {
    private Moderation m;
    public Commands(Moderation moderation) {
        this.m = moderation;
    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("mod")) {
                if(m.getKey().containsKey(player)) {
                    player.sendMessage("ok1");
                    m.getKey().remove(player, 1);
                    m.getList().remove(player);
                    for(Player players: Bukkit.getOnlinePlayers()) {
                        players.showPlayer(player);

                    }

                }else {
                    player.sendMessage("ok2");
                    m.getKey().put(player, 1);
                    m.getList().add(player);
                    for(Player players: Bukkit.getOnlinePlayers()) {
                        players.hidePlayer(player);
                        players.sendMessage("§7[§c-§7] "+player.getName()+" a rejoins HubZon !");
                    }
                    ItemStack vanishon = new ItemStack(Material.REDSTONE_TORCH_ON);
                    ItemMeta vonM = vanishon.getItemMeta();
                    vonM.setDisplayName("Vanish");
                    vanishon.setItemMeta(vonM);
                    ItemStack vanishoff = new ItemStack(Material.REDSTONE_TORCH_OFF);
                    ItemMeta voffM = vanishoff.getItemMeta();
                    voffM.setDisplayName("Vanish");
                    vanishoff.setItemMeta(voffM);
                    ItemStack targetoff = new ItemStack(Material.STICK);
                    ItemMeta toff = targetoff.getItemMeta();
                    toff.setDisplayName("Target");
                    targetoff.setItemMeta(toff);
                    ItemStack targeton = new ItemStack(Material.BLAZE_ROD);
                    ItemMeta ton = targetoff.getItemMeta();
                    ton.setDisplayName("Target");
                    targeton.setItemMeta(ton);
                    ItemStack ReportList = new ItemStack(Material.PAPER);
                    ItemMeta rlm = ReportList.getItemMeta();
                    rlm.setDisplayName("Liste des reports");
                    ReportList.setItemMeta(rlm);
                    ItemStack PlayerList = new ItemStack(Material.BOOK);
                    ItemMeta pl = PlayerList.getItemMeta();
                    pl.setDisplayName("Liste des joueurs");
                    PlayerList.setItemMeta(pl);
                    ItemStack Freeze = new ItemStack(Material.BARRIER);
                    ItemMeta feM = Freeze.getItemMeta();
                    feM.setDisplayName("Freeze");
                    Freeze.setItemMeta(feM);
                    ItemStack Punishment = new ItemStack(Material.BEDROCK);
                    ItemMeta pt = Punishment.getItemMeta();
                    pt.setDisplayName("Punnition");
                    Punishment.setItemMeta(pt);
                    ItemStack Kb= new ItemStack(Material.WOOD_SWORD);
                    ItemMeta KbM = Kb.getItemMeta();
                    KbM.addEnchant(Enchantment.KNOCKBACK, 2, true);
                    KbM.setDisplayName("KABESTIKE");
                    Kb.setItemMeta(KbM);

                    player.getInventory().clear();
                    player.updateInventory();
                    player.getInventory().setItem(6,Punishment);
                    player.getInventory().setItem(5,Freeze);
                    player.getInventory().setItem(3,ReportList);
                    player.getInventory().setItem(2,vanishon);
                    player.getInventory().setItem(0,targetoff);
                    player.getInventory().setItem(1,PlayerList);
                    player.getInventory().setItem(8,Kb);
                    player.updateInventory();


                }

            }

        }
        return false;
    }

}
