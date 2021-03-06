package cn.doitoo.game.tankwar.role.building.player;

import android.content.Context;
import android.graphics.Bitmap;
import cn.doitoo.game.framework.context.G;
import cn.doitoo.game.framework.util.Util;
import cn.doitoo.game.tankwar.R;
import cn.doitoo.game.tankwar.role.building.Pagoda;

/**
 * Created by IntelliJ IDEA. User: ���� Date: 2010-7-6 Time: 18:23:10
 */
public class PlayerPagoda1 extends Pagoda {

    @Override
    public PagodaType getPagodaType() {
        return Pagoda.PagodaType.Player;
    }

    public PlayerPagoda1(int x, int y) {
        super(x, y);
    }

    @Override
    public Bitmap getBitmap() {
        Context context = G.getContext();
        return Util.getBitMapById(context, R.drawable.pagoda01);
    }

    @Override
    public void subLife(int power) {
        if (power > defense) {
            power -= defense;
            life -= power;
        }
        if (life <= 0) {
            this.setVisabled(false);
        } else {
            blood.setCurrentLife(life);
            this.setLife(life);
        }
    }

}
