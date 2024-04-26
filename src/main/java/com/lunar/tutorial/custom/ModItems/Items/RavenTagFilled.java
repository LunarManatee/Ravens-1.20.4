package com.lunar.tutorial.custom.ModItems.Items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RavenTagFilled extends Item {

    public RavenTagFilled(Settings settings) {
        super(settings);
    }

    public static void assignedPerch(ItemStack stack, BlockPos pos) {
        NbtCompound nbt = new NbtCompound();
        ArrayList<Integer> pos_XYZ = new ArrayList<>();

        stack.setNbt(nbt);
        pos_XYZ.add(pos.getX()); pos_XYZ.add(pos.getY()); pos_XYZ.add(pos.getZ());
        nbt.putIntArray("ravens.bound_coords", pos_XYZ);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.hasNbt()) {
            assert stack.getNbt() != null;
            int[] array = stack.getNbt().getIntArray("ravens.bound_coords");
            tooltip.add(Text.literal("Tag Bound To "
                    + "(" + /* x-coordinate */ array[0]
                    + ", " + /* y-coordinate */ array[1]
                    + ", " + /* z-coordinate */ array[2]
                    + ")").formatted(Formatting.GRAY));
        }
    }

    public static int getAssignedPos(ItemStack stack, String key, int index) {
        assert stack.getNbt() != null;
        return stack.getNbt().getIntArray(key)[index];
    }
}
