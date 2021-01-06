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

import static de.bootifultodos.feiertage.Bundesland.GesetzlicherFeiertag.*;
import static java.util.Arrays.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import de.bootifultodos.feiertage.Bundesland.GesetzlicherFeiertag;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * @author Michael J. Simons, 2016-12-07
 */
public class FeiertagsBerechnungTest {

	List<FeiertagsDatum> getExpectedFeiertage() {
		return Arrays.asList(
			new FeiertagsDatum(Neujahr, LocalDate.of(2021, 1, 1)),
			new FeiertagsDatum(HeiligeDreiKoenige, LocalDate.of(2021, 1, 6)),
			new FeiertagsDatum(Karfreitag, LocalDate.of(2021, 4, 2)),
			new FeiertagsDatum(Ostermontag, LocalDate.of(2021, 4, 5)),
			new FeiertagsDatum(Maifeiertag, LocalDate.of(2021, 5, 1)),
			new FeiertagsDatum(ChristiHimmelfahrt, LocalDate.of(2021, 5, 13)),
			new FeiertagsDatum(Pfingstmontag, LocalDate.of(2021, 5, 24)),
			new FeiertagsDatum(Fronleichnam, LocalDate.of(2021, 6, 3)),
			new FeiertagsDatum(MariaeHimmelfahrt, LocalDate.of(2021, 8, 15)),
			new FeiertagsDatum(TagDerDeutschenEinheit, LocalDate.of(2021, 10, 3)),
			new FeiertagsDatum(Reformationstag, LocalDate.of(2021, 10, 31)),
			new FeiertagsDatum(Allerheiligen, LocalDate.of(2021, 11, 1)),
			new FeiertagsDatum(BussUndBettag, LocalDate.of(2021, 11, 17)),
			new FeiertagsDatum(Weihnachtstag1, LocalDate.of(2021, 12, 25)),
			new FeiertagsDatum(Weihnachtstag2, LocalDate.of(2021, 12, 26)));
	}

	@Test
	public void feiertageInShouldWork() {		
		final Bundesland bundesland = mock(Bundesland.class);
		when(bundesland.getFeiertage())
			.thenReturn(asList(GesetzlicherFeiertag.values()));	

		final List<FeiertagsDatum> expectedFeiertage
			= getExpectedFeiertage();

		final FeiertagsBerechnung service
			= new FeiertagsBerechnung();
		final List<FeiertagsDatum> feiertage
			= service.feiertageIn(bundesland, 2021);

		assertThat(feiertage)
			.isEqualTo(expectedFeiertage);
	}
}
