package net.abusingjava.swing.finish;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

import net.abusingjava.Author;
import net.abusingjava.Since;
import net.abusingjava.Version;
import net.abusingjava.swing.finish.types.Unit;

@Author("Julian Fleischer")
@Version("2011-08-21")
@Since(value = "2011-08-21", version = "1.0")
public class MagicLayoutManager implements LayoutManager {

	private final MagicPanel $parent;
	private final MagicPanel $panel;
	private final net.abusingjava.swing.finish.magic.Container $container; 
	
	MagicLayoutManager(final MagicPanel $parent, final MagicPanel $panel, final net.abusingjava.swing.finish.magic.Container $container) {
		this.$parent = $parent;
		this.$panel = $panel;
		this.$container = $container;
	}

	@Override
	public void addLayoutComponent(final String $s, final Component $c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeLayoutComponent(final Component $c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void layoutContainer(final Container $container) {
		if ($panel == $container) {
			net.abusingjava.swing.finish.magic.Container $def = $panel.getDefinition();
			
			double $width = $container.getWidth();
			double $height = $container.getHeight();
			
			int $starsWidth = 0;
			int $starsHeight = 0;

			double $posX = 0;
			double $posY = 0;

			if ($def.getPaddingLeft().getUnit() == Unit.PIXEL) {
				$posX = $def.getPaddingLeft().getValue();
				$width -= $posX;
			}
			if ($def.getPaddingTop().getUnit() == Unit.PIXEL) {
				$posY = $def.getPaddingTop().getValue();
				$height -= $posY;
			}
			if ($def.getPaddingRight().getUnit() == Unit.PIXEL) {
				$width -= $def.getPaddingRight().getValue();
			}
			if ($def.getPaddingBottom().getUnit() == Unit.PIXEL) {
				$height -= $def.getPaddingBottom().getValue();
			}
			
			double $remainingWidth = $width;
			double $remainingHeight = $height;
			
			int $newWidth = 0;
			int $newHeight = 0;
			
			int $newX = 0;
			int $newY = 0;
			
			for (net.abusingjava.swing.finish.magic.Component $c : this.$container) {
				switch ($panel.getOrientation()) {
				case VERTICAL:
					switch ($c.getHeight().getUnit()) {
					case PIXEL:
						$remainingHeight -= $c.getHeight().getValue();
						break;
					case STAR:
						$starsHeight += $c.getHeight().getValue();
						break;
					case PERCENT:
						$remainingHeight -= (int) (($c.getHeight().getValue() / 100.0) * $height);
						break;
					case AUTO:
						$starsHeight++;
						break;
					case INTRINSIC:
						// TODO
						break;
					}
					switch ($c.getWidth().getUnit()) {
					case STAR:
						$starsWidth = Math.max($starsWidth, $c.getWidth().getValue());
						break;
					case AUTO:
					case PIXEL:
					case PERCENT:
					case INTRINSIC:
						// nothing
						break;
					}
					break;
					
				case HORIZONTAL:
					switch ($c.getWidth().getUnit()) {
					case PIXEL:
						$remainingWidth -= $c.getWidth().getValue();
						break;
					case STAR:
						$starsWidth += $c.getWidth().getValue();
						break;
					case PERCENT:
						$remainingWidth -= (int) (($c.getWidth().getValue() / 100.0) * $width);
						break;
					case AUTO:
						$starsWidth++;
						break;
					case INTRINSIC:
						// TODO
						break;
					}
					switch ($c.getHeight().getUnit()) {
					case STAR:
						$starsHeight = Math.max($starsHeight, $c.getHeight().getValue());
						break;
					case AUTO:
					case PIXEL:
					case PERCENT:
					case INTRINSIC:
						// nothing
						break;
					}
					break;
					
				case FIXED:
					
					break;
				}
			}
			
			for (net.abusingjava.swing.finish.magic.Component $c : this.$container) {
				switch ($panel.getOrientation()) {
				case VERTICAL:
					$newWidth = (int) $width;
					switch ($c.getHeight().getUnit()) {
					case PIXEL:
						$newHeight = $c.getHeight().getValue();
						break;
					case STAR:
						$newHeight = (int) (($c.getHeight().getValue() / (double) $starsHeight) * $remainingHeight);
						break;
					case PERCENT:
						$newHeight = (int) (($c.getHeight().getValue() / 100.0) * $height);
						break;
					case AUTO:
						$newHeight = (int) ((1 / (double) $starsHeight) * $remainingHeight);
						break;
					case INTRINSIC:
						// TODO
						break;
					}
					switch ($c.getWidth().getUnit()) {
					case PIXEL:
						$newWidth = $c.getWidth().getValue();
						break;
					case STAR:
						$newWidth = (int) (($c.getWidth().getValue() / (double) $starsWidth) * $width);
						break;
					case PERCENT:
						$newWidth = (int) (($c.getWidth().getValue() / 100.0) * $width);
						break;
					case AUTO:
						$newWidth = (int) $width;
						break;
					case INTRINSIC:
						// TODO
						break;
					}
					$newX = (int) $posX;
					$newY = (int) $posY;
					$posY += $newHeight;
					break;
					
				case HORIZONTAL:
					$newHeight = (int) $height;
					switch ($c.getWidth().getUnit()) {
					case PIXEL:
						$newWidth = $c.getWidth().getValue();
						break;
					case STAR:
						$newWidth = (int) (($c.getWidth().getValue() / (double) $starsWidth) * $remainingWidth);
						break;
					case PERCENT:
						$newWidth = (int) (($c.getWidth().getValue() / 100.0) * $width);
						break;
					case AUTO:
						$newWidth = (int) ((1 / (double) $starsWidth) * $remainingWidth);
						break;
					case INTRINSIC:
						// TODO
						break;
					}
					switch ($c.getHeight().getUnit()) {
					case PIXEL:
						$newHeight = $c.getHeight().getValue();
						break;
					case STAR:
						$newHeight = (int) (($c.getHeight().getValue() / (double) $starsHeight) * $height);
						break;
					case PERCENT:
						$newHeight = (int) (($c.getHeight().getValue() / 100.0) * $height);
						break;
					case AUTO:
						$newHeight = (int) $height;
						break;
					case INTRINSIC:
						// TODO
						break;
					}
					$newX = (int) $posX;
					$newY = (int) $posY;
					$posX += $newWidth;
					break;
					
				case FIXED:
					switch ($c.getWidth().getUnit()) {
					case PERCENT:
						$newWidth = (int) (($c.getWidth().getValue() / 100.0) * $width);
						break;
					case PIXEL:
						$newWidth = $c.getWidth().getValue();
						break;
					case STAR:
					case INTRINSIC:
					case AUTO:
						break;
					}
					switch ($c.getHeight().getUnit()) {
					case PERCENT:
						$newHeight = (int) (($c.getHeight().getValue() / 100.0) * $height);
						break;
					case PIXEL:
						$newHeight = $c.getHeight().getValue();
						break;
					case STAR:
					case INTRINSIC:
					case AUTO:
						// TODO: Better debugging
						System.err.println("STAR, COMPUTE & AUTO are not allowed for positions in a box");
						break;
					}
					
					switch ($c.getPosX().getUnit()) {
					case PERCENT:
						$newX = (int) ((($c.getPosX().getValue() / 100.0) * $width) - (($c.getPosX().getValue() / 100.0) * $newWidth));
						break;
					case PIXEL:
						$newX = $c.getPosX().getValue();
						break;
					case STAR:
					case INTRINSIC:
					case AUTO:
						// TODO: Better debugging
						System.err.println("STAR, COMPUTE & AUTO are not allowed for dimensions in a box");
						break;
					}
					switch ($c.getPosY().getUnit()) {
					case PERCENT:
						$newY = (int) ((($c.getPosY().getValue() / 100.0) * $height) - (($c.getPosY().getValue() / 100.0) * $newHeight));
						break;
					case PIXEL:
						$newY = $c.getPosY().getValue();
						break;
					case STAR:
					case INTRINSIC:
					case AUTO:
						// TODO: Better debugging
						System.err.println("STAR, COMPUTE & AUTO are not allowed for dimensions in a box");
						break;
					}
					break;
				}
				$c.getJComponent().setSize($newWidth, $newHeight);
				$c.getJComponent().setLocation($newX, $newY);
			}
		}
	}

	@Override
	public Dimension minimumLayoutSize(final Container $c) {
		return new Dimension(0, 0);
	}

	@Override
	public Dimension preferredLayoutSize(final Container $c) {
		return minimumLayoutSize($c);
	}

	public MagicPanel getParent() {
		// TODO: Is this needed? 
		return $parent;
	}
}
