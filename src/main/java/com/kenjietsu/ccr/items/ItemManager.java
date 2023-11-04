package com.kenjietsu.ccr.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static ItemStack aote;
    public static ItemStack catcherItem;
    public static ItemStack papaFriaItem;
    public static ItemStack blindFold;
    public static void init() {
        createAote();
        createCatcherItem();
        createPapaFriaItem();
        createBlindFold();
    }
    private static void createAote() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setUnbreakable(true);
        Component displayName = Component.text("Aspect of the End").color(TextColor.color(0x524FF));
        meta.displayName(displayName);

        List<Component> lore;
        lore = new ArrayList<Component>();

        lore.add(Component.text("Will TP you 12 blocks forward").color(TextColor.color(0xF9FCFF)));

        meta.lore(lore);

        meta.addEnchant(Enchantment.DIG_SPEED, 1, true);

        item.setItemMeta(meta);

        aote = item;

    }
    private static void createCatcherItem() {
        ItemStack item = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        Component name = Component.text("Aniquilador de Entidades").color(net.kyori.adventure.text.format.TextColor.color(0x42FFE4));

        meta.displayName(name);
        meta.setCustomModelData(1);

        item.setItemMeta(meta);

        catcherItem = item;

    }
    private static void createPapaFriaItem() {
        ItemStack item = new ItemStack(Material.POISONOUS_POTATO);
        ItemMeta meta = item.getItemMeta();

        TextComponent itemName = Component.text("a").decorate(TextDecoration.BOLD).decorate(TextDecoration.OBFUSCATED).color(net.kyori.adventure.text.format.TextColor.color(0x232EFF))
                .append(Component.text("Papa Fria").decorate(TextDecoration.BOLD).color(net.kyori.adventure.text.format.TextColor.color(0x6AADFF))).decorate(TextDecoration.ITALIC)
                .append(Component.text("a").decorate(TextDecoration.BOLD).decorate(TextDecoration.OBFUSCATED).color(net.kyori.adventure.text.format.TextColor.color(0x232EFF)));

        meta.displayName(itemName);
        meta.setCustomModelData(1);

        item.setItemMeta(meta);
        papaFriaItem = item;
    }
    private static void createBlindFold() {
        ItemStack item = new ItemStack(Material.CARVED_PUMPKIN);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(1);
        meta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        blindFold = item;
    }
}
