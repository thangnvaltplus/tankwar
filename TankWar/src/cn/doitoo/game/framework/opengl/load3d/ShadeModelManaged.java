package cn.doitoo.game.framework.opengl.load3d;



public class ShadeModelManaged extends AbstractDirtyManaged 
{
	private ShadeModel _shadeModel;

	public ShadeModelManaged(IDirtyParent $parent)
	{
		super($parent);
		set(ShadeModel.SMOOTH);
	}
	
	public ShadeModel get()
	{
		return _shadeModel;
	}
	public void set(ShadeModel $shadeModel)
	{
		_shadeModel = $shadeModel;
		_dirty = true; // no need for callback
	}
}
