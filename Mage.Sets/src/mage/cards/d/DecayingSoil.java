package mage.cards.d;

import mage.abilities.triggers.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.DiesCreatureTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.ThresholdCondition;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.effects.common.ExileFromZoneTargetEffect;
import mage.abilities.effects.common.ReturnFromGraveyardToHandTargetEffect;
import mage.abilities.effects.common.continuous.GainAbilitySourceEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.AbilityWord;
import mage.constants.CardType;
import mage.constants.TargetController;
import mage.constants.Zone;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.permanent.TokenPredicate;

import java.util.UUID;

/**
 * @author cbt33, Nantuko (Nim Deathmantle)
 */
public final class DecayingSoil extends CardImpl {

    private static final FilterPermanent filter = new FilterCreaturePermanent("nontoken creature");

    static {
        filter.add(TargetController.YOU.getOwnerPredicate());
        filter.add(TokenPredicate.FALSE);
    }

    public DecayingSoil(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{1}{B}{B}");

        // At the beginning of your upkeep, exile a card from your graveyard.
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(
                new ExileFromZoneTargetEffect(Zone.GRAVEYARD, false)
                        .setText("exile a card from your graveyard")
        ));

        // Threshold - As long as seven or more cards are in your graveyard, Decaying Soil has "Whenever a nontoken creature is put into your graveyard from the battlefield, you may pay {1}. If you do, return that card to your hand."
        this.addAbility(new SimpleStaticAbility(new ConditionalContinuousEffect(
                new GainAbilitySourceEffect(new DecayingSoilTriggeredAbility()),
                ThresholdCondition.instance, "as long as seven or more cards are in your graveyard, " +
                "{this} has \"Whenever a nontoken creature is put into your graveyard from the battlefield, " +
                "you may pay {1}. If you do, return that card to your hand.\""
        )).setAbilityWord(AbilityWord.THRESHOLD));
    }

    private DecayingSoil(final DecayingSoil card) {
        super(card);
    }

    @Override
    public DecayingSoil copy() {
        return new DecayingSoil(this);
    }
}

class DecayingSoilTriggeredAbility extends DiesCreatureTriggeredAbility {

    private static final FilterPermanent filter = new FilterCreaturePermanent();

    static {
        filter.add(TargetController.YOU.getOwnerPredicate());
        filter.add(TokenPredicate.FALSE);
    }

    DecayingSoilTriggeredAbility() {
        super(new DoIfCostPaid(new ReturnFromGraveyardToHandTargetEffect(), new GenericManaCost(1)), false, filter, true);
    }

    private DecayingSoilTriggeredAbility(DecayingSoilTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public DecayingSoilTriggeredAbility copy() {
        return new DecayingSoilTriggeredAbility(this);
    }

    @Override
    public String getRule() {
        return "Whenever a nontoken creature is put into your graveyard from the battlefield, " +
                "you may pay {1}. If you do, return that card to your hand.";
    }
}
