package ind.plague.pvz.scene;

/**
 * @author PlagueWZK
 * description: SceneType
 * date: 2025/5/12 17:03
 */

public enum SceneType {
    MENU_SCENE;
//    SETTING_SCENE,
//    GAME_SCENE,
//    GAME_OVER_SCENE

     final String className;

    SceneType() {
        String[] split = this.name().split("_");
        StringBuilder  sb = new StringBuilder();
        for (String s : split) {
            sb.append(s.charAt(0)).append(s.substring(1).toLowerCase());
        }
        className = sb.toString();
    }

    public String getClassName() {
        return className;
    }
}
