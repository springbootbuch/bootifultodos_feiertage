/*
 * Copyright 2016-2017 michael-simons.eu.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.bootifultodos.feiertage;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.List;
import java.util.function.UnaryOperator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Repräsentiert deutsche Bundesländer. Die Bundeslandnummer ist im amtlichen
 * <a href="https://de.wikipedia.org/wiki/Amtlicher_Gemeindeschlüssel">Gemeindeschlüssel</a>
 * vorgeben. Die Kürzel entsprechen den offiziellen, europaweit verwendeten
 * Kürzeln deutscher Bundesländer.
 *
 * @author Michael J. Simons, 2016-11-30
 */
@AllArgsConstructor
@Getter
@Document(collection = "bundeslaender")
public final class Bundesland {

	/**
	 * Eine Aufzählung aller gesetzlichen Feiertage in Deutschland.
	 */
	public enum GesetzlicherFeiertag {
		Neujahr(Month.JANUARY, 1),
		HeiligeDreiKoenige(Month.JANUARY, 6),
		Karfreitag(-2),
		Ostermontag(1),
		Maifeiertag(Month.MAY, 1),
		ChristiHimmelfahrt(39),
		Pfingstmontag(50),
		Fronleichnam(60),
		MariaeHimmelfahrt(Month.AUGUST, 15),
		TagDerDeutschenEinheit(Month.OCTOBER, 3),
		Reformationstag(Month.OCTOBER, 31),
		Allerheiligen(Month.NOVEMBER, 1),
		BussUndBettag(e -> new FeiertagsBerechnung.BussUndBettag().apply(e.getYear())),
		Weihnachtstag1(Month.DECEMBER, 25),
		Weihnachtstag2(Month.DECEMBER, 26);

		/**
		 * Funktion, die ausgehend von Ostersonntag das Datum des Feiertags
		 * berechnet.
		 */
		final UnaryOperator<LocalDate> op;

		GesetzlicherFeiertag(final Integer offset) {
			this(e -> e.plusDays(offset));
		}

		GesetzlicherFeiertag(final Month month, final int day) {
			this(e -> MonthDay.of(month, day).atYear(e.getYear()));
		}

		GesetzlicherFeiertag(final UnaryOperator<LocalDate> compute) {
			this.op = compute;
		}

		FeiertagsDatum compute(final LocalDate ostersonntagsDatum) {
			return new FeiertagsDatum(this, this.op.apply(ostersonntagsDatum));
		}
	}

	/**
	 * Die MongoDB interne ID. Soll nicht nach außen gegeben werden.
	 */
	@Getter(AccessLevel.NONE)
	private final String id;

	/**
	 * Bundeslandnummer laut amtlichen Gemeindeschlüssel.
	 */
	private final short nummer;

	/**
	 * Offizielles Kürzel des Bundeslandes.
	 */
	private final String kuerzel;

	/**
	 * Offizieller Name des Bundeslandes.
	 */
	private final String name;

	/**
	 * Die Liste der gesetzlichen Feiertage, die in diesem Bundesland gelten.
	 */
	private final List<GesetzlicherFeiertag> feiertage;
}
