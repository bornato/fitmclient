package minegame159.meteorclient.events.world;

import net.minecraft.block.BlockState;
import net.minecraft.util.shape.VoxelShape;

public class FluidCollisionShapeEvent {
    private static final FluidCollisionShapeEvent INSTANCE = new FluidCollisionShapeEvent();

    public BlockState state;
    public VoxelShape shape;

    public static FluidCollisionShapeEvent get(BlockState state) {
        INSTANCE.state = state;
        INSTANCE.shape = null;
        return INSTANCE;
    }
}
