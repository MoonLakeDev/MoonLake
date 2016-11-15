/*
 * Copyright (C) 2016 The MoonLake Authors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
 
 
package com.minecraft.moonlake.api.player;

import com.minecraft.moonlake.api.fancy.FancyMessage;
import com.minecraft.moonlake.exception.CannotDependException;
import com.minecraft.moonlake.exception.PlayerNotOnlineException;
import com.minecraft.moonlake.manager.PlayerManager;
import com.minecraft.moonlake.nms.packet.Packet;
import com.minecraft.moonlake.property.ReadOnlyObjectProperty;
import com.minecraft.moonlake.property.ReadOnlyStringProperty;
import com.minecraft.moonlake.property.SimpleObjectProperty;
import com.minecraft.moonlake.property.SimpleStringProperty;
import com.minecraft.moonlake.util.StringUtil;
import com.minecraft.moonlake.validate.Validate;
import com.mojang.authlib.GameProfile;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.*;

/**
 * <hr />
 * <div>
 *     <h1>Minecraft MoonLake Player Wrapped Abstract</h1>
 *     <p>By Month_Light Ver: 1.7</p>
 * </div>
 * <hr />
 *
 * @version 1.7
 * @author Month_Light
 * @see MoonLakePlayer
 */
public abstract class AbstractPlayer implements MoonLakePlayer {

    private final ReadOnlyStringProperty nameProperty;
    private final ReadOnlyObjectProperty<Player> playerProperty;

    /**
     * 月色之湖玩家抽象类构造函数
     *
     * @param name 玩家名
     * @throws IllegalArgumentException 如果玩家名对象为 {@code null} 则抛出异常
     * @throws PlayerNotOnlineException 如果玩家没有在线则抛出异常
     */
    public AbstractPlayer(String name) throws IllegalArgumentException, PlayerNotOnlineException {

        this(Bukkit.getPlayer(name));
    }

    /**
     * 月色之湖玩家抽象类构造函数
     *
     * @param player 玩家对象
     * @throws IllegalArgumentException 如果玩家名对象为 {@code null} 则抛出异常
     * @throws PlayerNotOnlineException 如果玩家没有在线则抛出异常
     */
    public AbstractPlayer(Player player) throws IllegalArgumentException, PlayerNotOnlineException {

        Validate.notNull(player, "The player object is null.");

        if(!player.isOnline()) {

            throw new PlayerNotOnlineException(player.getName());
        }
        this.nameProperty = new SimpleStringProperty(player.getName());
        this.playerProperty = new SimpleObjectProperty<>(player);
    }

    @Override
    public Player getBukkitPlayer() {

        return playerProperty.get();
    }

    @Override
    public String getName() {

        return nameProperty.get();
    }
    
    @Override
    public UUID getUniqueId() {

        return getBukkitPlayer().getUniqueId();
    }

    @Override
    public GameProfile getProfile() {

        return PlayerManager.getProfile(getBukkitPlayer());
    }

    @Override
    public String getDisplayName() {

        return getBukkitPlayer().getDisplayName();
    }

    @Override
    public void setDisplayName(String displayName) {

        getBukkitPlayer().setDisplayName(displayName);
    }

    @Override
    public String getListName() {

        return getBukkitPlayer().getPlayerListName();
    }

    @Override
    public void setListName(String listName) {

        getBukkitPlayer().setPlayerListName(listName);
    }

    @Override
    public void chat(String message) {

        Validate.notNull(message, "The chat message object is null.");

        getBukkitPlayer().chat(message);
    }

    @Override
    public void send(String message) {

        getBukkitPlayer().sendMessage(StringUtil.toColor(message));
    }

    @Override
    public void send(String... message) {

        getBukkitPlayer().sendMessage(StringUtil.toColor(message));
    }

    @Override
    public void send(FancyMessage fancyMessage) {

        Validate.notNull(fancyMessage, "The fancy message object is null.");

        fancyMessage.send(getBukkitPlayer());
    }

    @Override
    public void send(FancyMessage... fancyMessages) {

        Validate.notNull(fancyMessages, "The fancy message object is null.");

        for(FancyMessage fancyMessage : fancyMessages) {

            send(fancyMessage);
        }
    }

