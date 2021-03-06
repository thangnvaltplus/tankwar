package cn.doitoo.game.framework.context;

import android.content.Context;
import cn.doitoo.game.framework.map.DoitooMap;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * data pool for common using
 *
 * @author Oliver O
 */
public class G {

    private static Context context;
    private static DoitooMap doitooMap;
    private static Map<String, Object> map = new LinkedHashMap<String, Object>();
    //用于屏幕显示调试信息
    private static Map<String, String> debugInfoMap = new LinkedHashMap<String, String>();

    public static Object get(String key) {
        return map.get(key);
    }

    public static void set(String key, Object value) {
        if (!map.containsKey(key))
            map.put(key, value);
    }

    public static int getInt(String key) {
        Object o = get(key);
        return (o == null) ? 0 : (Integer) get(key);
    }

    public static String getString(String key) {
        return (String) get(key);
    }

    public static float getFloat(String key) {
        return (Float) get(key);
    }

    public static double getDouble(String key) {
        return (Double) get(key);
    }

    public static DoitooMap getDoitooMap() {
        return doitooMap;
    }

    public static void setDoitooMap(DoitooMap doitooMap) {
        G.doitooMap = doitooMap;
    }

    public static void set(String key, Object value, boolean modifiable) {
        if (modifiable && map.containsKey(key))
            map.put(key, value);
        else if (!modifiable || !map.containsKey(key))
            set(key, value);
    }

    public String listKeys() {
        Set<String> keyset = map.keySet();
        StringBuilder sb = new StringBuilder();
        for (String str : keyset) {
            sb.append(str + "_");
        }
        return sb.toString();
    }

    public static Map<String, Object> getMap() {
        return map;
    }

    public static void setMap(Map<String, Object> map) {
        G.map = map;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        G.context = context;
    }

    public static void addDebugInfo(String key, String value) {
        G.debugInfoMap.put(key, value);
    }

    public static Map getDebugInfoMap() {
        return G.debugInfoMap;
    }
}
