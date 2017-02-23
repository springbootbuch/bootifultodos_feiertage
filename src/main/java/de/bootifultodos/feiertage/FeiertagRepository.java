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

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * Ein eigenes Repository, dass die Verwendung des MongoTemplates demonstriert.
 *
 * @author Michael J. Simons, 2016-12-06
 */
@Repository
@RequiredArgsConstructor
class FeiertagRepository {
    private final MongoTemplate mongoTemplate;

    public List<String> findAll() {
        return this.mongoTemplate.getCollection("bundeslaender").distinct("feiertage");
    }
}
