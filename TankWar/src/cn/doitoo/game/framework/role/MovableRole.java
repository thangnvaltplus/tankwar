package cn.doitoo.game.framework.role;

import java.util.LinkedList;
import java.util.List;

import android.graphics.Canvas;

/**
 * TODO add other functions
 *
 * @author Oliver O
 */
public abstract class MovableRole {
    private float oldX;

    private float oldY;

    private float x;

    private float y;

    private boolean isMoving;

    /**
     * 角色动画步骤
     */
    private int[] step_array = {0};
    /**
     * 当前角色所处动画的步数
     */
    private int step;

    /**
     * 是否显示
     */
    private boolean isVisabled;

    /**
     * 角色移动方向
     */
    private move_direct direction;

    public enum move_direct {
        LEFT, UP, DOWN, RIGHT
    }

    /**
     * 移动速度
     */
    private int speed;

    public static List<MovableRole> movableRoleList = new LinkedList<MovableRole>();

    public MovableRole(float x, float y) {
        super();
        this.x = x;
        this.y = y;
        this.oldX = x;
        this.oldY = y;
        movableRoleList.add(this);
    }

    public abstract float getWidth();

    public abstract float getHeight();

    public abstract void move();

    /**
     * 在屏幕上绘画角色，由子类实现
     *
     * @param c
     */
    public abstract void paint(Canvas c);

    public void stay() {
        this.x = this.oldX;
        this.y = this.oldY;
    }

    /**
     * 在角色动画当中切换至下一帧
     *
     * 如果角色需要动画显示，则每次画坦克之前都需要调用此方法
     */
    public void nextFrame() {
        if (step >= step_array.length) {
            step = 0;
        }
        step++;
    }

    /**
     * 先保存角色改变前的坐标再改变角色坐标，
     *
     * @param x 角色新的X坐标
     * @param y 角色新的Y坐标
     */
    public void setPosition(float x, float y) {
        this.oldX = getX();
        this.oldY = getY();
        this.x = x;
        this.y = y;

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public boolean isVisabled() {
        return isVisabled;
    }

    public void setVisabled(boolean isVisabled) {
        this.isVisabled = isVisabled;
    }

    public int[] getStep_array() {
        return step_array;
    }

    public void setStep_array(int[] step_array) {
        this.step_array = step_array;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public move_direct getDirection() {
        return direction;
    }

    public void setDirection(move_direct direction) {
        this.direction = direction;
    }
}
