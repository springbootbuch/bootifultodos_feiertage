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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.function.Function;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static java.time.Month.DECEMBER;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * Service, der alle Feiertage in Deutschland ermittelt und die Feiertage für
 * ein konkretes Jahr und ein konkretes Bundesland berechnen kann.
 *
 * @author Michael J. Simons, 2016-12-06
 */
@Service
class FeiertagsBerechnung {

	/**
	 * Berechnet das Datum des Ostersonntags nach der
	 * <a href="https://de.wikipedia.org/wiki/Gaußsche_Osterformel#Eine_erg.C3.A4nzte_Osterformel">Eine
	 * ergänzte Osterformel</a>
	 * im Gregorianischen Kalender.
	 */
	static final class Ostersonntag implements Function<Integer, LocalDate> {

		/**
		 * @param jahr Das Jahr, für das Ostersonntag ermittelt wird
		 * @return Ostersonntag im Gregorianischen Kalender
		 */
		@Override
		public LocalDate apply(final Integer jahr) {
			final int k = jahr / 100;
			final int m = 15 + (3 * k + 3) / 4 - (8 * k + 13) / 25;
			final int s = 2 - (3 * k + 3) / 4;
			final int a = jahr % 19;
			final int d = (19 * a + m) % 30;
			final int r = d / 29 + (d / 28 - d / 29) * (a / 11);
			final int og = 21 + d - r;
			final int sz = 7 - (jahr + jahr / 4 + s) % 7;
			final int oe = 7 - (og - sz) % 7;
			final int os = og + oe;

			final LocalDate ostersonntag;
			if (os <= 31) {
				ostersonntag = LocalDate.of(jahr, Month.MARCH, os);
			} else {
				ostersonntag = LocalDate.of(jahr, Month.APRIL, os % 31);
			}

			return ostersonntag;
		}
	}

	/**
	 * Berechnet das Datum des Buss und Bettags.
	 */
	static final class BussUndBettag implements Function<Integer, LocalDate> {

		@Override
		public LocalDate apply(final Integer jahr) {
			return LocalDate.of(jahr, DECEMBER, 25)
				.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)) // 4. Advent
				.minusWeeks(4) // 1. Advent
				.with(TemporalAdjusters.previous(DayOfWeek.WEDNESDAY)); // Mittwoch vor 1. Advent;
		}
	}

	private final Ostersonntag ostersonntag = new Ostersonntag();

	/**
	 * @param bundesland Das Bundesland dessen Feiertage berechnet werden sollen
	 * @param jahr Das Jahr, für das die Feiertage berechnet werden sollen
	 * @return Eine nach Datum aufsteigend sortierte Liste von Feiertagen
	 */
	@Cacheable(
		cacheNames = "feiertagsCache",
		key = "{#bundesland.nummer,#jahr}"
	)
	public List<FeiertagsDatum> feiertageIn(
		final Bundesland bundesland, final int jahr
	) {
		final LocalDate ostersonntagsDatum
			= this.ostersonntag.apply(jahr);
		return bundesland.getFeiertage().stream()
			.map(gf -> gf.compute(ostersonntagsDatum))
			.sorted(comparing(FeiertagsDatum::getDatum))
			.collect(toList());
	}
}
