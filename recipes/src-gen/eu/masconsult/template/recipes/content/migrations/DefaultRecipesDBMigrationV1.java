/*
 * Generated by Robotoworks Mechanoid
 */
package eu.masconsult.template.recipes.content.migrations;

import android.database.sqlite.SQLiteDatabase;
import com.robotoworks.mechanoid.db.SQLiteMigration;

public class DefaultRecipesDBMigrationV1 extends SQLiteMigration {
	@Override
	public void onBeforeUp(SQLiteDatabase db) {}
	
	@Override
	public void up(SQLiteDatabase db) {
		db.execSQL(
			"create table recipes ( " +
			"_id integer primary key autoincrement, " +
			"source text, " +
			"source_name text, " +
			"name text, " +
			"prep_time integer, " +
			"cook_time integer, " +
			"total_time integer, " +
			"serves integer, " +
			"directions text, " +
			"category text, " +
			"image text, " +
			"summary text, " +
			"favorite boolean default 0 " +
			") "
		);	
		db.execSQL(
			"create index idx_category on recipes ( category ) "
		);	
		db.execSQL(
			"create table ingredients ( " +
			"_id integer primary key autoincrement, " +
			"recipe_id integer, " +
			"ingredient text, " +
			"quantity integer, " +
			"units text " +
			") "
		);	
		db.execSQL(
			"create view categories as " +
			"select " +
			"_id as _id, " +
			"category as category " +
			"from recipes " +
			"group by category "
		);	
		db.execSQL(
			"create view search as " +
			"select _id as _id , category  as string from recipes " +
			"union all select _id as _id, name as string from recipes " +
			"union all select _id as _id, directions as string from recipes " +
			"union all select _id as _id, summary as string from recipes " +
			"union all select recipe_id as _id, ingredient as string from ingredients "
		);	
		db.execSQL(
			"create view search_with_recipe as " +
			"select  r._id as _id, " +
			"r.name as name, " +
			"r.image as image, " +
			"r.prep_time as prep_time, " +
			"r.cook_time as cook_time, " +
			"r.category as category, " +
			"s.string as string, " +
			"r.favorite as favorite, " +
			"r.total_time as total_time " +
			"from recipes as r join search  as s on (s._id = r._id) "
		);	
	}
	
	@Override
	public void onAfterUp(SQLiteDatabase db) {}
}