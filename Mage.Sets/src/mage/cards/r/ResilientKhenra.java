package mage.cards.r;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.dynamicvalue.common.SourcePermanentPowerValue;
import mage.abilities.effects.common.continuous.BoostTargetEffect;
import mage.abilities.keyword.EternalizeAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.target.common.TargetCreaturePermanent;

import java.util.UUID;

/**
 *
 * @author spjspj
 */
public final class ResilientKhenra extends CardImpl {

    public ResilientKhenra(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{G}");

        this.subtype.add(SubType.JACKAL, SubType.WIZARD);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // When Resilient Khenra enters the battlefield, you may have target creature get +X/+X until end of turn, where X is Resilient Khenra's power.
        Ability ability = new EntersBattlefieldTriggeredAbility(new BoostTargetEffect(
                SourcePermanentPowerValue.NOT_NEGATIVE, SourcePermanentPowerValue.NOT_NEGATIVE, Duration.EndOfTurn), true);
        ability.addTarget(new TargetCreaturePermanent());
        this.addAbility(ability);

        // Eternalize {4}{G}{G}
        this.addAbility(new EternalizeAbility(new ManaCostsImpl<>("{4}{G}{G}"), this));

    }

    private ResilientKhenra(final ResilientKhenra card) {
        super(card);
    }

    @Override
    public ResilientKhenra copy() {
        return new ResilientKhenra(this);
    }
}
