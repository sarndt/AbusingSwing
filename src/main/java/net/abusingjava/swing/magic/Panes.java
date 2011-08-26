package net.abusingjava.swing.magic;

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

	@XmlAttribute
	boolean $border = false;
	
	@XmlChildElements
	Pane[] $panes = new Pane[] {};

	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {

		final JXTaskPaneContainer $c = new JXTaskPaneContainer();

		for (Pane $p : $panes) {
			JXTaskPane $jxp = new JXTaskPane();
			Container $con = $p.getContainer();
			$con.create($main, $parent);
			
			$jxp.add($con.getRealComponent());
			$jxp.setTitle($p.getTitle());
			$jxp.setAnimated($p.getAnimated());
			$jxp.setCollapsed(!$p.getExpanded());
			
			$c.add($jxp);
		}

		$realComponent = $c;
		$component = new JScrollPane($c);

		if (!$border) {
			$component.setBorder(null);
		}
		
		super.create($main, $parent);
	}

}