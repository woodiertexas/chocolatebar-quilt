package io.github.woodiertexas.chocolate_bar;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class ChocolateBarItem extends Item {
    /*
    This is a super class of settings.
     */
    public ChocolateBarItem(Settings settings) {
        super(settings);
    }

    /*
    The block of code below deals with adding tooltips to the item.
     */
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext options) {
        //in '.styled()'; style is lamba'd to the 'style.withColor()' method to color the toolip.
        MutableText lore = Text.literal("Potato chocolate?").styled(style -> style.withColor(0xB79268));

        //This just finalizes the tooltip adding. That's quite literally all this does.
        tooltip.add(lore);
    }

}
