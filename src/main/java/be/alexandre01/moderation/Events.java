package be.alexandre01.moderation;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

public class Events implements Listener {
    private Moderation m;

    Events(Moderation moderation) {
        this.m = moderation;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        m.players.add(player);
        for (Player players : m.getList()) {
            player.hidePlayer(players);

        }
    }

    @EventHandler
    public void onQuit(PlayerJoinEvent event) {


        Player player = event.getPlayer();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onTabComplete(PlayerChatTabCompleteEvent e) {
        Player player = e.getPlayer();

        e.getTabCompletions().clear();

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            try{
                if (event.getItem().getType() == Material.BOOK) {

                    int lines = 0;
                    int online = Bukkit.getOnlinePlayers().size();
                    ArrayList<String> list = new ArrayList<String>();

                    for (Player all : Bukkit.getOnlinePlayers()) {

                        list.add(all.getName()); //add all online players to the list

                    }

                    while (lines * 9 < online) {

                        lines++; //while the lines times 9 is less than online players we add one more line to the inventory

                    }
                    if (lines < 7) {


                        Inventory inv = Bukkit.createInventory(null, lines * 9, "§cListe des joueurs"); //create the inventory with lines needed (slots, every line 9 slots)

                        int slot = 0;

                        for (int i = 0; i < online; i++) {

                            String put = list.get(i);

                            ItemStack kopf = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                            SkullMeta kopfmeta = (SkullMeta) kopf.getItemMeta();
                            kopfmeta.setDisplayName("§e" + put);
                            kopfmeta.setOwner(put);
                            kopf.setItemMeta(kopfmeta);


// get skullmeta

                            inv.setItem(slot, kopf);
                            slot++;


                        }

                        player.openInventory(inv);

                    } else {

                        lines = 6;

                    }

                }
                if (event.getItem().getType() == Material.WOOD_SWORD) {
                    ItemStack it = event.getItem();
                    ItemMeta itm = it.getItemMeta();
                    int enchantLevel = itm.getEnchantLevel(Enchantment.KNOCKBACK);
                    if(enchantLevel == 6){
                        enchantLevel = 10;

                        it.removeEnchantment(Enchantment.KNOCKBACK);
                        it.addUnsafeEnchantment(Enchantment.KNOCKBACK,enchantLevel);
                        Title.sItem(player, "Kb level "+ enchantLevel );
                    }else {
                        if (enchantLevel == 10) {
                            enchantLevel = 2;

                            it.removeEnchantment(Enchantment.KNOCKBACK);
                            it.addUnsafeEnchantment(Enchantment.KNOCKBACK, enchantLevel);
                            Title.sItem(player, "Kb level " + enchantLevel);
                        } else {
                            if (enchantLevel < 10) {
                                enchantLevel++;


                                it.removeEnchantment(Enchantment.KNOCKBACK);
                                it.addUnsafeEnchantment(Enchantment.KNOCKBACK, enchantLevel);
                                Title.sItem(player, "Kb level " + enchantLevel);
                            }
                        }
                    }

                }
            }catch (Exception e){

            }
        }
    }

    @EventHandler
    public void onChangeItem(PlayerItemHeldEvent event) {
        int slot = event.getNewSlot();
        Player player = event.getPlayer();
        PlayerInventory inv = player.getInventory();
        if (m.getKey().containsKey(player)) {
            try {
             if(inv.getItem(slot).getType() == Material.BOOK) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (inv.getItemInMainHand().getType() != Material.BOOK) {
                            cancel();
                        } else {
                            if (Bukkit.getOnlinePlayers().size() <= 1) {
                                Title.sItem(player, "Il y a " + Bukkit.getOnlinePlayers().size() + " joueur connecté sur le serveur");
                            } else {
                                Title.sItem(player, "Il y a " + Bukkit.getOnlinePlayers().size() + " joueurs connectés sur le serveur");
                            }
                        }
                    }
                }.runTaskTimer(m, 0L, 20L);
            }else if(inv.getItem(slot).getType() == Material.STICK) {

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (inv.getItemInMainHand().getType() != Material.STICK) {
                                cancel();
                            } else {
                                Title.sItem(player, "Nom : PLAYERNAME ||  DISTANCE : ? Blocks || Vie : ?%");
                            }
                        }
                    }.runTaskTimer(m, 0L, 20L);
                } else if (inv.getItem(slot).getType() == Material.WOOD_SWORD) {

                        Material material = inv.getItem(slot).getType();
                        ItemStack it = new ItemStack(material);
                        ItemMeta itm = it.getItemMeta();
                        int enchantLevel = itm.getEnchantLevel(Enchantment.KNOCKBACK);


                        new BukkitRunnable() {
                            @Override
                            public void run() {

                                if (inv.getItemInMainHand().getType() != Material.STICK) {
                                    cancel();
                                } else {
                                    int enchantLevel = itm.getEnchantLevel(Enchantment.KNOCKBACK);
                                    Title.sItem(player, "Kb level " + enchantLevel);
                                }
                            }
                        }.runTaskTimer(m, 0L, 20L);


                    }else if(inv.getItem(slot).getType() == Material.BARRIER) {

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    if (inv.getItemInMainHand().getType() != Material.BARRIER) {
                                        cancel();
                                    } else {
                                        Title.sItem(player, "Click gauche : Freeze target || Click droit : Freeze un joueur ciblé ");
                                    }
                                }

                            }.runTaskTimer(m, 0L, 20L);

                        } else {
                            if (inv.getItem(slot).getType() == Material.PAPER) {

                                new BukkitRunnable() {
                                    @Override
                                    public void run() {

                                        if (inv.getItemInMainHand().getType() != Material.PAPER) {
                                            cancel();
                                        } else {
                                            Title.sItem(player, "Total de report : ?");
                                        }
                                    }
                                }.runTaskTimer(m, 0L, 20L);

                            }
                        }



            }catch (Exception ignored) {


            }

        }
    }
    @EventHandler
    public void onDropItem(PlayerDropItemEvent event){
        if(m.getKey().containsKey(event.getPlayer())){
            event.setCancelled(true);
        }
    }
    }

