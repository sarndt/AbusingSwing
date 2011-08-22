package net.abusingjava.swing.finish.magic;

import java.awt.Dimension;

import javax.swing.JScrollPane;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import net.abusingjava.swing.finish.MagicPanel;
import net.abusingjava.xml.XmlChildElements;
import net.abusingjava.xml.XmlElement;

@XmlElement("panes")
public class Panes extends Component {
	
	@XmlChildElements
	Pane[] $panes = new Pane[] {};
	
	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		
		JXTaskPaneContainer $c = new JXTaskPaneContainer();
		
		for (Pane $p : $panes) {
			JXTaskPane $jxp = new JXTaskPane();
			Container $con = $p.getContainer();
			$con.create($main, $parent);
			MagicPanel $mp = new MagicPanel($main, $con);
			$jxp.add($mp);
			$jxp.setTitle($p.getTitle());
			$jxp.setAnimated($p.getAnimated());
			$jxp.setCollapsed(!$p.getExpanded());
			$mp.setSize(200, $p.getHeight());
			$mp.setPreferredSize(new Dimension(200, $p.getHeight()));
			System.out.println($mp.getSize());
			$c.add($jxp);
		}
		
		$realComponent = $c;
		$component = new JScrollPane($c);
		
		super.create($main, $parent);
	}
	
}