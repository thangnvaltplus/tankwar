package cn.doitoo.game.framework.opengl.load3d;

import java.util.ArrayList;


import android.util.Log;


public class Scene implements IObject3dContainer, IDirtyParent
{
	private ArrayList<Object3d> _children = new ArrayList<Object3d>();

	private ManagedLightList _lights;
	private CameraVo _camera;
	
	private Color4Managed _backgroundColor;
	private boolean _lightingEnabled;

	private ISceneController _sceneController;
	

	public Scene(ISceneController $sceneController) 
	{
		_sceneController = $sceneController;
		_lights = new ManagedLightList();
	}

	/**
	 * Allows you to use any Class implementing ISceneController
	 * to drive the Scene...
	 * @return
	 */
	public ISceneController sceneController()
	{
		return _sceneController;
	}
	public void sceneController(ISceneController $sceneController)
	{
		_sceneController = $sceneController;
	}
	
	//
	
	/**
	 * Resets Scene to default settings:
	 * Removes and clears any attached Object3ds.
	 * Resets light list, adds single default light.
	 */
	public void reset()
	{
		clearChildren(this);

		_children = new ArrayList<Object3d>();

		_camera = new CameraVo();
		
		_backgroundColor = new Color4Managed(0,0,0,255, this);
		
		lightingEnabled(true);
	}
	
	/**
	 * Adds Object3d to Scene. Object3d's must be added to Scene in order to be rendered
	 * Returns always true. 
	 */
	
	public void addChild(Object3d $o)
	{
		if (_children.contains($o)) return;
		
		_children.add($o);
		
		$o.parent(this);
		$o.scene(this);
	}
	

	public void addChildAt(Object3d $o, int $index)
	{
		if (_children.contains($o)) return;

		_children.add($index, $o);
	}
	
	/**
	 * Removes Object3d from Scene.
	 * Returns false if unsuccessful
	 */

	public boolean removeChild(Object3d $o)
	{
		$o.parent(null);
		$o.scene(null);
		return _children.remove($o);
	}
	

	public Object3d removeChildAt(int $index)
	{
		Object3d o = _children.remove($index);
		
		if (o != null) {
			o.parent(null);
			o.scene(null);
		}
		return o;
	}
	

	public Object3d getChildAt(int $index)
	{
		return _children.get($index);
	}
	
	/**
	 */
	
	public Object3d getChildByName(String $name)
	{
		for (int i = 0; i < _children.size(); i++)
		{
			if (_children.get(0).name() == $name) return _children.get(0); 
		}
		return null;
	}
	
	
	public int getChildIndexOf(Object3d $o)
	{
		return _children.indexOf($o);
	}
	

	public int numChildren()
	{
		return _children.size();
	}

	/**
	 * Scene's camera
	 */
	public CameraVo camera()
	{
		return _camera;
	}
	public void camera(CameraVo $camera)
	{
		_camera = $camera;
	}
	
	/**
	 * Scene instance's background color
	 */
	public Color4Managed backgroundColor()
	{
		return _backgroundColor;
	}

	/**
	 * Lights used by the Scene 
	 */
	public ManagedLightList lights()
	{
		return _lights;
	}

	/**
	 * Determines if lighting is enabled for Scene. 
	 */
	public boolean lightingEnabled()
	{
		return _lightingEnabled;
	}
	
	public void lightingEnabled(boolean $b)
	{
		_lightingEnabled = $b;
	}
	
	//

	/**
	 * Used by Renderer 
	 */
	void init() /*package-private*/ 
	{
		Log.i(Min3d.TAG, "Scene.init()");
		
		this.reset();
		_sceneController.initScene();
	}
	
	/**
	 * Used by Renderer 
	 */
	ArrayList<Object3d> children() /*package-private*/ 
	{
		return _children;
	}
	
	private void clearChildren(IObject3dContainer $c)
	{
		for (int i = $c.numChildren() - 1; i >= 0; i--)
		{
			Object3d o = $c.getChildAt(i);
			o.clear();
			
			if (o instanceof Object3dContainer)
			{
				clearChildren((Object3dContainer)o);
			}
		}
	}	

	public void onDirty()
	{
		//
	}
}
