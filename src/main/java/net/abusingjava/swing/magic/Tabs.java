package net.abusingjava.swing.magic;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import net.abusingjava.swing.MagicPanel;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlChildElements;
import net.abusingjava.xml.XmlElement;

@XmlElement("tabs")
public class Tabs extends Component {

	@XmlElement("tab")
	public static class Tab extends Panel {

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
	Tab[] $tabs = new Tab[] {};

	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {

		JTabbedPane $c = new JTabbedPane();

		int $i = 0;
		for (Tab $t : $tabs) {
			Container $con = $t.getContainer();
			$con.create($main, $parent);
			$c.add(new JScrollPane($con.getJComponent()));
			$c.setTitleAt($i++, $t.getTitle());
		}

		$component = $c;

		super.create($main, $parent);
	}

}