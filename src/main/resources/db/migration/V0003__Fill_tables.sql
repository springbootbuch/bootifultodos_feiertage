/* 
 * Copyright 2017 michael-simons.eu.
 *
 * Licensed under the Apache License, Version 2.0 (the 'License');
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an 'AS IS' BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

INSERT INTO bundeslaender(nummer, name, kuerzel) VALUES
    (1, 'Schleswig-Holstein', 'SH');
INSERT INTO bundeslaender_feiertage(bundesland_nummer, gesetzlicher_feiertag) VALUES
    (1, 'Neujahr'),
    (1, 'Karfreitag'),
    (1, 'Ostermontag'),
    (1, 'Maifeiertag'),
    (1, 'ChristiHimmelfahrt'),
    (1, 'Pfingstmontag'),
    (1, 'TagDerDeutschenEinheit'),
    (1, 'Weihnachtstag1'),
    (1, 'Weihnachtstag2');

INSERT INTO bundeslaender(nummer, name, kuerzel) VALUES
    (2, 'Freie und Hansestadt Hamburg', 'HH');
INSERT INTO bundeslaender_feiertage(bundesland_nummer, gesetzlicher_feiertag) VALUES
    (2, 'Neujahr'),
    (2, 'Karfreitag'),
    (2, 'Ostermontag'), 
    (2, 'Maifeiertag'), 
    (2, 'ChristiHimmelfahrt'), 
    (2, 'Pfingstmontag'), 
    (2, 'TagDerDeutschenEinheit'), 
    (2, 'Weihnachtstag1'), 
    (2, 'Weihnachtstag2');

INSERT INTO bundeslaender(nummer, name, kuerzel) VALUES
    (3, 'Niedersachsen', 'NI');
INSERT INTO bundeslaender_feiertage(bundesland_nummer, gesetzlicher_feiertag) VALUES
    (3, 'Neujahr'), 
    (3, 'Karfreitag'), 
    (3, 'Ostermontag'),
    (3, 'Maifeiertag'), 
    (3, 'ChristiHimmelfahrt'), 
    (3, 'Pfingstmontag'), 
    (3, 'TagDerDeutschenEinheit'),
    (3, 'Weihnachtstag1'),
    (3, 'Weihnachtstag2');

INSERT INTO bundeslaender(nummer, name, kuerzel) VALUES
    (4, 'Freie Hansestadt Bremen', 'HB');
INSERT INTO bundeslaender_feiertage(bundesland_nummer, gesetzlicher_feiertag) VALUES
    (4, 'Neujahr'),
    (4, 'Karfreitag'), 
    (4, 'Ostermontag'), 
    (4, 'Maifeiertag'), 
    (4, 'ChristiHimmelfahrt'), 
    (4, 'Pfingstmontag'), 
    (4, 'TagDerDeutschenEinheit'), 
    (4, 'Weihnachtstag1'), 
    (4, 'Weihnachtstag2');

INSERT INTO bundeslaender(nummer, name, kuerzel) VALUES
    (5, 'Nordrhein-Westfalen', 'NW');
INSERT INTO bundeslaender_feiertage(bundesland_nummer, gesetzlicher_feiertag) VALUES
    (5, 'Neujahr'), 
    (5, 'Karfreitag'),
    (5, 'Ostermontag'), 
    (5, 'Maifeiertag'), 
    (5, 'ChristiHimmelfahrt'), 
    (5, 'Pfingstmontag'), 
    (5, 'Fronleichnam'), 
    (5, 'TagDerDeutschenEinheit'), 
    (5, 'Allerheiligen'), 
    (5, 'Weihnachtstag1'), 
    (5, 'Weihnachtstag2');

INSERT INTO bundeslaender(nummer, name, kuerzel) VALUES
    (6, 'Hessen', 'HE');
INSERT INTO bundeslaender_feiertage(bundesland_nummer, gesetzlicher_feiertag) VALUES
    (6, 'Neujahr'),
    (6, 'Karfreitag'), 
    (6, 'Ostermontag'), 
    (6, 'Maifeiertag'), 
    (6, 'ChristiHimmelfahrt'), 
    (6, 'Pfingstmontag'), 
    (6, 'Fronleichnam'), 
    (6, 'TagDerDeutschenEinheit'),
    (6, 'Weihnachtstag1'), 
    (6, 'Weihnachtstag2');

INSERT INTO bundeslaender(nummer, name, kuerzel) VALUES
    (7, 'Rheinland-Pfalz', 'RP');
INSERT INTO bundeslaender_feiertage(bundesland_nummer, gesetzlicher_feiertag) VALUES
    (7, 'Neujahr'), 
    (7, 'Karfreitag'), 
    (7, 'Ostermontag'), 
    (7, 'Maifeiertag'), 
    (7, 'ChristiHimmelfahrt'), 
    (7, 'Pfingstmontag'), 
    (7, 'Fronleichnam'), 
    (7, 'TagDerDeutschenEinheit'), 
    (7, 'Allerheiligen'), 
    (7, 'Weihnachtstag1'), 
    (7, 'Weihnachtstag2');

INSERT INTO bundeslaender(nummer, name, kuerzel) VALUES
    (8, 'Baden-Württemberg', 'BW');
INSERT INTO bundeslaender_feiertage(bundesland_nummer, gesetzlicher_feiertag) VALUES
    (8, 'Neujahr'), 
    (8, 'HeiligeDreiKoenige'),
    (8, 'Karfreitag'), 
    (8, 'Ostermontag'), 
    (8, 'Maifeiertag'), 
    (8, 'ChristiHimmelfahrt'), 
    (8, 'Pfingstmontag'), 
    (8, 'Fronleichnam'), 
    (8, 'TagDerDeutschenEinheit'), 
    (8, 'Allerheiligen'), 
    (8, 'Weihnachtstag1'), 
    (8, 'Weihnachtstag2');

INSERT INTO bundeslaender(nummer, name, kuerzel) VALUES
    (9, 'Freistaat Bayern', 'BY');
INSERT INTO bundeslaender_feiertage(bundesland_nummer, gesetzlicher_feiertag) VALUES
    (9, 'Neujahr'), 
    (9, 'HeiligeDreiKoenige'), 
    (9, 'Karfreitag'), 
    (9, 'Ostermontag'), 
    (9, 'Maifeiertag'), 
    (9, 'ChristiHimmelfahrt'), 
    (9, 'Pfingstmontag'), 
    (9, 'Fronleichnam'), 
    (9, 'TagDerDeutschenEinheit'), 
    (9, 'Allerheiligen'), 
    (9, 'Weihnachtstag1'), 
    (9, 'Weihnachtstag2');

INSERT INTO bundeslaender(nummer, name, kuerzel) VALUES
    (10, 'Saarland', 'SL');
INSERT INTO bundeslaender_feiertage(bundesland_nummer, gesetzlicher_feiertag) VALUES
    (10, 'Neujahr'), 
    (10, 'Karfreitag'), 
    (10, 'Ostermontag'), 
    (10, 'Maifeiertag'), 
    (10, 'ChristiHimmelfahrt'), 
    (10, 'Pfingstmontag'), 
    (10, 'Fronleichnam'), 
    (10, 'MariaeHimmelfahrt'), 
    (10, 'TagDerDeutschenEinheit'), 
    (10, 'Allerheiligen'),
    (10, 'Weihnachtstag1'), 
    (10, 'Weihnachtstag2');

INSERT INTO bundeslaender(nummer, name, kuerzel) VALUES
    (11, 'Berlin', 'BE');
INSERT INTO bundeslaender_feiertage(bundesland_nummer, gesetzlicher_feiertag) VALUES
    (11, 'Neujahr'), 
    (11, 'Karfreitag'), 
    (11, 'Ostermontag'), 
    (11, 'Maifeiertag'), 
    (11, 'ChristiHimmelfahrt'),
    (11, 'Pfingstmontag'),
    (11, 'TagDerDeutschenEinheit'),
    (11, 'Weihnachtstag1'),
    (11, 'Weihnachtstag2');

INSERT INTO bundeslaender(nummer, name, kuerzel) VALUES
    (12, 'Brandenburg', 'BB');
INSERT INTO bundeslaender_feiertage(bundesland_nummer, gesetzlicher_feiertag) VALUES
    (12, 'Neujahr'),
    (12, 'Karfreitag'),
    (12, 'Ostermontag'),
    (12, 'Maifeiertag'),
    (12, 'ChristiHimmelfahrt'),
    (12, 'Pfingstmontag'),
    (12, 'TagDerDeutschenEinheit'),
    (12, 'Reformationstag'),
    (12, 'Weihnachtstag1'),
    (12, 'Weihnachtstag2');

INSERT INTO bundeslaender(nummer, name, kuerzel) VALUES
    (13, 'Mecklenburg-Vorpommern', 'MV');
INSERT INTO bundeslaender_feiertage(bundesland_nummer, gesetzlicher_feiertag) VALUES
    (13, 'Neujahr'),
    (13, 'Karfreitag'),
    (13, 'Ostermontag'),
    (13, 'Maifeiertag'),
    (13, 'ChristiHimmelfahrt'),
    (13, 'Pfingstmontag'),
    (13, 'TagDerDeutschenEinheit'),
    (13, 'Reformationstag'),
    (13, 'Weihnachtstag1'),
    (13, 'Weihnachtstag2');

INSERT INTO bundeslaender(nummer, name, kuerzel) VALUES
    (14, 'Freistaat Sachsen', 'SN');
INSERT INTO bundeslaender_feiertage(bundesland_nummer, gesetzlicher_feiertag) VALUES
    (14, 'Neujahr'),
    (14, 'Karfreitag'),
    (14, 'Ostermontag'),
    (14, 'Maifeiertag'),
    (14, 'ChristiHimmelfahrt'),
    (14, 'Pfingstmontag'),
    (14, 'TagDerDeutschenEinheit'),
    (14, 'Reformationstag'),
    (14, 'BussUndBettag'),
    (14, 'Weihnachtstag1'),
    (14, 'Weihnachtstag2');

INSERT INTO bundeslaender(nummer, name, kuerzel) VALUES
    (15, 'Sachsen-Anhalt', 'ST');
INSERT INTO bundeslaender_feiertage(bundesland_nummer, gesetzlicher_feiertag) VALUES
    (15, 'Neujahr'),
    (15, 'HeiligeDreiKoenige'),
    (15, 'Karfreitag'),
    (15, 'Ostermontag'),
    (15, 'Maifeiertag'),
    (15, 'ChristiHimmelfahrt'),
    (15, 'Pfingstmontag'),
    (15, 'TagDerDeutschenEinheit'),
    (15, 'Reformationstag'),
    (15, 'Weihnachtstag1'),
    (15, 'Weihnachtstag2');

INSERT INTO bundeslaender(nummer, name, kuerzel) VALUES
    (16, 'Freistaat Thüringen', 'TH');
INSERT INTO bundeslaender_feiertage(bundesland_nummer, gesetzlicher_feiertag) VALUES
    (16, 'Neujahr'),
    (16, 'Karfreitag'),
    (16, 'Ostermontag'),
    (16, 'Maifeiertag'),
    (16, 'ChristiHimmelfahrt'),
    (16, 'Pfingstmontag'),
    (16, 'TagDerDeutschenEinheit'),
    (16, 'Reformationstag'),
    (16, 'Weihnachtstag1'),
    (16, 'Weihnachtstag2');
