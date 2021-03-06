/*
 * SonarQube
 * Copyright (C) 2009-2018 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.server.platform.db.migration.version.v64;

import java.sql.SQLException;
import org.sonar.db.Database;
import org.sonar.server.platform.db.migration.def.ClobColumnDef;
import org.sonar.server.platform.db.migration.def.VarcharColumnDef;
import org.sonar.server.platform.db.migration.sql.CreateTableBuilder;
import org.sonar.server.platform.db.migration.step.DdlChange;

import static org.sonar.server.platform.db.migration.def.BigIntegerColumnDef.newBigIntegerColumnDefBuilder;
import static org.sonar.server.platform.db.migration.def.IntegerColumnDef.newIntegerColumnDefBuilder;
import static org.sonar.server.platform.db.migration.def.VarcharColumnDef.newVarcharColumnDefBuilder;

public class CreateRulesMetadata extends DdlChange {

  private static final String TABLE_NAME = "rules_metadata";

  public CreateRulesMetadata(Database db) {
    super(db);
  }

  @Override
  public void execute(Context context) throws SQLException {
    context.execute(new CreateTableBuilder(getDialect(), TABLE_NAME)
      .addPkColumn(newIntegerColumnDefBuilder()
        .setColumnName("rule_id")
        .setIsNullable(false)
        .build())
      .addPkColumn(newVarcharColumnDefBuilder()
        .setColumnName("organization_uuid")
        .setLimit(VarcharColumnDef.UUID_SIZE)
        .setIsNullable(false)
        .build())
      .addColumn(ClobColumnDef.newClobColumnDefBuilder()
        .setColumnName("note_data")
        .setIsNullable(true)
        .build())
      .addColumn(newVarcharColumnDefBuilder()
        .setColumnName("note_user_login")
        .setLimit(255)
        .setIsNullable(true)
        .build())
      .addColumn(newBigIntegerColumnDefBuilder()
        .setColumnName("note_created_at")
        .setIsNullable(true)
        .build())
      .addColumn(newBigIntegerColumnDefBuilder()
        .setColumnName("note_updated_at")
        .setIsNullable(true)
        .build())
      .addColumn(newVarcharColumnDefBuilder()
        .setColumnName("remediation_function")
        .setLimit(20)
        .setIsNullable(true)
        .build())
      .addColumn(newVarcharColumnDefBuilder()
        .setColumnName("remediation_gap_mult")
        .setLimit(20)
        .setIsNullable(true)
        .build())
      .addColumn(newVarcharColumnDefBuilder()
        .setColumnName("remediation_base_effort")
        .setLimit(20)
        .setIsNullable(true)
        .build())
      .addColumn(newVarcharColumnDefBuilder()
        .setColumnName("tags")
        .setLimit(4000)
        .setIsNullable(true)
        .build())
      .addColumn(newBigIntegerColumnDefBuilder()
        .setColumnName("created_at")
        .setIsNullable(false)
        .build())
      .addColumn(newBigIntegerColumnDefBuilder()
        .setColumnName("updated_at")
        .setIsNullable(false)
        .build())
      .withPkConstraintName("pk_" + TABLE_NAME)
      .build());
  }
}
