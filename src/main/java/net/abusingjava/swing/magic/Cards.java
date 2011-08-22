package net.abusingjava.swing.magic;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.abusingjava.swing.MagicPanel;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlChildElements;
import net.abusingjava.xml.XmlElement;

@XmlElement("cards")
public class Cards extends Component {


	@XmlElement("card")
	public static class Card extends Panel {

		@XmlAttribute
		String $name = "";

	}
	
	@XmlChildElements
	Card[] $cards = new Card[] {};
	
	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		JPanel $c = new JPanel(new CardLayout());

		int $i = 0;
		for (Card $card : $cards) {
			Container $con = $card.getContainer();
			$con.create($main, $parent);

			String $name = "card" + $i++;
			$c.add(new JScrollPane($con.getJComponent()), $name);
		}

		$component = $c;

		super.create($main, $parent);
	}
}