    @Override
    public double getHealth() {

        return getBukkitPlayer().getHealth();
    }

    @Override
    public double getMaxHealth() {

        return getBukkitPlayer().getMaxHealth();
    }

    @Override
    public void giveHealth(double amount) {

        if(getHealth() + amount >= getMaxHealth()) {

            setHealth(getMaxHealth());
            return;
        }
        setHealth(getHealth() + amount);
    }

    @Override
    public void takeHealth(double amount) {

        if(getHealth() - amount <= 0d) {

            setHealth(0d);
            return;
        }
        setHealth(getHealth() - amount);
    }

    @Override
    public float getXp() {

        return getBukkitPlayer().getExp();
    }

    @Override
    public int getLevel() {

        return getBukkitPlayer().getLevel();
    }

    @Override
    public void setHealth(double health) {

        getBukkitPlayer().setHealth(health);
    }

    @Override
    public void setMaxHealth(double maxHealth) {

        getBukkitPlayer().setMaxHealth(maxHealth);
    }

    @Override
    public void giveXp(float xp) {

        setXp(getXp() + xp);
    }

    @Override
    public void setXp(float xp) {

        getBukkitPlayer().setExp(xp);
    }

    @Override
    public void setLevel(int level) {

        getBukkitPlayer().setLevel(level);
    }

    @Override
    public float getFlySpeed() {

        return getBukkitPlayer().getFlySpeed();
    }

    @Override
    public int getFoodLevel() {

        return getBukkitPlayer().getFoodLevel();
    }

    @Override
    public void setFlySpeed(float flySpeed) {

        getBukkitPlayer().setFlySpeed(flySpeed);
    }

    @Override
    public void setFoodLevel(int foodLevel) {

        getBukkitPlayer().setFoodLevel(foodLevel);
    }

    @Override
    public Scoreboard getScoreboard() {

        return getBukkitPlayer().getScoreboard();
    }

    @Override
    public void setScoreboard(Scoreboard scoreboard) {

        getBukkitPlayer().setScoreboard(scoreboard);
    }

    @Override
    public void damage(double damage) {

        getBukkitPlayer().damage(damage);
    }

    @Override
    public void damage(double damage, LivingEntity damager) {

        getBukkitPlayer().damage(damage, damager);
    }

    @Override
    public Location getLocation() {

        return getBukkitPlayer().getLocation();
    }

    @Override
    public PlayerInventory getInventory() {

        return getBukkitPlayer().getInventory();
    }

