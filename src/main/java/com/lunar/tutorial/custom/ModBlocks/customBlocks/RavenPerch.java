package com.lunar.tutorial.custom.ModBlocks.customBlocks;

import com.lunar.tutorial.custom.ModItems.Items.RavenTagFilled;
import com.lunar.tutorial.custom.ModItems.ModItems;
import com.lunar.tutorial.custom.sounds.ModSounds;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.util.math.Direction.*;

public class RavenPerch extends HorizontalFacingBlock {
    public static final IntProperty SINGLE_USE = IntProperty.of("count", 0, 1);
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public RavenPerch(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(SINGLE_USE, 0));
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return null;
    }

    private static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(7, 0, 7, 9, 32, 9),
            Block.createCuboidShape(5, 28.5, 0, 14, 29.5, 16),
            Block.createCuboidShape(7.5, 18, -2, 8.5, 23, 7));

    private static final VoxelShape SHAPE_NORTH = VoxelShapes.union(
            Block.createCuboidShape(7, 0, 7, 9, 32, 9),
            Block.createCuboidShape(7.5, 28.5, -3, 8.5, 29.5, 11),
            Block.createCuboidShape(7.5, 18, -2, 8.5, 23, 7));

    private static final VoxelShape SHAPE_WEST = VoxelShapes.union(
            Block.createCuboidShape(7, 0, 7, 9, 32, 9),
            Block.createCuboidShape(-3, 28.5, 7.5, 11, 29.5, 8.5),
            Block.createCuboidShape(-2, 18, 7.5, 7, 23, 8.5));

    private static final VoxelShape SHAPE_EAST = VoxelShapes.union(
            Block.createCuboidShape(7, 0, 7, 9, 32, 9),
            Block.createCuboidShape(5, 28.5, 7.5, 19, 29.5, 8.5),
            Block.createCuboidShape(7, 18, 7.5, 18, 23, 8.5));

    private static final VoxelShape SHAPE_SOUTH = VoxelShapes.union(
            Block.createCuboidShape(7, 0, 7, 9, 32, 9),
            Block.createCuboidShape(7.5, 28.5, 5, 8.5, 29.5, 19),
            Block.createCuboidShape(7.5, 18, 7, 8.5, 23, 18));

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(FACING) == NORTH) {
            return SHAPE_NORTH;
        }
        else if (state.get(FACING) == WEST) {
            return SHAPE_WEST;
        }
        else if (state.get(FACING) == SOUTH) {
            return SHAPE_SOUTH;
        }
        else if (state.get(FACING) == EAST) {
            return SHAPE_EAST;
        }
        return SHAPE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getMainHandStack();

        if (!world.isClient() && (stack.isOf(ModItems.RAVEN_TAG_B)
                || stack.isOf(ModItems.RAVEN_TAG_F))
                && state.get(SINGLE_USE) == 0) {

            if (player.getMainHandStack().hasNbt()) {
                BlockPos prev_pos = new BlockPos(
                        RavenTagFilled.getAssignedPos(stack, "ravens.bound_coords", 0),
                        RavenTagFilled.getAssignedPos(stack, "ravens.bound_coords", 1),
                        RavenTagFilled.getAssignedPos(stack, "ravens.bound_coords", 2)
                );

                world.setBlockState(prev_pos, state.with(SINGLE_USE, 0));
            }

            player.setStackInHand(Hand.MAIN_HAND, new ItemStack(ModItems.RAVEN_TAG_F));

            world.playSound(null, pos, ModSounds.RAVEN_TAG_FILL, SoundCategory.AMBIENT, 1f, 1f);
            world.setBlockState(pos, state.with(SINGLE_USE, 1));

            RavenTagFilled.assignedPerch(player.getMainHandStack(), pos);
        }
        else if (!world.isClient() && (stack.isOf(ModItems.RAVEN_TAG_B)
                || stack.isOf(ModItems.RAVEN_TAG_F))
                && state.get(SINGLE_USE) == 1) {
            player.sendMessage(Text.literal("This perch can't be assigned to another raven tag ("
                    + pos.getX()
                    + ", " + pos.getY()
                    + ", " + pos.getZ()
                    + ")")
                    .formatted(Formatting.GRAY));
        }
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(SINGLE_USE);
    }
}
