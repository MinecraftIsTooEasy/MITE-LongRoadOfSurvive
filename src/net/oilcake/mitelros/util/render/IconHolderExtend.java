package net.oilcake.mitelros.util.render;
import net.minecraft.IIcon;

public class IconHolderExtend {
    private IIcon texture_back;
    private IIcon texture_bottom;
    private IIcon texture_front;
    private IIcon texture_side;
    private IIcon texture_top;

    public IconHolderExtend() {
    }

    public IIcon getTexture_back() {
            return this.texture_back;
        }
    public void setTexture_back(IIcon texture_back) {
        this.texture_back = texture_back;
    }
    public IIcon getTexture_bottom() {
        return this.texture_bottom;
    }

    public void setTexture_bottom(IIcon texture_bottom) {
            this.texture_bottom = texture_bottom;
        }

    public IIcon getTexture_front() {
            return this.texture_front;
        }
    public void setTexture_front(IIcon texture_front) {
            this.texture_front = texture_front;
        }

    public IIcon getTexture_side() {
            return this.texture_side;
        }

    public void setTexture_side(IIcon texture_side) {
            this.texture_side = texture_side;
        }

    public IIcon getTexture_top() {
            return this.texture_top;
        }

    public void setTexture_top(IIcon texture_top) {
            this.texture_top = texture_top;
        }

}
