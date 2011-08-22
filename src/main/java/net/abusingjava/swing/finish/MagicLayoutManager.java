package net.abusingjava.swing.finish;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

import net.abusingjava.Author;
import net.abusingjava.Since;
import net.abusingjava.Version;

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
			double $width = $container.getWidth();
			double $height = $container.getHeight();
			
			double $remainingWidth = $width;
			double $remainingHeight = $height;
			
			int $starsWidth = 0;
			int $starsHeight = 0;
			
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
					case COMPUTE:
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
					case COMPUTE:
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
					case COMPUTE:
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
					case COMPUTE:
						// nothing
						break;
					}
					break;
					
				case FIXED:
					
					break;
				}
			}
			
			double $posX = 0, $posY = 0;
			int $newWidth = 0, $newHeight = 0, $newX = 0, $newY = 0;
			
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
					case COMPUTE:
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
					case COMPUTE:
						// TODO
						break;
					}
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
					case COMPUTE:
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
					case COMPUTE:
						// TODO
						break;
					}
					$newX = (int) $posX;
					$posX += $newWidth;
					break;
					
				case FIXED:
					
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

}
