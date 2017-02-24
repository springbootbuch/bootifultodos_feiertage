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

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.UnaryOperator;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Repräsentiert deutsche Bundesländer. Die Bundeslandnummer ist im amtlichen
 * <a href="https://de.wikipedia.org/wiki/Amtlicher_Gemeindeschlüssel">Gemeindeschlüssel</a>
 * vorgeben. Die Kürzel entsprechen den offiziellen, europaweit verwendeten
 * Kürzeln deutscher Bundesländer.
 *
 * @author Michael J. Simons, 2016-11-30
 */
@SuppressWarnings({"checkstyle:designforextension"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Immutable
@Table(name = "bundeslaender")
public class Bundesland implements Serializable {

	private static final long serialVersionUID = 1L;

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
	 * Bundeslandnummer laut amtlichen Gemeindeschlüssel.
	 */
	@Id
	private short nummer;

	/**
	 * Offizielles Kürzel des Bundeslandes.
	 */
	private String kuerzel;

	/**
	 * Offizieller Name des Bundeslandes.
	 */
	private String name;

	/**
	 * Die Liste der gesetzlichen Feiertage, die in diesem Bundesland gelten.
	 */
	@ElementCollection(targetClass = GesetzlicherFeiertag.class)
	@CollectionTable(name = "bundeslaender_feiertage", joinColumns = @JoinColumn(name = "bundesland_nummer"))
	@Column(name = "gesetzlicher_feiertag", nullable = false)
	@Enumerated(EnumType.STRING)
	private List<GesetzlicherFeiertag> feiertage;

	public Bundesland(final short nummer, final String kuerzel, final String name, final List<GesetzlicherFeiertag> feiertage) {
		this.nummer = nummer;
		this.kuerzel = kuerzel;
		this.name = name;
		this.feiertage = new ArrayList<>(feiertage);
	}

	public List<GesetzlicherFeiertag> getFeiertage() {
		return Collections.unmodifiableList(this.feiertage);
	}
}
