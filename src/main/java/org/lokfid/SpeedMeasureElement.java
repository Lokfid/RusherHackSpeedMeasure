package org.lokfid;

import org.rusherhack.client.api.feature.hud.ResizeableHudElement;
import org.rusherhack.client.api.render.RenderContext;
import org.rusherhack.client.api.render.graphic.IGraphic;
import org.rusherhack.client.api.render.graphic.TextureGraphic;

public class SpeedMeasureElement extends ResizeableHudElement {
	private TextureGraphic graphic = null;

	public SpeedMeasureElement() {
		super("ExampleHudElement");
		try {
			this.graphic = new TextureGraphic("exampleplugin/graphics/rh_head.png", 235, 234);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public void renderContent(RenderContext context, int mouseX, int mouseY) {
		if (this.graphic != null)
			getRenderer().drawGraphicRectangle((IGraphic)this.graphic, 0.0D, 0.0D, getWidth(), getHeight());
	}

	public double getWidth() {
		return 200.0D;
	}

	public double getHeight() {
		return 200.0D;
	}
}
