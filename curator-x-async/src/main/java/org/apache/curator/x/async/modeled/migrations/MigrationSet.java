/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.curator.x.async.modeled.migrations;

import com.google.common.collect.ImmutableList;
import org.apache.curator.x.async.modeled.ZPath;
import java.util.List;
import java.util.Objects;

public interface MigrationSet
{
    String id();

    ZPath path();

    ZPath metaDataPath();

    List<Migration> migrations();

    static MigrationSet build(String id, ZPath path, ZPath metaDataPath, List<Migration> migrations)
    {
        Objects.requireNonNull(id, "id cannot be null");
        Objects.requireNonNull(path, "path cannot be null");
        Objects.requireNonNull(metaDataPath, "metaDataPath cannot be null");
        final List<Migration> migrationsCopy = ImmutableList.copyOf(migrations);
        return new MigrationSet()
        {
            @Override
            public String id()
            {
                return id;
            }

            @Override
            public ZPath path()
            {
                return path;
            }

            @Override
            public ZPath metaDataPath()
            {
                return metaDataPath;
            }

            @Override
            public List<Migration> migrations()
            {
                return migrationsCopy;
            }
        };
    }
}