    @Override
    public boolean hasBeforePlayed() {

        return getBukkitPlayer().hasPlayedBefore();
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOnGround() {

        return getBukkitPlayer().isOnGround();
    }

    @Override
    public boolean isFly() {

        return getBukkitPlayer().isFlying();
    }

    @Override
    public boolean isAllowFly() {

        return getBukkitPlayer().getAllowFlight();
    }

    @Override
    public void setAllowFly(boolean flag) {

        getBukkitPlayer().setAllowFlight(flag);
    }

    @Override
    public World getWorld() {

        return getBukkitPlayer().getWorld();
    }

    @Override
    public void updateInventory() {

        getBukkitPlayer().updateInventory();
    }

    @Override
    public void closeInventory() {

        getBukkitPlayer().closeInventory();
    }

    @Override
    public float getWalkSpeed() {

        return getBukkitPlayer().getWalkSpeed();
    }

    @Override
    public void setWalkSpeed(float speed) {

        getBukkitPlayer().setWalkSpeed(speed);
    }

    @Override
    public int getX() {

        return getLocation().getBlockX();
    }

    @Override
    public int getY() {

        return getLocation().getBlockY();
    }

    @Override
    public int getZ() {

        return getLocation().getBlockZ();
    }

    @Override
    public double getDoubleX() {

        return getLocation().getX();
    }

    @Override
    public double getDoubleY() {

        return getLocation().getY();
    }

    @Override
    public double getDoubleZ() {

        return getLocation().getZ();
    }

    @Override
    public void teleport(Entity entity) {

        getBukkitPlayer().teleport(entity);
    }

    @Override
    public void teleport(MoonLakePlayer player) {

        Validate.notNull(player, "The teleport player object is null.");

        teleport(player.getLocation());
    }

    @Override
    public void teleport(Location location) {

        getBukkitPlayer().teleport(location);
    }

    @Override
    public void teleport(World world) {

        Validate.notNull(world, "The teleport world object is null.");

        teleport(world.getSpawnLocation());
    }

    @Override
    public void teleport(String world) {

        Validate.notNull(world, "The teleport world string object is null.");

        World target = Bukkit.getServer().getWorld(world);

        teleport(target);
    }

    @Override
    public void teleport(int x, int y, int z) {

        teleport((double)x, (double)y, (double)z);
    }

    @Override
    public void teleport(double x, double y, double z) {

        getBukkitPlayer().teleport(new Location(getWorld(), x, y, z));
    }

    @Override
    public void teleport(World world, int x, int y, int z) {

        teleport(world, (double)x, (double)y, (double)z);
    }

    @Override
    public void teleport(String world, int x, int y, int z) {

        teleport(world, (double)x, (double)y, (double)z);
    }

    @Override
    public void teleport(World world, double x, double y, double z) {

        getBukkitPlayer().teleport(new Location(world, x, y, z));
    }

    @Override
    public void teleport(String world, double x, double y, double z) {

        Validate.notNull(world, "The teleport world string object is null.");

        World target = Bukkit.getServer().getWorld(world);

        teleport(target, x, y, z);
    }

    @Override
    public Block getTargetBlock(int distance) {

        return getTargetBlock(null, distance);
    }

    @Override
    public void playSound(String sound, float volume, float pitch) {

        Validate.notNull(sound, "The sound string object is null.");

        getBukkitPlayer().playSound(getLocation(), sound, volume, pitch);
    }

    @Override
    public void playSound(Sound sound, float volume, float pitch) {

        Validate.notNull(sound, "The sound object is null.");

        getBukkitPlayer().playSound(getLocation(), sound, volume, pitch);
    }

    @Override
    public void playNote(Instrument instrument, Note note) {

        Validate.notNull(instrument, "The instrument object is null.");
        Validate.notNull(note, "The note object is null.");

        getBukkitPlayer().playNote(getLocation(), instrument, note);
    }

    @Override
    public double distance(Location target) {

        Validate.notNull(target, "The distance target object is null.");

        return getLocation().distance(target);
    }

    @Override
    public ItemStack getItemOnCursor() {

        return getBukkitPlayer().getItemOnCursor();
    }

    @Override
    public void openInventory(Inventory inventory) {

        getBukkitPlayer().openInventory(inventory);
    }

    @Override
    public Inventory getTopInventory() {

        return getBukkitPlayer().getOpenInventory() != null ? getBukkitPlayer().getOpenInventory().getTopInventory() : null;
    }

    @Override
    public void addPotionEffect(PotionEffectType type, int level, int time) {

        Validate.notNull(type, "The potion effect type object is null.");

        getBukkitPlayer().addPotionEffect(new PotionEffect(type, time, level - 1));
    }

    @Override
    public void addPotionEffect(PotionEffectType type, int level, int time, boolean ambient) {

        Validate.notNull(type, "The potion effect type object is null.");

        getBukkitPlayer().addPotionEffect(new PotionEffect(type, time, level - 1, ambient));
    }

    @Override
    public void addPotionEffect(PotionEffectType type, int level, int time, boolean ambient, boolean particles) {

        Validate.notNull(type, "The potion effect type object is null.");

        getBukkitPlayer().addPotionEffect(new PotionEffect(type, time, level - 1, ambient, particles));
    }

    @Override
    public boolean hasPotionEffect(PotionEffectType type) {

        Validate.notNull(type, "The potion effect type object is null.");

        return getBukkitPlayer().hasPotionEffect(type);
    }

    @Override
    public void removePotionEffect(PotionEffectType type) {

        Validate.notNull(type, "The potion effect type object is null.");

        getBukkitPlayer().removePotionEffect(type);
    }

    @Override
    @SuppressWarnings("deprecation")
    public Map<Integer, ItemStack> addItemStack(ItemStack... itemStacks) {

        return getInventory().addItem(itemStacks);
    }

    @Override
    @SuppressWarnings("deprecation")
    public Map<Integer, ItemStack> removeItemStack(ItemStack... itemStacks) {

        return getInventory().removeItem(itemStacks);
    }

    @Override
    public void onKick() {

        onKick("无");
    }

    @Override
    public void onKick(String message) {

        Validate.notNull(message, "The message string object is null.");

        getBukkitPlayer().kickPlayer(message);
    }

    @Override
    public void onBanName() {

        onBanName(null);
    }

    @Override
    public void onBanName(String cause) {

        onBanName(cause, null);
    }

    @Override
    public void onBanName(String cause, Date time) {

        Bukkit.getServer().getBanList(BanList.Type.NAME).addBan(getName(), cause, time, null);
    }

    @Override
    public void onBanIp() {

        onBanIp(null);
    }

    @Override
    public void onBanIp(String cause) {

        onBanIp(cause, null);
    }

    @Override
    public void onBanIp(String cause, Date time) {

        Bukkit.getServer().getBanList(BanList.Type.IP).addBan(getName(), cause, time, null);
    }

    @Override
    public void onUnBan() {

        for(BanList.Type type : BanList.Type.values()) {

            if(Bukkit.getServer().getBanList(type).isBanned(getName())) {

                Bukkit.getServer().getBanList(type).pardon(getName());
            }
        }
    }

    @Override
    public boolean isShift() {

        return getBukkitPlayer().isSneaking();
    }

    @Override
    public boolean isSprinting() {

        return getBukkitPlayer().isSprinting();
    }

    @Override
    public boolean isOp() {

        return getBukkitPlayer().isOp();
    }

    @Override
    public void setVelocity(Vector vector) {

        Validate.notNull(vector, "The vector object is null.");

        getBukkitPlayer().setVelocity(vector);
    }

    @Override
    public void setNoDamageTicks(int ticks) {

        getBukkitPlayer().setNoDamageTicks(ticks);
    }

    @Override
    public void onEject() {

        getBukkitPlayer().eject();
    }

    @Override
    public void createExplosion(float power) {

        createExplosion(power, true, true);
    }

    @Override
    public void createExplosion(float power, boolean setFire) {

        createExplosion(power, setFire, true);
    }

    @Override
    public void createExplosion(float power, boolean setFire, boolean breakBlock) {

        getWorld().createExplosion(getX(), getY(), getZ(), power, setFire, breakBlock);
    }

    @Override
    public boolean performCommand(String command) {

        Validate.notNull(command, "The preform command string object is null.");

        return getBukkitPlayer().performCommand(command);
    }

    @Override
    public void onHide(Player target) {

        Validate.notNull(target, "The hide player target object is null.");

        getBukkitPlayer().hidePlayer(target);
    }

    @Override
    public void onHide(MoonLakePlayer target) {

        Validate.notNull(target, "The hide moonlake player target object is null.");

        onHide(target.getBukkitPlayer());
    }

    @Override
    public void onHide(String target) {

        onHide(PlayerManager.fromName(target));
    }

    @Override
    public void onShow(Player target) {

        Validate.notNull(target, "The show moonlake player target object is null.");

        target.showPlayer(target);
    }

    @Override
    public void onShow(MoonLakePlayer target) {

        Validate.notNull(target, "The show moonlake player target object is null.");

        onShow(target.getBukkitPlayer());
    }

    @Override
    public void onShow(String target) {

        onShow(PlayerManager.fromName(target));
    }

    @Override
    public boolean canSee(Player target) {

        Validate.notNull(target, "The can see player target object is null.");

        return getBukkitPlayer().canSee(target);
    }

    @Override
    public boolean canSee(MoonLakePlayer target) {

        Validate.notNull(target, "The can see moonlake player target object is null.");

        return canSee(target.getBukkitPlayer());
    }

    @Override
    public boolean canSee(String target) {

        return canSee(PlayerManager.fromName(target));
    }

    @Override
    public void setTime(long time) {

        setTime(time, true);
    }

    @Override
    public void setTime(long time, boolean relative) {

        getBukkitPlayer().setPlayerTime(time, relative);
    }

    @Override
    public void setWeather(WeatherType weather) {

        Validate.notNull(weather, "The weather type object is null.");

        getBukkitPlayer().setPlayerWeather(weather);
    }

    @Override
    public WeatherType getWeather() {

        return getBukkitPlayer().getPlayerWeather();
    }

    @Override
    public long getTime() {

        return getBukkitPlayer().getPlayerTime();
    }

    @Override
    public void resetWeather() {

        getBukkitPlayer().resetPlayerWeather();
    }

    @Override
    public void resetTime() {

        getBukkitPlayer().resetPlayerTime();
    }

    @Override
    public GameMode getGameMode() {

        return getBukkitPlayer().getGameMode();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void setGameMode(int mode) {

        GameMode gameMode = GameMode.getByValue(mode);

        if(gameMode != null) {

            setGameMode(gameMode);
        }
    }

    @Override
    public void setGameMode(GameMode mode) {

        Validate.notNull(mode, "The game mode type object is null.");

        getBukkitPlayer().setGameMode(mode);
    }

    @Override
    public void resetMaxHealth() {

        getBukkitPlayer().resetMaxHealth();
    }

    @Override
    public void clearPotionEffect() {

        if(getBukkitPlayer() != null && !getBukkitPlayer().getActivePotionEffects().isEmpty()) {

            for(PotionEffect potionEffect : getBukkitPlayer().getActivePotionEffects()) {

                if(potionEffect != null && getBukkitPlayer().hasPotionEffect(potionEffect.getType())) {

                    getBukkitPlayer().removePotionEffect(potionEffect.getType());
                }
            }
        }
    }

    @Override
    public Location getEyeLocation() {

        return getBukkitPlayer().getEyeLocation();
    }

    @Override
    public float getFallDistance() {

        return getBukkitPlayer().getFallDistance();
    }

    @Override
    public void setFallDistance(float fallDistance) {

        getBukkitPlayer().setFallDistance(fallDistance);
    }

    @Override
    public Vector getDirection() {

        return getLocation().getDirection();
    }

    @Override
    public double getLastDamage() {

        return getBukkitPlayer().getLastDamage();
    }

    public EntityDamageEvent getLastDamageCause() {

        return getBukkitPlayer().getLastDamageCause();
    }

    @Override
    public Block getTargetBlock(Set<Material> transparent, int maxDistance) {

        Validate.notNull(transparent, "The transparent collection object is null.");

        return getBukkitPlayer().getTargetBlock(transparent, maxDistance);
    }

    @Override
    public void setCanPickupItems(boolean pickup) {

        getBukkitPlayer().setCanPickupItems(pickup);
    }

    @Override
    public boolean isCanPickupItems() {

        return getBukkitPlayer().getCanPickupItems();
    }

    @Override
    public void setResourcePack(String url) {

        Validate.notNull(url, "The resource pack url string object is null.");

        getBukkitPlayer().setResourcePack(url);
    }

    @Override
    public <T extends Projectile> T launcherProjectile(Class<? extends T> projectile) {

        Validate.notNull(projectile, "The projectile class object is null.");

        return getBukkitPlayer().launchProjectile(projectile);
    }

    @Override
    public <T extends Projectile> T launcherProjectile(Class<? extends T> projectile, Vector vector) {

        Validate.notNull(projectile, "The projectile class object is null.");
        Validate.notNull(vector, "The projectile vector object is null.");

        return getBukkitPlayer().launchProjectile(projectile, vector);
    }

    @Override
    public void sendMessage(String message) {

        send(message);
    }

    @Override
    public void sendMessage(String[] messages) {

        send(messages);
    }

    @Override
    public Server getServer() {

        return getBukkitPlayer().getServer();
    }

    @Override
    public boolean isPermissionSet(String name) {

        Validate.notNull(name, "The permission name object is null.");

        return getBukkitPlayer().isPermissionSet(name);
    }

    @Override
    public boolean isPermissionSet(Permission perm) {

        Validate.notNull(perm, "The permission object is null.");

        return getBukkitPlayer().isPermissionSet(perm);
    }

    @Override
    public boolean hasPermission(String permission) {

        Validate.notNull(permission, "The permission string object is null.");

        return getBukkitPlayer().hasPermission(permission);
    }

    @Override
    public boolean hasPermission(Permission perm) {

        Validate.notNull(perm, "The permission object is null.");

        return getBukkitPlayer().hasPermission(perm);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {

        return getBukkitPlayer().addAttachment(plugin, name, value);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {

        return getBukkitPlayer().addAttachment(plugin);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {

        return getBukkitPlayer().addAttachment(plugin, name, value, ticks);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int ticks) {

        return getBukkitPlayer().addAttachment(plugin, ticks);
    }

    @Override
    public void removeAttachment(PermissionAttachment attachment) {

        getBukkitPlayer().removeAttachment(attachment);
    }

    @Override
    public void recalculatePermissions() {

        getBukkitPlayer().recalculatePermissions();
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {

        return getBukkitPlayer().getEffectivePermissions();
    }

    @Override
    public void setOp(boolean value) {

        getBukkitPlayer().setOp(value);
    }

    @Override
    public InetSocketAddress getAddress() {

        return getBukkitPlayer().getAddress();
    }

    @Override
    public String getIp() {

        InetAddress address = getAddress().getAddress();

        return address != null ? address.getHostAddress() : "127.0.0.1";
    }

    @Override
    public int getPort() {

        return getAddress().getPort();
    }

    @Override
    public int compareTo(MoonLakePlayer target) {

        return getName().compareTo(target == null ? null : target.getName());
    }

    @Override
    public boolean equals(Object object) {

        if(object == this) {

            return true;
        }
        if(object != null) {

            if(object instanceof MoonLakePlayer) {

                MoonLakePlayer target = (MoonLakePlayer)object;

                return getName().equals(target.getName());
            }
        }
        return false;
    }

    @Override
    public int getPing() {

        return PlayerLibraryFactorys.nmsPlayer().getPing(getName());
    }

    @Override
    public void sendPacket(Packet<?> packet) {

        Validate.notNull(packet, "The packet object is null.");

        packet.send(getBukkitPlayer());
    }

    @Override
    public void sendTitlePacket(String title) {

        PlayerLibraryFactorys.nmsPlayer().sendTitlePacket(getName(), StringUtil.toColor(title));
    }

    @Override
    public void sendTitlePacket(String title, String subTitle) {

        PlayerLibraryFactorys.nmsPlayer().sendTitlePacket(getName(), StringUtil.toColor(title), StringUtil.toColor(subTitle));
    }

    @Override
    public void sendTitlePacket(String title, int fadeIn, int stay, int fadeOut) {

        PlayerLibraryFactorys.nmsPlayer().sendTitlePacket(getName(), StringUtil.toColor(title), fadeIn, stay, fadeOut);
    }

    @Override
    public void sendTitlePacket(String title, String subTitle, int fadeIn, int stay, int fadeOut) {

        PlayerLibraryFactorys.nmsPlayer().sendTitlePacket(getName(), StringUtil.toColor(title), StringUtil.toColor(subTitle), fadeIn, stay, fadeOut);
    }

    @Override
    public void sendMainChatPacket(String message) {

        PlayerLibraryFactorys.nmsPlayer().sendMainChatPacket(getName(), StringUtil.toColor(message));
    }

    @Override
    public void sendTabListPacket(String header) {

        PlayerLibraryFactorys.nmsPlayer().sendTabListPacket(getName(), header);
    }

    @Override
    public void sendTabListPacket(String header, String footer) {

        PlayerLibraryFactorys.nmsPlayer().sendTabListPacket(getName(), header, footer);
    }

    @Override
    public void setItemCooldown(Material type, int tick) {

        PlayerLibraryFactorys.player().setItemCooldown(getName(), type, tick);
    }

    @Override
    public boolean hasItemCooldown(Material type) {

        return PlayerLibraryFactorys.player().hasItemCooldown(getName(), type);
    }

    @Override
    public double getMoney() {

        try {

            return PlayerLibraryFactorys.economyPlayer().getMoney(getName());
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'MoonLakeEconomy' plugin method 'getMoney' exception.", e);
        }
    }

    @Override
    public void setMoney(double money) {

        try {

            PlayerLibraryFactorys.economyPlayer().setMoney(getName(), money);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'MoonLakeEconomy' plugin method 'setMoney' exception.", e);
        }
    }

    @Override
    public void giveMoney(double money) {

        try {

            PlayerLibraryFactorys.economyPlayer().giveMoney(getName(), money);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'MoonLakeEconomy' plugin method 'giveMoney' exception.", e);
        }
    }

    @Override
    public void takeMoney(double money) {

        try {

            PlayerLibraryFactorys.economyPlayer().takeMoney(getName(), money);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'MoonLakeEconomy' plugin method 'takeMoney' exception.", e);
        }
    }

    @Override
    public int getPoint() {

        try {

            return PlayerLibraryFactorys.economyPlayer().getPoint(getName());
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'MoonLakeEconomy' plugin method 'getPoint' exception.", e);
        }
    }

    @Override
    public void setPoint(int point) {

        try {

            PlayerLibraryFactorys.economyPlayer().setPoint(getName(), point);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'MoonLakeEconomy' plugin method 'setPoint' exception.", e);
        }
    }

    @Override
    public void givePoint(int point) {

        try {

            PlayerLibraryFactorys.economyPlayer().givePoint(getName(), point);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'MoonLakeEconomy' plugin method 'givePoint' exception.", e);
        }
    }

    @Override
    public void takePoint(int point) {

        try {

            PlayerLibraryFactorys.economyPlayer().takePoint(getName(), point);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'MoonLakeEconomy' plugin method 'takePoint' exception.", e);
        }
    }

    @Override
    public void addPermissionsExGroup(String group) throws CannotDependException {

        Validate.notNull(group, "The group object is null.");

        try {

            PlayerLibraryFactorys.permissionsExPlayer().addPermissionsExGroup(this, group);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'addPermissionsExGroup' exception.", e);
        }
    }

    @Override
    public void addPermissionsExGroup(String group, String world) throws CannotDependException {

        Validate.notNull(group, "The group object is null.");

        try {

            PlayerLibraryFactorys.permissionsExPlayer().addPermissionsExGroup(this, group, world);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'addPermissionsExGroup' exception.", e);
        }
    }

    @Override
    public void removePermissionsExGroup(String group) throws CannotDependException {

        Validate.notNull(group, "The group object is null.");

        try {

            PlayerLibraryFactorys.permissionsExPlayer().removePermissionsExGroup(this, group);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'removePermissionsExGroup' exception.", e);
        }
    }

    @Override
    public void removePermissionsExGroup(String group, String world) throws CannotDependException {

        Validate.notNull(group, "The group object is null.");

        try {

            PlayerLibraryFactorys.permissionsExPlayer().removePermissionsExGroup(this, group, world);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'removePermissionsExGroup' exception.", e);
        }
    }

    @Override
    public boolean inPermissionsExGroup(String group) throws CannotDependException {

        Validate.notNull(group, "The group object is null.");

        try {

            return PlayerLibraryFactorys.permissionsExPlayer().inPermissionsExGroup(this, group);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'inPermissionsExGroup' exception.", e);
        }
    }

    @Override
    public boolean inPermissionsExGroup(String group, String world) throws CannotDependException {

        Validate.notNull(group, "The group object is null.");

        try {

            return PlayerLibraryFactorys.permissionsExPlayer().inPermissionsExGroup(this, group, world);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'inPermissionsExGroup' exception.", e);
        }
    }

    @Override
    public boolean inPermissionsExGroup(String group, boolean checkInheritance) throws CannotDependException {

        Validate.notNull(group, "The group object is null.");

        try {

            return PlayerLibraryFactorys.permissionsExPlayer().inPermissionsExGroup(this, group, checkInheritance);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'inPermissionsExGroup' exception.", e);
        }
    }

    @Override
    public boolean inPermissionsExGroup(String group, String world, boolean checkInheritance) throws CannotDependException {

        Validate.notNull(group, "The group object is null.");

        try {

            return PlayerLibraryFactorys.permissionsExPlayer().inPermissionsExGroup(this, group, world, checkInheritance);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'inPermissionsExGroup' exception.", e);
        }
    }

    @Override
    public List<String> getPermissionsExPer() throws CannotDependException {

        try {

            return PlayerLibraryFactorys.permissionsExPlayer().getPermissionsExPer(this);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'getPermissionsExPer' exception.", e);
        }
    }

    @Override
    public List<String> getPermissionsExPer(String world) throws CannotDependException {

        try {

            return PlayerLibraryFactorys.permissionsExPlayer().getPermissionsExPer(this, world);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'getPermissionsExPer' exception.", e);
        }
    }

    @Override
    public String getPermissionsExPrefix() throws CannotDependException {

        try {

            return PlayerLibraryFactorys.permissionsExPlayer().getPermissionsExPrefix(this);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'getPermissionsExPrefix' exception.", e);
        }
    }

    @Override
    public String getPermissionsExPrefix(String world) throws CannotDependException {

        try {

            return PlayerLibraryFactorys.permissionsExPlayer().getPermissionsExPrefix(this, world);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'getPermissionsExPrefix' exception.", e);
        }
    }

    @Override
    public String getPermissionsExOwnPrefix() throws CannotDependException {

        try {

            return PlayerLibraryFactorys.permissionsExPlayer().getPermissionsExOwnPerfix(this);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'getPermissionsExOwnPrefix' exception.", e);
        }
    }

    @Override
    public String getPermissionsExOwnPrefix(String world) throws CannotDependException {

        try {

            return PlayerLibraryFactorys.permissionsExPlayer().getPermissionsExOwnPerfix(this, world);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'getPermissionsExOwnPrefix' exception.", e);
        }
    }

    @Override
    public String getPermissionsExSuffix() throws CannotDependException {

        try {

            return PlayerLibraryFactorys.permissionsExPlayer().getPermissionsExSuffix(this);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'getPermissionsExSuffix' exception.", e);
        }
    }

    @Override
    public String getPermissionsExSuffix(String world) throws CannotDependException {

        try {

            return PlayerLibraryFactorys.permissionsExPlayer().getPermissionsExSuffix(this, world);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'getPermissionsExSuffix' exception.", e);
        }
    }

    @Override
    public String getPermissionsExOwnSuffix() throws CannotDependException {

        try {

            return PlayerLibraryFactorys.permissionsExPlayer().getPermissionsExOwnSuffix(this);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'getPermissionsExOwnSuffix' exception.", e);
        }
    }

    @Override
    public String getPermissionsExOwnSuffix(String world) throws CannotDependException {

        try {

            return PlayerLibraryFactorys.permissionsExPlayer().getPermissionsExOwnSuffix(this, world);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'getPermissionsExOwnSuffix' exception.", e);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public String[] getPermissionsExGroupNames() throws CannotDependException {

        try {

            return PlayerLibraryFactorys.permissionsExPlayer().getPermissionsExGroupNames(this);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'getPermissionsExGroupNames' exception.", e);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public String[] getPermissionsExGroupNames(String world) throws CannotDependException {

        try {

            return PlayerLibraryFactorys.permissionsExPlayer().getPermissionsExGroupNames(this, world);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'getPermissionsExGroupNames' exception.", e);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void setPermissionsExGroup(String group) throws CannotDependException {

        Validate.notNull(group, "The group object is null.");

        try {

            PlayerLibraryFactorys.permissionsExPlayer().setPermissionsExGroup(this, group);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'setPermissionsExGroup' exception.", e);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void setPermissionsExGroup(String group, String world) throws CannotDependException {

        Validate.notNull(group, "The group object is null.");

        try {

            PlayerLibraryFactorys.permissionsExPlayer().setPermissionsExGroup(this, group, world);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'setPermissionsExGroup' exception.", e);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void setPermissionsExGroups(String[] groups) throws CannotDependException {

        Validate.notNull(groups, "The groups object is null.");

        try {

            PlayerLibraryFactorys.permissionsExPlayer().setPermissionsExGroups(this, groups);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'setPermissionsExGroups' exception.", e);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void setPermissionsExGroups(String[] groups, String world) throws CannotDependException {

        Validate.notNull(groups, "The groups object is null.");

        try {

            PlayerLibraryFactorys.permissionsExPlayer().setPermissionsExGroups(this, groups, world);
        }
        catch (Exception e) {

            throw new CannotDependException("The call 'PermissionsEx' plugin method 'setPermissionsExGroups' exception.", e);
        }
    }
}
