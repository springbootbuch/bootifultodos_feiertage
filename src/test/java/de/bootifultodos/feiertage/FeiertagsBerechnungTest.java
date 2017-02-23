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

import de.bootifultodos.feiertage.FeiertagsBerechnung;
import de.bootifultodos.feiertage.Bundesland;
import de.bootifultodos.feiertage.FeiertagsDatum;
import de.bootifultodos.feiertage.FeiertagsBerechnung.GesetzlicherFeiertag;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Michael J. Simons, 2016-12-07
 */
public class FeiertagsBerechnungTest {

    @Test
    public void berechneFeiertageShouldWork() {
        final Bundesland bundeslandDocument = new Bundesland(
                "n/a",
                "n/a",
                "fiktiv",
                "fiktiv",
                Arrays.asList(GesetzlicherFeiertag.values()).stream().map(GesetzlicherFeiertag::name).collect(toList())
        );

        final List<FeiertagsDatum> expectedFeiertage = Arrays.asList(
                new FeiertagsDatum(GesetzlicherFeiertag.Neujahr.name(), LocalDate.of(2017, 1, 1)),
                new FeiertagsDatum(GesetzlicherFeiertag.HeiligeDreiKoenige.name(), LocalDate.of(2017, 1, 6)),
                new FeiertagsDatum(GesetzlicherFeiertag.Karfreitag.name(), LocalDate.of(2017, 4, 14)),
                new FeiertagsDatum(GesetzlicherFeiertag.Ostermontag.name(), LocalDate.of(2017, 4, 17)),
                new FeiertagsDatum(GesetzlicherFeiertag.Maifeiertag.name(), LocalDate.of(2017, 5, 1)),
                new FeiertagsDatum(GesetzlicherFeiertag.ChristiHimmelfahrt.name(), LocalDate.of(2017, 5, 25)),
                new FeiertagsDatum(GesetzlicherFeiertag.Pfingstmontag.name(), LocalDate.of(2017, 6, 5)),
                new FeiertagsDatum(GesetzlicherFeiertag.Fronleichnam.name(), LocalDate.of(2017, 6, 15)),
                new FeiertagsDatum(GesetzlicherFeiertag.MariaeHimmelfahrt.name(), LocalDate.of(2017, 8, 15)),
                new FeiertagsDatum(GesetzlicherFeiertag.TagDerDeutschenEinheit.name(), LocalDate.of(2017, 10, 3)),
                new FeiertagsDatum(GesetzlicherFeiertag.Reformationstag.name(), LocalDate.of(2017, 10, 31)),
                new FeiertagsDatum(GesetzlicherFeiertag.Allerheiligen.name(), LocalDate.of(2017, 11, 1)),
                new FeiertagsDatum(GesetzlicherFeiertag.BussUndBettag.name(), LocalDate.of(2017, 11, 22)),
                new FeiertagsDatum(GesetzlicherFeiertag.Weihnachtstag1.name(), LocalDate.of(2017, 12, 25)),
                new FeiertagsDatum(GesetzlicherFeiertag.Weihnachtstag2.name(), LocalDate.of(2017, 12, 26))
        );

        final List<FeiertagsDatum> feiertage = new FeiertagsBerechnung().berechneFeiertage(bundeslandDocument, 2017);
        assertThat(feiertage, is(equalTo(expectedFeiertage)));
    }

}
