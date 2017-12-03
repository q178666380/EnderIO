package crazypants.enderio.machines.machine.alloy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import crazypants.enderio.base.GuiID;
import crazypants.enderio.base.init.IModObject;
import crazypants.enderio.base.machine.base.block.AbstractMachineBlock;
import crazypants.enderio.base.paint.IPaintable;
import crazypants.enderio.base.render.IBlockStateWrapper;
import crazypants.enderio.base.render.registry.TextureRegistry;
import crazypants.enderio.base.render.registry.TextureRegistry.TextureSupplier;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAlloySmelter extends AbstractMachineBlock<TileAlloySmelter> implements IPaintable.ISolidBlockPaintableBlock, IPaintable.IWrenchHideablePaint {

  public static BlockAlloySmelter create(@Nonnull IModObject modObject) {
    BlockAlloySmelter res = new BlockAlloySmelter(modObject);
    res.init();
    return res;
  }

  public static final TextureSupplier vanillaSmeltingOn = TextureRegistry.registerTexture("blocks/furnace_smelting_on");
  public static final TextureSupplier vanillaSmeltingOff = TextureRegistry.registerTexture("blocks/furnace_smelting_off");
  public static final TextureSupplier vanillaSmeltingOnly = TextureRegistry.registerTexture("blocks/furnace_smelting_only");

  private BlockAlloySmelter(@Nonnull IModObject modObject) {
    super(modObject, TileAlloySmelter.class);
  }

  protected BlockAlloySmelter(@Nonnull IModObject mo, @Nullable Class<TileAlloySmelter> teClass, @Nonnull Material mat) {
    super(mo, teClass, mat);
  }

  protected BlockAlloySmelter(@Nonnull IModObject mo, @Nullable Class<TileAlloySmelter> teClass) {
    super(mo, teClass);
  }

  @Override
  public Object getServerGuiElement(int ID, @Nonnull EntityPlayer player, @Nonnull World world, @Nonnull BlockPos pos) {
    TileAlloySmelter te = getTileEntity(world, pos);
    if (te != null) {
      return new ContainerAlloySmelter(player.inventory, te);
    }
    return null;
  }

  @Override
  public Object getClientGuiElement(int ID, @Nonnull EntityPlayer player, @Nonnull World world, @Nonnull BlockPos pos) {
    TileAlloySmelter te = getTileEntity(world, pos);
    if (te != null) {
      return new GuiAlloySmelter(player.inventory, te);
    }
    return null;
  }

  @Override
  protected @Nonnull GuiID getGuiId() {
    return GuiID.GUI_ID_ALLOY_SMELTER;
  }

  @Override
  protected void setBlockStateWrapperCache(@Nonnull IBlockStateWrapper blockStateWrapper, @Nonnull IBlockAccess world, @Nonnull BlockPos pos,
      @Nonnull TileAlloySmelter tileEntity) {
    blockStateWrapper.addCacheKey(tileEntity.getFacing()).addCacheKey(tileEntity.isActive());
  }

}