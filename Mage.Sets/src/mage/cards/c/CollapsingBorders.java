
package mage.cards.c;

import java.util.UUID;
import mage.abilities.Ability;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.dynamicvalue.common.DomainValue;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.GainLifeTargetEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.AbilityWord;
import mage.constants.CardType;
import mage.constants.TargetController;

/**
 *
 * @author LoneFox
 *
 */
public final class CollapsingBorders extends CardImpl {

    public CollapsingBorders(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{3}{R}");

        // Domain - At the beginning of each player's upkeep, that player gains 1 life for each basic land type among lands they control. Then Collapsing Borders deals 3 damage to that player.
        Effect effect = new GainLifeTargetEffect(new DomainValue(true));
        effect.setText("that player gains 1 life for each basic land type among lands they control"); //removed punctuation
        Ability ability = new BeginningOfUpkeepTriggeredAbility(effect, TargetController.ANY, false);
        effect = new DamageTargetEffect(3);
        effect.setText("Then {this} deals 3 damage to that player"); //removed punctuation
        ability.addEffect(effect);
        ability.setAbilityWord(AbilityWord.DOMAIN);
        this.addAbility(ability);
    }

    public CollapsingBorders(final CollapsingBorders card) {
        super(card);
    }

    @Override
    public CollapsingBorders copy() {
        return new CollapsingBorders(this);
    }
}
