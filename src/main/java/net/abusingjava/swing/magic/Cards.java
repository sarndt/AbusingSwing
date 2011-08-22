package net.abusingjava.swing.magic;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.abusingjava.swing.MagicPanel;
import net.abusingjava.xml.XmlChildElements;
import net.abusingjava.xml.XmlElement;

@XmlElement("cards")
public class Cards extends Component {

	@XmlChildElements({ HBox.class, VBox.class, Box.class })
	Container[] $containers = new Container[] {};

	private boolean $created;
	
	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		if ($created) return;
		$created = true;
		
		JPanel $c = new JPanel(new CardLayout());

		int $i = 0;
		for (Container $con : $containers) {
			MagicPanel $mp = new MagicPanel($main, $con);

			String $name = "card" + $i++;
			$c.add(new JScrollPane($mp), $name);
		}

		$component = $c;

		super.create($main, $parent);
	}
}
