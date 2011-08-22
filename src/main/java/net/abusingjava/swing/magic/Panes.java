package net.abusingjava.swing.magic;

import java.awt.Dimension;

import javax.swing.JScrollPane;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import net.abusingjava.swing.MagicPanel;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlChildElements;
import net.abusingjava.xml.XmlElement;

@XmlElement("panes")
public class Panes extends Component {

	@XmlElement("pane")
	public static class Pane extends Panel {

		@XmlAttribute
		String $title = "";

		@XmlAttribute
		boolean $expanded = true;

		@XmlAttribute
		boolean $animated = false;

		@XmlAttribute
		int $height;

		public String getTitle() {
			return $title;
		}

		public boolean getExpanded() {
			return $expanded;
		}

		public boolean getAnimated() {
			return $animated;
		}

		public int getHeight() {
			return $height;
		}
	}

	@XmlChildElements
	Pane[] $panes = new Pane[] {};

	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {

		JXTaskPaneContainer $c = new JXTaskPaneContainer();

		for (Pane $p : $panes) {
			JXTaskPane $jxp = new JXTaskPane();
			Container $con = $p.getContainer();

			MagicPanel $mp = new MagicPanel($main, $con);
			
			$jxp.add($mp);
			$jxp.setTitle($p.getTitle());
			$jxp.setAnimated($p.getAnimated());
			$jxp.setCollapsed(!$p.getExpanded());
			$mp.setSize(200, $p.getHeight());
			$mp.setPreferredSize(new Dimension(200, $p.getHeight()));
			$c.add($jxp);
		}

		$realComponent = $c;
		$component = new JScrollPane($c);

		super.create($main, $parent);
	}

}