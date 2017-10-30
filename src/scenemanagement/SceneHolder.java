package scenemanagement;

import scenes.HomeScene;
import scenes.OrderStepExtraScene;
import scenes.OrderStepOverview;
import scenes.OrderStepPersonalData;
import scenes.OrderStepPizzaScene;
import scenes.OrderStepSelectExtra;
import scenes.OrderStepThankYou;
import scenes.PizzaScene;

public enum SceneHolder {
	HOME(new HomeScene()),
	STEPPIZZA(new OrderStepPizzaScene()),
	STEPEXTRAS(new OrderStepExtraScene()),
	STEPEXTRACHANGE(new OrderStepSelectExtra()),
	STEPPERSONALDATA(new OrderStepPersonalData()),
	STEPOVERVIEW(new OrderStepOverview()),
	STEPTHANKYOU(new OrderStepThankYou());
	
	private PizzaScene scene;
	private SceneHolder(PizzaScene scene){
		this.scene = scene;
	}
	
	public PizzaScene getPizzaScene(){
		return this.scene;
	}
	
	public void initialize(SceneManager sceneManager){
		scene.setSceneManager(sceneManager);
		scene.initialize();
	}
}
