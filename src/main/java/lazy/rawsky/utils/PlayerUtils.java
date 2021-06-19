package lazy.rawsky.utils;

import net.minecraft.entity.player.PlayerEntity;

public class PlayerUtils {

    public static final String TAG_FIRST_WORLD_JOIN = "isFirstWorldJoin";

    public static boolean checkAndSetFirstWorldJoin(PlayerEntity entity){
        boolean isFirstJoin = entity.getPersistentData().contains(TAG_FIRST_WORLD_JOIN);
        if(!isFirstJoin){
            entity.getPersistentData().putBoolean(TAG_FIRST_WORLD_JOIN, true);
            return true;
        }
        return false;
    }
}
